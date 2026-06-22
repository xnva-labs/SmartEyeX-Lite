package com.smarteyex.lite

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

class MotionDetector {
    
    private var previousGray: Mat? = null
    private val motionHistory = mutableListOf<Float>()
    private val maxHistorySize = 150  // 5 detik di 30 FPS
    
    // Thresholds
    private val motionThreshold = 10.0   // Di bawah ini = diam
    private val spikeMultiplier = 2.5f   // 2.5x rata-rata = spike
    private val highMotionThreshold = 40.0 // Di atas ini = gerakan tinggi
    
    // Danger zones (normalized 0-1, area kiri-atas-kanan-bawah)
    private var dangerZones = mutableListOf<Rect>()
    
    data class MotionResult(
        val motionLevel: Float,          // 0-100%
        val hasMotion: Boolean,          // Ada gerakan?
        val isSpike: Boolean,            // Gerakan tiba-tiba?
        val motionDirection: String,     // "naik", "turun", "stabil", "chaotic"
        val motionSpeed: String,         // "diam", "lambat", "normal", "cepat"
        val dangerZoneTriggered: Boolean,// Gerakan di zona bahaya?
        val motionHistory: List<Float>,  // History 5 detik
        val averageMotion: Float,        // Rata-rata 2 detik terakhir
        val motionTrend: String          // "meningkat", "menurun", "stabil"
    )
    
    /**
     * Process frame dari kamera
     */
    fun processFrame(bitmap: Bitmap): MotionResult {
        val mat = Mat()
        Utils.bitmapToMat(bitmap, mat)
        val gray = Mat()
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY)
        
        val motionLevel = calculateMotion(gray)
        motionHistory.add(motionLevel)
        if (motionHistory.size > maxHistorySize) {
            motionHistory.removeAt(0)
        }
        
        val hasMotion = motionLevel > motionThreshold
        val isSpike = detectSpike(motionLevel)
        val motionDirection = detectDirection()
        val motionSpeed = detectSpeed(motionLevel)
        val dangerZone = checkDangerZones(mat, gray)
        val averageMotion = if (motionHistory.size >= 60) {
            motionHistory.takeLast(60).average().toFloat()
        } else {
            motionLevel
        }
        val motionTrend = detectTrend()
        
        // Update previous frame
        previousGray?.release()
        previousGray = gray.clone()
        
        mat.release()
        gray.release()
        
        return MotionResult(
            motionLevel = motionLevel,
            hasMotion = hasMotion,
            isSpike = isSpike,
            motionDirection = motionDirection,
            motionSpeed = motionSpeed,
            dangerZoneTriggered = dangerZone,
            motionHistory = motionHistory.toList(),
            averageMotion = averageMotion,
            motionTrend = motionTrend
        )
    }
    
    /**
     * Hitung motion level (0-100%) — Frame Differencing
     */
    private fun calculateMotion(currentGray: Mat): Float {
        if (previousGray == null) return 0f
        
        val diff = Mat()
        Core.absdiff(previousGray, currentGray, diff)
        
        val thresh = Mat()
        Imgproc.threshold(diff, thresh, 30.0, 255.0, Imgproc.THRESH_BINARY)
        
        val totalPixels = thresh.total().toFloat()
        val changedPixels = Core.countNonZero(thresh).toFloat()
        val motionPercent = if (totalPixels > 0) (changedPixels / totalPixels) * 100f else 0f
        
        diff.release()
        thresh.release()
        
        return motionPercent
    }
    
    /**
     * Deteksi spike — gerakan tiba-tiba di atas normal
     */
    private fun detectSpike(currentMotion: Float): Boolean {
        if (motionHistory.size < 30) return false  // Butuh minimal 1 detik data
        
        val average = motionHistory.takeLast(60).average().toFloat()
        
        // Abaikan kalau rata-rata terlalu rendah (false positive saat sepi)
        if (average < 5f) return false
        
        return currentMotion > (average * spikeMultiplier) && currentMotion > highMotionThreshold
    }
    
    /**
     * Deteksi arah gerakan
     */
    private fun detectDirection(): String {
        if (motionHistory.size < 10) return "stabil"
        
        val recent = motionHistory.takeLast(10)
        val first = recent.take(5).average()
        val last = recent.takeLast(5).average()
        val diff = last - first
        
        return when {
            diff > 10 -> "naik"
            diff < -10 -> "turun"
            recent.all { it < motionThreshold } -> "stabil"
            else -> "chaotic"
        }
    }
    
    /**
     * Deteksi kecepatan gerakan
     */
    private fun detectSpeed(motionLevel: Float): String {
        return when {
            motionLevel < motionThreshold -> "diam"
            motionLevel < 20 -> "lambat"
            motionLevel < 50 -> "normal"
            else -> "cepat"
        }
    }
    
    /**
     * Deteksi tren gerakan dalam 3 detik terakhir
     */
    private fun detectTrend(): String {
        if (motionHistory.size < 90) return "stabil"
        
        val first30 = motionHistory.takeLast(90).take(30).average()
        val last30 = motionHistory.takeLast(30).average()
        val diff = last30 - first30
        
        return when {
            diff > 5 -> "meningkat"
            diff < -5 -> "menurun"
            else -> "stabil"
        }
    }
    
    /**
     * Cek gerakan di zona bahaya
     */
    private fun checkDangerZones(colorMat: Mat, grayMat: Mat): Boolean {
        if (dangerZones.isEmpty()) return false
        
        // Deteksi kontur di seluruh frame
        val thresh = Mat()
        Imgproc.threshold(grayMat, thresh, 30.0, 255.0, Imgproc.THRESH_BINARY)
        
        val contours = mutableListOf<MatOfPoint>()
        Imgproc.findContours(thresh, contours, Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE)
        
        // Cek apakah ada kontur signifikan di zona bahaya
        for (contour in contours) {
            val area = Imgproc.contourArea(contour)
            if (area > 2000) {  // Gerakan signifikan
                val rect = Imgproc.boundingRect(contour)
                val centerX = rect.x + rect.width / 2
                val centerY = rect.y + rect.height / 2
                
                // Normalize ke 0-1
                val normX = centerX.toFloat() / colorMat.cols()
                val normY = centerY.toFloat() / colorMat.rows()
                
                for (zone in dangerZones) {
                    if (normX >= zone.x && normX <= zone.x + zone.width &&
                        normY >= zone.y && normY <= zone.y + zone.height) {
                        thresh.release()
                        return true
                    }
                }
            }
        }
        
        thresh.release()
        return false
    }
    
    /**
     * Set zona bahaya (koordinat 0-1)
     */
    fun setDangerZone(x: Double, y: Double, width: Double, height: Double) {
        dangerZones.add(
            Rect(x, y, width, height)
        )
    }
    
    /**
     * Clear semua zona bahaya
     */
    fun clearDangerZones() {
        dangerZones.clear()
    }
    
    /**
     * Set zona bahaya default (area mesin)
     */
    fun setDefaultDangerZones() {
        clearDangerZones()
        // Zona tengah frame — biasanya area kerja
        dangerZones.add(Rect(0.2, 0.2, 0.6, 0.6))
        // Zona bawah — area dekat mesin
        dangerZones.add(Rect(0.1, 0.6, 0.8, 0.4))
    }
    
    /**
     * Dapatkan deskripsi gerakan
     */
    fun getMotionDescription(result: MotionResult): String {
        if (!result.hasMotion) return "Tidak ada gerakan"
        
        val parts = mutableListOf<String>()
        
        if (result.isSpike) parts.add("⚠️ GERAKAN TIBA-TIBA!")
        if (result.dangerZoneTriggered) parts.add("🚨 DI ZONA BAHAYA!")
        
        parts.add("Gerakan: ${result.motionSpeed}")
        parts.add("Arah: ${result.motionDirection}")
        parts.add("Level: ${result.motionLevel.toInt()}%")
        
        return parts.joinToString(" | ")
    }
    
    /**
     * Release resources
     */
    fun release() {
        previousGray?.release()
        previousGray = null
        motionHistory.clear()
        dangerZones.clear()
    }
}