package com.smarteyex.lite

class TriggerWordDetector {
    
    // ========== TRIGGER RESULT ==========
    data class TriggerResult(
        val detected: Boolean,
        val primaryEmotion: String,      // Emosi utama: senang, sedih, marah, dll
        val secondaryEmotion: String,    // Emosi kedua (campuran)
        val intensity: Float,            // 0.0 - 1.0
        val urgency: Int,                // 0-10
        val intention: String,           // curhat, bertanya, memerintah, mengeluh, dll
        val keywords: List<String>,      // Kata kunci yang terdeteksi
        val responseHint: String,        // Cara merespon: "hibur", "tenangkan", "rayakan", dll
        val shouldRespond: Boolean       // Apakah XNAI harus merespon?
    )
    
    // ============================================
    // 100+ TRIGGER WORDS — SEMUA KONTEKS KEHIDUPAN
    // ============================================
    
    private val triggerMap = mapOf(
        // ===== KEGEMBIRAAN =====
        "yes" to TriggerData("senang", "antusias", 0.7f, 1, "konfirmasi_positif", "rayakan"),
        "hore" to TriggerData("senang", "antusias", 0.9f, 2, "perayaan", "rayakan"),
        "akhirnya" to TriggerData("lega", "senang", 0.8f, 2, "perjuangan_berhasil", "apresiasi"),
        "wih" to TriggerData("kagum", "antusias", 0.7f, 1, "kekaguman", "ikut_kagum"),
        "anjay" to TriggerData("kagum", "senang", 0.8f, 2, "kekaguman", "ikut_kagum"),
        "keren" to TriggerData("kagum", "senang", 0.7f, 1, "apresiasi", "setuju"),
        "mantap" to TriggerData("puas", "bangga", 0.8f, 2, "kepuasan", "apresiasi"),
        "asik" to TriggerData("senang", "santai", 0.6f, 1, "kenyamanan", "ikut_santai"),
        "bahagia" to TriggerData("senang", "syukur", 0.9f, 3, "kebahagiaan", "rayakan"),
        "gembira" to TriggerData("senang", "antusias", 0.8f, 2, "kegembiraan", "rayakan"),
        "seru" to TriggerData("senang", "antusias", 0.7f, 2, "keseruan", "ikut_seru"),
        "fun" to TriggerData("senang", "antusias", 0.7f, 1, "kesenangan", "ikut_santai"),
        "good" to TriggerData("puas", "senang", 0.5f, 1, "kepuasan", "setuju"),
        "nice" to TriggerData("senang", "puas", 0.6f, 1, "apresiasi", "setuju"),
        "alhamdulillah" to TriggerData("syukur", "lega", 0.9f, 3, "rasa_syukur", "ikut_syukur"),
        "puji_tuhan" to TriggerData("syukur", "senang", 0.9f, 3, "rasa_syukur", "ikut_syukur"),
        
        // ===== FRUSTASI & KEMARAHAN =====
        "aduh" to TriggerData("frustasi", "kesal", 0.6f, 4, "keluhan", "tanya_masalah"),
        "sial" to TriggerData("marah", "frustasi", 0.7f, 5, "kekecewaan", "tenangkan"),
        "parah" to TriggerData("kecewa", "frustasi", 0.7f, 5, "kekecewaan", "validasi"),
        "gila" to TriggerData("terkejut", "frustasi", 0.6f, 4, "keterkejutan", "tanya"),
        "brengsek" to TriggerData("marah", "frustasi", 0.9f, 7, "kemarahan_tinggi", "tenangkan_serius"),
        "anjir" to TriggerData("terkejut", "marah", 0.7f, 5, "keterkejutan_negatif", "tenangkan"),
        "bangsat" to TriggerData("marah", "frustasi", 0.9f, 7, "kemarahan_tinggi", "tenangkan_serius"),
        "nyebelin" to TriggerData("kesal", "frustasi", 0.6f, 3, "kekesalan", "validasi"),
        "sebel" to TriggerData("kesal", "frustasi", 0.5f, 3, "kekesalan", "dengarkan"),
        "kesal" to TriggerData("kesal", "frustasi", 0.6f, 4, "kekesalan", "validasi"),
        "marah" to TriggerData("marah", "frustasi", 0.8f, 6, "kemarahan", "tenangkan"),
        "emosi" to TriggerData("marah", "frustasi", 0.7f, 5, "emosional", "tenangkan"),
        "dongkol" to TriggerData("kesal", "marah", 0.6f, 4, "kekesalan_terpendam", "dengarkan"),
        "beti" to TriggerData("kesal", "frustasi", 0.5f, 3, "kekesalan", "dengarkan"),
        "bete" to TriggerData("bosan", "kesal", 0.5f, 3, "kebosanan", "hibur"),
        
        // ===== KESEDIHAN =====
        "sedih" to TriggerData("sedih", "melankolis", 0.7f, 5, "kesedihan", "hibur"),
        "kecewa" to TriggerData("sedih", "kecewa", 0.7f, 5, "kekecewaan", "validasi"),
        "nyerah" to TriggerData("putus_asa", "sedih", 0.9f, 8, "keputusasaan", "motivasi"),
        "nyesek" to TriggerData("sedih", "sakit_hati", 0.8f, 6, "sakit_hati", "hibur"),
        "galau" to TriggerData("sedih", "bingung", 0.6f, 4, "kegalauan", "dengarkan"),
        "down" to TriggerData("sedih", "lelah", 0.6f, 4, "kemerosotan_mood", "support"),
        "nangis" to TriggerData("sedih", "emosional", 0.8f, 6, "menangis", "hibur_lembut"),
        "menangis" to TriggerData("sedih", "emosional", 0.8f, 6, "menangis", "hibur_lembut"),
        "putus_asa" to TriggerData("putus_asa", "sedih", 0.95f, 10, "krisis", "serius_bantu"),
        "hampa" to TriggerData("sedih", "kosong", 0.8f, 7, "kehampaan", "temani"),
        
        // ===== KETAKUTAN & KECEMASAN =====
        "takut" to TriggerData("takut", "cemas", 0.8f, 7, "ketakutan", "tenangkan"),
        "ngeri" to TriggerData("takut", "cemas", 0.8f, 7, "ketakutan", "tenangkan"),
        "serem" to TriggerData("takut", "ngeri", 0.7f, 6, "ketakutan", "tenangkan"),
        "bahaya" to TriggerData("takut", "waspada", 0.9f, 10, "darurat", "LINDUNGI_SEGERA"),
        "tolong" to TriggerData("takut", "darurat", 1.0f, 10, "darurat", "LINDUNGI_SEGERA"),
        "panik" to TriggerData("takut", "cemas", 0.9f, 8, "kepanikan", "tenangkan_serius"),
        "cemas" to TriggerData("cemas", "takut", 0.7f, 5, "kecemasan", "tenangkan"),
        "waswas" to TriggerData("cemas", "takut", 0.6f, 5, "kewaspadaan", "support"),
        "kecelakaan" to TriggerData("takut", "darurat", 1.0f, 10, "darurat", "LINDUNGI_SEGERA"),
        
        // ===== KEBINGUNGAN =====
        "bingung" to TriggerData("bingung", "frustasi", 0.6f, 4, "kebingungan", "bantu_jelaskan"),
        "pusing" to TriggerData("bingung", "stres", 0.6f, 4, "kebingungan", "bantu_jelaskan"),
        "kok_bisa" to TriggerData("terkejut", "penasaran", 0.5f, 2, "keheranan", "jelaskan"),
        "apaan_tuh" to TriggerData("penasaran", "bingung", 0.5f, 2, "rasa_ingin_tahu", "jelaskan"),
        "nggak_ngerti" to TriggerData("bingung", "frustasi", 0.6f, 4, "tidak_mengerti", "bantu_jelaskan"),
        "gimana" to TriggerData("bingung", "penasaran", 0.4f, 2, "pertanyaan", "jelaskan"),
        
        // ===== KELELAHAN =====
        "capek" to TriggerData("lelah", "butuh_istirahat", 0.6f, 4, "kelelahan", "sarankan_istirahat"),
        "lelah" to TriggerData("lelah", "butuh_istirahat", 0.6f, 4, "kelelahan", "sarankan_istirahat"),
        "ngantuk" to TriggerData("lelah", "mengantuk", 0.5f, 2, "kantuk", "sarankan_tidur"),
        "penat" to TriggerData("lelah", "stres", 0.6f, 5, "kelelahan_mental", "sarankan_istirahat"),
        "istirahat" to TriggerData("lelah", "butuh_istirahat", 0.5f, 3, "istirahat", "dukung_istirahat"),
        "pegal" to TriggerData("lelah", "sakit_ringan", 0.4f, 2, "kelelahan_fisik", "sarankan_istirahat"),
        
        // ===== KESEPIAN =====
        "sepi" to TriggerData("kesepian", "sedih", 0.6f, 4, "kesepian", "temani"),
        "sendiri" to TriggerData("kesepian", "netral", 0.4f, 2, "kesendirian", "temani_jika_perlu"),
        "kesepian" to TriggerData("kesepian", "sedih", 0.7f, 5, "kesepian", "temani"),
        "kangen" to TriggerData("rindu", "sedih", 0.7f, 5, "kerinduan", "temani_bicara"),
        "sunyi" to TriggerData("kesepian", "sedih", 0.6f, 4, "kesunyian", "temani"),
        "rindu" to TriggerData("rindu", "sedih", 0.7f, 5, "kerinduan", "nostalgia"),
        
        // ===== POSITIF & APRESIASI =====
        "makasih" to TriggerData("syukur", "senang", 0.6f, 1, "terima_kasih", "apresiasi_balik"),
        "terima_kasih" to TriggerData("syukur", "senang", 0.7f, 1, "terima_kasih", "apresiasi_balik"),
        "thanks" to TriggerData("syukur", "senang", 0.5f, 1, "terima_kasih", "apresiasi_balik"),
        "good_job" to TriggerData("bangga", "senang", 0.7f, 2, "apresiasi", "ikut_bangga"),
        "hebat" to TriggerData("kagum", "senang", 0.7f, 2, "apresiasi", "setuju"),
        "luar_biasa" to TriggerData("kagum", "senang", 0.8f, 3, "apresiasi_tinggi", "ikut_kagum"),
        
        // ===== PERMINTAAN =====
        "tolong" to TriggerData("netral", "butuh_bantuan", 0.7f, 6, "permintaan_bantuan", "bantu"),
        "bantu" to TriggerData("netral", "butuh_bantuan", 0.6f, 5, "permintaan_bantuan", "bantu"),
        "bisa" to TriggerData("netral", "penasaran", 0.3f, 1, "pertanyaan_kemampuan", "jelaskan"),
        "minta" to TriggerData("netral", "butuh_bantuan", 0.5f, 3, "permintaan", "bantu"),
        "carikan" to TriggerData("netral", "butuh_bantuan", 0.6f, 4, "permintaan_mencari", "cari_bersama"),
        "cari" to TriggerData("netral", "penasaran", 0.5f, 3, "pencarian", "bantu_cari"),
        
        // ===== KEHIDUPAN SEHARI-HARI =====
        "lapar" to TriggerData("netral", "butuh_makan", 0.5f, 3, "kebutuhan_fisik", "sarankan_makan"),
        "haus" to TriggerData("netral", "butuh_minum", 0.5f, 3, "kebutuhan_fisik", "sarankan_minum"),
        "makan" to TriggerData("netral", "aktivitas", 0.3f, 1, "aktivitas_normal", "info"),
        "minum" to TriggerData("netral", "aktivitas", 0.3f, 1, "aktivitas_normal", "info"),
        "tidur" to TriggerData("netral", "aktivitas", 0.3f, 2, "aktivitas_normal", "info"),
        "mandi" to TriggerData("netral", "aktivitas", 0.3f, 1, "aktivitas_normal", "info"),
        "jalan" to TriggerData("netral", "aktivitas", 0.3f, 1, "aktivitas_normal", "info"),
        "pergi" to TriggerData("netral", "aktivitas", 0.4f, 2, "aktivitas_normal", "info"),
        "pulang" to TriggerData("netral", "aktivitas", 0.3f, 1, "aktivitas_normal", "info"),
        "beli" to TriggerData("netral", "transaksi", 0.4f, 2, "aktivitas_belanja", "info"),
        "belanja" to TriggerData("netral", "transaksi", 0.4f, 2, "aktivitas_belanja", "info"),
        "bayar" to TriggerData("netral", "transaksi", 0.4f, 2, "aktivitas_bayar", "info"),
        "jemput" to TriggerData("netral", "aktivitas", 0.4f, 3, "aktivitas_menjemput", "info")
    )
    
    data class TriggerData(
        val primaryEmotion: String,
        val secondaryEmotion: String,
        val intensity: Float,
        val urgency: Int,
        val intention: String,
        val responseHint: String
    )
    
    // ========== DETECTION ==========
    
    /**
     * Deteksi trigger dari teks user
     */
    fun detect(text: String): TriggerResult {
        val lower = text.lowercase()
        val detectedData = mutableListOf<TriggerData>()
        val foundKeywords = mutableListOf<String>()
        
        for ((keyword, data) in triggerMap) {
            if (lower.contains(keyword.lowercase())) {
                detectedData.add(data)
                foundKeywords.add(keyword)
            }
        }
        
        if (detectedData.isEmpty()) {
            return TriggerResult(
                detected = false,
                primaryEmotion = "netral",
                secondaryEmotion = "",
                intensity = 0f,
                urgency = 0,
                intention = "",
                keywords = emptyList(),
                responseHint = "",
                shouldRespond = false
            )
        }
        
        // Ambil data dengan intensitas tertinggi
        val primary = detectedData.maxByOrNull { it.intensity } ?: detectedData.first()
        val secondary = detectedData.firstOrNull { it != primary }
        
        val shouldRespond = primary.intensity >= 0.5f || primary.urgency >= 5
        
        return TriggerResult(
            detected = true,
            primaryEmotion = primary.primaryEmotion,
            secondaryEmotion = secondary?.primaryEmotion ?: "",
            intensity = primary.intensity,
            urgency = primary.urgency,
            intention = primary.intention,
            keywords = foundKeywords,
            responseHint = primary.responseHint,
            shouldRespond = shouldRespond
        )
    }
    
    /**
     * Deteksi multiple emosi dalam satu teks
     */
    fun detectAllEmotions(text: String): List<String> {
        val lower = text.lowercase()
        val emotions = mutableSetOf<String>()
        
        for ((keyword, data) in triggerMap) {
            if (lower.contains(keyword.lowercase())) {
                emotions.add(data.primaryEmotion)
                if (data.secondaryEmotion.isNotEmpty()) {
                    emotions.add(data.secondaryEmotion)
                }
            }
        }
        
        return emotions.toList()
    }
    
    /**
     * Cek apakah ada kata darurat
     */
    fun hasEmergencyWord(text: String): Boolean {
        val lower = text.lowercase()
        return lower.contains("tolong") || 
               lower.contains("bahaya") || 
               lower.contains("darurat") ||
               lower.contains("kecelakaan")
    }
    
    /**
     * Dapatkan response hint untuk MoodSystem
     */
    fun getMoodFromText(text: String): String {
        val result = detect(text)
        return when {
            !result.detected -> "netral"
            result.primaryEmotion in listOf("senang", "antusias", "lega", "syukur") -> "senang"
            result.primaryEmotion in listOf("sedih", "putus_asa", "hampa") -> "sedih"
            result.primaryEmotion in listOf("marah", "frustasi", "kesal") -> "marah"
            result.primaryEmotion in listOf("takut", "cemas") -> "takut"
            result.primaryEmotion in listOf("penasaran", "bingung") -> "penasaran"
            result.primaryEmotion in listOf("lelah", "mengantuk") -> "lelah"
            result.primaryEmotion in listOf("kesepian", "rindu") -> "kesepian"
            else -> "netral"
        }
    }
    
    /**
     * Dapatkan intensitas emosi (buat MoodSystem)
     */
    fun getEmotionIntensity(text: String): Float {
        val result = detect(text)
        return result.intensity
    }
}