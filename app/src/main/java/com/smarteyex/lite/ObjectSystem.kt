package com.smarteyex.lite

class ObjectSystem {
    
    val allObjects: Map<String, KnowledgeObject> = mapOf(
        // ============================================
        // 1. BENGKEL & PERKAKAS (15 benda)
        // ============================================
        "mesin_bubut" to KnowledgeObject(
            name = "Mesin Bubut", category = "mesin_produksi",
            parts = listOf("chuck", "engkol", "mata_pisau", "bed", "tailstock", "motor", "panel_kontrol", "tombol_darurat", "lampu_indikator", "poros_utama", "eretan", "tool_post", "coolant_pump"),
            functions = listOf("memotong_logam", "membentuk_benda_kerja", "membuat_ulir", "menghaluskan_permukaan", "membuat_lubang_center", "memotong_alur", "mengurangi_diameter"),
            interactions = listOf("tangan_memutar_engkol", "mata_pisau_memotong_benda", "chuck_berputar", "operator_mengganti_pisau", "coolant_mendinginkan"),
            dangers = listOf("tangan_dekat_chuck_berputar", "benda_terlepas", "mata_pisau_patah", "beram_panas", "sarung_tangan_terlilit", "tidak_pakai_kacamata"),
            contexts = listOf("bengkel", "siang", "malam", "kerja", "lembur", "produksi"),
            relatedObjects = listOf("engkol", "chuck", "mata_pisau", "kunci_chuck", "kacamata_pelindung", "coolant", "jangka_sorong"),
            material = "besi_cor", weight = "500-2000kg", size = "2-4meter"
        ),
        "engkol" to KnowledgeObject(
            name = "Engkol", category = "bagian_mesin",
            parts = listOf("tuas", "pegangan", "poros", "skala", "pengunci"),
            functions = listOf("menggerakkan_pisau_maju_mundur", "mengatur_kecepatan_potong", "mengatur_kedalaman_potong"),
            interactions = listOf("tangan_memutar_engkol", "engkol_terhubung_ke_eretan", "skala_menunjukkan_ukuran"),
            dangers = listOf("engkol_terlalu_cepat_bahaya", "tangan_terjepit", "engkol_longgar", "skala_tidak_terbaca"),
            contexts = listOf("bengkel", "mesin_bubut"), relatedObjects = listOf("mesin_bubut", "tangan", "mata_pisau"),
            material = "baja", weight = "1-3kg", size = "20-40cm"
        ),
        "chuck" to KnowledgeObject(
            name = "Chuck", category = "bagian_mesin",
            parts = listOf("rahang", "kunci_chuck", "poros_utama", "baut_penjepit"),
            functions = listOf("memegang_benda_kerja", "berputar_bersama_benda", "menjepit_benda_kerja"),
            interactions = listOf("chuck_berputar", "tangan_mendekatkan_benda", "kunci_mengencangkan_rahang"),
            dangers = listOf("tangan_terlalu_dekat_chuck_berputar", "benda_tidak_kencang_terlepas", "kunci_chuck_tertinggal_terlempar"),
            contexts = listOf("bengkel", "mesin_bubut"), relatedObjects = listOf("mesin_bubut", "kunci_chuck", "benda_kerja"),
            material = "baja_keras", weight = "5-50kg", size = "10-40cm"
        ),
        "mata_pisau_bubut" to KnowledgeObject(
            name = "Mata Pisau Bubut", category = "alat_potong",
            parts = listOf("ujung_potong", "badan_pisau", "dudukan", "chip_breaker"),
            functions = listOf("memotong_logam", "membentuk_benda", "membuat_ulir", "menghaluskan"),
            interactions = listOf("mata_pisau_memotong_benda", "tangan_mengganti_pisau", "coolant_mendinginkan"),
            dangers = listOf("mata_pisau_tajam_melukai", "mata_pisau_tumpul_macet", "mata_pisau_panas", "beram_tajam"),
            contexts = listOf("bengkel", "mesin_bubut"), relatedObjects = listOf("mesin_bubut", "tool_post", "coolant"),
            material = "karbida/HSS", weight = "100-500gr", size = "1-3cm"
        ),
        "palu" to KnowledgeObject(
            name = "Palu", category = "perkakas",
            parts = listOf("kepala_palu", "gagang", "permukaan_pukul", "cakar"),
            functions = listOf("memukul", "meratakan", "memasang_paku", "membentuk_logam", "menghancurkan", "mencabut_paku"),
            interactions = listOf("tangan_memegang_gagang", "kepala_palu_memukul_benda", "cakar_mencabut_paku"),
            dangers = listOf("palu_meleset_melukai_tangan", "gagang_patah", "kepala_palu_terlepas", "terlalu_berat"),
            contexts = listOf("bengkel", "konstruksi", "rumah"), relatedObjects = listOf("paku", "benda_kerja", "tangan"),
            material = "baja_kayu", weight = "0.5-5kg", size = "25-40cm"
        ),
        "obeng" to KnowledgeObject(
            name = "Obeng", category = "perkakas",
            parts = listOf("mata_obeng", "gagang", "batang"),
            functions = listOf("memutar_sekrup", "membuka_penutup", "mencongkel_ringan", "mengencangkan_baut_kecil"),
            interactions = listOf("tangan_memegang_gagang", "mata_obeng_memutar_sekrup"),
            dangers = listOf("obeng_tergelincir_melukai", "mata_obeng_tidak_pas_merusak_sekrup", "gagang_patah"),
            contexts = listOf("bengkel", "perbaikan", "rumah", "elektronik"), relatedObjects = listOf("sekrup", "baut", "tangan"),
            material = "baja_plastik", weight = "100-300gr", size = "10-30cm"
        ),
        "kunci_inggris" to KnowledgeObject(
            name = "Kunci Inggris", category = "perkakas",
            parts = listOf("rahang", "pegangan", "pengatur_ukuran", "gigi_penyetel"),
            functions = listOf("mengencangkan_mur", "melonggarkan_baut", "menjepit", "memutar_pipa"),
            interactions = listOf("tangan_memegang_kunci", "rahang_menjepit_mur", "pengatur_mengubah_ukuran"),
            dangers = listOf("kunci_terlepas_melukai", "ukuran_tidak_pas_merusak_mur", "gigi_penyetel_aus"),
            contexts = listOf("bengkel", "perbaikan", "pipa"), relatedObjects = listOf("mur", "baut", "pipa"),
            material = "baja", weight = "0.5-3kg", size = "15-45cm"
        ),
        "tang" to KnowledgeObject(
            name = "Tang", category = "perkakas",
            parts = listOf("rahang", "gagang", "sendi", "pemotong_kawat"),
            functions = listOf("menjepit", "memotong_kawat", "memegang_benda_kecil", "mengupas_kabel"),
            interactions = listOf("tangan_memegang_gagang", "rahang_menjepit_kawat", "pemotong_memotong_kabel"),
            dangers = listOf("tang_terlepas_menjepit_tangan", "kawat_putus_melukai", "gagang_karet_aus_tersengat_listrik"),
            contexts = listOf("bengkel", "listrik", "perbaikan"), relatedObjects = listOf("kawat", "kabel", "paku_kecil"),
            material = "baja_karet", weight = "200-500gr", size = "15-25cm"
        ),
        "gerinda" to KnowledgeObject(
            name = "Gerinda", category = "perkakas_listrik",
            parts = listOf("batu_gerinda", "motor", "pelindung", "gagang", "saklar", "kabel_power"),
            functions = listOf("memotong_logam", "menghaluskan_permukaan", "mengikis_karat", "mempertajam_alat"),
            interactions = listOf("tangan_memegang_gagang", "batu_gerinda_berputar_cepat", "percikan_api_saat_memotong"),
            dangers = listOf("batu_gerinda_pecah_terlempar", "percikan_api_kena_mata", "kabel_terpotong", "tidak_pakai_pelindung"),
            contexts = listOf("bengkel", "konstruksi"), relatedObjects = listOf("kacamata_pelindung", "sarung_tangan", "masker"),
            material = "baja_plastik", weight = "2-5kg", size = "30-50cm"
        ),
        "bor" to KnowledgeObject(
            name = "Bor", category = "perkakas_listrik",
            parts = listOf("mata_bor", "chuck_bor", "motor", "gagang", "saklar", "baterai_atau_kabel"),
            functions = listOf("membuat_lubang", "memperbesar_lubang", "memasang_sekrup", "mengaduk"),
            interactions = listOf("tangan_memegang_gagang", "mata_bor_berputar_menembus_benda", "chuck_mengencangkan_mata_bor"),
            dangers = listOf("mata_bor_patah", "bor_meleset_melukai", "tangan_terkena_mata_bor", "kabel_terlilit"),
            contexts = listOf("bengkel", "konstruksi", "rumah"), relatedObjects = listOf("mata_bor", "sekrup", "kacamata"),
            material = "baja_plastik", weight = "1-3kg", size = "20-35cm"
        ),
        "gergaji" to KnowledgeObject(
            name = "Gergaji", category = "perkakas",
            parts = listOf("mata_gergaji", "gagang", "gigi_pemotong"),
            functions = listOf("memotong_kayu", "memotong_logam", "memotong_plastik", "memotong_pipa"),
            interactions = listOf("tangan_memegang_gagang", "mata_gergaji_memotong_benda", "gerakan_maju_mundur"),
            dangers = listOf("mata_gergaji_patah", "tangan_tergores", "gigi_tumpul_macet", "benda_tidak_dijepit"),
            contexts = listOf("bengkel", "konstruksi"), relatedObjects = listOf("penjepit", "kacamata", "sarung_tangan"),
            material = "baja_kayu", weight = "300-800gr", size = "30-60cm"
        ),
        "mesin_las" to KnowledgeObject(
            name = "Mesin Las", category = "mesin_produksi",
            parts = listOf("elektroda", "kabel_massa", "kabel_elektroda", "penjepit", "trafo", "helm_las", "panel_arus"),
            functions = listOf("menyambung_logam", "memotong_logam_tebal", "mengisi_lubang_logam"),
            interactions = listOf("tangan_memegang_penjepit", "elektroda_menempel_benda", "percikan_api", "helm_melindungi_mata"),
            dangers = listOf("percikan_api_terbakar", "mata_terkena_sinar_las", "tersengat_listrik", "gas_beracun", "kulit_terbakar"),
            contexts = listOf("bengkel", "konstruksi"), relatedObjects = listOf("helm_las", "sarung_tangan_las", "apron_las", "palu_terak"),
            material = "baja_tembaga", weight = "5-30kg", size = "30x30x50cm"
        ),
        "kompresor" to KnowledgeObject(
            name = "Kompresor", category = "mesin_bantu",
            parts = listOf("tangki_udara", "motor", "pompa", "selang", "pressure_gauge", "katup_pengaman", "drain_valve"),
            functions = listOf("menghasilkan_udara_tekan", "mengisi_angin_ban", "menggerakkan_alat_pneumatik", "membersihkan_debu"),
            interactions = listOf("selang_terhubung_ke_alat", "pressure_gauge_menunjukkan_tekanan", "motor_menyala"),
            dangers = listOf("tangki_ledak_jika_berkarat", "selang_putus_terlempar", "tekanan_terlalu_tinggi", "kebisingan"),
            contexts = listOf("bengkel"), relatedObjects = listOf("selang", "air_gun", "ban", "alat_pneumatik"),
            material = "baja", weight = "20-100kg", size = "50x50x100cm"
        ),
        "dongkrak" to KnowledgeObject(
            name = "Dongkrak", category = "perkakas",
            parts = listOf("lengan_angkat", "sadel", "handle", "roda", "katup_pelepas", "silinder_hidrolik"),
            functions = listOf("mengangkat_kendaraan", "menahan_beban", "memudahkan_perbaikan_bawah"),
            interactions = listOf("handle_dipompa", "sadel_menyentuh_rangka", "katup_dibuka_untuk_menurunkan"),
            dangers = listOf("dongkrak_ambles", "beban_terlalu_berat", "tidak_pakai_jack_stand", "selang_hidrolik_bocor"),
            contexts = listOf("bengkel", "jalan_raya"), relatedObjects = listOf("jack_stand", "mobil", "kunci_roda"),
            material = "baja", weight = "5-20kg", size = "30x20x20cm"
        ),
        "multimeter" to KnowledgeObject(
            name = "Multimeter", category = "alat_ukur",
            parts = listOf("layar", "probe_merah", "probe_hitam", "selector", "port", "baterai", "sekring"),
            functions = listOf("mengukur_tegangan", "mengukur_arus", "mengukur_hambatan", "cek_kontinuitas", "cek_dioda"),
            interactions = listOf("probe_ditempelkan_ke_rangkaian", "selector_diputar", "layar_menunjukkan_angka"),
            dangers = listOf("probe_tertukar_meledak", "selector_salah_rusak", "ukur_arus_paralel_korslet", "baterai_bocor"),
            contexts = listOf("bengkel", "lab", "elektronik"), relatedObjects = listOf("kabel", "baterai", "resistor", "pcb"),
            material = "plastik_elektronik", weight = "200-500gr", size = "7x15x4cm"
        ),

        // ============================================
        // 2. RUMAH TANGGA (12 benda)
        // ============================================
        "gelas" to KnowledgeObject(
            name = "Gelas", category = "peralatan_minum",
            parts = listOf("badan_gelas", "bibir_gelas", "dasar_gelas", "gagang"),
            functions = listOf("menampung_cairan", "tempat_minum", "bisa_berisi_air_kopi_teh_es_susu_jus"),
            interactions = listOf("tangan_memegang_gelas", "mulut_minum_dari_gelas", "gelas_di_atas_meja", "gelas_diisi_cairan", "gelas_dicuci"),
            dangers = listOf("gelas_kaca_pecah", "gelas_panas_melepuh", "gelas_berembun_berisi_es", "gelas_jatuh"),
            contexts = listOf("rumah", "dapur", "meja", "bengkel"), relatedObjects = listOf("air", "kopi", "teh", "es", "meja"),
            material = "kaca_plastik_keramik", weight = "100-500gr", size = "8-15cm"
        ),
        "cangkir" to KnowledgeObject(
            name = "Cangkir", category = "peralatan_minum",
            parts = listOf("badan_cangkir", "gagang", "bibir", "alas", "tutup"),
            functions = listOf("tempat_minum_kopi", "tempat_minum_teh", "menjaga_suhu_panas"),
            interactions = listOf("tangan_memegang_gagang", "mulut_minum_dari_cangkir", "cangkir_di_atas_meja"),
            dangers = listOf("cangkir_panas_melepuh", "cangkir_pecah", "gagang_patah"),
            contexts = listOf("rumah", "dapur", "meja", "pagi", "malam"), relatedObjects = listOf("kopi", "teh", "meja"),
            material = "keramik_porselen", weight = "200-400gr", size = "8-12cm"
        ),
        "piring" to KnowledgeObject(
            name = "Piring", category = "peralatan_makan",
            parts = listOf("permukaan", "bibir_piring", "dasar", "alas"),
            functions = listOf("tempat_makanan", "menyajikan_hidangan", "alas_makan"),
            interactions = listOf("sendok_mengambil_makanan", "piring_di_atas_meja", "piring_dicuci"),
            dangers = listOf("piring_pecah", "pecahan_tajam", "piring_panas_melepuh"),
            contexts = listOf("rumah", "dapur", "meja_makan"), relatedObjects = listOf("sendok", "garpu", "makanan", "meja"),
            material = "keramik_kaca_melamin", weight = "200-800gr", size = "15-30cm"
        ),
        "sendok" to KnowledgeObject(
            name = "Sendok", category = "peralatan_makan",
            parts = listOf("kepala_sendok", "gagang", "leher"),
            functions = listOf("mengambil_makanan", "mengaduk", "menyuap", "mengukur_bahan"),
            interactions = listOf("tangan_memegang_gagang", "kepala_sendok_mengambil_makanan", "sendok_di_atas_piring"),
            dangers = listOf("sendok_logam_panas", "sendok_tajam_melukai_mulut"),
            contexts = listOf("rumah", "dapur", "meja_makan"), relatedObjects = listOf("piring", "makanan", "garpu"),
            material = "stainless_steel_plastik", weight = "20-100gr", size = "15-20cm"
        ),
        "garpu" to KnowledgeObject(
            name = "Garpu", category = "peralatan_makan",
            parts = listOf("gigi_garpu", "gagang", "leher"),
            functions = listOf("menusuk_makanan", "mengambil_makanan", "memotong_makanan_lunak"),
            interactions = listOf("tangan_memegang_gagang", "gigi_garpu_menusuk_makanan", "garpu_di_atas_piring"),
            dangers = listOf("garpu_tajam_melukai", "garpu_logam_panas"),
            contexts = listOf("rumah", "dapur", "meja_makan"), relatedObjects = listOf("piring", "makanan", "sendok", "pisau_makan"),
            material = "stainless_steel", weight = "30-80gr", size = "15-20cm"
        ),
        "pisau_dapur" to KnowledgeObject(
            name = "Pisau Dapur", category = "peralatan_masak",
            parts = listOf("mata_pisau", "gagang", "ujung", "tajam", "punggung_pisau"),
            functions = listOf("memotong_bahan_makanan", "mengiris", "mencincang", "mengupas"),
            interactions = listOf("tangan_memegang_gagang", "mata_pisau_memotong_bahan", "talenan_sebagai_alas"),
            dangers = listOf("pisau_tajam_melukai_jari", "pisau_jatuh_kena_kaki", "pisau_tumpul_lebih_berbahaya"),
            contexts = listOf("dapur", "rumah"), relatedObjects = listOf("talenan", "bahan_makanan", "pengasah_pisau"),
            material = "stainless_steel_kayu", weight = "100-300gr", size = "15-25cm"
        ),
        "wajan" to KnowledgeObject(
            name = "Wajan", category = "peralatan_masak",
            parts = listOf("permukaan_masak", "gagang", "dasar", "lapisan_anti_lengket"),
            functions = listOf("menggoreng", "menumis", "memasak_dengan_minyak_sedikit"),
            interactions = listOf("spatula_mengaduk_di_wajan", "minyak_dipanaskan", "bahan_makanan_dimasukkank"),
            dangers = listOf("minyak_panas_meletup", "gagang_panas", "wajan_terlalu_panas_terbakar"),
            contexts = listOf("dapur", "rumah"), relatedObjects = listOf("spatula", "minyak_goreng", "kompor", "talenan"),
            material = "aluminium_baja_teflon", weight = "500gr-2kg", size = "20-30cm"
        ),
        "panci" to KnowledgeObject(
            name = "Panci", category = "peralatan_masak",
            parts = listOf("badan_panci", "tutup", "gagang", "dasar_tebal", "cerat"),
            functions = listOf("merebus", "mengukus", "membuat_sup", "memanaskan_air"),
            interactions = listOf("air_dididihkan", "bahan_dimasukkan", "tutup_menutup_uap"),
            dangers = listOf("uap_panas_melepuh", "air_mendidih_tumpah", "gagang_panas", "tutup_terlempar"),
            contexts = listOf("dapur", "rumah"), relatedObjects = listOf("air", "kompor", "sendok_sayur"),
            material = "stainless_steel_aluminium", weight = "300gr-1.5kg", size = "15-25cm"
        ),
        "kompor" to KnowledgeObject(
            name = "Kompor", category = "peralatan_masak",
            parts = listOf("tungku", "pemantik", "knob_pengatur", "selang_gas", "body"),
            functions = listOf("memanaskan_masakan", "sumber_api_untuk_memasak", "merebus", "menggoreng"),
            interactions = listOf("panci_di_atas_tungku", "tangan_memutar_knob", "api_menyala", "gas_mengalir"),
            dangers = listOf("gas_bocor_meledak", "api_menyambar", "terlupa_dimatiin", "anak_main_knob"),
            contexts = listOf("dapur", "rumah"), relatedObjects = listOf("panci", "wajan", "gas", "pemadam_api"),
            material = "baja_kaca", weight = "5-15kg", size = "60x40x15cm"
        ),
        "kulkas" to KnowledgeObject(
            name = "Kulkas", category = "elektronik_rumah",
            parts = listOf("pintu", "rak", "freezer", "kompresor", "termistor", "lampu", "seal_pintu"),
            functions = listOf("menyimpan_makanan_segar", "mendinginkan_minuman", "membekukan", "memperlambat_pembusukan"),
            interactions = listOf("makanan_disimpan", "pintu_dibuka_tutup", "minuman_didinginkan"),
            dangers = listOf("kompresor_panas", "freon_bocor", "makanan_basi_kelamaan", "pintu_tidak_tertutup"),
            contexts = listOf("dapur", "rumah"), relatedObjects = listOf("makanan", "minuman", "es_batu"),
            material = "baja_plastik", weight = "30-80kg", size = "60x70x180cm"
        ),
        "sapu" to KnowledgeObject(
            name = "Sapu", category = "alat_kebersihan",
            parts = listOf("gagang", "ijuk_atau_serabut", "pengikat"),
            functions = listOf("menyapu_lantai", "membersihkan_debu", "mengumpulkan_sampah"),
            interactions = listOf("tangan_memegang_gagang", "ijuk_menyapu_lantai", "sampah_dikumpulkan"),
            dangers = listOf("gagang_patah", "ijuk_rontok", "tersandung_gagang"),
            contexts = listOf("rumah", "bengkel", "halaman"), relatedObjects = listOf("pengki", "tempat_sampah", "pel"),
            material = "kayu_plastik_ijuk", weight = "300-800gr", size = "100-150cm"
        ),
        "pel" to KnowledgeObject(
            name = "Pel", category = "alat_kebersihan",
            parts = listOf("gagang", "kain_pel", "ember", "pemeras"),
            functions = listOf("mengepel_lantai", "membersihkan_noda", "mengeringkan_lantai_basah"),
            interactions = listOf("kain_dicelup_ke_ember", "gagang_didorong", "lantai_dibersihkan"),
            dangers = listOf("lantai_basah_licin", "kain_kotor_menyebarkan_bakteri"),
            contexts = listOf("rumah"), relatedObjects = listOf("ember", "sabun_lantai", "sapu"),
            material = "aluminium_kain", weight = "500gr-1kg", size = "120-150cm"
        ),

        // ============================================
        // 3. LABORATORIUM (8 benda)
        // ============================================
        "mikroskop" to KnowledgeObject(
            name = "Mikroskop", category = "alat_lab",
            parts = listOf("lensa_okuler", "lensa_objektif", "meja_spesimen", "cermin", "fokus_kasar", "fokus_halus", "lengan", "dasar"),
            functions = listOf("melihat_benda_mikro", "memperbesar_gambar", "mengamati_sel", "mengamati_bakteri"),
            interactions = listOf("mata_melihat_lensa_okuler", "tangan_memutar_fokus", "spesimen_di_meja"),
            dangers = listOf("lensa_pecah", "fokus_terlalu_cepat_tabrak_lensa", "terkena_cairan_kimia"),
            contexts = listOf("laboratorium", "riset"), relatedObjects = listOf("kaca_preparat", "pipet", "spesimen"),
            material = "logam_kaca", weight = "3-8kg", size = "30-50cm"
        ),
        "tabung_reaksi" to KnowledgeObject(
            name = "Tabung Reaksi", category = "alat_lab",
            parts = listOf("badan_tabung", "mulut_tabung", "dasar_tabung", "tutup_karet"),
            functions = listOf("menampung_cairan_kimia", "mencampur_reagen", "memanaskan_larutan"),
            interactions = listOf("pipet_meneteskan_cairan", "bunsen_memanaskan", "rak_menyimpan"),
            dangers = listOf("tabung_pecah", "cairan_tumpah", "terlalu_panas_meletus", "kaca_tajam"),
            contexts = listOf("laboratorium"), relatedObjects = listOf("pipet", "bunsen", "rak_tabung", "sarung_tangan"),
            material = "kaca_borosilikat", weight = "20-100gr", size = "1-3x10-20cm"
        ),
        "gelas_ukur" to KnowledgeObject(
            name = "Gelas Ukur", category = "alat_lab",
            parts = listOf("badan", "skala_ukur", "bibir", "dasar", "corong_tuang"),
            functions = listOf("mengukur_volume_cairan", "menampung_larutan", "mencampur"),
            interactions = listOf("cairan_dituang", "mata_membaca_skala", "tangan_memegang"),
            dangers = listOf("gelas_pecah", "skala_tidak_terbaca", "cairan_kimia_berbahaya"),
            contexts = listOf("laboratorium"), relatedObjects = listOf("cairan_kimia", "pipet", "corong"),
            material = "kaca_borosilikat_plastik", weight = "50-500gr", size = "5-20cm"
        ),
        "pipet" to KnowledgeObject(
            name = "Pipet", category = "alat_lab",
            parts = listOf("badan_pipet", "ujung", "bulb_karet", "skala"),
            functions = listOf("mengambil_cairan", "memindahkan_cairan", "meneteskan_reagen"),
            interactions = listOf("bulb_ditekan", "cairan_terhisap", "ujung_diarahkan_ke_tabung"),
            dangers = listOf("cairan_terhisap_ke_mulut", "pipet_pecah", "cairan_berbahaya_terkena_kulit"),
            contexts = listOf("laboratorium"), relatedObjects = listOf("tabung_reaksi", "gelas_ukur", "cairan_kimia"),
            material = "kaca_plastik", weight = "10-50gr", size = "15-30cm"
        ),
        "bunsen" to KnowledgeObject(
            name = "Pembakar Bunsen", category = "alat_lab",
            parts = listOf("mulut_pembakar", "selang_gas", "pengatur_udara", "dasar"),
            functions = listOf("memanaskan_larutan", "sterilisasi", "sumber_api_lab"),
            interactions = listOf("gas_dinyalakan", "udara_diatur", "tabung_dipanaskan"),
            dangers = listOf("api_menyambar", "gas_bocor", "terbakar", "rambut_terbakar"),
            contexts = listOf("laboratorium"), relatedObjects = listOf("tabung_reaksi", "kasa", "kaki_tiga"),
            material = "logam", weight = "500gr-1kg", size = "15-20cm"
        ),
        "kaca_preparat" to KnowledgeObject(
            name = "Kaca Preparat", category = "alat_lab",
            parts = listOf("permukaan_kaca", "tepi", "penutup"),
            functions = listOf("alas_spesimen_mikroskop", "menjaga_spesimen_tetap_di_tempat"),
            interactions = listOf("spesimen_diletakkan", "kaca_di_atas_meja_mikroskop"),
            dangers = listOf("kaca_pecah_tajam", "terpotong", "spesimen_hilang"),
            contexts = listOf("laboratorium"), relatedObjects = listOf("mikroskop", "spesimen", "pipet"),
            material = "kaca", weight = "5-10gr", size = "2.5x7.5cm"
        ),
        "osiloskop" to KnowledgeObject(
            name = "Osiloskop", category = "alat_ukur_lab",
            parts = listOf("layar", "probe", "knob_volt_div", "knob_time_div", "channel", "trigger"),
            functions = listOf("melihat_sinyal_listrik", "mengukur_frekuensi", "mengukur_amplitudo"),
            interactions = listOf("probe_ditempelkan", "knob_diputar", "layar_menunjukkan_gelombang"),
            dangers = listOf("probe_tertukar", "tegangan_terlalu_tinggi", "korslet"),
            contexts = listOf("laboratorium", "bengkel_elektronik"), relatedObjects = listOf("probe", "multimeter", "pcb"),
            material = "plastik_elektronik", weight = "2-5kg", size = "30x20x40cm"
        ),
        "sarung_tangan_lateks" to KnowledgeObject(
            name = "Sarung Tangan Lateks", category = "alat_pelindung",
            parts = listOf("jari", "telapak", "pergelangan", "bahan_lateks"),
            functions = listOf("melindungi_tangan_dari_kimia", "menjaga_sterilitas", "mencegah_kontaminasi"),
            interactions = listOf("tangan_memakai", "jari_masuk", "pergelangan_ditarik"),
            dangers = listOf("lateks_robek", "alergi_lateks", "tidak_pakai_saat_perlu"),
            contexts = listOf("laboratorium", "rumah_sakit"), relatedObjects = listOf("bahan_kimia", "pasien"),
            material = "lateks_nitril", weight = "5-10gr", size = "S/M/L"
        ),

        // ============================================
        // 4. ELEKTRONIK (6 benda)
        // ============================================
        "smartphone" to KnowledgeObject(
            name = "Smartphone", category = "elektronik",
            parts = listOf("layar", "tombol_power", "tombol_volume", "kamera_depan", "kamera_belakang", "speaker", "mikrofon", "port_charger", "baterai", "sim_card", "sensor_sidik_jari"),
            functions = listOf("telepon", "kirim_pesan", "cari_informasi", "foto", "video", "aplikasi", "navigasi", "alarm"),
            interactions = listOf("tangan_memegang", "mata_melihat_layar", "jari_mengetik", "telinga_mendengar"),
            dangers = listOf("smartphone_jatuh", "mata_lelah", "baterai_habis", "terkena_air", "dicuri"),
            contexts = listOf("rumah", "bengkel", "mana_saja"), relatedObjects = listOf("charger", "headset", "powerbank"),
            material = "aluminium_kaca_plastik", weight = "150-250gr", size = "6-7inch"
        ),
        "laptop" to KnowledgeObject(
            name = "Laptop", category = "elektronik",
            parts = listOf("layar", "keyboard", "trackpad", "baterai", "port_usb", "port_hdmi", "kipas", "harddisk", "ram", "processor"),
            functions = listOf("komputasi", "mengetik", "browsing", "coding", "desain", "presentasi"),
            interactions = listOf("tangan_mengetik", "mata_melihat_layar", "trackpad_digunakan"),
            dangers = listOf("terkena_air", "jatuh", "overheat", "baterai_bocor", "mata_lelah"),
            contexts = listOf("rumah", "kantor", "cafe"), relatedObjects = listOf("charger", "mouse", "keyboard_external"),
            material = "aluminium_plastik", weight = "1-2.5kg", size = "13-15inch"
        ),
        "charger" to KnowledgeObject(
            name = "Charger", category = "elektronik",
            parts = listOf("kepala_charger", "kabel", "port_usb", "colokan_listrik", "adaptor"),
            functions = listOf("mengisi_baterai", "menyuplai_daya", "fast_charging"),
            interactions = listOf("colokan_ditancapkan", "kabel_dihubungkan_ke_device"),
            dangers = listOf("kabel_rusak_korslet", "charger_palsu_meledak", "overheat", "colokan_longgar"),
            contexts = listOf("rumah", "bengkel", "mana_saja"), relatedObjects = listOf("smartphone", "laptop", "powerbank"),
            material = "plastik_tembaga", weight = "50-200gr", size = "5x5x3cm"
        ),
        "headset" to KnowledgeObject(
            name = "Headset", category = "elektronik",
            parts = listOf("earcup", "driver", "mic", "headband", "kabel_atau_bluetooth", "bantalan"),
            functions = listOf("mendengarkan_audio", "telepon", "meredam_suara_luar", "mendengarkan_musik"),
            interactions = listOf("earcup_di_telinga", "mic_dekat_mulut", "headband_di_atas_kepala"),
            dangers = listOf("volume_terlalu_keras", "telinga_sakit", "kabel_terlilit_leher", "baterai_habis"),
            contexts = listOf("rumah", "bengkel", "jalan"), relatedObjects = listOf("smartphone", "laptop", "charger"),
            material = "plastik_kulit_sintetis", weight = "200-400gr", size = "15-20cm"
        ),
        "tv" to KnowledgeObject(
            name = "TV", category = "elektronik",
            parts = listOf("layar", "speaker", "port_hdmi", "remote", "stand_atau_braket", "power_supply"),
            functions = listOf("menonton_siaran", "streaming", "gaming", "display_kedua"),
            interactions = listOf("remote_ditekan", "mata_melihat_layar", "hdmi_dicolokkan"),
            dangers = listOf("tv_jatuh", "layar_tergores", "mata_lelah", "listrik_korslet"),
            contexts = listOf("rumah", "ruang_tamu"), relatedObjects = listOf("remote", "hdmi", "soundbar"),
            material = "plastik_kaca", weight = "5-20kg", size = "32-65inch"
        ),
        "kipas_angin" to KnowledgeObject(
            name = "Kipas Angin", category = "elektronik_rumah",
            parts = listOf("baling_baling", "motor", "grill_pelindung", "stand", "saklar", "kabel_power"),
            functions = listOf("mendinginkan_ruangan", "sirkulasi_udara", "mengusir_nyamuk"),
            interactions = listOf("baling_berputar", "angin_keluar", "saklar_ditekan"),
            dangers = listOf("jari_masuk_grill", "baling_patah", "motor_overheat", "kabel_terkelupas"),
            contexts = listOf("rumah", "bengkel"), relatedObjects = listOf("listrik", "remote"),
            material = "plastik_besi", weight = "2-5kg", size = "40-120cm"
        ),

        // ============================================
        // 5. KENDARAAN (5 benda)
        // ============================================
        "mobil" to KnowledgeObject(
            name = "Mobil", category = "kendaraan",
            parts = listOf("mesin", "ban", "stir", "rem", "gas", "kopling", "lampu_depan", "lampu_belakang", "spion", "jok", "sabuk_pengaman", "dashboard", "ac", "radio"),
            functions = listOf("transportasi", "mengangkut_orang", "mengangkut_barang", "berkendara_jauh"),
            interactions = listOf("tangan_memegang_stir", "kaki_menginjak_pedal", "mata_melihat_spion", "sabuk_dipakai"),
            dangers = listOf("kecelakaan", "ban_bocor", "rem_blong", "mesin_overheat", "mengantuk_saat_nyetir"),
            contexts = listOf("jalan_raya", "parkir", "bengkel"), relatedObjects = listOf("stir", "ban", "kunci_mobil", "bensin"),
            material = "baja_aluminium_plastik", weight = "1000-2000kg", size = "4-5meter"
        ),
        "motor" to KnowledgeObject(
            name = "Motor", category = "kendaraan",
            parts = listOf("mesin", "ban", "stang", "rem", "gas", "lampu", "spion", "jok", "standar", "helm", "klakson", "rantai"),
            functions = listOf("transportasi_cepat", "menerobos_macet", "berkendara_jarak_pendek"),
            interactions = listOf("tangan_memegang_stang", "kaki_menginjak_rem", "kepala_pakai_helm"),
            dangers = listOf("kecelakaan", "jatuh", "jalan_licin", "tidak_pakai_helm", "blind_spot"),
            contexts = listOf("jalan_raya", "parkir"), relatedObjects = listOf("helm", "stang", "ban", "bensin"),
            material = "baja_aluminium_plastik", weight = "100-150kg", size = "2meter"
        ),
        "sepeda" to KnowledgeObject(
            name = "Sepeda", category = "kendaraan",
            parts = listOf("rangka", "pedal", "rantai", "ban", "stang", "rem", "sadel", "gir", "lampu"),
            functions = listOf("transportasi_sehat", "olahraga", "rekreasi", "ramah_lingkungan"),
            interactions = listOf("kaki_mengayuh_pedal", "tangan_memegang_stang", "rem_ditarik"),
            dangers = listOf("rantai_lepas", "ban_bocor", "rem_blong", "jalan_licin", "tidak_pakai_helm"),
            contexts = listOf("jalan_raya", "taman", "pedesaan"), relatedObjects = listOf("helm", "pompa_ban", "lampu"),
            material = "baja_aluminium", weight = "10-15kg", size = "1.5-2meter"
        ),
        "bus" to KnowledgeObject(
            name = "Bus", category = "kendaraan",
            parts = listOf("mesin", "ban", "stir", "pintu_otomatis", "kursi_penumpang", "ac", "bagasi", "kaca_besar"),
            functions = listOf("transportasi_umum", "mengangkut_banyak_orang", "perjalanan_jauh"),
            interactions = listOf("penumpang_duduk", "pintu_terbuka", "sopir_mengemudi"),
            dangers = listOf("kecelakaan_besar", "rem_blong", "penumpang_berdiri_jatuh", "bus_terbakar"),
            contexts = listOf("jalan_raya", "terminal", "jalan_tol"), relatedObjects = listOf("halte", "tiket", "sopir"),
            material = "baja_kaca", weight = "10-15ton", size = "12meter"
        ),
        "truk" to KnowledgeObject(
            name = "Truk", category = "kendaraan",
            parts = listOf("mesin_diesel", "ban_besar", "bak_muatan", "stir", "rem_angin", "lampu", "spion_besar", "klakson_keras"),
            functions = listOf("mengangkut_barang_berat", "logistik", "konstruksi", "pengiriman"),
            interactions = listOf("barang_di_bak", "sopir_mengemudi", "rem_angin_bunyi"),
            dangers = listOf("muatan_terlepas", "rem_blong", "blind_spot_besar", "terguling_di_tikungan"),
            contexts = listOf("jalan_raya", "pelabuhan", "pabrik"), relatedObjects = listOf("muatan", "rantai_pengikat", "sopir"),
            material = "baja_besi", weight = "5-20ton", size = "8-12meter"
        ),

        // ============================================
        // 6. ALAM (5 benda)
        // ============================================
        "pohon" to KnowledgeObject(
            name = "Pohon", category = "alam",
            parts = listOf("akar", "batang", "dahan", "ranting", "daun", "buah", "bunga", "kulit_kayu"),
            functions = listOf("menghasilkan_oksigen", "menyerap_CO2", "tempat_berteduh", "sumber_kayu", "sumber_buah", "habitat_hewan"),
            interactions = listOf("orang_berteduh", "daun_jatuh", "buah_dipetik", "burung_bersarang"),
            dangers = listOf("dahan_patah_jatuh", "pohon_tumbang_badai", "petir_menyambar", "akar_mengangkat_jalan"),
            contexts = listOf("luar_ruangan", "taman", "hutan", "jalan"), relatedObjects = listOf("tanah", "air", "matahari"),
            material = "kayu_daun", weight = "bervariasi", size = "1-50meter"
        ),
        "batu" to KnowledgeObject(
            name = "Batu", category = "alam",
            parts = listOf("permukaan", "inti", "retakan", "lapisan_mineral"),
            functions = listOf("bahan_bangunan", "alat_pukul_primitif", "penghias_taman", "penahan_tanah"),
            interactions = listOf("batu_di_tanah", "orang_menginjak", "batu_dilempar"),
            dangers = listOf("batu_jatuh_dari_ketinggian", "batu_tajam_melukai", "tanah_longsor"),
            contexts = listOf("luar_ruangan", "gunung", "sungai", "jalan"), relatedObjects = listOf("tanah", "air", "pasir"),
            material = "mineral", weight = "bervariasi", size = "1cm-10meter"
        ),
        "air" to KnowledgeObject(
            name = "Air", category = "cairan",
            parts = listOf("molekul_H2O"),
            functions = listOf("diminum", "membersihkan", "mendinginkan", "pelarut", "mengairi_tanaman", "pendingin_mesin"),
            interactions = listOf("gelas_berisi_air", "mulut_minum", "air_mengalir", "air_membasahi", "air_mendidih"),
            dangers = listOf("air_kotor_menyebabkan_sakit", "air_tumpah_lantai_licin", "air_dekat_listrik", "banjir", "tenggelam"),
            contexts = listOf("rumah", "bengkel", "dapur", "luar_ruangan", "semua_tempat"),
            relatedObjects = listOf("gelas", "botol", "keran", "pipa"),
            material = "cairan", weight = "1kg/liter", size = "molekuler"
        ),
        "api" to KnowledgeObject(
            name = "Api", category = "energi",
            parts = listOf("nyala", "bara", "asap", "panas"),
            functions = listOf("memanaskan", "memasak", "menerangi", "membakar", "sumber_energi"),
            interactions = listOf("api_menyala_di_kompor", "api_membakar_benda", "tangan_menjauh", "orang_menghangatkan"),
            dangers = listOf("api_membakar_kulit", "kebakaran", "asap_beracun", "api_menyambar_bensin"),
            contexts = listOf("dapur", "bengkel", "luar_ruangan", "darurat"), relatedObjects = listOf("kompor", "bensin", "kayu", "pemadam_api"),
            material = "plasma_panas", weight = "hampir_tidak_ada", size = "bervariasi"
        ),
        "tanah" to KnowledgeObject(
            name = "Tanah", category = "alam",
            parts = listOf("butiran", "humus", "mineral", "air_tanah", "organisme"),
            functions = listOf("tempat_tumbuh_tanaman", "fondasi_bangunan", "menyaring_air", "sumber_mineral"),
            interactions = listOf("akar_tumbuh", "orang_menginjak", "cangkul_menggali", "air_meresap"),
            dangers = listOf("tanah_longsor", "tanah_tercemar", "lubang_tersembunyi", "pasir_hisap"),
            contexts = listOf("luar_ruangan", "kebun", "hutan"), relatedObjects = listOf("pohon", "batu", "air", "cangkul"),
            material = "campuran_mineral_organik", weight = "bervariasi", size = "planet"
        ),

        // ============================================
        // 7. BANGUNAN (4 benda)
        // ============================================
        "pintu" to KnowledgeObject(
            name = "Pintu", category = "bangunan",
            parts = listOf("daun_pintu", "gagang", "engsel", "kunci", "lubang_kunci", "spion", "kusen"),
            functions = listOf("akses_masuk", "akses_keluar", "pembatas_ruangan", "keamanan", "privasi"),
            interactions = listOf("tangan_memutar_gagang", "orang_melewati_pintu", "kunci_dimasukkan"),
            dangers = listOf("pintu_tertutup_tiba_tiba", "tangan_terjepit", "pintu_terkunci", "kunci_patah"),
            contexts = listOf("rumah", "bengkel", "semua_ruangan"), relatedObjects = listOf("kunci", "gagang", "engsel"),
            material = "kayu_kaca_besi", weight = "10-50kg", size = "80x210cm"
        ),
        "jendela" to KnowledgeObject(
            name = "Jendela", category = "bangunan",
            parts = listOf("kaca", "bingkai", "engsel", "tirai", "kunci_jendela", "kusen"),
            functions = listOf("sirkulasi_udara", "masuknya_cahaya", "melihat_keluar", "ventilasi"),
            interactions = listOf("jendela_dibuka", "tirai_ditarik", "mata_melihat_keluar"),
            dangers = listOf("kaca_pecah", "jendela_jatuh", "anak_jatuh_dari_jendela", "angin_kencang"),
            contexts = listOf("rumah", "kantor"), relatedObjects = listOf("tirai", "kunci_jendela"),
            material = "kaca_kayu_aluminium", weight = "5-20kg", size = "60x120cm"
        ),
        "tangga" to KnowledgeObject(
            name = "Tangga", category = "bangunan",
            parts = listOf("anak_tangga", "pegangan_handrail", "bordes", "struktur"),
            functions = listOf("naik_antar_lantai", "turun_antar_lantai", "akses_vertikal"),
            interactions = listOf("kaki_menginjak", "tangan_memegang_handrail", "orang_menaiki"),
            dangers = listOf("terpeleset", "tersandung", "pegangan_longgar", "anak_terjatuh", "tangga_gelap"),
            contexts = listOf("rumah", "kantor", "mall"), relatedObjects = listOf("handrail", "lampu_tangga"),
            material = "beton_kayu_besi", weight = "bervariasi", size = "bervariasi"
        ),
        "atap" to KnowledgeObject(
            name = "Atap", category = "bangunan",
            parts = listOf("genteng_atau_seng", "rangka", "talang_air", "plafon", "bubungan"),
            functions = listOf("melindungi_dari_hujan", "melindungi_dari_panas", "pelindung_cuaca"),
            interactions = listOf("hujan_jatuh_di_atas_atap", "orang_berteduh", "burung_hinggap"),
            dangers = listOf("genteng_longsor", "atap_bocor", "rangka_keropos", "orang_jatuh_dari_atap"),
            contexts = listOf("rumah", "bangunan"), relatedObjects = listOf("genteng", "talang", "plafon"),
            material = "genteng_tanah_liat_seng_beton", weight = "bervariasi", size = "seluas_bangunan"
        ),

        // ============================================
        // 8. PENDIDIKAN (4 benda)
        // ============================================
        "buku" to KnowledgeObject(
            name = "Buku", category = "pendidikan",
            parts = listOf("cover", "halaman", "spine", "daftar_isi", "bab"),
            functions = listOf("sumber_pengetahuan", "bacaan", "referensi", "hiburan", "pencatatan"),
            interactions = listOf("tangan_memegang", "mata_membaca", "halaman_dibuka", "pembatas_disimpan"),
            dangers = listOf("buku_basah_rusak", "kertas_tergores", "api_membakar", "rayap"),
            contexts = listOf("rumah", "sekolah", "perpustakaan"), relatedObjects = listOf("pena", "pembatas_buku", "rak_buku"),
            material = "kertas_karton", weight = "100gr-2kg", size = "A5-A4"
        ),
        "pena" to KnowledgeObject(
            name = "Pena/Pulpen", category = "pendidikan",
            parts = listOf("mata_pena", "tinta", "gagang", "tutup", "klip"),
            functions = listOf("menulis", "menggambar", "menandatangani", "mencatat"),
            interactions = listOf("tangan_memegang", "mata_pena_menulis_di_kertas", "tinta_keluar"),
            dangers = listOf("tinta_bocor", "mata_pena_tajam", "tertelan_tutup"),
            contexts = listOf("rumah", "sekolah", "kantor"), relatedObjects = listOf("kertas", "buku", "penggaris"),
            material = "plastik_logam_tinta", weight = "10-30gr", size = "15cm"
        ),
        "papan_tulis" to KnowledgeObject(
            name = "Papan Tulis", category = "pendidikan",
            parts = listOf("permukaan_tulis", "bingkai", "penghapus", "spidol", "tempat_spidol"),
            functions = listOf("menulis_materi", "menjelaskan", "presentasi", "mencatat_ide"),
            interactions = listOf("spidol_menulis", "penghapus_membersihkan", "mata_membaca"),
            dangers = listOf("debu_kapur", "spidol_permanen_salah_gunakan", "papan_jatuh"),
            contexts = listOf("sekolah", "kantor", "ruang_rapat"), relatedObjects = listOf("spidol", "penghapus", "proyektor"),
            material = "kayu_plastik_whiteboard", weight = "3-10kg", size = "120x240cm"
        ),
        "proyektor" to KnowledgeObject(
            name = "Proyektor", category = "elektronik_pendidikan",
            parts = listOf("lensa", "lampu", "port_hdmi_vga", "kipas", "speaker", "remote", "fokus"),
            functions = listOf("menampilkan_gambar_besar", "presentasi", "nonton_bareng", "mengajar"),
            interactions = listOf("cahaya_dipancarkan", "lensa_difokuskan", "laptop_dihubungkan"),
            dangers = listOf("lampu_panas", "kipas_macet_overheat", "lensa_pecah", "tersandung_kabel"),
            contexts = listOf("sekolah", "kantor", "ruang_rapat"), relatedObjects = listOf("laptop", "layar", "hdmi", "remote"),
            material = "plastik_kaca", weight = "2-5kg", size = "30x25x10cm"
        ),

        // ============================================
        // 9. MEDIS (4 benda)
        // ============================================
        "stetoskop" to KnowledgeObject(
            name = "Stetoskop", category = "alat_medis",
            parts = listOf("earpiece", "tube", "chestpiece", "bell", "diaphragm"),
            functions = listOf("mendengarkan_suara_jantung", "mendengarkan_suara_paru", "mendengarkan_suara_usus"),
            interactions = listOf("earpiece_di_telinga_dokter", "chestpiece_di_dada_pasien"),
            dangers = listOf("tube_rusak", "earpiece_kotor", "salah_diagnosis"),
            contexts = listOf("rumah_sakit", "klinik"), relatedObjects = listOf("pasien", "dokter", "tensimeter"),
            material = "karet_logam", weight = "200-400gr", size = "70cm"
        ),
        "tensimeter" to KnowledgeObject(
            name = "Tensimeter", category = "alat_medis",
            parts = listOf("manset", "gauge_jarum", "pompa", "katup", "selang"),
            functions = listOf("mengukur_tekanan_darah", "sistolik", "diastolik", "deteksi_hipertensi"),
            interactions = listOf("manset_dililitkan", "pompa_ditekan", "gauge_dibaca"),
            dangers = listOf("manset_terlalu_ketat", "gauge_rusak", "salah_baca"),
            contexts = listOf("rumah_sakit", "klinik"), relatedObjects = listOf("stetoskop", "pasien"),
            material = "kain_karet_logam", weight = "500gr-1kg", size = "15x10cm"
        ),
        "termometer" to KnowledgeObject(
            name = "Termometer", category = "alat_medis",
            parts = listOf("ujung_sensor", "layar", "baterai", "tombol", "body"),
            functions = listOf("mengukur_suhu_tubuh", "deteksi_demam", "suhu_ruangan"),
            interactions = listOf("ujung_ditempelkan", "layar_dibaca", "tombol_ditekan"),
            dangers = listOf("baterai_bocor", "pecah_merkuri", "salah_baca"),
            contexts = listOf("rumah_sakit", "rumah"), relatedObjects = listOf("pasien", "obat"),
            material = "plastik_logam", weight = "50-150gr", size = "10-15cm"
        ),
        "jarum_suntik" to KnowledgeObject(
            name = "Jarum Suntik", category = "alat_medis",
            parts = listOf("jarum", "barrel", "plunger", "tutup_jarum", "skala_ukur"),
            functions = listOf("menyuntikkan_obat", "mengambil_darah", "memberi_vaksin"),
            interactions = listOf("jarum_ditusukkan", "plunger_didorong", "cairan_masuk_ke_tubuh"),
            dangers = listOf("jarum_tajam_tertusuk", "infeksi", "obat_salah_dosis", "jarum_bekas_pakai_ulang"),
            contexts = listOf("rumah_sakit", "klinik"), relatedObjects = listOf("obat", "kapas_alkohol", "perban"),
            material = "plastik_logam", weight = "5-20gr", size = "5-10cm"
        ),

        // ============================================
        // 10. LAIN-LAIN (4 benda)
        // ============================================
        "kunci_gembok" to KnowledgeObject(
            name = "Kunci Gembok", category = "keamanan",
            parts = listOf("badan", "lubang_kunci", "shackle", "silinder", "pegas"),
            functions = listOf("mengunci", "mengamankan_barang", "mencegah_pencurian"),
            interactions = listOf("shackle_dimasukkan", "kunci_diputar", "gembok_tertutup"),
            dangers = listOf("kunci_hilang", "gembok_berkarat", "shackle_digergaji"),
            contexts = listOf("bengkel", "rumah", "pagar"), relatedObjects = listOf("kunci", "rantai", "pintu"),
            material = "baja_kuningan", weight = "100-500gr", size = "5-10cm"
        ),
        "tali" to KnowledgeObject(
            name = "Tali", category = "alat_bantu",
            parts = listOf("serat", "ujung", "simpul", "anyaman"),
            functions = listOf("mengikat", "menahan_beban", "menarik", "menggantung"),
            interactions = listOf("tali_diikatkan", "simpul_dibuat", "beban_ditarik"),
            dangers = listOf("tali_putus", "simpul_lepas", "tangan_terbakar_gesekan", "terlilit_leher"),
            contexts = listOf("bengkel", "luar_ruangan", "rumah"), relatedObjects = listOf("beban", "kait", "gunting"),
            material = "nilon_katun_serat_alami", weight = "bervariasi", size = "1-100meter"
        ),
        "lakban" to KnowledgeObject(
            name = "Lakban/Selotip", category = "alat_bantu",
            parts = listOf("gulungan", "perekat", "inti_karton", "permukaan_luar"),
            functions = listOf("merekatkan", "menempelkan", "menambal_sementara", "menandai"),
            interactions = listOf("lakban_ditarik", "ditempelkan", "digunting", "direkatkan"),
            dangers = listOf("perekat_mengering", "terlalu_lengket_sulit_dilepas"),
            contexts = listOf("bengkel", "rumah", "kantor"), relatedObjects = listOf("gunting", "kardus", "plastik"),
            material = "plastik_perekat", weight = "100-300gr", size = "2-5cm_lebar"
        ),
        "baterai" to KnowledgeObject(
            name = "Baterai", category = "sumber_daya",
            parts = listOf("anoda", "katoda", "elektrolit", "terminal_positif", "terminal_negatif", "casing"),
            functions = listOf("menyimpan_energi", "menyuplai_listrik", "sumber_daya_portabel"),
            interactions = listOf("baterai_dimasukkan", "terminal_dihubungkan", "alat_menyala"),
            dangers = listOf("baterai_bocor", "meledak", "tertelan_anak", "korslet", "dibuang_sembarangan"),
            contexts = listOf("rumah", "bengkel", "elektronik"), relatedObjects = listOf("charger", "alat_elektronik"),
            material = "logam_kimia", weight = "10-100gr", size = "AA/AAA/18650"
        )
    )
    
    fun getObject(name: String): KnowledgeObject? = allObjects[name.lowercase()]
    fun getObjectsByCategory(category: String): List<KnowledgeObject> =
        allObjects.values.filter { it.category == category.lowercase() }
    fun getDangerousObjects(): List<KnowledgeObject> =
        allObjects.values.filter { it.dangers.size >= 5 }.sortedByDescending { it.dangers.size }
    fun findObjectByContext(context: String): List<KnowledgeObject> =
        allObjects.values.filter { it.contexts.any { c -> c.contains(context.lowercase(), true) } }
    fun findObjectByInteraction(interaction: String): List<KnowledgeObject> =
        allObjects.values.filter { it.interactions.any { i -> i.contains(interaction.lowercase(), true) } }
}