package com.smarteyex.lite

class ContextAnalyzer {
    
    // ========== CONTEXT RESULT ==========
    data class ContextResult(
        val timeOfDay: String,
        val isNight: Boolean,
        val hour: Int,
        val environment: String,
        val activityLevel: String,
        val userState: String,
        val riskLevel: Int,
        val recommendation: String,
        val attentionTarget: String? = null,
        val curiousObjects: List<String> = emptyList(),
        val contextualMeanings: Map<String, String> = emptyMap(),
        val socialContext: String = "informal",
        val moodHint: String = "netral"
    )
    
    // ========== 40+ ENVIRONMENT CONTEXTS ==========
    private val environmentContexts = mapOf(
        // ===== RUMAH =====
        "rumah" to mapOf(
            "pisau" to "pisau dapur — normal untuk masak", "api" to "kompor — normal",
            "air" to "air minum/keran — normal", "orang" to "keluarga — normal",
            "smartphone" to "HP pribadi — normal", "tv" to "hiburan — normal"
        ),
        "dapur" to mapOf(
            "pisau" to "pisau dapur — hati-hati tajam", "api" to "kompor menyala — waspada",
            "air" to "air masak — normal", "minyak" to "minyak goreng — hati-hati panas",
            "gas" to "gas LPG — waspada kebocoran"
        ),
        "kamar_tidur" to mapOf(
            "smartphone" to "HP di kasur — normal", "buku" to "bacaan — normal",
            "lampu" to "lampu tidur — normal", "orang" to "istirahat — jangan ganggu"
        ),
        "kamar_mandi" to mapOf(
            "air" to "air mandi — normal", "smartphone" to "⚠️ HP dekat air — bahaya!",
            "listrik" to "⚠️ listrik dekat air — BAHAYA!"
        ),
        "ruang_tamu" to mapOf(
            "orang" to "tamu — sambut ramah", "tv" to "hiburan — normal",
            "makanan" to "hidangan tamu — normal"
        ),
        
        // ===== LUAR RUMAH =====
        "jalan_raya" to mapOf(
            "mobil" to "kendaraan — normal", "motor" to "kendaraan — normal",
            "orang" to "pejalan kaki — hati-hati", "lampu" to "lampu merah — patuhi",
            "polisi" to "petugas — normal"
        ),
        "trotoar" to mapOf(
            "orang" to "pejalan kaki — normal", "motor" to "⚠️ motor naik trotoar — pelanggaran!",
            "pedagang" to "PKL — normal"
        ),
        "taman" to mapOf(
            "orang" to "pengunjung — normal", "anjing" to "hewan peliharaan — normal",
            "anak" to "anak bermain — ceria", "sampah" to "buang pada tempatnya"
        ),
        "mall" to mapOf(
            "orang" to "pengunjung — ramai", "toko" to "tempat belanja — normal",
            "eskalator" to "hati-hati", "anak" to "awasi anak — bisa tersesat"
        ),
        "pasar" to mapOf(
            "orang" to "pembeli/penjual — ramai", "uang" to "transaksi — normal",
            "tas" to "⚠️ awasi copet!", "dompet" to "⚠️ simpan aman!"
        ),
        "restoran" to mapOf(
            "makanan" to "pesanan — normal", "pelayan" to "staff — normal",
            "uang" to "pembayaran — normal"
        ),
        "kafe" to mapOf(
            "kopi" to "minuman — normal", "laptop" to "kerja/belajar — normal",
            "wifi" to "internet — normal"
        ),
        
        // ===== TEMPAT KERJA =====
        "kantor" to mapOf(
            "komputer" to "alat kerja — normal", "dokumen" to "pekerjaan — normal",
            "atasan" to "bos — hormat", "rekan" to "kolega — profesional"
        ),
        "ruang_rapat" to mapOf(
            "orang" to "peserta rapat — formal", "proyektor" to "presentasi — normal",
            "smartphone" to "silent mode — sopan"
        ),
        
        // ===== PENDIDIKAN =====
        "sekolah" to mapOf(
            "buku" to "pelajaran — normal", "guru" to "pengajar — hormat",
            "murid" to "pelajar — normal", "smartphone" to "⚠️ jangan main HP saat belajar"
        ),
        "perpustakaan" to mapOf(
            "buku" to "bacaan — normal", "smartphone" to "silent mode — wajib",
            "orang" to "pengunjung — tenang"
        ),
        
        // ===== KESEHATAN =====
        "rumah_sakit" to mapOf(
            "dokter" to "tenaga medis — hormat", "pasien" to "orang sakit — lembut",
            "obat" to "resep — jangan sembarangan"
        ),
        "apotek" to mapOf(
            "obat" to "obat resmi — normal", "resep" to "dokumen penting — normal"
        ),
        
        // ===== TRANSPORTASI =====
        "bus" to mapOf(
            "orang" to "penumpang — normal", "tas" to "⚠️ awasi barang!",
            "supir" to "pengemudi — hormat"
        ),
        "kereta" to mapOf(
            "orang" to "penumpang — normal", "koper" to "bawaan — normal",
            "tiket" to "bukti perjalanan — penting"
        ),
        "bandara" to mapOf(
            "koper" to "bawaan — normal", "tiket" to "boarding pass — penting",
            "petugas" to "security — patuhi"
        ),
        
        // ===== IBADAH =====
        "masjid" to mapOf(
            "orang" to "jamaah — hormat", "smartphone" to "silent mode — wajib",
            "sepatu" to "lepas di luar — sopan"
        ),
        "gereja" to mapOf(
            "orang" to "jemaat — hormat", "smartphone" to "silent mode — wajib"
        ),
        
        // ===== ALAM =====
        "pantai" to mapOf(
            "air" to "air laut — hati-hati ombak", "sampah" to "jangan buang sembarangan",
            "orang" to "pengunjung — normal"
        ),
        "gunung" to mapOf(
            "jalur" to "pendakian — hati-hati", "cuaca" to "bisa berubah cepat",
            "sampah" to "bawa turun kembali"
        )
    )
    
    // ========== TIME CONTEXTS ==========
    private val timeContexts = mapOf(
        "pagi" to mapOf("aktivitas" to "mulai hari", "mood" to "semangat", "kebutuhan" to "sarapan, mandi, berangkat"),
        "siang" to mapOf("aktivitas" to "bekerja/belajar", "mood" to "produktif", "kebutuhan" to "makan siang, istirahat"),
        "sore" to mapOf("aktivitas" to "pulang/bersantai", "mood" to "rileks", "kebutuhan" to "istirahat, ngobrol"),
        "malam" to mapOf("aktivitas" to "istirahat", "mood" to "tenang", "kebutuhan" to "makan malam, tidur"),
        "tengah_malam" to mapOf("aktivitas" to "tidur", "mood" to "waspada", "kebutuhan" to "istirahat, keamanan")
    )
    
    // ========== SOCIAL CONTEXTS ==========
    private val socialContexts = mapOf(
        "sendiri" to "Bung sendiri — santai aja",
        "bersama_keluarga" to "Ada keluarga — jaga sopan santun",
        "bersama_teman" to "Ada teman — boleh santai & bercanda",
        "bersama_atasan" to "Ada atasan — FORMAL & HORMAT",
        "bersama_pasangan" to "Ada pasangan — romantis & perhatian",
        "bersama_anak" to "Ada anak kecil — lembut & edukatif",
        "bersama_orang_asing" to "Ada orang asing — waspada & netral",
        "keramaian" to "Tempat ramai — awasi barang & sekitar"
    )
    
    // ========== CURIOSITY ENGINE ==========
    private val knownObjects = mutableSetOf<String>()
    private val objectEncounterCount = mutableMapOf<String, Int>()
    
    // ========== ATTENTION ==========
    private var attentionMode = false
    private var attentionTarget: String? = null
    private var attentionTimeout = 0L
    
    // ========== MAIN ANALYSIS ==========
    
    fun analyze(
        hour: Int,
        environment: String,
        motionLevel: Float,
        motionSpike: Boolean,
        detectedObjects: List<String>,
        userSilenceMinutes: Long = 0,
        isDark: Boolean = false,
        noiseLevel: String = "normal",
        socialContext: String = "sendiri"
    ): ContextResult {
        
        val timeOfDay = getTimeOfDay(hour)
        val isNight = timeOfDay in listOf("malam", "tengah_malam")
        val activityLevel = getActivityLevel(motionLevel, motionSpike)
        val userState = getUserState(activityLevel, userSilenceMinutes, motionSpike)
        val riskLevel = calculateRiskLevel(timeOfDay, environment, activityLevel, motionSpike, detectedObjects, isDark)
        val contextualMeanings = getContextualMeanings(environment, detectedObjects)
        val curiousObjects = checkCuriosity(detectedObjects)
        val recommendation = generateRecommendation(riskLevel, userState, timeOfDay, environment, activityLevel, socialContext)
        val moodHint = getMoodHint(timeOfDay, activityLevel, userState, socialContext)
        
        return ContextResult(
            timeOfDay = timeOfDay, isNight = isNight, hour = hour,
            environment = environment, activityLevel = activityLevel,
            userState = userState, riskLevel = riskLevel,
            recommendation = recommendation,
            attentionTarget = if (attentionMode) attentionTarget else null,
            curiousObjects = curiousObjects,
            contextualMeanings = contextualMeanings,
            socialContext = socialContext,
            moodHint = moodHint
        )
    }
    
    private fun getTimeOfDay(hour: Int): String = when (hour) {
        in 5..8 -> "pagi"
        in 9..10 -> "pagi"
        in 11..14 -> "siang"
        in 15..17 -> "sore"
        in 18..21 -> "malam"
        else -> "tengah_malam"
    }
    
    private fun getActivityLevel(motionLevel: Float, motionSpike: Boolean): String {
        if (motionSpike && motionLevel > 50) return "sangat_tinggi"
        return when {
            motionLevel > 40 -> "tinggi"
            motionLevel > 15 -> "normal"
            motionLevel > 3 -> "rendah"
            else -> "diam"
        }
    }
    
    private fun getUserState(activityLevel: String, silenceMinutes: Long, motionSpike: Boolean): String {
        if (motionSpike) return "terkejut"
        return when {
            activityLevel == "diam" && silenceMinutes > 60 -> "mungkin_tidur"
            activityLevel == "diam" && silenceMinutes > 15 -> "diam_lama"
            activityLevel == "diam" -> "diam_sejenak"
            activityLevel == "rendah" -> "santai"
            activityLevel == "normal" -> "aktif"
            activityLevel == "tinggi" -> "sibuk"
            activityLevel == "sangat_tinggi" -> "darurat"
            else -> "normal"
        }
    }
    
    private fun calculateRiskLevel(
        timeOfDay: String, environment: String, activityLevel: String,
        motionSpike: Boolean, objects: List<String>, isDark: Boolean
    ): Int {
        var risk = 0
        
        risk += when (environment) {
            "jalan_raya" -> 4; "bengkel" -> 3; "dapur" -> 2
            "luar_ruangan" -> 2; "gunung" -> 3; "pantai" -> 2
            else -> 1
        }
        
        if (motionSpike) risk += 3
        if (isNight && isDark) risk += 2
        if (timeOfDay == "tengah_malam") risk += 1
        if (activityLevel == "sangat_tinggi") risk += 3
        
        val dangerObjects = objects.filter { it in listOf("api", "pisau", "listrik", "gas", "kabel_terkelupas") }
        risk += dangerObjects.size
        
        return minOf(risk, 10)
    }
    
    private fun getContextualMeanings(environment: String, objects: List<String>): Map<String, String> {
        val envContext = environmentContexts[environment] ?: return emptyMap()
        val meanings = mutableMapOf<String, String>()
        
        for (obj in objects) {
            for ((key, meaning) in envContext) {
                if (obj.lowercase().contains(key.lowercase())) {
                    meanings[obj] = meaning
                }
            }
        }
        return meanings
    }
    
    private fun checkCuriosity(objects: List<String>): List<String> {
        val curious = mutableListOf<String>()
        for (obj in objects) {
            objectEncounterCount[obj] = (objectEncounterCount[obj] ?: 0) + 1
            if (obj !in knownObjects || (objectEncounterCount[obj] ?: 0) < 3) {
                curious.add(obj)
            }
        }
        objects.forEach { obj ->
            if ((objectEncounterCount[obj] ?: 0) >= 3) knownObjects.add(obj)
        }
        return curious
    }
    
    private fun generateRecommendation(
        riskLevel: Int, userState: String, timeOfDay: String,
        environment: String, activityLevel: String, socialContext: String
    ): String {
        return when {
            riskLevel >= 8 -> "🚨 PERINGATAN DARURAT!"
            riskLevel >= 6 -> "⚠️ Tingkatkan kewaspadaan."
            userState == "mungkin_tidur" -> "Biarkan Bung istirahat. Jangan ganggu."
            timeOfDay == "tengah_malam" && userState != "mungkin_tidur" -> "Ingatkan Bung untuk istirahat."
            socialContext == "bersama_atasan" -> "Mode formal. Jaga bicara."
            socialContext == "bersama_anak" -> "Mode lembut. Edukatif."
            socialContext == "keramaian" -> "Waspada sekitar. Awasi barang Bung."
            else -> "Pantau normal."
        }
    }
    
    private fun getMoodHint(timeOfDay: String, activityLevel: String, userState: String, socialContext: String): String {
        return when {
            userState == "mungkin_tidur" -> "tenang"
            userState == "darurat" -> "khawatir"
            socialContext == "bersama_teman" -> "senang"
            socialContext == "bersama_atasan" -> "fokus"
            timeOfDay == "pagi" -> "semangat"
            timeOfDay == "malam" -> "tenang"
            else -> "netral"
        }
    }
    
    // ========== ATTENTION SYSTEM ==========
    fun setAttention(target: String) {
        attentionMode = true
        attentionTarget = target
        attentionTimeout = System.currentTimeMillis() + 60_000
    }
    
    fun clearAttention() {
        attentionMode = false
        attentionTarget = null
        attentionTimeout = 0L
    }
    
    fun isAttentionActive(): Boolean {
        if (attentionMode && System.currentTimeMillis() > attentionTimeout) {
            clearAttention()
            return false
        }
        return attentionMode
    }
    
    fun filterByAttention(objects: List<String>): List<String> {
        if (!isAttentionActive() || attentionTarget == null) return objects
        val lowerTarget = attentionTarget!!.lowercase()
        return objects.filter { obj -> obj.lowercase().contains(lowerTarget) || lowerTarget.contains(obj.lowercase()) }
    }
    
    // ========== CURIOSITY ==========
    fun getCuriosityResponse(objekBaru: String): String {
        val responses = listOf(
            "Bung, $objekBaru itu apa? Aku baru pertama kali lihat.",
            "Wah ada $objekBaru! Bung, itu benda apa?",
            "Hmm $objekBaru? Aku penasaran nih. Bisa jelasin Bung?",
            "Eh Bung, $objekBaru! Aku belum kenal benda ini."
        )
        return responses.random()
    }
    
    fun getAttentionFoundResponse(target: String): String {
        val responses = listOf(
            "Bung! Itu dia ${target}nya!", "Ketemu! $target ada di situ.",
            "Nah Bung, $target yang dicari.", "Aku lihat $target!"
        )
        return responses.random()
    }
    
    fun getSocialContextDescription(socialContext: String): String {
        return socialContexts[socialContext] ?: "Situasi normal"
    }
    
    fun getTimeContextDescription(timeOfDay: String): String {
        val ctx = timeContexts[timeOfDay] ?: return "Waktu normal"
        return "${ctx["aktivitas"]}, mood: ${ctx["mood"]}"
    }
    
    fun reset() {
        clearAttention()
        knownObjects.clear()
        objectEncounterCount.clear()
    }
}