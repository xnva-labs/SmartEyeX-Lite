package com.smarteyex.lite

class ActivitySystem {
    
    val allActivities: Map<String, HumanActivity> = mapOf(
        // ===== PEKERJAAN BENGKEL =====
        "bekerja_mesin_bubut" to HumanActivity(
            name = "Bekerja dengan Mesin Bubut",
            bodyParts = listOf("tangan", "mata", "kaki", "punggung", "jari", "lengan", "leher"),
            objects = listOf("mesin_bubut", "engkol", "chuck", "benda_kerja", "mata_pisau", "kacamata_pelindung", "kunci_chuck", "coolant", "jangka_sorong"),
            duration = "30_menit_sampai_8_jam",
            context = listOf("bengkel", "siang", "malam", "sendiri", "produksi", "lembur"),
            dangerLevel = 8,
            commonMistakes = listOf("engkol_terlalu_cepat", "tidak_pakai_kacamata", "tangan_dekat_chuck", "benda_tidak_kencang", "tidak_pakai_sepatu_safety", "sarung_tangan_terlilit"),
            safetyProcedure = listOf("pakai_kacamata_pelindung", "periksa_benda_kerja_terjepit_kuat", "atur_kecepatan_sesuai_material", "jaga_jarak_tangan_dari_chuck", "pakai_sepatu_safety", "matikan_mesin_saat_mengganti_pisau")
        ),
        "mengelas" to HumanActivity(
            name = "Mengelas",
            bodyParts = listOf("tangan", "mata", "badan", "lengan", "leher"),
            objects = listOf("mesin_las", "elektroda", "helm_las", "sarung_tangan_las", "apron_las", "benda_kerja", "palu_terak", "sikat_kawat", "gerinda"),
            duration = "30_menit_sampai_6_jam",
            context = listOf("bengkel", "siang", "produksi", "konstruksi", "perbaikan"),
            dangerLevel = 9,
            commonMistakes = listOf("tidak_pakai_helm_las", "tidak_pakai_sarung_tangan", "terlalu_lama_di_satu_titik", "tidak_membersihkan_terak", "las_terlalu_panas", "tidak_pakai_apron"),
            safetyProcedure = listOf("pakai_helm_las_wajib", "pakai_sarung_tangan_las", "pakai_apron_las", "pastikan_ventilasi_baik", "jauhkan_bahan_mudah_terbakar", "bersihkan_terak_setelah_las")
        ),
        "memperbaiki_mesin" to HumanActivity(
            name = "Memperbaiki Mesin",
            bodyParts = listOf("tangan", "jari", "mata", "badan", "punggung", "lengan"),
            objects = listOf("obeng", "kunci_inggris", "tang", "palu", "sparepart", "manual_book", "senter", "multimeter", "kunci_sok", "dongkrak"),
            duration = "15_menit_sampai_seharian",
            context = listOf("bengkel", "siang", "sendiri", "perbaikan", "darurat"),
            dangerLevel = 6,
            commonMistakes = listOf("tidak_mematikan_mesin_dulu", "menggunakan_alat_tidak_sesuai", "lupa_memasang_kembali", "tidak_cek_manual", "terburu_buru"),
            safetyProcedure = listOf("matikan_mesin_dan_sumber_listrik", "lepas_aki_untuk_kendaraan", "cek_manual_book", "siapkan_alat_lengkap", "foto_sebelum_dibongkar", "uji_coba_setelah_perbaikan")
        ),
        "menggergaji" to HumanActivity(
            name = "Menggergaji",
            bodyParts = listOf("tangan", "lengan", "mata", "badan"),
            objects = listOf("gergaji", "benda_kerja", "penjepit", "meteran", "pensil", "kacamata_pelindung", "sarung_tangan"),
            duration = "5_menit_sampai_1_jam",
            context = listOf("bengkel", "konstruksi", "siang"),
            dangerLevel = 5,
            commonMistakes = listOf("tidak_menjepit_benda", "mata_gergaji_tumpul", "terlalu_cepat", "tidak_pakai_kacamata"),
            safetyProcedure = listOf("jepit_benda_kerja", "pakai_kacamata", "gergaji_perlahan", "jauhkan_tangan_dari_mata_gergaji")
        ),
        "mengebor" to HumanActivity(
            name = "Mengebor",
            bodyParts = listOf("tangan", "mata", "lengan", "badan"),
            objects = listOf("bor", "mata_bor", "benda_kerja", "penjepit", "kacamata_pelindung", "masker"),
            duration = "1_menit_sampai_30_menit",
            context = listOf("bengkel", "konstruksi", "rumah", "siang"),
            dangerLevel = 4,
            commonMistakes = listOf("mata_bor_tumpul", "kecepatan_bor_terlalu_tinggi", "tidak_menjepit_benda", "bor_meleset"),
            safetyProcedure = listOf("pakai_kacamata", "jepit_benda", "pakai_mata_bor_tajam", "atur_kecepatan_sesuai_material")
        ),
        
        // ===== KEGIATAN RUMAH =====
        "makan" to HumanActivity(
            name = "Makan",
            bodyParts = listOf("tangan", "mulut", "mata", "jari"),
            objects = listOf("piring", "sendok", "garpu", "makanan", "meja", "kursi", "tisu", "gelas", "pisau_makan"),
            duration = "15_30_menit",
            context = listOf("rumah", "dapur", "meja", "siang", "malam", "sendiri", "bersama_keluarga", "restoran"),
            dangerLevel = 2,
            commonMistakes = listOf("makan_terlalu_cepat_bisa_tersedak", "makan_terlalu_banyak", "tidak_cuci_tangan", "makanan_terlalu_panas"),
            safetyProcedure = listOf("cuci_tangan_sebelum_makan", "kunyah_perlahan_30_kali", "minum_cukup", "jangan_bicara_saat_mulut_penuh")
        ),
        "minum" to HumanActivity(
            name = "Minum",
            bodyParts = listOf("tangan", "mulut", "tenggorokan"),
            objects = listOf("gelas", "cangkir", "botol", "air", "kopi", "teh", "es", "sedotan"),
            duration = "1_15_menit",
            context = listOf("rumah", "bengkel", "dapur", "meja", "pagi", "siang", "malam", "kapan_saja"),
            dangerLevel = 1,
            commonMistakes = listOf("minum_terlalu_cepat_tersedak", "minum_es_malam_hari", "minuman_terlalu_panas", "kurang_minum_dehidrasi"),
            safetyProcedure = listOf("cek_suhu_minuman_sebelum_minum", "minum_perlahan", "hindari_es_malam_hari", "minum_8_gelas_sehari")
        ),
        "memasak" to HumanActivity(
            name = "Memasak",
            bodyParts = listOf("tangan", "mata", "hidung", "badan", "lengan"),
            objects = listOf("kompor", "wajan", "panci", "pisau_dapur", "talenan", "bahan_makanan", "minyak_goreng", "spatula", "sendok_sayur", "celemek"),
            duration = "30_menit_sampai_3_jam",
            context = listOf("dapur", "rumah", "pagi", "siang", "malam"),
            dangerLevel = 5,
            commonMistakes = listOf("meninggalkan_kompor_menyala", "pisau_tumpul", "minyak_terlalu_panas_sampai_terbakar", "tidak_pakai_celemek", "tangan_basah_pegang_listrik"),
            safetyProcedure = listOf("jangan_tinggalkan_kompor_menyala", "pakai_pisau_tajam_lebih_aman", "jauhkan_anak_dari_dapur", "sedia_pemadam_api", "pakai_celemek")
        ),
        "membersihkan_rumah" to HumanActivity(
            name = "Membersihkan Rumah",
            bodyParts = listOf("tangan", "badan", "kaki", "punggung", "lengan"),
            objects = listOf("sapu", "pel", "kemoceng", "vacuum_cleaner", "lap", "ember", "sabun", "sarung_tangan_karet"),
            duration = "30_menit_sampai_beberapa_jam",
            context = listOf("rumah", "pagi", "siang", "sendiri", "bersih_bersih"),
            dangerLevel = 2,
            commonMistakes = listOf("lantai_basah_licin", "tidak_pakai_sarung_tangan", "membungkuk_terlalu_lama", "campur_bahan_kimia_berbahaya"),
            safetyProcedure = listOf("beri_tanda_lantai_basah", "pakai_sarung_tangan", "istirahat_setiap_30_menit", "jangan_campur_pemutih_dengan_pembersih_lain")
        ),
        "tidur" to HumanActivity(
            name = "Tidur",
            bodyParts = listOf("mata", "badan", "kepala", "punggung", "seluruh_tubuh"),
            objects = listOf("tempat_tidur", "bantal", "selimut", "guling", "lampu_tidur", "jam_beker"),
            duration = "6_8_jam",
            context = listOf("rumah", "kamar", "malam", "sendiri", "istirahat"),
            dangerLevel = 0,
            commonMistakes = listOf("tidur_terlalu_malam", "main_HP_sebelum_tidur", "lampu_terlalu_terang", "makan_berat_sebelum_tidur"),
            safetyProcedure = listOf("matikan_lampu_atau_redupkan", "jauhkan_HP_dari_tempat_tidur", "tidur_sebelum_jam_11", "jangan_makan_berat_2_jam_sebelum_tidur")
        ),
        "mandi" to HumanActivity(
            name = "Mandi",
            bodyParts = listOf("tangan", "badan", "kepala", "kaki", "seluruh_tubuh"),
            objects = listOf("air", "sabun", "shampoo", "handuk", "sikat_gigi", "pasta_gigi", "bak_mandi", "shower"),
            duration = "10_30_menit",
            context = listOf("kamar_mandi", "rumah", "pagi", "sore", "malam"),
            dangerLevel = 2,
            commonMistakes = listOf("lantai_kamar_mandi_licin", "air_terlalu_panas", "terpeleset", "handuk_basah_di_lantai"),
            safetyProcedure = listOf("pakai_keset_anti_licin", "cek_suhu_air", "jangan_berlari_di_kamar_mandi")
        ),
        
        // ===== KEGIATAN FISIK =====
        "berjalan" to HumanActivity(
            name = "Berjalan",
            bodyParts = listOf("kaki", "badan", "mata", "tangan"),
            objects = listOf("lantai", "jalan", "trotoar", "sepatu", "sendal", "tongkat", "payung"),
            duration = "singkat_hingga_berjam_jam",
            context = listOf("bengkel", "rumah", "luar", "pagi", "siang", "malam", "hujan", "panas"),
            dangerLevel = 3,
            commonMistakes = listOf("tidak_melihat_jalan", "berjalan_terlalu_cepat", "memakai_sendal_licin", "main_HP_sambil_jalan"),
            safetyProcedure = listOf("lihat_jalan_depan", "pakai_sepatu_nyaman", "hati_hati_jalan_licin", "jangan_main_HP_sambil_jalan")
        ),
        "berlari" to HumanActivity(
            name = "Berlari",
            bodyParts = listOf("kaki", "badan", "tangan", "jantung", "paru"),
            objects = listOf("jalan", "trek", "sepatu_lari", "baju_olahraga", "botol_air", "handuk"),
            duration = "15_menit_sampai_1_jam",
            context = listOf("luar", "pagi", "sore", "olahraga", "maraton"),
            dangerLevel = 4,
            commonMistakes = listOf("tidak_pemanasan", "berlari_terlalu_jauh_untuk_pemula", "dehidrasi", "sepatu_tidak_cocok"),
            safetyProcedure = listOf("pemanasan_minimal_10_menit", "bawa_air_minum", "istirahat_jika_lelah_atau_nyeri", "pakai_sepatu_lari_yang_nyaman")
        ),
        "olahraga" to HumanActivity(
            name = "Olahraga",
            bodyParts = listOf("badan", "tangan", "kaki", "jantung", "paru", "otot"),
            objects = listOf("matras", "beban", "tali_skipping", "baju_olahraga", "handuk", "botol_air", "sepeda", "bola"),
            duration = "30_menit_sampai_2_jam",
            context = listOf("rumah", "gym", "luar", "pagi", "sore", "sehat"),
            dangerLevel = 3,
            commonMistakes = listOf("tidak_pemanasan", "gerakan_salah_bisa_cidera", "terlalu_memaksa_diri", "kurang_minum", "tidak_pendinginan"),
            safetyProcedure = listOf("pemanasan_15_menit", "cek_gerakan_benar_dari_sumber_terpercaya", "minum_cukup", "pendinginan_10_menit")
        ),
        "berenang" to HumanActivity(
            name = "Berenang",
            bodyParts = listOf("tangan", "kaki", "badan", "paru", "seluruh_otot"),
            objects = listOf("kolam_renang", "baju_renang", "kacamata_renang", "pelampung", "handuk"),
            duration = "30_menit_sampai_1_jam",
            context = listOf("kolam_renang", "pantai", "pagi", "siang", "olahraga"),
            dangerLevel = 6,
            commonMistakes = listOf("tidak_pemanasan", "berenang_sendirian", "tidak_tahu_kedalaman", "makan_berat_sebelum_berenang"),
            safetyProcedure = listOf("pemanasan_dulu", "jangan_berenang_sendirian", "cek_kedalaman_air", "tunggu_1_jam_setelah_makan")
        ),
        
        // ===== KEGIATAN DIGITAL =====
        "mengetik" to HumanActivity(
            name = "Mengetik",
            bodyParts = listOf("jari", "mata", "tangan", "punggung", "leher", "pergelangan_tangan"),
            objects = listOf("keyboard", "komputer", "layar", "meja", "kursi", "mouse", "keyboard_wrist_rest"),
            duration = "singkat_hingga_berjam_jam",
            context = listOf("rumah", "kantor", "ruang_kerja", "siang", "malam", "produktif"),
            dangerLevel = 2,
            commonMistakes = listOf("postur_buruk", "terlalu_lama_tanpa_istirahat", "layar_terlalu_terang", "pergelangan_tangan_menekuk"),
            safetyProcedure = listOf("atur_postur_ergonomis", "istirahat_20_menit_sekali_rule_20-20-20", "atur_kecerahan_layar", "pakai_wrist_rest")
        ),
        "menggunakan_smartphone" to HumanActivity(
            name = "Menggunakan Smartphone",
            bodyParts = listOf("jari", "mata", "tangan", "leher", "ibu_jari"),
            objects = listOf("smartphone", "charger", "headset", "powerbank", "casing"),
            duration = "singkat_hingga_berjam_jam",
            context = listOf("rumah", "bengkel", "luar", "mana_saja", "kapan_saja"),
            dangerLevel = 2,
            commonMistakes = listOf("terlalu_lama_menunduk_text_neck", "mata_terlalu_dekat", "pakai_sambil_dicas", "tidak_istirahat", "cahaya_layar_terlalu_terang_di_malam"),
            safetyProcedure = listOf("angkat_HP_setinggi_mata", "istirahat_20-20-20", "jangan_pakai_sambil_dicas", "aktifkan_mode_malam")
        ),
        "membaca" to HumanActivity(
            name = "Membaca",
            bodyParts = listOf("mata", "tangan", "leher", "punggung"),
            objects = listOf("buku", "majalah", "koran", "tablet", "lampu_baca", "pembatas_buku"),
            duration = "15_menit_sampai_berjam_jam",
            context = listOf("rumah", "perpustakaan", "kafe", "pagi", "siang", "malam"),
            dangerLevel = 1,
            commonMistakes = listOf("membaca_di_tempat_gelap", "jarak_baca_terlalu_dekat", "postur_buruk", "terlalu_lama_tanpa_istirahat"),
            safetyProcedure = listOf("pastikan_pencahayaan_cukup", "jarak_baca_30cm", "duduk_tegak", "istirahat_setiap_30_menit")
        ),
        "menonton" to HumanActivity(
            name = "Menonton TV/Film",
            bodyParts = listOf("mata", "badan", "telinga"),
            objects = listOf("tv", "remote", "sofa", "laptop", "headset", "cemilan", "minuman"),
            duration = "30_menit_sampai_beberapa_jam",
            context = listOf("rumah", "bioskop", "malam", "santai", "sendiri", "bersama"),
            dangerLevel = 1,
            commonMistakes = listOf("terlalu_lama_diam", "mata_lelah", "volume_terlalu_keras", "makan_cemilan_berlebihan"),
            safetyProcedure = listOf("istirahat_setiap_jam", "atur_volume_aman", "jaga_jarak_pandang", "batasi_cemilan")
        ),
        
        // ===== KEGIATAN SOSIAL =====
        "ngobrol" to HumanActivity(
            name = "Ngobrol/Bercakap-cakap",
            bodyParts = listOf("mulut", "telinga", "mata", "tangan"),
            objects = listOf("kursi", "meja", "minuman", "smartphone"),
            duration = "5_menit_sampai_berjam_jam",
            context = listOf("rumah", "kafe", "bengkel", "mana_saja", "santai"),
            dangerLevel = 0,
            commonMistakes = listOf("mendominasi_pembicaraan", "tidak_mendengarkan", "memotong_pembicaraan"),
            safetyProcedure = listOf("dengarkan_lawan_bicara", "jaga_nada_suara", "hormati_pendapat_lain")
        ),
        "belajar" to HumanActivity(
            name = "Belajar",
            bodyParts = listOf("mata", "tangan", "otak", "punggung"),
            objects = listOf("buku", "laptop", "catatan", "pena", "highlighter", "meja", "kursi", "lampu_belajar"),
            duration = "30_menit_sampai_berjam_jam",
            context = listOf("rumah", "sekolah", "perpustakaan", "pagi", "siang", "malam"),
            dangerLevel = 1,
            commonMistakes = listOf("belajar_maraton_tanpa_istirahat", "tidak_mencatat", "multitasking", "belajar_sambil_main_HP"),
            safetyProcedure = listOf("istirahat_setiap_25_menit_teknik_pomodoro", "catat_poin_penting", "jauhkan_HP", "pastikan_pencahayaan_baik")
        ),
        "telepon" to HumanActivity(
            name = "Telepon",
            bodyParts = listOf("telinga", "mulut", "tangan", "mata"),
            objects = listOf("smartphone", "headset", "charger"),
            duration = "singkat_hingga_berjam_jam",
            context = listOf("bengkel", "rumah", "mana_saja"),
            dangerLevel = 2,
            commonMistakes = listOf("telepon_sambil_nyetir", "volume_terlalu_keras", "telepon_sambil_dicas"),
            safetyProcedure = listOf("jangan_telepon_sambil_nyetir", "pakai_headset_untuk_panggilan_lama", "atur_volume_aman")
        ),
        
        // ===== KEGIATAN KHUSUS =====
        "sholat" to HumanActivity(
            name = "Sholat/Beribadah",
            bodyParts = listOf("badan", "tangan", "kaki", "kepala", "mulut"),
            objects = listOf("sajadah", "sarung", "mukena", "peci", "tasbih", "al_quran"),
            duration = "5_15_menit",
            context = listOf("rumah", "masjid", "musholla", "pagi", "siang", "sore", "malam", "khusyuk"),
            dangerLevel = 0,
            commonMistakes = listOf("terburu_buru", "tidak_khusyuk", "gerakan_tidak_sempurna"),
            safetyProcedure = listOf("cari_tempat_tenang", "fokus_ibadah", "jangan_terburu_buru")
        ),
        "menyetir" to HumanActivity(
            name = "Menyetir Kendaraan",
            bodyParts = listOf("tangan", "kaki", "mata", "telinga"),
            objects = listOf("stir", "pedal_gas", "pedal_rem", "pedal_kopling", "spion", "sabuk_pengaman", "lampu_sein", "klakson"),
            duration = "singkat_hingga_berjam_jam",
            context = listOf("jalan_raya", "pagi", "siang", "malam", "macet", "hujan", "perjalanan_jauh"),
            dangerLevel = 7,
            commonMistakes = listOf("tidak_pakai_sabuk_pengaman", "main_HP_saat_nyetir", "ngantuk_saat_nyetir", "ngebut", "tidak_nyalakan_sein"),
            safetyProcedure = listOf("pakai_sabuk_pengaman", "jangan_main_HP", "istirahat_setiap_2_jam", "patuhi_batas_kecepatan", "nyalakan_sein_saat_belok")
        )
    )
    
    fun getActivity(name: String): HumanActivity? = allActivities[name.lowercase()]
    
    fun getDangerousActivities(): List<HumanActivity> = 
        allActivities.values.filter { it.dangerLevel >= 7 }.sortedByDescending { it.dangerLevel }
    
    fun getActivitiesByContext(context: String): List<HumanActivity> =
        allActivities.values.filter { it.context.any { c -> c.contains(context.lowercase(), true) } }
    
    fun getActivitiesByBodyPart(bodyPart: String): List<HumanActivity> =
        allActivities.values.filter { it.bodyParts.any { b -> b.contains(bodyPart.lowercase(), true) } }
    
    fun getActivitiesByObject(obj: String): List<HumanActivity> =
        allActivities.values.filter { it.objects.any { o -> o.contains(obj.lowercase(), true) } }
    
    fun getSafetyTips(activityName: String): List<String> {
        return getActivity(activityName)?.safetyProcedure ?: emptyList()
    }
}