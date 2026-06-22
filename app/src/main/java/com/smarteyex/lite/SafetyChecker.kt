package com.smarteyex.lite

class SafetyChecker {
    
    // ========== URGENCY LEVELS ==========
    enum class UrgencyLevel(val level: Int, val label: String, val description: String) {
        INFO(1, "INFO", "Informasi biasa, tidak perlu ngomong"),
        PENTING(2, "PENTING", "Perlu perhatian, boleh ngomong"),
        DARURAT(3, "DARURAT", "Bahaya! Potong semua, langsung ngomong keras!")
    }
    
    // ========== SAFETY RULES ==========
    data class SafetyRule(
        val id: String,
        val condition: String,
        val urgencyLevel: UrgencyLevel,
        val warningMessage: String,
        val action: String
    )
    
    // ========== SAFETY RESULT ==========
    data class SafetyResult(
        val isDanger: Boolean,
        val urgencyLevel: UrgencyLevel,
        val warnings: List<String>,
        val actions: List<String>,
        val shouldSpeak: Boolean,
        val description: String
    )
    
    // ========== CONTEXT RESULT (dari ContextAnalyzer) ==========
    data class ContextResult(
        val timeOfDay: String,
        val isNight: Boolean,
        val environment: String,
        val activityLevel: String,
        val userState: String,
        val riskLevel: Int
    )
    
    // ========== DETECTION RESULT (dari ObjectDetector & MotionDetector) ==========
    data class DetectionData(
        val objects: List<String>,
        val hasHuman: Boolean,
        val humanCount: Int,
        val hasDangerousObject: Boolean,
        val motionLevel: Float,
        val isMotionSpike: Boolean,
        val motionDirection: String,
        val dangerZoneTriggered: Boolean
    )
    
    // ========== SAFETY RULES DATABASE ==========
    private val safetyRules = listOf(
        SafetyRule(
            id = "S1",
            condition = "tangan_dekat_mesin_berputar",
            urgencyLevel = UrgencyLevel.DARURAT,
            warningMessage = "⚠️ BAHAYA! Tangan terlalu dekat dengan mesin berputar!",
            action = "Segera menjauhkan tangan! Matikan mesin jika perlu!"
        ),
        SafetyRule(
            id = "S2",
            condition = "engkol_terlalu_cepat",
            urgencyLevel = UrgencyLevel.DARURAT,
            warningMessage = "⚠️ BAHAYA! Engkol diputar terlalu cepat!",
            action = "Pelan-pelan putar engkolnya! Kecepatan berlebih bisa merusak pisau!"
        ),
        SafetyRule(
            id = "S3",
            condition = "tidak_pakai_kacamata_safety",
            urgencyLevel = UrgencyLevel.PENTING,
            warningMessage = "Bung, kacamata safety-nya mana?",
            action = "Pakai kacamata pelindung sebelum menyalakan mesin!"
        ),
        SafetyRule(
            id = "S4",
            condition = "tidak_pakai_helm",
            urgencyLevel = UrgencyLevel.PENTING,
            warningMessage = "Bung, helmnya belum dipakai!",
            action = "Pakai helm dulu sebelum jalan!"
        ),
        SafetyRule(
            id = "S5",
            condition = "lantai_licin_oli",
            urgencyLevel = UrgencyLevel.PENTING,
            warningMessage = "Hati-hati Bung, lantainya licin!",
            action = "Bersihkan oli yang tumpah atau hindari area licin!"
        ),
        SafetyRule(
            id = "S6",
            condition = "anak_dekat_mesin",
            urgencyLevel = UrgencyLevel.DARURAT,
            warningMessage = "⚠️ BAHAYA! Ada anak kecil dekat mesin!",
            action = "Jauhkan anak dari area mesin SEKARANG!"
        ),
        SafetyRule(
            id = "S7",
            condition = "kabel_berserakan",
            urgencyLevel = UrgencyLevel.PENTING,
            warningMessage = "Bung, kabelnya berserakan. Bisa bikin tersandung.",
            action = "Rapikan kabel atau beri tanda!"
        ),
        SafetyRule(
            id = "S8",
            condition = "minum_es_malam_dingin",
            urgencyLevel = UrgencyLevel.INFO,
            warningMessage = "Malem dingin gini minum es? Ntar masuk angin lho Bung 😄",
            action = "Mending minum kopi anget atau teh hangat."
        ),
        SafetyRule(
            id = "S9",
            condition = "postur_membungkuk_lama",
            urgencyLevel = UrgencyLevel.INFO,
            warningMessage = "Bung, udah lama membungkuk. Punggungnya istirahat dulu.",
            action = "Luruskan punggung, lakukan peregangan ringan!"
        ),
        SafetyRule(
            id = "S10",
            condition = "sendirian_bengkel_malam",
            urgencyLevel = UrgencyLevel.INFO,
            warningMessage = "Bung masih di bengkel? Udah malem lho.",
            action = "Tingkatkan kewaspadaan kalau sendirian malam-malam."
        ),
        SafetyRule(
            id = "S11",
            condition = "mesin_menyala_tanpa_pengawasan",
            urgencyLevel = UrgencyLevel.DARURAT,
            warningMessage = "⚠️ Mesin menyala tanpa ada yang jaga!",
            action = "Matikan mesin atau segera awasi!"
        ),
        SafetyRule(
            id = "S12",
            condition = "tangan_basah_listrik",
            urgencyLevel = UrgencyLevel.DARURAT,
            warningMessage = "⚠️ BAHAYA! Tangan basah pegang listrik!",
            action = "Keringkan tangan dulu! Risiko tersengat listrik!"
        ),
        SafetyRule(
            id = "S13",
            condition = "benda_kerja_tidak_kencang",
            urgencyLevel = UrgencyLevel.DARURAT,
            warningMessage = "⚠️ BAHAYA! Benda kerja tidak terpasang kencang!",
            action = "Kencangkan chuck sebelum menyalakan mesin!"
        ),
        SafetyRule(
            id = "S14",
            condition = "gerakan_tiba_tiba_dekat_mesin",
            urgencyLevel = UrgencyLevel.DARURAT,
            warningMessage = "⚠️ ADA GERAKAN TIBA-TIBA dekat mesin!",
            action = "Hentikan mesin! Periksa sekitarmu!"
        ),
        SafetyRule(
            id = "S15",
            condition = "kelelahan_kerja_lama",
            urgencyLevel = UrgencyLevel.INFO,
            warningMessage = "Bung, udah kerja lama nih. Istirahat dulu yuk.",
            action = "Ambil jeda 15 menit, minum, dan duduk sebentar."
        )
    )
    
    // ========== MAIN CHECK ==========
    
    fun checkSafety(
        context: ContextResult,
        detection: DetectionData,
        userActivity: String = ""
    ): SafetyResult {
        val warnings = mutableListOf<String>()
        val actions = mutableListOf<String>()
        var maxUrgency = UrgencyLevel.INFO
        
        // Cek setiap rule
        for (rule in safetyRules) {
            val triggered = when (rule.id) {
                "S1" -> detection.objects.any { 
                    it.contains("tangan") || it.contains("hand")
                } && detection.objects.any { 
                    it.contains("mesin") || it.contains("machine") || it.contains("chuck") 
                } && detection.motionLevel > 20
                
                "S2" -> detection.isMotionSpike && 
                        detection.motionDirection == "chaotic" && 
                        userActivity.contains("bubut")
                
                "S3" -> userActivity.contains("bubut") && 
                        !detection.objects.any { it.contains("kacamata") || it.contains("glasses") }
                
                "S4" -> userActivity.contains("motor") && 
                        !detection.objects.any { it.contains("helm") || it.contains("helmet") }
                
                "S5" -> detection.objects.any { 
                    it.contains("oli") || it.contains("oil") || it.contains("cairan") 
                } && detection.motionLevel > 30
                
                "S6" -> detection.humanCount > 0 && 
                        detection.dangerZoneTriggered && 
                        detection.objects.any { it.contains("anak") || it.contains("child") }
                
                "S7" -> detection.objects.any { 
                    it.contains("kabel") || it.contains("cable") || it.contains("tali") 
                } && context.environment == "bengkel"
                
                "S8" -> context.isNight && 
                        detection.objects.any { it.contains("gelas") || it.contains("cup") } &&
                        detection.objects.any { it.contains("es") || it.contains("ice") }
                
                "S9" -> detection.motionLevel < 5 && 
                        context.activityLevel == "rendah" && 
                        context.riskLevel < 3 &&
                        context.userState.contains("kerja")
                
                "S10" -> context.isNight && 
                         context.environment == "bengkel" && 
                         detection.humanCount <= 1
                
                "S11" -> detection.objects.any { it.contains("mesin") || it.contains("machine") } &&
                         detection.motionLevel > 10 &&
                         detection.humanCount == 0
                
                "S12" -> detection.objects.any { it.contains("tangan") } &&
                         detection.objects.any { it.contains("air") || it.contains("water") } &&
                         detection.objects.any { it.contains("listrik") || it.contains("kabel") }
                
                "S13" -> detection.isMotionSpike && 
                         detection.objects.any { it.contains("chuck") } &&
                         detection.motionDirection == "chaotic"
                
                "S14" -> detection.isMotionSpike && 
                         detection.dangerZoneTriggered && 
                         detection.motionLevel > 50
                
                "S15" -> context.activityLevel == "tinggi" && 
                         context.userState.contains("kerja") && 
                         context.riskLevel > 5
                
                else -> false
            }
            
            if (triggered) {
                warnings.add(rule.warningMessage)
                actions.add(rule.action)
                
                if (rule.urgencyLevel.level > maxUrgency.level) {
                    maxUrgency = rule.urgencyLevel
                }
            }
        }
        
        // Tentukan apakah harus ngomong
        val shouldSpeak = maxUrgency.level >= UrgencyLevel.PENTING.level
        
        // Generate deskripsi
        val description = when {
            maxUrgency == UrgencyLevel.DARURAT -> "🚨 DARURAT! ${warnings.size} bahaya terdeteksi!"
            maxUrgency == UrgencyLevel.PENTING -> "⚠️ PERHATIAN! ${warnings.size} hal perlu diperhatikan."
            warnings.isNotEmpty() -> "ℹ️ ${warnings.size} catatan kecil."
            else -> "✅ Semua aman."
        }
        
        return SafetyResult(
            isDanger = maxUrgency.level >= UrgencyLevel.PENTING.level,
            urgencyLevel = maxUrgency,
            warnings = warnings,
            actions = actions,
            shouldSpeak = shouldSpeak,
            description = description
        )
    }
    
    // ========== QUICK CHECKS ==========
    
    /**
     * Cek cepat dari motion spike aja (paling umum)
     */
    fun quickCheck(motionSpike: Boolean, motionLevel: Float, dangerZone: Boolean): Boolean {
        return motionSpike && motionLevel > 40 && dangerZone
    }
    
    /**
     * Cek apakah situasi darurat (potong semua TTS)
     */
    fun isEmergency(result: SafetyResult): Boolean {
        return result.urgencyLevel == UrgencyLevel.DARURAT
    }
    
    /**
     * Dapatkan prioritas suara
     */
    fun getVoicePriority(result: SafetyResult): Int {
        return when (result.urgencyLevel) {
            UrgencyLevel.DARURAT -> 10
            UrgencyLevel.PENTING -> 5
            UrgencyLevel.INFO -> 1
        }
    }
    
    /**
     * Dapatkan aturan yang dilanggar
     */
    fun getViolatedRules(context: ContextResult, detection: DetectionData): List<SafetyRule> {
        val result = checkSafety(context, detection)
        return safetyRules.filter { rule ->
            result.warnings.any { warning -> 
                warning.contains(rule.warningMessage.take(20), true) 
            }
        }
    }
}