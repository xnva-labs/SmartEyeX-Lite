package com.smarteyex.lite

import android.Manifest
import android.animation.ValueAnimator
import android.content.pm.PackageManager
import android.graphics.*
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Base64
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class CameraActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var previewView: PreviewView
    private lateinit var resultText: TextView
    private lateinit var captureButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var scanProgress: ProgressBar
    private lateinit var scanLine: View
    private lateinit var realtimeToggle: ToggleButton

    private var imageCapture: ImageCapture? = null
    private var imageAnalysis: ImageAnalysis? = null
    private lateinit var cameraExecutor: ExecutorService
    private var tts: TextToSpeech? = null

    private var isRealtimeMode = false
    private var lastApiCall = 0L
    private var lastSpeech = 0L
    private var lastDetected = ""
    private var memoryContext = ""

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val OPENAI_KEY = "sk-YOUR_API_KEY_HERE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        previewView = findViewById(R.id.previewView)
        resultText = findViewById(R.id.resultText)
        captureButton = findViewById(R.id.captureButton)
        backButton = findViewById(R.id.backButton)
        scanProgress = findViewById(R.id.scanProgress)
        scanLine = findViewById(R.id.scanLine)
        realtimeToggle = findViewById(R.id.realtimeToggle)
        cameraExecutor = Executors.newSingleThreadExecutor()
        tts = TextToSpeech(this, this)

        val animator = ValueAnimator.ofFloat(0f, 1500f)
        animator.duration = 3000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.addUpdateListener { scanLine.translationY = it.animatedValue as Float }
        animator.start()

        if (allPermissionsGranted()) startCamera()
        else ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)

        captureButton.setOnClickListener {
            if (!isRealtimeMode) takePhoto()
        }

        realtimeToggle.setOnCheckedChangeListener { _, on ->
            isRealtimeMode = on
            if (on) {
                startRealtime()
                resultText.text = "👁️ SIAGA AKTIF"
                captureButton.visibility = View.GONE
                scanLine.visibility = View.GONE
            } else {
                stopRealtime()
                resultText.text = "📷 Mode Foto"
                captureButton.visibility = View.VISIBLE
                scanLine.visibility = View.VISIBLE
            }
        }

        backButton.setOnClickListener {
            stopRealtime()
            tts?.stop()
            finish()
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCamera() {
        val provider = ProcessCameraProvider.getInstance(this)
        provider.addListener({
            val cameraProvider = provider.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()
            imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888)
                .build()
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, CameraSelector.DEFAULT_BACK_CAMERA, preview, imageCapture, imageAnalysis!!)
            } catch (e: Exception) {
                Toast.makeText(this, "Gagal kamera: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    // ==================== REALTIME ====================

    private fun startRealtime() {
        isRealtimeMode = true
        lastDetected = ""
        memoryContext = ""
        imageAnalysis?.setAnalyzer(cameraExecutor) { proxy ->
            if (!isRealtimeMode) { proxy.close(); return@setAnalyzer }
            val now = System.currentTimeMillis()
            if (now - lastApiCall < 3000) { proxy.close(); return@setAnalyzer }
            lastApiCall = now
            val bmp = proxyToBitmap(proxy)
            if (bmp != null) {
                detectAndReason(proxy, bmp)
            }
            proxy.close()
        }
    }

    private fun stopRealtime() {
        isRealtimeMode = false
        imageAnalysis?.clearAnalyzer()
    }

    private fun detectAndReason(proxy: ImageProxy, bmp: Bitmap) {
        val mediaImage = proxy.image ?: return
        val inputImage = InputImage.fromMediaImage(mediaImage, proxy.imageInfo.rotationDegrees)
        val detector = ObjectDetection.getClient(
            ObjectDetectorOptions.Builder()
                .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                .enableClassification()
                .build()
        )
        detector.process(inputImage)
            .addOnSuccessListener { objects ->
                val labels = objects.flatMap { it.labels }.map { it.text }.distinct()
                val currentDetected = labels.joinToString(", ")
                
                if (currentDetected.isNotEmpty() && currentDetected != lastDetected) {
                    lastDetected = currentDetected
                    val now = System.currentTimeMillis()
                    if (now - lastSpeech > 5000) {
                        lastSpeech = now
                        thread {
                            callOpenAI(bmp, labels)
                        }
                    }
                }
                runOnUiThread {
                    if (currentDetected.isNotEmpty() && !resultText.text.toString().startsWith("🤖"))
                        resultText.text = "👁️ $currentDetected"
                }
            }
    }

    // ==================== OPENAI ====================

    private fun callOpenAI(bmp: Bitmap, labels: List<String>) {
        try {
            runOnUiThread { scanProgress.visibility = View.VISIBLE }
            
            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            val base64 = bitmapToBase64(bmp)
            
            val systemPrompt = """
Kamu adalah XNAI, AI companion yang hidup bersama Bung X. Kamera selalu menyala.

ATURAN:
- Situasi normal/tidak penting → jawab tepat: SILENT
- Bahaya/kesalahan → peringatan singkat
- Momen lucu/unik → komentar santai ala Gen Z
- MAKS 20 kata, Bahasa Indonesia
- Panggil "Bund" atau "Bung"

MEMORI BUNG X:
- Suka kopi, kerja bengkel mesin bubut, sering minum es

CONTOH:
- Lihat engkol mesin terlalu cepat → "Bund, pelan-pelan! Kecepatan terlalu tinggi."
- Lihat minum es malam hari → "Malem dingin minum es? Kacau men ntar sakit 😂"
- Lihat situasi normal → SILENT
            """.trimIndent()

            val userMsg = JSONObject().apply {
                put("role", "user")
                put("content", JSONArray().apply {
                    put(JSONObject().apply {
                        put("type", "text")
                        put("text", "WAKTU: $time\nOBJEK: ${labels.joinToString(", ")}\n\nAnalisis dan putuskan bicara atau diam.")
                    })
                    put(JSONObject().apply {
                        put("type", "image_url")
                        put("image_url", JSONObject().apply {
                            put("url", "data:image/jpeg;base64,$base64")
                        })
                    })
                })
            }

            val payload = JSONObject().apply {
                put("model", "gpt-4o-mini")
                put("max_tokens", 60)
                put("temperature", 0.9)
                put("messages", JSONArray().apply {
                    put(JSONObject().apply {
                        put("role", "system")
                        put("content", systemPrompt)
                    })
                    put(userMsg)
                })
            }

            val url = java.net.URL("https://api.openai.com/v1/chat/completions")
            val conn = url.openConnection() as java.net.HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Authorization", "Bearer $OPENAI_KEY")
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true
            conn.outputStream.write(payload.toString().toByteArray())
            
            val response = conn.inputStream.bufferedReader().readText()
            val json = JSONObject(response)
            val content = json.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content").trim()
            
            runOnUiThread {
                scanProgress.visibility = View.GONE
                if (!content.contains("SILENT", true) && content.isNotEmpty()) {
                    resultText.text = "🤖 $content"
                    speak(content)
                }
            }
        } catch (e: Exception) {
            runOnUiThread {
                scanProgress.visibility = View.GONE
                resultText.text = "👁️ Memantau..."
            }
        }
    }

    // ==================== TTS ====================

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale("id", "ID")
        }
    }

    private fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "xna_${System.currentTimeMillis()}")
    }

    // ==================== MODE FOTO ====================

    private fun takePhoto() {
        val capture = imageCapture ?: return
        capture.takePicture(ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                analyzePhoto(image)
            }
            override fun onError(ex: ImageCaptureException) {
                Toast.makeText(this@CameraActivity, "Gagal: ${ex.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun analyzePhoto(imageProxy: ImageProxy) {
        runOnUiThread {
            scanProgress.visibility = View.VISIBLE
            resultText.text = "🔍 MENGANALISIS..."
        }
        val mediaImage = imageProxy.image
        if (mediaImage == null) { imageProxy.close(); return }
        val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        val detector = ObjectDetection.getClient(
            ObjectDetectorOptions.Builder()
                .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                .enableClassification()
                .build()
        )
        detector.process(inputImage)
            .addOnSuccessListener { objects ->
                runOnUiThread { scanProgress.visibility = View.GONE }
                if (objects.isNotEmpty()) {
                    val labels = objects.mapNotNull { obj ->
                        obj.labels.firstOrNull()?.let {
                            "${it.text} (${(it.confidence * 100).toInt()}%)"
                        }
                    }
                    runOnUiThread { resultText.text = "🔍 ${labels.joinToString(", ")}" }
                } else {
                    runOnUiThread { resultText.text = "🔍 TIDAK TERDETEKSI" }
                }
                imageProxy.close()
            }
            .addOnFailureListener { e ->
                runOnUiThread {
                    scanProgress.visibility = View.GONE
                    resultText.text = "⚠️ ERROR: ${e.message}"
                }
                imageProxy.close()
            }
    }

    // ==================== HELPERS ====================

    private fun proxyToBitmap(proxy: ImageProxy): Bitmap? {
        try {
            val yBuffer = proxy.planes[0].buffer
            val uBuffer = proxy.planes[1].buffer
            val vBuffer = proxy.planes[2].buffer
            val ySize = yBuffer.remaining()
            val uSize = uBuffer.remaining()
            val vSize = vBuffer.remaining()
            val nv21 = ByteArray(ySize + uSize + vSize)
            yBuffer.get(nv21, 0, ySize)
            vBuffer.get(nv21, ySize, vSize)
            uBuffer.get(nv21, ySize + vSize, uSize)
            val yuv = YuvImage(nv21, ImageFormat.NV21, proxy.width, proxy.height, null)
            val out = ByteArrayOutputStream()
            yuv.compressToJpeg(Rect(0, 0, proxy.width, proxy.height), 50, out)
            return BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size())
        } catch (e: Exception) {
            return null
        }
    }

    private fun bitmapToBase64(bmp: Bitmap): String {
        val resized = Bitmap.createScaledBitmap(bmp, 512, (512f / bmp.width * bmp.height).toInt(), true)
        val out = ByteArrayOutputStream()
        resized.compress(Bitmap.CompressFormat.JPEG, 50, out)
        return Base64.encodeToString(out.toByteArray(), Base64.NO_WRAP)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) startCamera()
            else { Toast.makeText(this, "Izin kamera ditolak", Toast.LENGTH_SHORT).show(); finish() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRealtime()
        tts?.stop()
        tts?.shutdown()
        cameraExecutor.shutdown()
    }
}