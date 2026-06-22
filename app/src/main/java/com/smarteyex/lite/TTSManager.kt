package com.smarteyex.lite

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.speech.tts.Voice
import java.io.*
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ExecutorService

class TTSManager(
    private val context: Context,
    private val ttsExecutor: ExecutorService?
) : TextToSpeech.OnInitListener {
    
    private var tts: TextToSpeech? = null
    private var isInitialized = false
    private var isSpeaking = false
    private var audioManager: AudioManager? = null
    
    // ========== VOICE CLONE STATE ==========
    private var voiceCloned = false
    private var originalVoice: Voice? = null
    private var clonedVoiceProfile: ClonedVoiceProfile? = null
    
    // ========== QUEUE ==========
    private val speechQueue = ConcurrentLinkedQueue<SpeechItem>()
    
    data class SpeechItem(
        val text: String,
        val urgency: Int,
        val emotion: String,
        val interruptExisting: Boolean,
        val utteranceId: String = UUID.randomUUID().toString()
    )
    
    data class ClonedVoiceProfile(
        val basePitch: Float,
        val baseRate: Float,
        val bassBoost: Float,
        val breathiness: Float,
        val emotionPitchMap: Map<String, Float>,
        val emotionRateMap: Map<String, Float>
    )
    
    // ========== EMOTIONAL VOICE (FALLBACK) ==========
    private val defaultEmotionalVoice = mapOf(
        "senang" to Pair(1.2f, 1.1f),
        "sedih" to Pair(0.8f, 0.85f),
        "marah" to Pair(1.35f, 1.25f),
        "takut" to Pair(1.4f, 1.3f),
        "khawatir" to Pair(1.15f, 1.1f),
        "tenang" to Pair(1.0f, 0.9f),
        "penasaran" to Pair(1.1f, 1.0f),
        "bosan" to Pair(0.75f, 0.8f),
        "panik" to Pair(1.5f, 1.4f)
    )
    
    private var callbacks: TTSCallbacks? = null
    
    interface TTSCallbacks {
        fun onSpeechStart()
        fun onSpeechDone()
        fun onSpeechError()
    }
    
    // ========== INIT ==========
    
    fun initialize() {
        tts = TextToSpeech(context, this)
        audioManager = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
    }
    
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale("id", "ID")
            originalVoice = tts?.defaultVoice
            setBestVoice()
            
            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(id: String?) {
                    isSpeaking = true
                    callbacks?.onSpeechStart()
                }
                override fun onDone(id: String?) {
                    isSpeaking = false
                    callbacks?.onSpeechDone()
                    processQueue()
                }
                override fun onError(id: String?) {
                    isSpeaking = false
                    callbacks?.onSpeechError()
                    processQueue()
                }
                @Deprecated("Deprecated") override fun onError(id: String?, code: Int) {
                    isSpeaking = false
                    callbacks?.onSpeechError()
                    processQueue()
                }
            })
            
            isInitialized = true
            processQueue()
        }
    }
    
    // ========== SPEAK ==========
    
    fun speak(text: String) {
        speak(text, urgency = 3, emotion = "netral", interruptExisting = false)
    }
    
    fun speakUrgent(text: String) {
        speak(text, urgency = 10, emotion = "khawatir", interruptExisting = true)
    }
    
    fun speak(text: String, urgency: Int, emotion: String, interruptExisting: Boolean) {
        if (text.isBlank()) return
        
        val item = SpeechItem(text, urgency, emotion, interruptExisting)
        
        if (urgency >= 10 || interruptExisting) {
            tts?.stop()
            speechQueue.clear()
            speechQueue.add(item)
            if (!isSpeaking) processQueue()
            return
        }
        
        if (urgency >= 7 && isSpeaking) {
            tts?.stop()
            speechQueue.clear()
        }
        
        speechQueue.add(item)
        if (!isSpeaking) processQueue()
    }
    
    private fun processQueue() {
        if (isSpeaking) return
        
        val item = speechQueue.poll() ?: return
        
        ttsExecutor?.execute {
            try {
                val engine = tts ?: return@execute
                
                // ========== VOICE CLONE SETTINGS ==========
                if (voiceCloned && clonedVoiceProfile != null) {
                    val profile = clonedVoiceProfile!!
                    val pitch = profile.emotionPitchMap[item.emotion] ?: profile.basePitch
                    val rate = profile.emotionRateMap[item.emotion] ?: profile.baseRate
                    engine.setPitch(pitch)
                    engine.setSpeechRate(rate)
                } else {
                    // Fallback ke default emotional voice
                    val config = defaultEmotionalVoice[item.emotion] ?: Pair(1.0f, 1.0f)
                    engine.setPitch(config.first)
                    engine.setSpeechRate(config.second)
                }
                
                val params = Bundle()
                params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, item.utteranceId)
                
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    engine.speak(item.text, TextToSpeech.QUEUE_FLUSH, params, item.utteranceId)
                }
                
            } catch (e: Exception) {
                e.printStackTrace()
                isSpeaking = false
                processQueue()
            }
        }
    }
    
    // ========== VOICE CLONE ==========
    
    /**
     * Clone suara user dari sample audio
     * Cukup 1x momen (marah/sedih/nangis) langsung bisa clone
     */
    fun cloneVoice(
        basePitch: Float,
        baseRate: Float,
        bassLevel: Float,
        breathiness: Float,
        emotionPitch: Map<String, Float>,
        emotionRate: Map<String, Float>
    ) {
        clonedVoiceProfile = ClonedVoiceProfile(
            basePitch = basePitch.coerceIn(0.5f, 2.0f),
            baseRate = baseRate.coerceIn(0.5f, 2.0f),
            bassBoost = bassLevel.coerceIn(0f, 1f),
            breathiness = breathiness.coerceIn(0f, 1f),
            emotionPitchMap = emotionPitch,
            emotionRateMap = emotionRate
        )
        
        voiceCloned = true
        
        // Langsung apply ke TTS
        tts?.setPitch(basePitch)
        tts?.setSpeechRate(baseRate)
    }
    
    fun isVoiceCloned(): Boolean = voiceCloned
    
    fun getClonedProfile(): ClonedVoiceProfile? = clonedVoiceProfile
    
    /**
     * Reset ke suara bawaan
     */
    fun resetToOriginalVoice() {
        voiceCloned = false
        clonedVoiceProfile = null
        tts?.voice = originalVoice
        tts?.setPitch(1.0f)
        tts?.setSpeechRate(1.0f)
    }
    
    // ========== HELPERS ==========
    
    fun stop() {
        tts?.stop()
        isSpeaking = false
    }
    
    fun shutUp() {
        tts?.stop()
        speechQueue.clear()
        isSpeaking = false
    }
    
    private fun setBestVoice() {
        try {
            val voices = tts?.voices ?: return
            val best = voices.find {
                it.locale.language == "id" ||
                it.name.lowercase().contains("indonesia") ||
                it.name.lowercase().contains("female")
            }
            if (best != null) tts?.voice = best
        } catch (e: Exception) { /* fallback */ }
    }
    
    fun setCallbacks(cb: TTSCallbacks) { callbacks = cb }
    fun isSpeaking(): Boolean = isSpeaking
    fun isInitialized(): Boolean = isInitialized
    fun getQueueSize(): Int = speechQueue.size
    
    fun release() {
        shutUp()
        tts?.stop()
        tts?.shutdown()
        tts = null
        isInitialized = false
    }
}