package com.smarteyex.lite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import java.util.concurrent.ExecutorService

class STTManager(
    private val context: Context,
    private val ttsManager: TTSManager,
    private val ttsExecutor: ExecutorService?
) {
    
    private var speechRecognizer: SpeechRecognizer? = null
    private var isListening = false
    
    private var onResult: ((String) -> Unit)? = null
    private var onPartial: ((String) -> Unit)? = null
    private var onSilence: (() -> Unit)? = null
    
    // ========== VOICE ANALYZER (BUAT CLONE) ==========
    private var voiceAnalyzed = false
    private var onVoiceProfileReady: ((VoiceCloneData) -> Unit)? = null
    
    data class VoiceCloneData(
        val basePitch: Float,
        val baseRate: Float,
        val bassLevel: Float,
        val breathiness: Float,
        val toneType: String,
        val emotionPitch: Map<String, Float>,
        val emotionRate: Map<String, Float>
    )
    
    // ========== INIT ==========
    
    fun initialize() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer?.setRecognitionListener(object : RecognitionListener {
            
            override fun onResults(results: Bundle?) {
                val text = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull() ?: ""
                if (text.isNotEmpty()) {
                    isListening = false
                    onResult?.invoke(text)
                    
                    // Analisis suara buat voice clone
                    if (!voiceAnalyzed) {
                        analyzeVoiceAndClone(text)
                    }
                }
            }
            
            override fun onPartialResults(results: Bundle?) {
                val text = results?.getStringArrayList(SpeechRecognizer.RESULTS_PARTIAL)?.firstOrNull() ?: ""
                if (text.isNotEmpty()) onPartial?.invoke(text)
            }
            
            override fun onRmsChanged(rmsdB: Float) {
                // rmsdB = 0-10, bisa buat estimasi volume & intensitas
            }
            
            override fun onEndOfSpeech() {
                isListening = false
                onSilence?.invoke()
            }
            
            override fun onError(error: Int) {
                isListening = false
            }
            
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }
    
    // ========== LISTEN ==========
    
    fun startListening() {
        if (isListening) return
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "id-ID")
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        }
        speechRecognizer?.startListening(intent)
        isListening = true
    }
    
    fun stopListening() {
        speechRecognizer?.stopListening()
        isListening = false
    }
    
    // ========== VOICE CLONE ANALYZER ==========
    
    /**
     * Analisis suara user dari teks + estimasi pitch
     * CUKUP 1 KALI MOMEN (marah/sedih/nangis)
     */
    private fun analyzeVoiceAndClone(text: String) {
        ttsExecutor?.execute {
            val emotion = detectEmotion(text)
            val intensity = estimateIntensity(text)
            
            // Pitch berdasarkan emosi
            val basePitch = when {
                emotion in listOf("marah", "takut", "panik") -> 1.35f
                emotion in listOf("sedih", "bosan") -> 0.8f
                emotion in listOf("senang", "kagum") -> 1.2f
                else -> 1.0f
            }
            
            val baseRate = when {
                emotion in listOf("marah", "takut", "panik") -> 1.25f
                emotion in listOf("sedih", "bosan") -> 0.85f
                else -> 1.0f
            }
            
            val bassLevel = when {
                text.length > 50 -> 0.6f  // Kalimat panjang → suara berat
                text.contains("!") -> 0.4f
                else -> 0.5f
            }
            
            val breathiness = when (emotion) {
                "sedih" -> 0.4f
                "takut" -> 0.5f
                "marah" -> 0.1f
                else -> 0.2f
            }
            
            val toneType = when {
                bassLevel > 0.6f -> "bass"
                basePitch > 1.3f -> "tenor"
                basePitch < 0.9f -> "baritone"
                else -> "normal"
            }
            
            // Emotional pitch & rate map
            val emotionPitch = mapOf(
                "senang" to basePitch * 1.2f,
                "sedih" to basePitch * 0.75f,
                "marah" to basePitch * 1.35f,
                "takut" to basePitch * 1.4f,
                "khawatir" to basePitch * 1.15f,
                "tenang" to basePitch * 0.9f,
                "penasaran" to basePitch * 1.1f,
                "bosan" to basePitch * 0.7f,
                "panik" to basePitch * 1.5f
            )
            
            val emotionRate = mapOf(
                "senang" to baseRate * 1.1f,
                "sedih" to baseRate * 0.8f,
                "marah" to baseRate * 1.3f,
                "takut" to baseRate * 1.35f,
                "khawatir" to baseRate * 1.15f,
                "tenang" to baseRate * 0.85f,
                "penasaran" to baseRate * 1.0f,
                "bosan" to baseRate * 0.75f,
                "panik" to baseRate * 1.4f
            )
            
            val cloneData = VoiceCloneData(
                basePitch = basePitch,
                baseRate = baseRate,
                bassLevel = bassLevel,
                breathiness = breathiness,
                toneType = toneType,
                emotionPitch = emotionPitch,
                emotionRate = emotionRate
            )
            
            // Clone suara ke TTS
            ttsManager.cloneVoice(
                basePitch = cloneData.basePitch,
                baseRate = cloneData.baseRate,
                bassLevel = cloneData.bassLevel,
                breathiness = cloneData.breathiness,
                emotionPitch = cloneData.emotionPitch,
                emotionRate = cloneData.emotionRate
            )
            
            voiceAnalyzed = true
            onVoiceProfileReady?.invoke(cloneData)
        }
    }
    
    private fun detectEmotion(text: String): String {
        val lower = text.lowercase()
        return when {
            lower.contains("tolong") || lower.contains("aduh") || lower.contains("sakit") -> "takut"
            lower.contains("marah") || lower.contains("sial") || lower.contains("brengsek") -> "marah"
            lower.contains("sedih") || lower.contains("nangis") || lower.contains("nyerah") -> "sedih"
            lower.contains("haha") || lower.contains("wkwk") || lower.contains("yes") || lower.contains("hore") -> "senang"
            lower.contains("wow") || lower.contains("keren") || lower.contains("gila") -> "kagum"
            lower.contains("capek") || lower.contains("ngantuk") -> "bosan"
            else -> "netral"
        }
    }
    
    private fun estimateIntensity(text: String): Float {
        return when {
            text.contains("!") || text.contains("?") -> 0.8f
            text.any { it.isUpperCase() } -> 0.7f
            text.length > 100 -> 0.6f
            else -> 0.4f
        }
    }
    
    // ========== CALLBACKS ==========
    
    fun setOnResult(cb: (String) -> Unit) { onResult = cb }
    fun setOnPartial(cb: (String) -> Unit) { onPartial = cb }
    fun setOnSilence(cb: () -> Unit) { onSilence = cb }
    fun setOnVoiceProfileReady(cb: (VoiceCloneData) -> Unit) { onVoiceProfileReady = cb }
    
    fun isListening(): Boolean = isListening
    fun isVoiceAnalyzed(): Boolean = voiceAnalyzed
    
    fun release() {
        stopListening()
        speechRecognizer?.destroy()
        speechRecognizer = null
    }
}