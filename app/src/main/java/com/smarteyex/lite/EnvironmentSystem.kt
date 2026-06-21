package com.smarteyex.lite

data class EnvironmentData(
    val name: String,
    val category: String,
    val features: List<String>,
    val commonObjects: List<String>,
    val commonActivities: List<String>,
    val dangers: List<String>,
    val timeContext: List<String>,
    val mood: String,
    val lightingLevel: String,
    val noiseLevel: String,
    val temperature: String
)

class EnvironmentSystem {
    
    val allEnvironments: Map<String, EnvironmentData> = mapOf(
        "bengkel" to EnvironmentData(
            name = "Bengkel", category = "ruang_kerja",
            features = listOf("lantai_beton", "dinding_tinggi", "ventilasi_terbuka", "pencahayaan_lampu_tl", "kebisingan_tinggi", "bau_oli_logam", "suhu_panas_siang", "berantakan_tapi_teratur"),
            commonObjects = listOf("mesin_bubut", "mesin_las", "perkakas", "meja_kerja", "rak_alat", "kursi_besi", "lampu", "kacamata_pelindung", "sarung_tangan", "helm", "earplug", "kompresor", "gerinda", "bor", "tangki_oli"),
            commonActivities = listOf("membubut", "mengelas", "memperbaiki_mesin", "mengukur", "memeriksa", "membersihkan_alat", "menggergaji", "mengebor"),
            dangers = listOf("lantai_licin_oli", "kebisingan_tinggi", "percikan_api", "benda_berat_jatuh", "mesin_tidak_terjaga", "kabel_berserakan", "debu_logam", "gas_beracun", "tersandung", "tersengat_listrik"),
            timeContext = listOf("siang", "malam", "kerja", "lembur", "sendiri", "bersama_rekan", "bising"),
            mood = "FOKUS", lightingLevel = "TERANG_BUATAN", noiseLevel = "SANGAT_BISING", temperature = "PANAS"
        ),
        "laboratorium" to EnvironmentData(
            name = "Laboratorium", category = "ruang_riset",
            features = listOf("lantai_keramik", "meja_panjang", "pencahayaan_terang", "suhu_terkontrol", "bersih", "steril", "bau_kimia"),
            commonObjects = listOf("mikroskop", "tabung_reaksi", "gelas_ukur", "pipet", "bunsen", "sarung_tangan_lateks", "kacamata_lab", "jas_lab", "komputer", "sensor", "osiloskop", "multimeter"),
            commonActivities = listOf("eksperimen", "mengukur", "mencatat", "menganalisis", "membersihkan_alat_lab", "mengamati_mikroskop"),
            dangers = listOf("bahan_kimia_berbahaya", "api_bunsen", "alat_pecah", "gas_beracun", "listrik", "radiasi", "kontaminasi", "reaksi_kimia_tidak_terduga"),
            timeContext = listOf("siang", "malam", "riset", "sendiri", "bersama_tim", "steril"),
            mood = "FOKUS", lightingLevel = "SANGAT_TERANG", noiseLevel = "HENING", temperature = "DINGIN_TERKONTROL"
        ),
        "rumah" to EnvironmentData(
            name = "Rumah", category = "tempat_tinggal",
            features = listOf("lantai_keramik_kayu", "dinding_bata", "atap", "jendela", "pintu", "pencahayaan_nyaman", "suhu_nyaman", "bau_masakan"),
            commonObjects = listOf("meja", "kursi", "sofa", "tv", "kipas", "ac", "gelas", "cangkir", "piring", "sendok", "garpu", "tempat_tidur", "bantal", "selimut", "smartphone", "charger"),
            commonActivities = listOf("istirahat", "makan", "minum", "nonton_tv", "tidur", "ngobrol", "membersihkan_rumah", "memasak", "mengetik", "membaca"),
            dangers = listOf("lantai_basah_licin", "listrik_korslet", "kompor_lupa_dimatiin", "gas_bocor", "tangga_licin", "tersandung_kabel", "barang_jatuh", "anak_jatuh", "kebakaran"),
            timeContext = listOf("pagi", "siang", "malam", "sendiri", "bersama_keluarga", "tamu", "santai"),
            mood = "TENANG", lightingLevel = "NYAMAN", noiseLevel = "RENDAH", temperature = "NYAMAN"
        ),
        "dapur" to EnvironmentData(
            name = "Dapur", category = "ruang_masak",
            features = listOf("lantai_keramik", "meja_dapur", "wastafel", "ventilasi", "lemari_penyimpanan", "bau_makanan", "uap_masakan"),
            commonObjects = listOf("kompor", "wajan", "panci", "pisau_dapur", "talenan", "sendok", "garpu", "piring", "gelas", "cangkir", "kulkas", "microwave", "blender", "rice_cooker", "oven"),
            commonActivities = listOf("memasak", "makan", "minum", "mencuci_piring", "menyimpan_makanan", "membuat_kopi", "memotong_bahan"),
            dangers = listOf("kompor_menyala", "pisau_tajam", "lantai_basah_licin", "gas_bocor", "api", "minyak_panas_meletup", "listrik_dekat_air", "uap_panas"),
            timeContext = listOf("pagi", "siang", "malam", "sendiri", "bersama_keluarga", "sibuk"),
            mood = "AKTIF", lightingLevel = "TERANG", noiseLevel = "SEDANG", temperature = "PANAS"
        ),
        "kamar_tidur" to EnvironmentData(
            name = "Kamar Tidur", category = "ruang_istirahat",
            features = listOf("lantai_karpet_keramik", "dinding", "jendela_tirai", "pencahayaan_redup", "suhu_sejuk", "harum_pengharum"),
            commonObjects = listOf("tempat_tidur", "bantal", "selimut", "guling", "lemari", "meja_samping", "lampu_tidur", "smartphone", "charger", "buku", "jam_beker"),
            commonActivities = listOf("tidur", "berbaring", "membaca", "bermain_HP", "istirahat", "berpikir", "mendengarkan_musik"),
            dangers = listOf("jatuh_dari_tempat_tidur", "listrik_korslet", "lupa_matiin_lilin", "tersandung_kabel", "lampu_terlalu_terang_ganggu_tidur"),
            timeContext = listOf("malam", "pagi", "sendiri", "istirahat", "hening"),
            mood = "TENANG", lightingLevel = "REDUP", noiseLevel = "SANGAT_HENING", temperature = "SEJUK"
        ),
        "kamar_mandi" to EnvironmentData(
            name = "Kamar Mandi", category = "ruang_bersih",
            features = listOf("lantai_keramik", "dinding_keramik", "cermin", "ventilasi", "bau_sabun", "uap_air"),
            commonObjects = listOf("bak_mandi", "shower", "wastafel", "cermin", "sabun", "shampoo", "sikat_gigi", "pasta_gigi", "handuk", "keset", "ember", "gayung"),
            commonActivities = listOf("mandi", "sikat_gigi", "cuci_muka", "cuci_tangan", "buang_air", "bercermin"),
            dangers = listOf("lantai_licin_sabun", "terpeleset", "air_terlalu_panas", "listrik_dekat_air", "handuk_basah_di_lantai"),
            timeContext = listOf("pagi", "sore", "malam", "sendiri"),
            mood = "NETRAL", lightingLevel = "TERANG", noiseLevel = "HENING", temperature = "LEMBAB"
        ),
        "luar_ruangan" to EnvironmentData(
            name = "Luar Ruangan", category = "terbuka",
            features = listOf("jalan_aspal", "trotoar", "pohon", "rumput", "bangunan", "langit", "matahari", "hujan", "angin", "debu"),
            commonObjects = listOf("kendaraan", "lampu_jalan", "rambu", "bangku", "tanaman", "sepeda", "motor", "mobil", "bus", "truk", "payung"),
            commonActivities = listOf("berjalan", "berkendara", "menyeberang", "duduk", "berlari", "bersepeda", "berfoto", "belanja", "berteduh"),
            dangers = listOf("jalan_ramai", "jalan_licin_hujan", "gelap_tanpa_lampu", "kendaraan_melaju_cepat", "tersesat", "pohon_tumbang", "petir", "banjir", "panas_terik"),
            timeContext = listOf("pagi", "siang", "malam", "hujan", "panas", "macet"),
            mood = "WASPADA", lightingLevel = "ALAMI", noiseLevel = "BISING", temperature = "BERVARIASI"
        ),
        "kantor" to EnvironmentData(
            name = "Kantor", category = "ruang_kerja",
            features = listOf("lantai_karpet", "cubicle", "meja_kerja", "ac", "pencahayaan_terang", "printer", "ruang_rapat", "bau_kertas_kopi"),
            commonObjects = listOf("komputer", "keyboard", "mouse", "layar", "telepon", "printer", "kertas", "pena", "stapler", "lemari_arsip", "mesin_fotokopi"),
            commonActivities = listOf("bekerja", "rapat", "mengetik", "telepon", "presentasi", "makan_siang", "ngobrol_rekan"),
            dangers = listOf("mata_lelah", "punggung_sakit", "stres_kerja", "kabel_berserakan", "kursi_tidak_ergonomis", "ac_terlalu_dingin", "kebakaran_arsip"),
            timeContext = listOf("siang", "pagi", "sendiri", "bersama_rekan", "deadline", "sibuk"),
            mood = "FOKUS", lightingLevel = "TERANG", noiseLevel = "RENDAH", temperature = "DINGIN_AC"
        ),
        "jalan_raya" to EnvironmentData(
            name = "Jalan Raya", category = "transportasi",
            features = listOf("aspal", "marka_jalan", "lampu_merah", "rambu_lalu_lintas", "trotoar", "halte", "zebra_cross", "polisi_tidur"),
            commonObjects = listOf("mobil", "motor", "bus", "truk", "sepeda", "lampu_merah", "rambu", "polisi_tidur", "cone", "marka_jalan"),
            commonActivities = listOf("berkendara", "menyeberang", "menunggu_lampu_hijau", "macet", "parkir", "berhenti_darurat"),
            dangers = listOf("kecelakaan", "tabrak_lari", "lampu_merah_diterobos", "jalan_licin", "tikungan_tajam", "kendaraan_berat", "pejalan_kaki_menyeberang_sembarangan", "blind_spot"),
            timeContext = listOf("pagi", "siang", "malam", "macet", "hujan", "darurat", "sibuk"),
            mood = "WASPADA", lightingLevel = "ALAMI_BUATAN", noiseLevel = "SANGAT_BISING", temperature = "PANAS_BERDEBU"
        ),
        "sekolah" to EnvironmentData(
            name = "Sekolah", category = "pendidikan",
            features = listOf("kelas", "papan_tulis", "meja_belajar", "kursi", "perpustakaan", "lab", "lapangan", "kantin", "lorong"),
            commonObjects = listOf("buku", "pena", "pensil", "penghapus", "penggaris", "tas", "laptop", "proyektor", "spidol"),
            commonActivities = listOf("belajar", "mengajar", "membaca", "menulis", "ujian", "diskusi", "bermain", "olahraga", "upacara"),
            dangers = listOf("tangga_licin", "tersandung", "listrik", "kebakaran", "perundungan", "jatuh_dari_tangga"),
            timeContext = listOf("pagi", "siang", "sendiri", "bersama_teman", "ujian", "ramai"),
            mood = "FOKUS", lightingLevel = "TERANG", noiseLevel = "SEDANG_BISING", temperature = "NYAMAN"
        ),
        "rumah_sakit" to EnvironmentData(
            name = "Rumah Sakit", category = "kesehatan",
            features = listOf("lantai_keramik", "dinding_putih", "bau_obat", "steril", "igd", "ruang_rawat", "apotek"),
            commonObjects = listOf("tempat_tidur_pasien", "infus", "tensimeter", "stetoskop", "obat", "masker", "sarung_tangan_medis", "kursi_roda"),
            commonActivities = listOf("periksa_pasien", "rawat_inap", "operasi", "tunggu_antrian", "konsultasi_dokter"),
            dangers = listOf("infeksi_nosokomial", "tertular_penyakit", "alat_medis_tajam", "reaksi_obat", "jatuh_dari_tempat_tidur"),
            timeContext = listOf("pagi", "siang", "malam", "darurat", "24_jam"),
            mood = "TENANG_TAPI_SIAGA", lightingLevel = "TERANG", noiseLevel = "RENDAH", temperature = "DINGIN"
        ),
        "mall" to EnvironmentData(
            name = "Mall/Pusat Perbelanjaan", category = "komersial",
            features = listOf("lantai_marmer", "eskalator", "lift", "ac_dingin", "musik_latar", "banyak_toko", "food_court"),
            commonObjects = listOf("escalator", "lift", "trolley", "keranjang", "kasir", "display_produk", "meja_makan", "kursi"),
            commonActivities = listOf("belanja", "makan", "nonton_bioskop", "jalan_jalan", "mencoba_produk", "antri_kasir"),
            dangers = listOf("tersesat", "terpeleset_eskalator", "dicopet", "anak_hilang", "kebakaran", "lift_macet"),
            timeContext = listOf("siang", "malam", "ramai", "akhir_pekan", "diskon"),
            mood = "SANTAI", lightingLevel = "TERANG", noiseLevel = "SEDANG", temperature = "DINGIN_AC"
        ),
        "hutan" to EnvironmentData(
            name = "Hutan", category = "alam",
            features = listOf("pepohonan", "tanah_lumpur", "sungai_kecil", "batu_batuan", "dedaunan_kering", "bau_tanah", "suara_burung_serangga"),
            commonObjects = listOf("pohon", "batu", "tanah", "daun", "ranting", "sungai", "jamur", "akar"),
            commonActivities = listOf("mendaki", "berkemah", "menjelajah", "berburu", "memancing", "foto_alam"),
            dangers = listOf("tersesat", "gigitan_ular", "hewan_buas", "pohon_tumbang", "tanah_longsor", "sungai_meluap", "tidak_ada_sinyal"),
            timeContext = listOf("pagi", "siang", "sore", "jangan_malam", "sendiri_berbahaya"),
            mood = "WASPADA", lightingLevel = "ALAMI_REDUP", noiseLevel = "ALAMI", temperature = "LEMBAB_SEJUK"
        )
    )
    
    fun getEnvironment(name: String): EnvironmentData? = allEnvironments[name.lowercase()]
    
    fun getEnvironmentsByCategory(category: String): List<EnvironmentData> =
        allEnvironments.values.filter { it.category == category.lowercase() }
    
    fun getDangerousEnvironments(): List<EnvironmentData> =
        allEnvironments.values.filter { it.dangers.size >= 6 }.sortedByDescending { it.dangers.size }
    
    fun getEnvironmentByObject(objName: String): List<EnvironmentData> =
        allEnvironments.values.filter { it.commonObjects.any { o -> o.contains(objName.lowercase(), true) } }
    
    fun getEnvironmentByActivity(activityName: String): List<EnvironmentData> =
        allEnvironments.values.filter { it.commonActivities.any { a -> a.contains(activityName.lowercase(), true) } }
    
    fun isSafeAtNight(envName: String): Boolean {
        val env = getEnvironment(envName) ?: return true
        return !env.timeContext.contains("jangan_malam") && 
               !env.timeContext.contains("sendiri_berbahaya")
    }
}