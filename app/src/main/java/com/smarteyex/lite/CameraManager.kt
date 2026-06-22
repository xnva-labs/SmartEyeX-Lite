package com.smarteyex.lite

import android.content.Context
import android.graphics.*
import android.os.SystemClock
import android.util.Base64
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.concurrent.*
import kotlin.math.min

class CameraManager(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val previewView: PreviewView
) {
    
    // ========== EXECUTORS (Pisah biar ga macet) ==========
    private var cameraExecutor: ExecutorService? = null
    private var aiExecutor: ExecutorService? = null
    private var ttsExecutor: ExecutorService? = null
    
    private var cameraProvider: ProcessCameraProvider? = null
    private var camera: Camera? = null
    private var imageCapture: ImageCapture? = null
    private var imageAnalysis: ImageAnalysis? = null
    
    private var isRealtimeMode = false
    private var frameCallback: ((Bitmap) -> Unit)? = null
    private var lastFrameTime = 0L
    private var targetFps = 10
    private var frameCount = 0L
    private var currentLensFacing = CameraSelector.LENS_FACING_BACK
    
    // ========== VISION STATE MANAGER ==========
    private var lastDescription: String = ""
    private var lastObjects: List<String> = emptyList()
    private var consecutiveSameCount = 0
    private val visionMemory = mutableListOf<VisionState>()
    private val maxVisionMemory = 20
    
    // ========== ATTENTION MODE ==========
    private var attentionTarget: String? = null
    private var attentionMode = false
    
    // ========== URGENCY INTERRUPT ==========
    private var urgencyInterrupt: (() -> Unit)? = null
    private var isUrgencyActive = false
    
    data class VisionState(
        val timestamp: Long,
        val description: String,
        val objects: List<String>,
        val motionLevel: Float,
        val context: String = ""
    )
    
    enum class UrgencyLevel {
        INFO,       // Diam aja
        PENTING,    // Boleh ngomong
        DARURAT     // Potong semua, langsung ngomong
    }
    
    // ========== INITIALIZATION ==========
    
    fun initialize(
        onFrame: ((Bitmap) -> Unit)? = null,
        cameraFps: Int = 10,
        aiThreads: Int = 2,
        ttsThreads: Int = 1
    ) {
        cameraExecutor = Executors.newSingleThreadExecutor()
        aiExecutor = ThreadPoolExecutor(2, aiThreads, 30L, TimeUnit.SECONDS, LinkedBlockingQueue())
        ttsExecutor = Executors.newSingleThreadExecutor()
        
        frameCallback = onFrame
        targetFps = cameraFps
        
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            bindPreview()
        }, ContextCompat.getMainExecutor(context))
    }
    
    // ========== PREVIEW MODE ==========
    
    private fun bindPreview() {
        val provider = cameraProvider ?: return
        
        val preview = Preview.Builder()
            .build()
            .also { it.setSurfaceProvider(previewView.surfaceProvider) }
        
        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .setFlashMode(ImageCapture.FLASH_MODE_AUTO)
            .build()
        
        try {
            provider.unbindAll()
            camera = provider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.Builder().requireLensFacing(currentLensFacing).build(),
                preview,
                imageCapture
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    // ========== REALTIME MODE ==========
    
    fun startRealtimeMode(fps: Int = 10, onFrame: (Bitmap) -> Unit) {
        isRealtimeMode = true
        targetFps = fps
        frameCallback = onFrame
        lastFrameTime = 0L
        frameCount = 0L
        
        val provider = cameraProvider ?: return
        
        val preview = Preview.Builder()
            .build()
            .also { it.setSurfaceProvider(previewView.surfaceProvider) }
        
        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()
        
        imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888)
            .build()
        
        imageAnalysis?.setAnalyzer(cameraExecutor!!) { imageProxy ->
            if (!isRealtimeMode) {
                imageProxy.close()
                return@setAnalyzer
            }
            
            // Cek urgency interrupt
            if (isUrgencyActive) {
                imageProxy.close()
                return@setAnalyzer
            }
            
            frameCount++
            val currentTime = SystemClock.elapsedRealtime()
            val frameInterval = 1000L / targetFps
            
            if (currentTime - lastFrameTime >= frameInterval) {
                lastFrameTime = currentTime
                
                val bitmap = yuvToBitmap(imageProxy)
                if (bitmap != null) {
                    frameCallback?.invoke(bitmap)
                }
            }
            
            imageProxy.close()
        }
        
        try {
            provider.unbindAll()
            camera = provider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.Builder().requireLensFacing(currentLensFacing).build(),
                preview,
                imageCapture,
                imageAnalysis
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    fun stopRealtimeMode() {
        isRealtimeMode = false
        frameCallback = null
        imageAnalysis?.clearAnalyzer()
        bindPreview()
    }
    
    // ========== PHOTO MODE ==========
    
    fun takePhoto(onPhotoTaken: (Bitmap) -> Unit) {
        val capture = imageCapture ?: return
        
        capture.takePicture(
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    val bitmap = yuvToBitmap(image)
                    if (bitmap != null) {
                        onPhotoTaken(bitmap)
                    }
                    image.close()
                }
                
                override fun onError(exception: ImageCaptureException) {
                    exception.printStackTrace()
                }
            }
        )
    }
    
    // ========== YUV → BITMAP (HEMAT CPU) ==========
    
    private fun yuvToBitmap(imageProxy: ImageProxy): Bitmap? {
        return try {
            val planes = imageProxy.planes
            val yBuffer = planes[0].buffer
            val uBuffer = planes[1].buffer
            val vBuffer = planes[2].buffer
            
            val ySize = yBuffer.remaining()
            val uSize = uBuffer.remaining()
            val vSize = vBuffer.remaining()
            
            val nv21 = ByteArray(ySize + uSize + vSize)
            yBuffer.get(nv21, 0, ySize)
            vBuffer.get(nv21, ySize, vSize)
            uBuffer.get(nv21, ySize + vSize, uSize)
            
            val yuvImage = YuvImage(
                nv21, ImageFormat.NV21,
                imageProxy.width, imageProxy.height, null
            )
            
            val out = ByteArrayOutputStream()
            yuvImage.compressToJpeg(
                Rect(0, 0, imageProxy.width, imageProxy.height),
                50, out
            )
            
            BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size())
        } catch (e: Exception) {
            null
        }
    }
    
    // ========== SWITCH CAMERA ==========
    
    fun switchCamera() {
        currentLensFacing = if (currentLensFacing == CameraSelector.LENS_FACING_BACK) {
            CameraSelector.LENS_FACING_FRONT
        } else {
            CameraSelector.LENS_FACING_BACK
        }
        
        if (isRealtimeMode) {
            val currentCallback = frameCallback
            stopRealtimeMode()
            startRealtimeMode(targetFps) { bitmap -> currentCallback?.invoke(bitmap) }
        } else {
            bindPreview()
        }
    }
    
    // ========== VISION STATE MANAGER ==========
    
    fun updateVisionState(description: String, objects: List<String>, motionLevel: Float, context: String = "") {
        val now = SystemClock.elapsedRealtime()
        
        if (description == lastDescription && objects == lastObjects) {
            consecutiveSameCount++
        } else {
            consecutiveSameCount = 0
            lastDescription = description
            lastObjects = objects
            
            visionMemory.add(
                VisionState(now, description, objects, motionLevel, context)
            )
            
            if (visionMemory.size > maxVisionMemory) {
                visionMemory.removeAt(0)
            }
        }
    }
    
    fun hasSignificantChange(): Boolean = consecutiveSameCount < 3
    
    fun getPreviousDescription(): String = lastDescription
    
    fun getPreviousObjects(): List<String> = lastObjects
    
    fun getVisionMemory(): List<VisionState> = visionMemory.toList()
    
    fun getVisionContext(durationMs: Long = 10000): String {
        val now = SystemClock.elapsedRealtime()
        val recent = visionMemory.filter { now - it.timestamp <= durationMs }
        
        if (recent.isEmpty()) return "Tidak ada data vision."
        
        return buildString {
            append("📸 10 DETIK TERAKHIR:\n")
            recent.forEach { state ->
                val timeAgo = (now - state.timestamp) / 1000
                append("  ${timeAgo}s lalu: ${state.description} (gerakan: ${state.motionLevel.toInt()}%)\n")
            }
        }
    }
    
    // ========== ATTENTION MODE ==========
    
    fun setAttentionTarget(target: String?) {
        attentionTarget = target?.lowercase()
        attentionMode = target != null
    }
    
    fun getAttentionTarget(): String? = attentionTarget
    
    fun isAttentionMode(): Boolean = attentionMode
    
    fun clearAttention() {
        attentionTarget = null
        attentionMode = false
    }
    
    fun filterByAttention(objects: List<String>): List<String> {
        if (!attentionMode || attentionTarget == null) return objects
        
        return objects.filter { obj ->
            obj.lowercase().contains(attentionTarget!!) ||
            attentionTarget!!.contains(obj.lowercase())
        }.ifEmpty { objects } // Kalau ga ada yang match, balikin semua
    }
    
    // ========== URGENCY INTERRUPT ==========
    
    fun setUrgencyInterrupt(callback: () -> Unit) {
        urgencyInterrupt = callback
    }
    
    fun triggerUrgency(level: UrgencyLevel) {
        if (level == UrgencyLevel.DARURAT) {
            isUrgencyActive = true
            
            // Potong semua TTS yang sedang berjalan
            ttsExecutor?.submit {
                urgencyInterrupt?.invoke()
            }
            
            // Kembalikan setelah 3 detik
            cameraExecutor?.submit {
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    // ignore
                }
                isUrgencyActive = false
            }
        }
    }
    
    fun isUrgencyActive(): Boolean = isUrgencyActive
    
    // ========== IMAGE PROCESSING ==========
    
    fun resizeForML(bitmap: Bitmap, maxSize: Int = 512): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val ratio = min(maxSize.toFloat() / width, maxSize.toFloat() / height)
        
        if (ratio >= 1f) return bitmap
        
        val newWidth = (width * ratio).toInt()
        val newHeight = (height * ratio).toInt()
        
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }
    
    fun bitmapToBase64(bitmap: Bitmap, quality: Int = 50): String {
        val resized = resizeForML(bitmap)
        val outputStream = ByteArrayOutputStream()
        resized.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP)
    }
    
    // ========== FLASH ==========
    
    fun setFlash(enabled: Boolean) {
        imageCapture?.flashMode = if (enabled) ImageCapture.FLASH_MODE_ON 
                                else ImageCapture.FLASH_MODE_OFF
    }
    
    // ========== GETTERS ==========
    
    fun getCurrentFps(): Int = targetFps
    fun isRealtimeActive(): Boolean = isRealtimeMode
    fun getAiExecutor(): ExecutorService? = aiExecutor
    fun getTtsExecutor(): ExecutorService? = ttsExecutor
    fun getCameraExecutor(): ExecutorService? = cameraExecutor
    
    // ========== RELEASE ==========
    
    fun release() {
        stopRealtimeMode()
        
        try {
            cameraExecutor?.shutdown()
            cameraExecutor?.awaitTermination(1, TimeUnit.SECONDS)
        } catch (e: Exception) {
            // ignore
        }
        
        try {
            aiExecutor?.shutdown()
        } catch (e: Exception) {
            // ignore
        }
        
        try {
            ttsExecutor?.shutdown()
        } catch (e: Exception) {
            // ignore
        }
        
        cameraProvider?.unbindAll()
    }
}