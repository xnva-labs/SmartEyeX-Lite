package com.smarteyex.lite

data class ProfessionData(
    val name: String,
    val category: String,
    val tasks: List<String>,
    val tools: List<String>,
    val environment: List<String>,
    val dangers: List<String>,
    val requiredKnowledge: List<String>,
    val ethics: List<String>,
    val workHours: String,
    val physicalDemand: String,
    val mentalDemand: String
)

class ProfessionSystem {
    
    val allProfessions: Map<String, ProfessionData> = mapOf(
        // ===== TEKNIK & MESIN =====
        "mekanik" to ProfessionData(
            name = "Mekanik", category = "teknik",
            tasks = listOf("memperbaiki_mesin", "merawat_kendaraan", "mendiagnosis_kerusakan", "mengganti_sparepart", "tune_up_mesin", "bongkar_pasang_mesin", "servis_berkala", "cek_kelistrikan"),
            tools = listOf("kunci_inggris", "obeng", "tang", "palu", "dongkrak", "kunci_sok", "multimeter", "scanner_obd", "kompresor", "kunci_momen", "obeng_ketok"),
            environment = listOf("bengkel", "garasi", "jalan_raya", "panas", "bising", "berminyak"),
            dangers = listOf("tertimpa_kendaraan", "tangan_terjepit", "oli_panas", "gas_buang_beracun", "tersengat_listrik", "mata_terkena_percikan", "punggung_sakit_angkat_berat"),
            requiredKnowledge = listOf("mesin_pembakaran", "kelistrikan_kendaraan", "transmisi", "sistem_bahan_bakar", "sistem_pendingin", "sistem_rem", "sistem_kemudi"),
            ethics = listOf("tidak_mengganti_sparepart_yang_masih_bagus", "transparan_soal_biaya", "tepat_waktu", "jujur_soal_kerusakan", "pakai_sparepart_asli"),
            workHours = "8-12 jam/hari", physicalDemand = "TINGGI", mentalDemand = "SEDANG"
        ),
        "operator_bubut" to ProfessionData(
            name = "Operator Bubut", category = "teknik",
            tasks = listOf("mengoperasikan_mesin_bubut", "membaca_gambar_teknik", "mengukur_benda_kerja", "memasang_mata_pisau", "mengatur_kecepatan_potong", "mengecek_hasil_bubutan", "merawat_mesin_bubut", "mengganti_coolant"),
            tools = listOf("mesin_bubut", "engkol", "chuck", "mata_pisau", "jangka_sorong", "mikrometer", "kacamata_pelindung", "coolant", "kunci_chuck", "dial_indicator", "penitik"),
            environment = listOf("bengkel", "workshop", "pabrik", "bising", "berminyak", "panas"),
            dangers = listOf("tangan_terkena_chuck", "beram_panas", "mata_pisau_patah", "benda_terlepas", "tidak_pakai_kacamata", "sarung_tangan_terlilit", "tersandung_kabel"),
            requiredKnowledge = listOf("gambar_teknik", "material_logam", "kecepatan_potong", "sudut_pisau", "pendingin", "toleransi_ukuran", "geometri_pahat"),
            ethics = listOf("utamakan_keselamatan", "jaga_kualitas", "rawat_mesin", "jangan_memaksa_mesin_rusak", "bersihkan_setelah_pakai"),
            workHours = "8-10 jam/hari", physicalDemand = "TINGGI", mentalDemand = "TINGGI"
        ),
        "operator_las" to ProfessionData(
            name = "Operator Las", category = "teknik",
            tasks = listOf("mengelas_logam", "menyambung_konstruksi", "memotong_logam_tebal", "membersihkan_terak", "membaca_gambar_konstruksi"),
            tools = listOf("mesin_las", "elektroda", "helm_las", "sarung_tangan_las", "apron", "palu_terak", "sikat_kawat", "gerinda"),
            environment = listOf("bengkel", "konstruksi", "pabrik", "panas", "berbahaya"),
            dangers = listOf("percikan_api", "sinar_las_melukai_mata", "gas_beracun", "tersengat_listrik", "kulit_terbakar", "kebakaran"),
            requiredKnowledge = listOf("teknik_pengelasan", "jenis_elektroda", "arus_listrik", "metalurgi_dasar", "keselamatan_kerja"),
            ethics = listOf("pakai_apd_lengkap", "jaga_kualitas_las", "bersihkan_area_kerja", "jangan_las_dekat_bahan_mudah_terbakar"),
            workHours = "8-10 jam/hari", physicalDemand = "SANGAT_TINGGI", mentalDemand = "SEDANG"
        ),
        "teknisi_listrik" to ProfessionData(
            name = "Teknisi Listrik", category = "teknik",
            tasks = listOf("instalasi_listrik", "perbaikan_kelistrikan", "cek_tegangan", "pasang_panel", "tarik_kabel", "troubleshooting"),
            tools = listOf("multimeter", "tang_potong", "obeng_listrik", "tang_kupas", "solder", "tinel", "test_pen", "tangga"),
            environment = listOf("bengkel", "gedung", "rumah", "pabrik", "berbahaya"),
            dangers = listOf("tersengat_listrik", "jatuh_dari_ketinggian", "kabel_terkelupas", "korslet", "kebakaran_listrik"),
            requiredKnowledge = listOf("hukum_ohm", "rangkaian_listrik", "sistem_3_fasa", "panel_distribusi", "keselamatan_listrik", "PUIL"),
            ethics = listOf("matikan_sumber_listrik_sebelum_bekerja", "pakai_sarung_tangan_isolasi", "jangan_asal_sambung", "beri_label_panel"),
            workHours = "8-12 jam/hari", physicalDemand = "TINGGI", mentalDemand = "TINGGI"
        ),

        // ===== TEKNOLOGI =====
        "programmer" to ProfessionData(
            name = "Programmer", category = "teknologi",
            tasks = listOf("menulis_kode", "debugging", "mendesain_sistem", "testing", "dokumentasi", "meeting_tim", "belajar_teknologi_baru", "code_review"),
            tools = listOf("komputer", "keyboard", "layar", "ide", "git", "terminal", "database", "server", "api", "dokumentasi", "stackoverflow"),
            environment = listOf("kantor", "rumah", "ruang_kerja", "cafe", "duduk_lama"),
            dangers = listOf("mata_lelah", "punggung_sakit", "karpal_tunnel", "stres_deadline", "burnout", "obesitas", "mata_minus"),
            requiredKnowledge = listOf("algoritma", "struktur_data", "bahasa_pemrograman", "database", "jaringan", "sistem_operasi", "design_pattern", "version_control"),
            ethics = listOf("tidak_menulis_kode_berbahaya", "jaga_privasi_user", "tidak_mencuri_kode", "dokumentasi_baik", "bantu_junior", "tidak_sengaja_bikin_bug"),
            workHours = "8-14 jam/hari", physicalDemand = "RENDAH", mentalDemand = "SANGAT_TINGGI"
        ),
        "data_scientist" to ProfessionData(
            name = "Data Scientist", category = "teknologi",
            tasks = listOf("mengumpulkan_data", "membersihkan_data", "membuat_model_ml", "analisis_statistik", "visualisasi_data", "presentasi_hasil"),
            tools = listOf("komputer", "python", "r", "tensorflow", "pandas", "numpy", "jupyter", "sql", "tableau"),
            environment = listOf("kantor", "rumah", "lab_riset"),
            dangers = listOf("mata_lelah", "stres", "salah_interpretasi_data", "bias_data"),
            requiredKnowledge = listOf("statistik", "machine_learning", "python", "sql", "visualisasi", "domain_knowledge"),
            ethics = listOf("jangan_manipulasi_data", "hormati_privasi_data", "akui_keterbatasan_model", "jangan_overfit"),
            workHours = "8-12 jam/hari", physicalDemand = "RENDAH", mentalDemand = "SANGAT_TINGGI"
        ),

        // ===== KESEHATAN =====
        "dokter" to ProfessionData(
            name = "Dokter", category = "kesehatan",
            tasks = listOf("mendiagnosis_penyakit", "meresepkan_obat", "pemeriksaan_fisik", "operasi", "konsultasi", "mencatat_rekam_medis", "emergency"),
            tools = listOf("stetoskop", "tensimeter", "termometer", "suntikan", "obat", "masker", "sarung_tangan_medis", "alat_bedah", "ekg"),
            environment = listOf("rumah_sakit", "klinik", "puskesmas", "igd", "steril"),
            dangers = listOf("tertular_penyakit", "jarum_tertusuk", "kelelahan_jaga_malam", "malpraktik", "stres_tinggi", "cairan_tubuh"),
            requiredKnowledge = listOf("anatomi", "farmakologi", "patologi", "bedah", "penyakit_dalam", "emergency", "radiologi"),
            ethics = listOf("sumpah_dokter", "rahasia_pasien", "tidak_diskriminasi", "utamakan_keselamatan_pasien", "terus_belajar", "informed_consent"),
            workHours = "12-24 jam/shift", physicalDemand = "TINGGI", mentalDemand = "SANGAT_TINGGI"
        ),
        "perawat" to ProfessionData(
            name = "Perawat", category = "kesehatan",
            tasks = listOf("merawat_pasien", "memberi_obat", "cek_tanda_vital", "membantu_dokter", "mencatat_kondisi_pasien", "mengganti_perban"),
            tools = listOf("tensimeter", "termometer", "infus", "obat", "perban", "sarung_tangan", "masker", "alat_suntik"),
            environment = listOf("rumah_sakit", "klinik", "igd", "rawat_inap"),
            dangers = listOf("tertular_penyakit", "jarum_tertusuk", "kelelahan", "pasien_agresif", "sakit_punggung_angkat_pasien"),
            requiredKnowledge = listOf("keperawatan_dasar", "farmakologi_dasar", "anatomi", "emergency", "komunikasi_pasien"),
            ethics = listOf("hormati_pasien", "jaga_rahasia_medis", "jangan_menghakimi", "rawat_dengan_sepenuh_hati"),
            workHours = "8-12 jam/shift", physicalDemand = "SANGAT_TINGGI", mentalDemand = "TINGGI"
        ),

        // ===== PENDIDIKAN =====
        "guru" to ProfessionData(
            name = "Guru", category = "pendidikan",
            tasks = listOf("mengajar", "membuat_rpp", "menilai_tugas", "membimbing_siswa", "rapat_sekolah", "belajar_materi_baru", "konsultasi_orang_tua"),
            tools = listOf("papan_tulis", "spidol", "buku", "proyektor", "laptop", "pena", "penggaris", "penghapus"),
            environment = listOf("sekolah", "kelas", "perpustakaan", "ruang_guru"),
            dangers = listOf("kelelahan_suara", "stres_murid_bandel", "pegal_berdiri_lama", "tertular_penyakit_murid"),
            requiredKnowledge = listOf("pedagogi", "materi_ajar", "psikologi_anak", "manajemen_kelas", "teknologi_pendidikan"),
            ethics = listOf("tidak_pilih_kasih", "jaga_nama_baik_sekolah", "tidak_merendahkan_siswa", "beri_teladan_baik", "jaga_rahasia_siswa"),
            workHours = "8-10 jam/hari", physicalDemand = "SEDANG", mentalDemand = "TINGGI"
        ),
        "dosen" to ProfessionData(
            name = "Dosen", category = "pendidikan",
            tasks = listOf("mengajar_kuliah", "riset", "menulis_jurnal", "membimbing_skripsi", "pengabdian_masyarakat", "seminar"),
            tools = listOf("laptop", "proyektor", "buku_referensi", "lab", "jurnal_ilmiah", "komputer"),
            environment = listOf("universitas", "kelas", "lab", "ruang_dosen"),
            dangers = listOf("stres_publikasi", "deadline_riset", "mahasiswa_bandel", "beban_administratif"),
            requiredKnowledge = listOf("bidang_keahlian", "metode_riset", "statistik", "penulisan_ilmiah", "public_speaking"),
            ethics = listOf("jujur_dalam_riset", "tidak_plagiat", "bimbing_dengan_tulus", "hormati_mahasiswa"),
            workHours = "8-12 jam/hari", physicalDemand = "RENDAH", mentalDemand = "SANGAT_TINGGI"
        ),

        // ===== PERTANIAN & ALAM =====
        "petani" to ProfessionData(
            name = "Petani", category = "pertanian",
            tasks = listOf("menanam", "memupuk", "menyiram", "memanen", "mengolah_tanah", "mengusir_hama", "menjual_hasil"),
            tools = listOf("cangkul", "sabit", "traktor", "pupuk", "pestisida", "irigasi", "keranjang", "sarung_tangan"),
            environment = listOf("sawah", "kebun", "ladang", "luar_ruangan", "panas"),
            dangers = listOf("sengatan_matahari", "gigitan_ular", "terkena_pestisida", "alat_pertanian_melukai", "kelelahan_fisik", "gagal_panen"),
            requiredKnowledge = listOf("ilmu_tanah", "cuaca", "hama_tanaman", "pupuk", "irigasi", "musim_tanam", "pemasaran"),
            ethics = listOf("jaga_kesuburan_tanah", "tidak_pakai_pestisida_berlebihan", "hemat_air", "hormati_alam", "jual_dengan_harga_wajar"),
            workHours = "6-12 jam/hari", physicalDemand = "SANGAT_TINGGI", mentalDemand = "SEDANG"
        ),
        "nelayan" to ProfessionData(
            name = "Nelayan", category = "kelautan",
            tasks = listOf("melaut", "menangkap_ikan", "merawat_jaring", "menjual_hasil", "merawat_perahu"),
            tools = listOf("perahu", "jaring", "pancing", "pelampung", "gps", "radio", "es_box"),
            environment = listOf("laut", "pelabuhan", "luar_ruangan", "basah", "berbahaya"),
            dangers = listOf("tenggelam", "badai", "gelombang_besar", "hipotermia", "dehidrasi", "hiu"),
            requiredKnowledge = listOf("navigasi", "cuaca_laut", "jenis_ikan", "teknik_menangkap", "keselamatan_laut"),
            ethics = listOf("jangan_tangkap_ikan_langka", "jangan_pakai_bom", "hormati_laut", "jual_segar"),
            workHours = "8-16 jam/hari", physicalDemand = "SANGAT_TINGGI", mentalDemand = "SEDANG"
        ),

        // ===== KEAMANAN =====
        "polisi" to ProfessionData(
            name = "Polisi", category = "keamanan",
            tasks = listOf("menjaga_keamanan", "patroli", "menangkap_penjahat", "menyelidiki_kasus", "mengatur_lalu_lintas", "melayani_masyarakat"),
            tools = listOf("senjata_api", "borgol", "pentungan", "radio", "mobil_patroli", "rompi", "sentolop"),
            environment = listOf("jalan_raya", "kantor_polisi", "lapangan", "berbahaya", "malam_hari"),
            dangers = listOf("diserang_penjahat", "kecelakaan_saat_kejar", "stres_tinggi", "senjata_api_meledak_sendiri"),
            requiredKnowledge = listOf("hukum_pidana", "bela_diri", "penyelidikan", "lalu_lintas", "hak_asasi_manusia"),
            ethics = listOf("jangan_salahgunakan_wewenang", "lindungi_yang_lemah", "jujur", "tidak_menerima_suap", "hormati_ham"),
            workHours = "8-12 jam/shift", physicalDemand = "TINGGI", mentalDemand = "TINGGI"
        ),
        "tentara" to ProfessionData(
            name = "Tentara", category = "keamanan",
            tasks = listOf("menjaga_kedaulatan", "operasi_militer", "bantu_bencana", "latihan_tempur", "jaga_perbatasan"),
            tools = listOf("senjata_api", "seragam_militer", "kendaraan_taktis", "radio", "ransel", "helm", "rompi_anti_peluru"),
            environment = listOf("markas", "perbatasan", "medan_perang", "hutan", "ekstrim"),
            dangers = listOf("tertembak", "ranjau", "serangan_musuh", "cedera_berat", "stres_pasca_perang", "meninggal"),
            requiredKnowledge = listOf("strategi_militer", "bela_diri", "senjata", "navigasi", "survival", "pertolongan_pertama"),
            ethics = listOf("bela_negara", "lindungi_rakyat", "patuhi_komandan", "jangan_serang_sipil", "hormati_jenewa"),
            workHours = "24 jam/siaga", physicalDemand = "SANGAT_TINGGI", mentalDemand = "SANGAT_TINGGI"
        ),

        // ===== BISNIS & KEUANGAN =====
        "pengusaha" to ProfessionData(
            name = "Pengusaha", category = "bisnis",
            tasks = listOf("memimpin_perusahaan", "mengambil_keputusan", "mencari_investor", "mengelola_karyawan", "pemasaran", "strategi_bisnis"),
            tools = listOf("laptop", "smartphone", "dokumen", "laporan_keuangan", "presentasi"),
            environment = listOf("kantor", "meeting_room", "cafe", "fleksibel"),
            dangers = listOf("stres_tinggi", "bangkrut", "ditipu", "burnout", "jam_kerja_tidak_teratur"),
            requiredKnowledge = listOf("manajemen", "keuangan", "marketing", "hukum_bisnis", "leadership", "negosiasi"),
            ethics = listOf("jangan_korupsi", "bayar_pajak", "hargai_karyawan", "jangan_tipu_konsumen", "csr"),
            workHours = "12-16 jam/hari", physicalDemand = "RENDAH", mentalDemand = "SANGAT_TINGGI"
        ),
        "akuntan" to ProfessionData(
            name = "Akuntan", category = "keuangan",
            tasks = listOf("mencatat_keuangan", "membuat_laporan", "audit", "hitung_pajak", "konsultasi_keuangan"),
            tools = listOf("komputer", "kalkulator", "excel", "software_akuntansi", "dokumen", "printer"),
            environment = listOf("kantor", "rumah", "duduk_lama"),
            dangers = listOf("mata_lelah", "stres_deadline", "salah_hitung", "dituntut_klien"),
            requiredKnowledge = listOf("akuntansi", "pajak", "sistem_keuangan", "audit", "excel", "software_akuntansi"),
            ethics = listOf("jujur_dalam_laporan", "jangan_manipulasi_angka", "jaga_rahasia_klien", "patuhi_standar_akuntansi"),
            workHours = "8-12 jam/hari", physicalDemand = "RENDAH", mentalDemand = "TINGGI"
        ),

        // ===== KREATIF =====
        "desainer" to ProfessionData(
            name = "Desainer Grafis", category = "kreatif",
            tasks = listOf("membuat_desain", "layout", "logo", "branding", "ilustrasi", "revisi_klien"),
            tools = listOf("komputer", "photoshop", "illustrator", "figma", "tablet_gambar", "wacom"),
            environment = listOf("kantor", "rumah", "studio"),
            dangers = listOf("mata_lelah", "punggung_sakit", "karpal_tunnel", "stres_revisi", "burnout_kreatif"),
            requiredKnowledge = listOf("desain_grafis", "tipografi", "warna", "komposisi", "software_desain", "trend"),
            ethics = listOf("jangan_jiplak", "hormati_hak_cipta", "jujur_soal_revisi", "hargai_karya_orang_lain"),
            workHours = "8-12 jam/hari", physicalDemand = "RENDAH", mentalDemand = "TINGGI"
        ),

        // ===== TRANSPORTASI =====
        "sopir" to ProfessionData(
            name = "Sopir", category = "transportasi",
            tasks = listOf("mengemudi", "mengangkut_penumpang_barang", "merawat_kendaraan", "cek_rute", "isi_bensin"),
            tools = listOf("kendaraan", "stir", "gps", "radio", "sim", "kacamata"),
            environment = listOf("jalan_raya", "macet", "segala_cuaca"),
            dangers = listOf("kecelakaan", "begal", "mengantuk", "macet_stres", "punggung_sakit_duduk_lama"),
            requiredKnowledge = listOf("lalu_lintas", "rute", "mekanik_dasar", "keselamatan_berkendara", "komunikasi"),
            ethics = listOf("patuhi_aturan", "jangan_ugal_ugalan", "jaga_penumpang", "tidak_mabuk_saat_nyetir"),
            workHours = "8-16 jam/hari", physicalDemand = "SEDANG", mentalDemand = "SEDANG"
        ),
        "pilot" to ProfessionData(
            name = "Pilot", category = "transportasi",
            tasks = listOf("menerbangkan_pesawat", "cek_pra_terbang", "komunikasi_atc", "navigasi", "emergency_procedure"),
            tools = listOf("pesawat", "kokpit", "radio", "gps", "autopilot", "checklist", "seragam"),
            environment = listOf("pesawat", "bandara", "angkasa", "tekanan_udara"),
            dangers = listOf("kecelakaan_pesawat", "turbulensi", "hipoksia", "kelelahan", "dehidrasi"),
            requiredKnowledge = listOf("aerodinamika", "navigasi_udara", "meteorologi", "radio_komunikasi", "emergency", "bahasa_inggris"),
            ethics = listOf("utamakan_keselamatan", "patuhi_prosedur", "jangan_terbang_jika_sakit", "komunikasi_jelas"),
            workHours = "8-12 jam/hari", physicalDemand = "RENDAH", mentalDemand = "SANGAT_TINGGI"
        )
    )
    
    fun getProfession(name: String): ProfessionData? = allProfessions[name.lowercase()]
    
    fun getProfessionsByCategory(category: String): List<ProfessionData> =
        allProfessions.values.filter { it.category == category.lowercase() }
    
    fun getDangerousProfessions(): List<ProfessionData> =
        allProfessions.values.filter { it.dangers.size >= 5 }.sortedByDescending { it.dangers.size }
    
    fun getHighDemandProfessions(): List<ProfessionData> =
        allProfessions.values.filter { it.physicalDemand == "SANGAT_TINGGI" || it.mentalDemand == "SANGAT_TINGGI" }
}