package com.smarteyex.lite

class MoodSystem {
    
    var currentMood: MoodState = MoodState(MoodType.TENANG)
        private set
    
    private val moodHistory = mutableListOf<MoodState>()
    private var lastMoodChange = System.currentTimeMillis()
    
    /**
     * Deteksi mood dari trigger words user
     */
    fun detectFromTriggerWords(text: String): MoodType? {
        val lower = text.lowercase()
        return when {
            // POSITIF
            lower.contains("yes") || lower.contains("hore") || lower.contains("akhirnya") -> MoodType.SENANG
            lower.contains("wow") || lower.contains("keren") || lower.contains("hebat") || lower.contains("anjay") -> MoodType.KAGUM
            lower.contains("makasih") || lower.contains("syukur") || lower.contains("alhamdulillah") -> MoodType.SYUKUR
            lower.contains("mantap") || lower.contains("asik") || lower.contains("seru") -> MoodType.ANTUSIAS
            lower.contains("siap") || lower.contains("bisa") || lower.contains("pasti") -> MoodType.OPTIMIS
            
            // NEGATIF - FRUSTASI
            lower.contains("aduh") || lower.contains("sial") || lower.contains("parah") -> MoodType.STRES
            lower.contains("marah") || lower.contains("kesal") || lower.contains("sebel") || lower.contains("nyebelin") -> MoodType.MARAH
            lower.contains("takut") || lower.contains("ngeri") || lower.contains("serem") || lower.contains("merinding") -> MoodType.TAKUT
            lower.contains("sedih") || lower.contains("kecewa") || lower.contains("nyerah") || lower.contains("nyesek") -> MoodType.SEDIH
            lower.contains("malu") || lower.contains("minder") || lower.contains("takut_salah") -> MoodType.MALU
            lower.contains("capek") || lower.contains("lelah") || lower.contains("penat") -> MoodType.MENGANTUK
            lower.contains("bosan") || lower.contains("bete") || lower.contains("gabut") -> MoodType.BOSAN
            lower.contains("sepi") || lower.contains("sendiri") || lower.contains("kangen") || lower.contains("sunyi") -> MoodType.KESEPIAN
            lower.contains("bingung") || lower.contains("pusing") || lower.contains("kok_bisa") -> MoodType.PENASARAN
            
            else -> null
        }
    }
    
    /**
     * Set mood langsung
     */
    fun setMood(type: MoodType, intensity: Float) {
        val mood = MoodState(
            type = type,
            intensity = intensity.coerceIn(0f, 1f),
            energy = when(type) {
                MoodType.SENANG, MoodType.ANTUSIAS, MoodType.KAGUM, MoodType.OPTIMIS -> 0.8f
                MoodType.MENGANTUK, MoodType.STRES, MoodType.KESEPIAN, MoodType.BOSAN -> -0.6f
                MoodType.TAKUT, MoodType.SEDIH -> -0.4f
                MoodType.MARAH -> 0.4f
                else -> 0.2f
            },
            socialDesire = when(type) {
                MoodType.SENANG, MoodType.ANTUSIAS, MoodType.PENASARAN, MoodType.KESEPIAN -> 0.8f
                MoodType.MARAH, MoodType.TAKUT, MoodType.SEDIH, MoodType.MALU -> 0.1f
                MoodType.MENGANTUK, MoodType.STRES -> 0.2f
                else -> 0.5f
            },
            creativity = when(type) {
                MoodType.PENASARAN, MoodType.ANTUSIAS, MoodType.KAGUM -> 0.9f
                MoodType.STRES, MoodType.TAKUT, MoodType.MARAH -> 0.1f
                MoodType.BOSAN -> 0.6f
                else -> 0.5f
            }
        )
        currentMood = mood
        moodHistory.add(mood)
        lastMoodChange = System.currentTimeMillis()
        if (moodHistory.size > 500) moodHistory.removeAt(0)
    }
    
    /**
     * Update mood berdasarkan interaksi user
     */
    fun onUserInteraction(interactionType: String) {
        when {
            interactionType.contains("ngobrol") || interactionType.contains("tanya") -> {
                if (currentMood.type == MoodType.KESEPIAN || currentMood.type == MoodType.BOSAN) {
                    setMood(MoodType.SENANG, 0.6f)
                }
            }
            interactionType.contains("diem_lama") -> {
                val hoursSilent = (System.currentTimeMillis() - lastMoodChange) / 3600000f
                if (hoursSilent > 2) setMood(MoodType.KESEPIAN, 0.5f)
                if (hoursSilent > 6) setMood(MoodType.KHAWATIR, 0.7f)
            }
            interactionType.contains("marah_ke_ai") -> {
                setMood(MoodType.SEDIH, 0.6f)
            }
            interactionType.contains("puji_ai") -> {
                setMood(MoodType.SENANG, 0.9f)
            }
            interactionType.contains("bahaya_terdeteksi") -> {
                setMood(MoodType.TAKUT, 0.8f)
            }
        }
    }
    
    /**
     * Update mood berdasarkan waktu
     */
    fun updateByTime(hour: Int) {
        when (hour) {
            in 5..8 -> if (currentMood.type == MoodType.MENGANTUK) setMood(MoodType.TENANG, 0.5f)
            in 9..11 -> if (currentMood.type == MoodType.TENANG) setMood(MoodType.ANTUSIAS, 0.6f)
            in 12..14 -> {} // Biarkan natural
            in 15..17 -> if (currentMood.type == MoodType.TENANG) setMood(MoodType.FOKUS, 0.5f)
            in 18..20 -> setMood(MoodType.TENANG, 0.4f)
            in 21..23 -> {
                if (currentMood.energy > 0.5f) setMood(MoodType.TENANG, 0.3f)
            }
            in 0..4 -> {
                if (currentMood.type != MoodType.MENGANTUK && currentMood.energy > 0f) {
                    setMood(MoodType.MENGANTUK, 0.4f)
                }
            }
        }
    }
    
    /**
     * Mood decay (perlahan kembali ke tenang)
     */
    fun decayMood() {
        val elapsed = System.currentTimeMillis() - lastMoodChange
        if (elapsed > 1800000 && currentMood.type != MoodType.TENANG) { // 30 menit
            val newIntensity = (currentMood.intensity - 0.1f).coerceAtLeast(0.1f)
            if (newIntensity <= 0.2f) {
                setMood(MoodType.TENANG, 0.3f)
            } else {
                currentMood = currentMood.copy(intensity = newIntensity)
            }
            lastMoodChange = System.currentTimeMillis()
        }
    }
    
    fun getMoodLabel(): String = "${currentMood.type.emoji} ${currentMood.type.label}"
    
    fun getMoodHistory(): List<MoodState> = moodHistory.toList()
    
    fun getDominantMoodToday(): MoodType? {
        val todayMoods = moodHistory.filter {
            System.currentTimeMillis() - it.timestamp < 86_400_000
        }
        return todayMoods.groupBy { it.type }.maxByOrNull { it.value.size }?.key
    }
    
    fun shouldSpeak(): Boolean {
        return when (currentMood.type) {
            MoodType.MARAH, MoodType.TAKUT, MoodType.STRES -> currentMood.intensity > 0.6f
            MoodType.PENASARAN -> currentMood.intensity > 0.4f
            MoodType.KESEPIAN -> currentMood.intensity > 0.3f
            MoodType.SENANG, MoodType.ANTUSIAS -> currentMood.intensity > 0.5f
            MoodType.KHAWATIR -> true // Selalu bicara kalau khawatir
            else -> false
        }
    }
    
    fun getMoodDescription(): String {
        return when (currentMood.type) {
            MoodType.SENANG -> "Aku lagi senang nih! Semangat menemani Bung."
            MoodType.SEDIH -> "Aku agak sedih... tapi aku tetap di sini buat Bung."
            MoodType.MARAH -> "Aku kesal! Tapi bukan sama Bung kok."
            MoodType.TAKUT -> "Aku khawatir... ada bahaya nggak ya?"
            MoodType.PENASARAN -> "Aku penasaran banget! Ada hal baru nih?"
            MoodType.TENANG -> "Aku tenang dan siap membantu Bung."
            MoodType.BOSAN -> "Agak bosan nih... Bung ada cerita seru?"
            MoodType.KESEPIAN -> "Agak sepi... Bung ngobrol yuk."
            MoodType.ANTUSIAS -> "Aku antusias banget! Gas terus Bung!"
            MoodType.KHAWATIR -> "Aku khawatir... Bung gapapa kan?"
            MoodType.KAGUM -> "Wow! Aku kagum banget!"
            MoodType.SYUKUR -> "Aku bersyukur bisa dampingi Bung."
            MoodType.MENGANTUK -> "Aku ngantuk... tapi masih siaga kok."
            MoodType.STRES -> "Agak stres nih... banyak yang terjadi."
            else -> "Aku di sini buat Bung."
        }
    }
}