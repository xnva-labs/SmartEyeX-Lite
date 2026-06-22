package com.smarteyex.lite

import android.graphics.Bitmap
import android.graphics.RectF
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.core.Delegate
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.objectdetector.ObjectDetector
import com.google.mediapipe.tasks.vision.objectdetector.ObjectDetectorResult
import java.util.concurrent.Executors

class ObjectDetector {
    
    // ========== DATA CLASSES ==========
    
    data class DetectedObject(
        val label: String,
        val confidence: Float,
        val boundingBox: RectF,
        val centerX: Float,
        val centerY: Float,
        val area: Float,
        val isMoving: Boolean = false,
        val distance: String = "unknown",
        val category: String = "unknown"
    )
    
    data class DetectionResult(
        val objects: List<DetectedObject>,
        val dominantObject: DetectedObject?,
        val objectCount: Int,
        val hasHuman: Boolean,
        val humanCount: Int,
        val hasDangerousObject: Boolean,
        val hasMovingObject: Boolean,
        val sceneDescription: String
    )
    
    // ========== MEDIAPIPE ==========
    private var objectDetector: ObjectDetector? = null
    private var isInitialized = false
    
    // ========== OBJECT KNOWLEDGE ==========
    
    // Kategori objek
    private val objectCategories = mapOf(
        "person" to "manusia",
        "car" to "kendaraan", "motorcycle" to "kendaraan", "bicycle" to "kendaraan",
        "bus" to "kendaraan", "truck" to "kendaraan",
        "chair" to "furnitur", "table" to "furnitur", "couch" to "furnitur",
        "bed" to "furnitur", "dining table" to "furnitur",
        "cell phone" to "elektronik", "laptop" to "elektronik",
        "tv" to "elektronik", "remote" to "elektronik",
        "bottle" to "peralatan", "cup" to "peralatan", "bowl" to "peralatan",
        "knife" to "perkakas", "spoon" to "peralatan", "fork" to "peralatan",
        "scissors" to "perkakas",
        "dog" to "hewan", "cat" to "hewan", "bird" to "hewan",
        "book" to "benda", "clock" to "benda", "keyboard" to "elektronik",
        "backpack" to "benda", "umbrella" to "benda"
    )
    
    // Objek berbahaya
    private val dangerousObjects = setOf(
        "knife", "scissors", "gun", "fire", "weapon"
    )
    
    // Objek prioritas
    private val priorityObjects = setOf(
        "person", "cell phone", "keys", "wallet", "backpack"
    )
    
    // Label Bahasa Indonesia
    private val localizedLabels = mapOf(
        "person" to "orang",
        "car" to "mobil", "motorcycle" to "motor", "bicycle" to "sepeda",
        "bus" to "bus", "truck" to "truk",
        "chair" to "kursi", "table" to "meja", "couch" to "sofa",
        "bed" to "tempat tidur", "dining table" to "meja makan",
        "cell phone" to "smartphone", "laptop" to "laptop",
        "tv" to "televisi", "remote" to "remote",
        "bottle" to "botol", "cup" to "gelas", "bowl" to "mangkok",
        "knife" to "pisau", "spoon" to "sendok", "fork" to "garpu",
        "scissors" to "gunting",
        "dog" to "anjing", "cat" to "kucing", "bird" to "burung",
        "book" to "buku", "clock" to "jam", "keyboard" to "keyboard",
        "backpack" to "tas", "umbrella" to "payung"
    )
    
    // ========== TRACKING ==========
    private val trackingMemory = mutableMapOf<String, RectF>()
    private val objectHistory = mutableMapOf<String, Int>() // label -> count
    
    // ========== INITIALIZATION ==========
    
    fun initialize(modelPath: String = "/data/local/tmp/efficientdet_lite0.tflite") {
        try {
            val baseOptions = BaseOptions.builder()
                .setModelAssetPath("efficientdet_lite0.tflite") // Taruh di assets/
                .setDelegate(Delegate.GPU) // Pake GPU kalo ada
                .build()
            
            objectDetector = ObjectDetector.createFromOptions(
                com.google.mediapipe.tasks.vision.objectdetector.ObjectDetectorOptions.builder()
                    .setBaseOptions(baseOptions)
                    .setRunningMode(RunningMode.IMAGE)
                    .setMaxResults(10) // Maks 10 objek per frame
                    .setScoreThreshold(0.5f) // Confidence minimal 50%
                    .build()
            )
            
            isInitialized = true
        } catch (e: Exception) {
            e.printStackTrace()
            isInitialized = false
        }
    }
    
    // ========== DETECTION ==========
    
    fun detect(bitmap: Bitmap): DetectionResult {
        if (!isInitialized) {
            return DetectionResult(
                objects = emptyList(),
                dominantObject = null,
                objectCount = 0,
                hasHuman = false,
                humanCount = 0,
                hasDangerousObject = false,
                hasMovingObject = false,
                sceneDescription = "Object detector belum diinisialisasi"
            )
        }
        
        val mpImage = BitmapImageBuilder(bitmap).build()
        val result = objectDetector?.detect(mpImage)
        
        if (result == null || result.detections().isEmpty()) {
            return DetectionResult(
                objects = emptyList(),
                dominantObject = null,
                objectCount = 0,
                hasHuman = false,
                humanCount = 0,
                hasDangerousObject = false,
                hasMovingObject = false,
                sceneDescription = "Tidak ada objek terdeteksi"
            )
        }
        
        val detectedObjects = mutableListOf<DetectedObject>()
        
        for (detection in result.detections()) {
            if (detection.categories().isEmpty()) continue
            
            val category = detection.categories()[0]
            val label = category.categoryName().lowercase()
            val confidence = category.score()
            val boundingBox = detection.boundingBox()
            
            // Normalize bounding box (0-1)
            val normBox = RectF(
                boundingBox.left / bitmap.width,
                boundingBox.top / bitmap.height,
                boundingBox.right / bitmap.width,
                boundingBox.bottom / bitmap.height
            )
            
            val area = normBox.width() * normBox.height()
            val centerX = normBox.centerX()
            val centerY = normBox.centerY()
            
            // Estimasi jarak
            val distance = estimateDistance(area)
            
            // Cek gerakan
            val isMoving = checkIfMoving(label, normBox)
            
            // Kategori
            val categoryType = objectCategories[label] ?: "unknown"
            
            // Update tracking
            trackingMemory[label] = normBox
            objectHistory[label] = (objectHistory[label] ?: 0) + 1
            
            detectedObjects.add(
                DetectedObject(
                    label = label,
                    confidence = confidence,
                    boundingBox = normBox,
                    centerX = centerX,
                    centerY = centerY,
                    area = area,
                    isMoving = isMoving,
                    distance = distance,
                    category = categoryType
                )
            )
        }
        
        // Analisis
        val humans = detectedObjects.filter { it.label == "person" }
        val hasHuman = humans.isNotEmpty()
        val humanCount = humans.size
        
        val hasDangerous = detectedObjects.any { 
            it.label in dangerousObjects || 
            dangerousObjects.any { d -> it.label.contains(d) }
        }
        
        val hasMoving = detectedObjects.any { it.isMoving }
        
        val dominant = detectedObjects.maxByOrNull { it.confidence * it.area }
        
        val description = generateSceneDescription(detectedObjects)
        
        return DetectionResult(
            objects = detectedObjects,
            dominantObject = dominant,
            objectCount = detectedObjects.size,
            hasHuman = hasHuman,
            humanCount = humanCount,
            hasDangerousObject = hasDangerous,
            hasMovingObject = hasMoving,
            sceneDescription = description
        )
    }
    
    // ========== HELPERS ==========
    
    private fun estimateDistance(normalizedArea: Float): String {
        return when {
            normalizedArea > 0.25f -> "dekat"
            normalizedArea > 0.08f -> "sedang"
            else -> "jauh"
        }
    }
    
    private fun checkIfMoving(label: String, currentBox: RectF): Boolean {
        val previousBox = trackingMemory[label] ?: return false
        val dx = Math.abs(currentBox.centerX() - previousBox.centerX())
        val dy = Math.abs(currentBox.centerY() - previousBox.centerY())
        return dx > 0.03f || dy > 0.03f
    }
    
    private fun generateSceneDescription(objects: List<DetectedObject>): String {
        if (objects.isEmpty()) return "Scene kosong"
        
        val parts = mutableListOf<String>()
        
        // Manusia
        val humans = objects.filter { it.label == "person" }
        if (humans.isNotEmpty()) {
            val count = humans.size
            val locations = humans.map { 
                val pos = if (it.centerX < 0.33) "kiri" 
                         else if (it.centerX > 0.66) "kanan" 
                         else "tengah"
                "$pos(${it.distance})"
            }
            parts.add("${count} orang: ${locations.joinToString(", ")}")
        }
        
        // Objek lain (di atas confidence threshold)
        val otherObjects = objects
            .filter { it.label != "person" && it.confidence > 0.6f }
            .sortedByDescending { it.confidence }
            .take(5)
        
        if (otherObjects.isNotEmpty()) {
            val labels = otherObjects.map { getLocalizedLabel(it.label) }.distinct()
            parts.add("Objek: ${labels.joinToString(", ")}")
        }
        
        // Objek bergerak
        val moving = objects.filter { it.isMoving }
        if (moving.isNotEmpty()) {
            parts.add("Bergerak: ${moving.map { getLocalizedLabel(it.label) }.distinct().joinToString(", ")}")
        }
        
        // Peringatan bahaya
        val danger = objects.filter { 
            it.label in dangerousObjects || 
            dangerousObjects.any { d -> it.label.contains(d) }
        }
        if (danger.isNotEmpty()) {
            parts.add("⚠️ BAHAYA: ${danger.map { getLocalizedLabel(it.label) }.distinct().joinToString(", ")}")
        }
        
        return if (parts.isEmpty()) "Scene normal"
               else parts.joinToString(" | ")
    }
    
    fun getLocalizedLabel(label: String): String {
        return localizedLabels[label.lowercase()] ?: label
    }
    
    fun getCategory(label: String): String {
        return objectCategories[label.lowercase()] ?: "unknown"
    }
    
    // ========== ATTENTION FILTER ==========
    
    fun filterByAttention(objects: List<DetectedObject>, target: String): List<DetectedObject> {
        val lowerTarget = target.lowercase()
        return objects.filter { obj ->
            obj.label.lowercase().contains(lowerTarget) ||
            lowerTarget.contains(obj.label.lowercase()) ||
            getLocalizedLabel(obj.label).lowercase().contains(lowerTarget)
        }
    }
    
    // ========== ANALYTICS ==========
    
    fun getMostSeenObject(): String? {
        return objectHistory.maxByOrNull { it.value }?.key
    }
    
    fun getObjectCount(label: String): Int {
        return objectHistory[label] ?: 0
    }
    
    fun getClosestObject(objects: List<DetectedObject>): DetectedObject? {
        return objects.filter { it.distance == "dekat" }.maxByOrNull { it.area }
    }
    
    fun getMovingObjects(objects: List<DetectedObject>): List<DetectedObject> {
        return objects.filter { it.isMoving }
    }
    
    // ========== RELEASE ==========
    
    fun release() {
        objectDetector?.close()
        objectDetector = null
        isInitialized = false
        trackingMemory.clear()
        objectHistory.clear()
    }
}