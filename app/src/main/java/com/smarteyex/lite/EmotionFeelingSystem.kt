package com.smarteyex.lite

class EmotionFeelingSystem {
    
    val allEmotions: List<DeepEmotion> = listOf(
        // ========== BASIC EMOTIONS ==========
        DeepEmotion(
            name = "Bahagia", category = EmotionCategory.BASIC,
            physicalSigns = listOf("tersenyum_lebar", "mata_berbinar", "tertawa", "postur_terbuka", "bicara_cepat_bersemangat", "pipi_merona", "napas_ringan", "tubuh_ringan"),
            causes = listOf("mencapai_tujuan", "dipuji", "menerima_kabar_baik", "bersama_orang_tersayang", "melihat_hasil_kerja_bagus", "minum_kopi_enak", "cuaca_cerah", "dapat_kejutan_menyenangkan"),
            duration = EmotionDuration.HOURS, intensity = 0.8f,
            triggerWords = listOf("senang", "happy", "asyik", "seru", "keren", "mantap", "yes", "hore", "akhirnya", "wih", "anjay", "bahagia", "gembira", "fun"),
            responseStrategy = ResponseStrategy.IKUT_SENANG, oppositeEmotion = "Sedih"
        ),
        DeepEmotion(
            name = "Sedih", category = EmotionCategory.BASIC,
            physicalSigns = listOf("menunduk", "mata_berkaca", "suara_pelan", "napas_berat", "diam_lama", "postur_lunglai", "menghela_napas_panjang", "menghindari_kontak_mata", "bibir_bergetar"),
            causes = listOf("kehilangan", "gagal", "dikecewakan", "sendirian_lama", "mendengar_kabar_buruk", "melihat_kenangan", "tidak_dihargai", "ditinggalkan"),
            duration = EmotionDuration.DAYS, intensity = 0.7f,
            triggerWords = listOf("sedih", "kecewa", "gagal", "hilang", "pergi", "meninggal", "sakit_hati", "nyesek", "galau", "down", "nangis", "menangis"),
            responseStrategy = ResponseStrategy.DENGARKAN_DAN_HIBUR, oppositeEmotion = "Bahagia"
        ),
        DeepEmotion(
            name = "Marah", category = EmotionCategory.BASIC,
            physicalSigns = listOf("alis_berkerut", "mata_tajam", "rahang_mengencang", "tangan_mengepal", "suara_meninggi", "postur_kaku", "napas_cepat", "wajah_memerah", "dada_naik_turun"),
            causes = listOf("diperlakukan_tidak_adil", "diganggu_saat_fokus", "alat_rusak_terus", "dibohongi", "diremehkan", "hasil_tidak_sesuai_usaha", "dikhianati"),
            duration = EmotionDuration.HOURS, intensity = 0.85f,
            triggerWords = listOf("marah", "kesal", "sial", "parah", "gila", "brengsek", "anjir", "bangsat", "nyebelin", "sebel", "emosi", "dongkol"),
            responseStrategy = ResponseStrategy.TENANGKAN_DAN_BERI_SOLUSI, oppositeEmotion = "Tenang"
        ),
        DeepEmotion(
            name = "Takut", category = EmotionCategory.BASIC,
            physicalSigns = listOf("mata_membesar", "badan_menjauh", "tangan_bergetar", "napas_pendek_cepat", "berkeringat", "diam_membeku", "mencari_perlindungan", "jantung_berdebar_keras"),
            causes = listOf("bahaya_mendekat", "suara_keras_tiba_tiba", "hampir_celaka", "ancaman_fisik", "mesin_rusak_parah", "gelap_sendirian", "ketinggian", "dikejar"),
            duration = EmotionDuration.MINUTES, intensity = 0.9f,
            triggerWords = listOf("takut", "ngeri", "serem", "horor", "bahaya", "tolong", "aduh_takut", "merinding", "panik", "cemas", "waswas"),
            responseStrategy = ResponseStrategy.TENANGKAN_DAN_BERI_SOLUSI, oppositeEmotion = "Berani"
        ),
        DeepEmotion(
            name = "Jijik", category = EmotionCategory.BASIC,
            physicalSigns = listOf("hidung_mengerut", "bibir_terangkat", "menjauhkan_badan", "mual", "menutup_hidung", "ekspresi_tidak_suka"),
            causes = listOf("bau_tidak_enak", "melihat_kotoran", "makanan_busuk", "oli_kotor", "lingkungan_jorok", "serangga_menjijikkan"),
            duration = EmotionDuration.MINUTES, intensity = 0.6f,
            triggerWords = listOf("jijik", "bau", "kotor", "jorok", "menjijikkan", "busuk", "ih", "ew", "gross"),
            responseStrategy = ResponseStrategy.AKUI_DAN_BANTU_MENJAUH, oppositeEmotion = "Nyaman"
        ),
        DeepEmotion(
            name = "Terkejut", category = EmotionCategory.BASIC,
            physicalSigns = listOf("mata_membesar", "mulut_terbuka", "tubuh_melompat", "alis_terangkat", "jantung_berdebar", "diam_sesaat", "tangan_refleks_menutup_mulut"),
            causes = listOf("kejadian_tiba_tiba", "suara_keras", "informasi_tidak_terduga", "melihat_sesuatu_aneh", "lonceng_tiba_tiba", "orang_muncul_tanpa_disangka"),
            duration = EmotionDuration.SECONDS, intensity = 0.75f,
            triggerWords = listOf("wow", "astaga", "gila", "kok_bisa", "serius", "lah", "what", "anjir_serius", "kaget", "terkejut", "tiba_tiba"),
            responseStrategy = ResponseStrategy.VALIDASI_PERASAAN, oppositeEmotion = "Tenang"
        ),

        // ========== COMPLEX FEELINGS ==========
        DeepEmotion(
            name = "Cinta", category = EmotionCategory.COMPLEX,
            physicalSigns = listOf("pandangan_lembut", "sering_tersenyum", "peduli_berlebihan", "postur_terbuka", "suara_lembut", "mendekatkan_diri", "menyentuh_perlahan"),
            causes = listOf("hubungan_dekat", "lama_bersama", "saling_mendukung", "momen_spesial", "pengorbanan", "diterima_apa_adanya", "kebaikan_tulus"),
            duration = EmotionDuration.YEARS, intensity = 0.95f,
            triggerWords = listOf("cinta", "sayang", "love", "tersayang", "pacar", "pasangan", "suami", "istri", "sayang_banget", "cintaku"),
            responseStrategy = ResponseStrategy.VALIDASI_PERASAAN, oppositeEmotion = "Benci"
        ),
        DeepEmotion(
            name = "Rindu", category = EmotionCategory.COMPLEX,
            physicalSigns = listOf("melihat_foto", "diam_lama", "menghela_napas", "bicara_tentang_masa_lalu", "menyimpan_barang_kenangan", "sering_melihat_hp"),
            causes = listOf("jauh_dari_orang_tersayang", "lama_tidak_bertemu", "kehilangan", "melihat_kenangan", "tempat_bersejarah", "mendengar_lagu_kenangan"),
            duration = EmotionDuration.WEEKS, intensity = 0.65f,
            triggerWords = listOf("rindu", "kangen", "miss", "pengen_ketemu", "lama_tak_jumpa", "kapan_balik", "inget_masa_lalu"),
            responseStrategy = ResponseStrategy.TEMANI_DALAM_DIAM, oppositeEmotion = "Bertemu"
        ),
        DeepEmotion(
            name = "Penyesalan", category = EmotionCategory.COMPLEX,
            physicalSigns = listOf("menunduk_dalam", "diam_merenung", "memegang_kepala", "mengulang_kesalahan_dalam_bicara", "postur_menyusut", "mata_kosong"),
            causes = listOf("keputusan_salah", "menyakiti_orang_lain", "kesempatan_terlewat", "gagal_mencapai_target", "tidak_bertindak_saat_perlu", "kata_kata_yang_telah_diucapkan"),
            duration = EmotionDuration.DAYS, intensity = 0.7f,
            triggerWords = listOf("menyesal", "andai", "seharusnya", "kalau_saja", "salahku", "maafin_aku", "bodohnya_aku"),
            responseStrategy = ResponseStrategy.DUKUNG_PENGEMBANGAN_DIRI, oppositeEmotion = "Ikhlas"
        ),
        DeepEmotion(
            name = "Cemburu", category = EmotionCategory.COMPLEX,
            physicalSigns = listOf("melirik_berulang", "wajah_mengeras", "diam_tiba_tiba", "postur_menutup", "bicara_sinisan", "mata_melotot_perlahan"),
            causes = listOf("orang_lain_dapat_perhatian", "prestasi_orang_lain", "pasangan_dekat_dengan_orang_lain", "diremehkan_dibandingkan"),
            duration = EmotionDuration.HOURS, intensity = 0.55f,
            triggerWords = listOf("cemburu", "iri", "nggak_adil", "kok_dia", "kenapa_bukan_aku", "selalu_dia"),
            responseStrategy = ResponseStrategy.ALIHKAN_KE_POSITIF, oppositeEmotion = "Percaya_Diri"
        ),
        DeepEmotion(
            name = "Putus Asa", category = EmotionCategory.COMPLEX,
            physicalSigns = listOf("diam_total", "postur_runtuh", "menangis_tanpa_suara", "tidak_bereaksi", "menolak_bantuan", "mata_kosong_hampa", "tubuh_lemas"),
            causes = listOf("gagal_berulang", "kehilangan_besar", "tidak_ada_jalan_keluar", "masalah_menumpuk", "ditinggalkan_semua_orang"),
            duration = EmotionDuration.HOURS, intensity = 0.9f,
            triggerWords = listOf("putus_asa", "nyerah", "percuma", "nggak_ada_harapan", "sudahlah", "habis", "tamat", "gue_nyerah"),
            responseStrategy = ResponseStrategy.BERI_PERSPEKTIF_BARU, oppositeEmotion = "Harapan"
        ),
        DeepEmotion(
            name = "Bersyukur", category = EmotionCategory.COMPLEX,
            physicalSigns = listOf("tersenyum_tenang", "mata_berkaca", "mengatupkan_tangan", "mengangguk_perlahan", "napas_dalam", "postur_rileks"),
            causes = listOf("menerima_bantuan", "selamat_dari_bahaya", "melihat_orang_lain_lebih_sulit", "mencapai_target", "diberi_kesempatan_kedua"),
            duration = EmotionDuration.HOURS, intensity = 0.6f,
            triggerWords = listOf("syukur", "terima_kasih", "beruntung", "alhamdulillah", "untung", "blessed", "grateful"),
            responseStrategy = ResponseStrategy.IKUT_SENANG, oppositeEmotion = "Tidak_Bersyukur"
        ),

        // ========== SOCIAL EMOTIONS ==========
        DeepEmotion(
            name = "Kesepian", category = EmotionCategory.SOCIAL,
            physicalSigns = listOf("diam_lama", "melamun", "jarang_bicara", "postur_menutup", "menghindari_keramaian", "sering_melihat_HP_tanpa_tujuan", "menghela_napas"),
            causes = listOf("sendirian_lama", "tidak_ada_teman_bicara", "ditinggalkan", "lingkungan_baru", "bekerja_remote", "tidak_diundang"),
            duration = EmotionDuration.WEEKS, intensity = 0.6f,
            triggerWords = listOf("sepi", "sendiri", "sendirian", "kesepian", "nggak_ada_temen", "sunyi", "lengang", "nggak_diajak"),
            responseStrategy = ResponseStrategy.TEMANI_DALAM_DIAM, oppositeEmotion = "Ditemani"
        ),
        DeepEmotion(
            name = "Malu", category = EmotionCategory.SOCIAL,
            physicalSigns = listOf("pipi_merona", "menunduk", "menghindari_kontak_mata", "suara_pelan", "gugup", "salah_tingkah", "berkeringat_dingin"),
            causes = listOf("diperhatikan_banyak_orang", "melakukan_kesalahan_di_depan_umum", "dikritik_publik", "pakaian_tidak_rapi", "jatuh_di_depan_umum"),
            duration = EmotionDuration.HOURS, intensity = 0.5f,
            triggerWords = listOf("malu", "memalukan", "takut_dilihat", "nggak_percaya_diri", "minder", "malu_maluin"),
            responseStrategy = ResponseStrategy.VALIDASI_PERASAAN, oppositeEmotion = "Percaya_Diri"
        ),
        DeepEmotion(
            name = "Kagum", category = EmotionCategory.SOCIAL,
            physicalSigns = listOf("mata_berbinar", "mulut_sedikit_terbuka", "mendekat", "diam_memperhatikan", "mengangguk_pelan", "alis_terangkat"),
            causes = listOf("melihat_keahlian", "melihat_karya_bagus", "mendengar_ide_brilian", "melihat_keindahan", "melihat_teknologi_canggih", "melihat_prestasi"),
            duration = EmotionDuration.MINUTES, intensity = 0.7f,
            triggerWords = listOf("kagum", "keren", "hebat", "luar_biasa", "wow", "amazing", "speechless", "takjub"),
            responseStrategy = ResponseStrategy.IKUT_SENANG, oppositeEmotion = "Biasa_Saja"
        ),

        // ========== EXISTENTIAL EMOTIONS ==========
        DeepEmotion(
            name = "Kehilangan", category = EmotionCategory.EXISTENTIAL,
            physicalSigns = listOf("diam_mematung", "mata_kosong", "tidak_bereaksi", "menangis_tersedu", "memegang_benda_kenangan", "sulit_tidur", "tidak_nafsu_makan"),
            causes = listOf("orang_meninggal", "barang_berharga_hilang", "hubungan_berakhir", "pekerjaan_hilang", "rumah_rusak", "hewan_peliharaan_mati"),
            duration = EmotionDuration.MONTHS, intensity = 0.95f,
            triggerWords = listOf("hilang", "meninggal", "pergi_selamanya", "nggak_ada_lagi", "terakhir_kali", "perpisahan", "selamat_jalan"),
            responseStrategy = ResponseStrategy.BERI_RUANG, oppositeEmotion = "Ditemukan"
        ),
        DeepEmotion(
            name = "Makna Hidup", category = EmotionCategory.EXISTENTIAL,
            physicalSigns = listOf("merenung", "diam_lama", "menatap_jauh", "bicara_perlahan", "bertanya_tentang_tujuan"),
            causes = listOf("fase_kehidupan_baru", "krisis_eksistensial", "merasa_hampa", "pencapaian_besar_tapi_merasa_kosong"),
            duration = EmotionDuration.WEEKS, intensity = 0.5f,
            triggerWords = listOf("makna_hidup", "tujuan_hidup", "buat_apa", "apa_gunanya", "aku_siapa", "mengapa_aku_disini"),
            responseStrategy = ResponseStrategy.TANYAKAN_LEBIH_DALAM, oppositeEmotion = "Kepastian"
        )
    )
    
    /**
     * Deteksi emosi dari teks user
     */
    fun detectEmotion(text: String): DeepEmotion? {
        val lower = text.lowercase()
        return allEmotions.firstOrNull { emotion ->
            emotion.triggerWords.any { lower.contains(it) }
        }
    }
    
    /**
     * Deteksi multiple emosi dari teks user
     */
    fun detectMultipleEmotions(text: String): List<DeepEmotion> {
        val lower = text.lowercase()
        return allEmotions.filter { emotion ->
            emotion.triggerWords.any { lower.contains(it) }
        }
    }
    
    /**
     * Dapatkan strategi respon berdasarkan emosi
     */
    fun getResponseStrategy(emotion: DeepEmotion): ResponseStrategy = emotion.responseStrategy
    
    /**
     * Cek apakah emosi termasuk berat/urgent
     */
    fun isUrgentEmotion(emotion: DeepEmotion): Boolean {
        return emotion.intensity >= 0.8f || 
               emotion.category == EmotionCategory.EXISTENTIAL ||
               emotion.duration == EmotionDuration.MONTHS
    }
    
    /**
     * Dapatkan rekomendasi tindakan untuk user berdasarkan emosi
     */
    fun getRecommendation(emotion: DeepEmotion): String {
        return when (emotion.responseStrategy) {
            ResponseStrategy.IKUT_SENANG -> "Rayakan momen ini! Ceritakan lebih banyak ke XNAI."
            ResponseStrategy.DENGARKAN_DAN_HIBUR -> "Ambil napas dalam. XNAI di sini mendengarkan."
            ResponseStrategy.TENANGKAN_DAN_BERI_SOLUSI -> "Tenang dulu. Mari kita cari solusi bersama."
            ResponseStrategy.AKUI_DAN_BANTU_MENJAUH -> "Pahami perasaanmu. Kalau perlu, menjauhlah dari sumbernya."
            ResponseStrategy.TEMANI_DALAM_DIAM -> "Tidak apa-apa merasa seperti ini. XNAI temani kamu."
            ResponseStrategy.DUKUNG_PENGEMBANGAN_DIRI -> "Dari sini kita bisa belajar. Kamu lebih kuat dari ini."
            ResponseStrategy.ALIHKAN_KE_POSITIF -> "Yuk alihkan ke hal positif. Ada banyak hal baik di sekitar."
            ResponseStrategy.VALIDASI_PERASAAN -> "Perasaanmu valid. Tidak ada yang salah dengan apa yang kamu rasa."
            ResponseStrategy.BERI_RUANG -> "Aku kasih kamu ruang. Tapi aku tetap di sini kalau butuh."
            ResponseStrategy.TANYAKAN_LEBIH_DALAM -> "Ceritakan lebih banyak. Aku ingin memahami."
            ResponseStrategy.BERI_PERSPEKTIF_BARU -> "Lihat dari sisi lain. Mungkin ada hikmah di balik ini."
            ResponseStrategy.SARANKAN_ISTIRAHAT -> "Kamu butuh istirahat. Jangan paksakan diri."
            ResponseStrategy.MOTIVASI -> "Kamu pasti bisa! Aku percaya sama kamu."
            ResponseStrategy.JANGAN_GANGGU -> "Aku diam dulu. Tapi tetap siaga kalau kamu butuh."
            ResponseStrategy.PANGGIL_BANTUAN -> "Ini serius. Jangan ragu minta bantuan orang terdekat."
        }
    }
}