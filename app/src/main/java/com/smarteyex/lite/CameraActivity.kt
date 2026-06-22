package com.smarteyex.lite

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    // ========== UI ==========
    private lateinit var previewView: PreviewView
    private lateinit var resultText: TextView
    private lateinit var moodText: TextView
    private lateinit var knowledgeText: TextView
    private lateinit var captureButton: ImageButton
    private lateinit var micButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var scanProgress: ProgressBar
    private lateinit var scanLine: View
    private lateinit var realtimeToggle: ToggleButton

    // ========== MANAGERS ==========
    private var cameraManager: CameraManager? = null
    private var motionDetector: MotionDetector? = null
    private var objectDetector: ObjectDetector? = null
    private var gptManager: GPTManager? = null
    private var ttsManager: TTSManager? = null
    private var sttManager: STTManager? = null
    private var safetyChecker: SafetyChecker? = null
    private var contextAnalyzer: ContextAnalyzer? = null
    private var triggerDetector: TriggerWordDetector? = null
    private var githubSync: GitHubSyncManager? = null
    private var database: MemoryDatabase? = null

    // ========== BRAIN SYSTEMS ==========
    private var moodSystem: MoodSystem? = null
    private var memorySystem: MemorySystem? = null
    private var personalitySystem: PersonalitySystem? = null
    private var socialRelationSystem: SocialRelationSystem? = null

    // ========== STATE ==========
    private var isRealtimeMode = false
    private var lastGptCall = 0L
    private var lastSpeech = 0L
    private var lastUserInteraction = System.currentTimeMillis()
    private val mainExecutor = Executors.newSingleThreadExecutor()

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
        private const val OPENAI_API_KEY = "sk-YOUR_API_KEY_HERE"
        private const val GITHUB_TOKEN = "ghp_YOUR_TOKEN_HERE"
        private const val GITHUB_REPO = "username/xnai-knowledge"
    }

    // ========== LIFECYCLE ==========

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        initUI()
        initManagers()
        checkPermissions()
    }

    private fun initUI() {
        previewView = findViewById(R.id.previewView)
        resultText = findViewById(R.id.resultText)
        moodText = findViewById(R.id.moodText)
        knowledgeText = findViewById(R.id.knowledgeText)
        captureButton = findViewById(R.id.captureButton)
        micButton = findViewById(R.id.micButton)
        backButton = findViewById(R.id.backButton)
        scanProgress = findViewById(R.id.scanProgress)
        scanLine = findViewById(R.id.scanLine)
        realtimeToggle = findViewById(R.id.realtimeToggle)

        captureButton.setOnClickListener { takePhoto() }
        micButton.setOnClickListener { toggleListening() }
        backButton.setOnClickListener { finish() }

        realtimeToggle.setOnCheckedChangeListener { _, on ->
            if (on) startRealtimeMode() else stopRealtimeMode()
        }
    }

    private fun initManagers() {
        // Database
        database = MemoryDatabase.getInstance(this)

        // Brain Systems
        moodSystem = MoodSystem()
        memorySystem = MemorySystem()
        personalitySystem = PersonalitySystem()
        socialRelationSystem = SocialRelationSystem()

        // Load owner nickname dari memory
        socialRelationSystem?.let { social ->
            memorySystem?.let { mem ->
                social.loadOwnerNickname(mem)
            }
        }

        // Camera
        cameraManager = CameraManager(this, this, previewView)
        cameraManager?.initialize()

        // Vision
        motionDetector = MotionDetector()
        objectDetector = ObjectDetector()
        objectDetector?.initialize()

        // AI
        gptManager = GPTManager(OPENAI_API_KEY, cameraManager?.getAiExecutor())
        safetyChecker = SafetyChecker()
        contextAnalyzer = ContextAnalyzer()
        triggerDetector = TriggerWordDetector()

        // Voice
        ttsManager = TTSManager(this, cameraManager?.getTtsExecutor())
        ttsManager?.initialize()
        sttManager = STTManager(this, ttsManager!!, cameraManager?.getTtsExecutor())
        sttManager?.initialize()

        // GitHub Sync
        githubSync = GitHubSyncManager(this, database!!)
        githubSync?.configure(GITHUB_TOKEN, GITHUB_REPO, "user_${System.currentTimeMillis()}")

        // TTS Callbacks
        ttsManager?.setCallbacks(object : TTSManager.TTSCallbacks {
            override fun onSpeechStart() {}
            override fun onSpeechDone() {}
            override fun onSpeechError() {}
        })

        // STT Callbacks
        sttManager?.setOnResult { text ->
            lastUserInteraction = System.currentTimeMillis()
            handleUserSpeech(text)
        }

        // Voice Clone callback
        sttManager?.setOnVoiceProfileReady { profile ->
            runOnUiThread {
                knowledgeText?.text = "🎤 Voice cloned! Tone: ${profile.toneType}"
            }
        }
    }

    private fun checkPermissions() {
        if (REQUIRED_PERMISSIONS.all {
                ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
            }) {
            // All permissions granted
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
    }

    // ========== PHOTO MODE ==========

    private fun takePhoto() {
        cameraManager?.takePhoto { bitmap ->
            runOnUiThread {
                resultText.text = "📸 Menganalisis..."
                scanProgress.visibility = View.VISIBLE
            }
            analyzeFrame(bitmap)
        }
    }

    // ========== REALTIME MODE ==========

    private fun startRealtimeMode() {
        isRealtimeMode = true
        captureButton.visibility = View.GONE
        micButton.visibility = View.VISIBLE
        scanLine.visibility = View.GONE

        resultText.text = "👁️ XNAI SIAGA"
        moodText.text = moodSystem?.getMoodLabel() ?: "😌 Tenang"
        knowledgeText.text = "📚 Siap belajar..."

        cameraManager?.startRealtimeMode(fps = 5) { bitmap ->
            analyzeFrame(bitmap)
        }
    }

    private fun stopRealtimeMode() {
        isRealtimeMode = false
        cameraManager?.stopRealtimeMode()
        captureButton.visibility = View.VISIBLE
        micButton.visibility = View.GONE
        scanLine.visibility = View.VISIBLE
        resultText.text = "📷 Mode Foto"
    }

    // ========== FRAME ANALYSIS ==========

    private fun analyzeFrame(bitmap: Bitmap) {
        mainExecutor.execute {
            try {
                val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

                // 1. Motion Detection
                val motionResult = motionDetector?.processFrame(bitmap)

                // 2. Object Detection
                val detectionResult = objectDetector?.detect(bitmap)

                // 3. Context Analysis
                val objects = detectionResult?.objects?.map { it.label } ?: emptyList()
                val contextResult = contextAnalyzer?.analyze(
                    hour = hour,
                    environment = "bengkel", // Bisa di-detect dari environment
                    motionLevel = motionResult?.motionLevel ?: 0f,
                    motionSpike = motionResult?.isSpike ?: false,
                    detectedObjects = objects,
                    userSilenceMinutes = (System.currentTimeMillis() - lastUserInteraction) / 60000
                )

                // 4. Safety Check
                val detectionData = SafetyChecker.DetectionData(
                    objects = objects,
                    hasHuman = detectionResult?.hasHuman ?: false,
                    humanCount = detectionResult?.humanCount ?: 0,
                    hasDangerousObject = detectionResult?.hasDangerousObject ?: false,
                    motionLevel = motionResult?.motionLevel ?: 0f,
                    isMotionSpike = motionResult?.isSpike ?: false,
                    motionDirection = motionResult?.motionDirection ?: "stabil",
                    dangerZoneTriggered = motionResult?.dangerZoneTriggered ?: false
                )

                val safetyResult = safetyChecker?.checkSafety(
                    context = SafetyChecker.ContextResult(
                        timeOfDay = contextResult?.timeOfDay ?: "siang",
                        isNight = contextResult?.isNight ?: false,
                        environment = contextResult?.environment ?: "bengkel",
                        activityLevel = contextResult?.activityLevel ?: "normal",
                        userState = contextResult?.userState ?: "aktif",
                        riskLevel = contextResult?.riskLevel ?: 2
                    ),
                    detection = detectionData
                )

                // 5. Update Vision State
                val description = detectionResult?.sceneDescription ?: "Scene normal"
                cameraManager?.updateVisionState(description, objects, motionResult?.motionLevel ?: 0f)

                // 6. Curiosity check
                val curiousObjects = contextResult?.curiousObjects ?: emptyList()
                if (curiousObjects.isNotEmpty() && System.currentTimeMillis() - lastSpeech > 10000) {
                    val curiosityText = contextAnalyzer?.getCuriosityResponse(curiousObjects.first())
                    if (curiosityText != null) {
                        lastSpeech = System.currentTimeMillis()
                        runOnUiThread { resultText.text = "🤔 $curiosityText" }
                        ttsManager?.speak(curiosityText)
                    }
                }

                // 7. Panggil GPT jika perlu
                val now = System.currentTimeMillis()
                if (safetyResult?.isDanger == true && now - lastGptCall > 3000) {
                    lastGptCall = now
                    callGPT(bitmap, contextResult, detectionResult, safetyResult)
                }

                // 8. Update UI
                runOnUiThread {
                    moodText?.text = moodSystem?.getMoodLabel() ?: "😌"
                    val knowledgeCount = database?.learnedFactDao()?.let {
                        // Async, skip for now
                    }
                    knowledgeText?.text = "📚 Objects: ${detectionResult?.objectCount ?: 0}"
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // ========== GPT CALL ==========

    private fun callGPT(
        bitmap: Bitmap,
        context: ContextAnalyzer.ContextResult?,
        detection: ObjectDetector.DetectionResult?,
        safety: SafetyChecker.SafetyResult?
    ) {
        val base64 = cameraManager?.bitmapToBase64(bitmap, 50) ?: return

        val prompt = """
            Konteks: ${context?.timeOfDay}, ${context?.environment}, Risk: ${context?.riskLevel}
            ${if (safety?.isDanger == true) "⚠️ BAHAYA! ${safety.warnings.joinToString()}" else ""}
            Objek: ${detection?.objects?.map { it.label }?.joinToString() ?: "tidak ada"}
        """.trimIndent()

        val response = gptManager?.analyzeVision(bitmap, prompt)
        if (response != null && response.shouldSpeak) {
            lastSpeech = System.currentTimeMillis()
            val urgency = when (response.urgencyLevel) {
                "darurat" -> 10
                "penting" -> 5
                else -> 3
            }

            runOnUiThread { resultText.text = "🤖 ${response.text}" }

            if (urgency >= 10) {
                ttsManager?.speakUrgent(response.text)
            } else {
                ttsManager?.speak(response.text, urgency, response.detectedMood, false)
            }

            // Simpan ke memory
            memorySystem?.remember(
                content = response.text,
                type = MemoryType.FACT,
                emotionalWeight = if (urgency >= 7) 0.8f else 0.3f,
                tags = listOf("gpt_response", response.detectedMood)
            )

            // Sync ke GitHub
            githubSync?.addToGlobalKnowledge(
                key = "response_${System.currentTimeMillis()}",
                value = response.text,
                category = "gpt_response",
                emotionalWeight = 0.5f
            )
        }
    }

    // ========== USER SPEECH ==========

    private fun handleUserSpeech(text: String) {
        runOnUiThread { resultText.text = "🗣️ Lo: $text" }

        // 1. Trigger detection
        val trigger = triggerDetector?.detect(text)
        if (trigger?.detected == true) {
            trigger.primaryEmotion.let { emotion ->
                moodSystem?.detectFromTriggerWords(text)?.let { mood ->
                    moodSystem?.setMood(mood, trigger.intensity)
                    runOnUiThread { moodText?.text = moodSystem?.getMoodLabel() }
                }
            }
        }

        // 2. Cek di local knowledge DULU (hemat API!)
        val localAnswer = githubSync?.runBlocking { searchGlobalKnowledge(text) }
        if (localAnswer != null) {
            runOnUiThread { resultText.text = "🤖 XNAI: $localAnswer" }
            ttsManager?.speak(localAnswer)
            lastSpeech = System.currentTimeMillis()
            return
        }

        // 3. Cek di database
        val dbFacts = database?.learnedFactDao()
        // ... search local DB

        // 4. Panggil GPT jika tidak ditemukan
        val response = gptManager?.chat(text)
        if (response != null && response.text.isNotEmpty()) {
            runOnUiThread { resultText.text = "🤖 XNAI: ${response.text}" }
            ttsManager?.speak(response.text)
            lastSpeech = System.currentTimeMillis()

            // Simpan ke database
            database?.learnedFactDao()?.insert(
                LearnedFactEntity(
                    key = text.take(100),
                    value = response.text,
                    category = "conversation",
                    emotionalWeight = 0.5f
                )
            )
        }
    }

    // ========== MIC ==========

    private fun toggleListening() {
        if (sttManager?.isListening() == true) {
            sttManager?.stopListening()
            micButton.setImageResource(android.R.drawable.ic_btn_speak_now)
        } else {
            sttManager?.startListening()
            micButton.setImageResource(android.R.drawable.ic_btn_speak_now)
        }
    }

    // ========== LIFECYCLE ==========

    override fun onDestroy() {
        super.onDestroy()
        stopRealtimeMode()
        cameraManager?.release()
        motionDetector?.release()
        objectDetector?.release()
        ttsManager?.release()
        sttManager?.release()
        mainExecutor.shutdown()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // All good!
            } else {
                Toast.makeText(this, "Izin ditolak", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    // Helper untuk run blocking di coroutine
    private fun <T> runBlocking(block: suspend () -> T): T {
        return kotlinx.coroutines.runBlocking { block() }
    }
}