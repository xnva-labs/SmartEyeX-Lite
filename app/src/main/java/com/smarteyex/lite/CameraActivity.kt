package com.smarteyex.lite

import android.Manifest
import android.animation.ValueAnimator
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var resultText: TextView
    private lateinit var captureButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var scanProgress: ProgressBar
    private lateinit var scanLine: View

    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
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
        cameraExecutor = Executors.newSingleThreadExecutor()

        // Animasi garis scan bergerak
val animator = ValueAnimator.ofFloat(0f, 1500f)

animator.duration = 3000
animator.repeatCount = ValueAnimator.INFINITE
animator.repeatMode = ValueAnimator.REVERSE

animator.addUpdateListener {
    scanLine.translationY = it.animatedValue as Float
}

animator.start()

        // Cek dan minta izin kamera
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        captureButton.setOnClickListener {
            takePhoto()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Toast.makeText(this, "Gagal buka kamera: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        imageCapture.takePicture(ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                analyzeImage(image)
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(this@CameraActivity, "Gagal ambil foto: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun analyzeImage(imageProxy: ImageProxy) {
    runOnUiThread {
        scanProgress.visibility = View.VISIBLE
        resultText.text = "🔍 MENGANALISIS..."
    }

    val mediaImage = imageProxy.image

    if (mediaImage == null) {
        imageProxy.close()
        return
    }

    val inputImage = InputImage.fromMediaImage(
        mediaImage,
        imageProxy.imageInfo.rotationDegrees
    )

    val options = ObjectDetectorOptions.Builder()
        .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
        .enableClassification()
        .build()

    val objectDetector = ObjectDetection.getClient(options)

    objectDetector.process(inputImage)
        .addOnSuccessListener { objects ->

            runOnUiThread {
                scanProgress.visibility = View.GONE
            }

            if (objects.isNotEmpty()) {

    val labels = objects.mapNotNull { obj ->
        obj.labels.firstOrNull()?.text?.let { label ->
            val confidence =
                (obj.labels.firstOrNull()?.confidence ?: 0f) * 100

            "$label (${confidence.toInt()}%)"
        }
    }

    runOnUiThread {
        resultText.text =
            "🔍 TERDETEKSI: ${labels.joinToString(", ")}"
    }

    imageProxy.close()

} else {

    val labeler =
        ImageLabeling.getClient(
            ImageLabelerOptions.DEFAULT_OPTIONS
        )

    labeler.process(inputImage)
                    .addOnSuccessListener { labels ->

                        runOnUiThread {
    scanProgress.visibility = View.GONE
                            if (labels.isNotEmpty()) {

                                val topLabel = labels[0].text
                                val confidence =
                                    (labels[0].confidence * 100).toInt()

                                resultText.text =
                                    "🔍 KEMUNGKINAN: $topLabel ($confidence%)"

                            } else {

                                resultText.text =
                                    "🔍 TIDAK TERDETEKSI"
                            }
                        }

                        imageProxy.close()
                    }
                    .addOnFailureListener { e ->

                        runOnUiThread {
   scanProgress.visibility = View.GONE
                            resultText.text =
                                "⚠️ ERROR: ${e.message}"
                        }

                        imageProxy.close()
                    }
            }
        }
}
        .addOnFailureListener { e ->

            runOnUiThread {
                scanProgress.visibility = View.GONE
                resultText.text = "⚠️ GAGAL: ${e.message}"
            }

            imageProxy.close()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this, "Izin kamera ditolak. Fitur kamera tidak bisa digunakan.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}