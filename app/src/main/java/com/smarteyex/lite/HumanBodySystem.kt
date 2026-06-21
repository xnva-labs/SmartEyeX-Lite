package com.smarteyex.lite

class HumanBodySystem {
    
    val allBodyParts: Map<String, BodyPart> = mapOf(
        // ===== KEPALA =====
        "kepala" to BodyPart("Kepala", BodyCategory.KEPALA,
            functions = listOf("berpikir", "melindungi_otak", "pusat_sensor", "menoleh", "mengangguk", "menggeleng"),
            commonGestures = listOf(
                Gesture("mengangguk", "Kepala bergerak naik turun", "Setuju/mengerti", "NETRAL", listOf("iya", "oke", "paham", "setuju")),
                Gesture("menggeleng", "Kepala bergerak kiri kanan", "Tidak setuju/menolak", "NETRAL", listOf("nggak", "jangan", "bukan", "tidak")),
                Gesture("menunduk", "Kepala menunduk ke bawah", "Sedih/malu/berpikir/lelah", "SEDIH", listOf("sedih", "malu", "capek", "lelah")),
                Gesture("menoleh", "Kepala berputar ke samping", "Mencari/mendengar/penasaran", "PENASARAN", listOf("apa", "suara", "lihat", "dengar")),
                Gesture("menengadah", "Kepala mendongak ke atas", "Berpikir/berdoa/frustasi", "BERPIKIR", listOf("pikir", "kenapa", "aduh")),
                Gesture("memiringkan_kepala", "Kepala dimiringkan", "Mendengarkan/bingung", "PENASARAN", listOf("dengar", "bingung", "apa"))
            ),
            relatedObjects = listOf("bantal", "helm", "topi", "headset", "kacamata"),
            fatigueSigns = listOf("pusing", "sakit_kepala", "leher_kaku", "kepala_berat"),
            dangerSigns = listOf("kepala_terbentur", "pusing_berat", "tidak_pakai_helm", "kepala_terkena_benda_jatuh"),
            painIndicators = listOf("memegang_kepala", "mengerang_kesakitan", "menggeleng_lemas", "menunduk_lama")
        ),
        "mata" to BodyPart("Mata", BodyCategory.WAJAH,
            functions = listOf("melihat", "membaca", "mengamati", "melirik", "menatap", "mengedip", "menangis", "membedakan_warna", "mengukur_jarak"),
            commonGestures = listOf(
                Gesture("melirik", "Mata bergerak cepat ke samping", "Penasaran/waspada/curiga", "PENASARAN", listOf("apa_tuh", "lihat", "siapa")),
                Gesture("menatap", "Mata fokus pada satu titik", "Fokus/tertarik/serius", "FOKUS", listOf("fokus", "serius", "perhatikan")),
                Gesture("mengedip_cepat", "Kedipan lebih cepat dari normal", "Bingung/gugup/stres", "GUGUP", listOf("bingung", "gugup", "panik")),
                Gesture("mata_berkaca", "Mata berkaca-kaca", "Sedih/terharu/emosional", "SEDIH", listOf("sedih", "terharu", "nangis")),
                Gesture("mata_membesar", "Mata terbuka lebar", "Terkejut/takut/kagum", "TAKUT", listOf("takut", "kaget", "wow")),
                Gesture("mengedip_lambat", "Kedipan lambat dan berat", "Mengantuk/bosan/lelah", "BOSAN", listOf("ngantuk", "bosan", "capek")),
                Gesture("menyipitkan_mata", "Mata menyipit", "Curiga/melihat_jauh/silau", "CURIGA", listOf("curiga", "silau", "jauh")),
                Gesture("mengucek_mata", "Tangan mengucek mata", "Lelah/bangun_tidur/alergi", "LELAH", listOf("capek", "ngantuk", "gatal"))
            ),
            relatedObjects = listOf("buku", "layar", "kacamata", "tv", "smartphone", "komputer", "mikroskop"),
            fatigueSigns = listOf("mata_lelah", "mata_kering", "pandangan_kabur", "lingkaran_hitam", "mata_perih"),
            dangerSigns = listOf("mata_terkena_debu", "mata_terkena_percikan", "terlalu_lama_menatap_layar", "mata_terkena_bahan_kimia"),
            painIndicators = listOf("mengucek_mata", "menyipitkan_mata", "menutup_mata_sebelah", "berkedip_berlebihan")
        ),
        "mulut" to BodyPart("Mulut", BodyCategory.WAJAH,
            functions = listOf("berbicara", "makan", "minum", "tersenyum", "tertawa", "batuk", "menguap", "bersiul", "bernyanyi", "berbisik", "berteriak"),
            commonGestures = listOf(
                Gesture("tersenyum", "Sudut bibir terangkat ke atas", "Senang/puas/ramah", "SENANG", listOf("senang", "happy", "oke", "sip")),
                Gesture("tertawa", "Mulut terbuka dengan suara", "Sangat senang/gembira", "SENANG", listOf("wkwk", "haha", "lucu", "ngakak")),
                Gesture("menguap", "Mulut terbuka lebar tanpa suara", "Mengantuk/bosan", "BOSAN", listOf("ngantuk", "bosan", "capek")),
                Gesture("bibir_monyong", "Bibir maju ke depan", "Kesal/ngambek/sebel", "KESAL", listOf("kesal", "sebel", "ngambek")),
                Gesture("menggigit_bibir", "Gigi menggigit bibir bawah", "Gugup/berpikir/khawatir", "GUGUP", listOf("gugup", "mikir", "khawatir")),
                Gesture("bibir_bergetar", "Bibir bergetar", "Menahan_tangis/sedih/dingin", "SEDIH", listOf("sedih", "nangis", "dingin")),
                Gesture("menyeringai", "Bibir tertarik ke samping", "Sakit/marah/sinis", "MARAH", listOf("sakit", "marah", "nyebelin")),
                Gesture("menutup_mulut", "Tangan menutup mulut", "Terkejut/rahasia/malu", "TERKEJUT", listOf("kaget", "rahasia", "malu"))
            ),
            relatedObjects = listOf("gelas", "makanan", "sedotan", "mic", "rokok", "sikat_gigi"),
            fatigueSigns = listOf("bibir_kering", "sariawan", "suara_serak", "bibir_pecah"),
            dangerSigns = listOf("tersedak", "minuman_terlalu_panas", "makanan_beracun", "bibir_terkena_bahan_kimia"),
            painIndicators = listOf("memegang_tenggorokan", "batuk_keras", "menutup_mulut", "meringis")
        ),
        "telinga" to BodyPart("Telinga", BodyCategory.WAJAH,
            functions = listOf("mendengar", "mendengarkan", "menyeimbangkan_tubuh", "membedakan_suara", "mengenali_nada"),
            commonGestures = listOf(
                Gesture("memiringkan_kepala", "Kepala dimiringkan ke satu sisi", "Mendengarkan_seksama", "FOKUS", listOf("dengar", "apa", "suara")),
                Gesture("menutup_telinga", "Tangan menutup kedua telinga", "Suara_terlalu_keras/tidak_mau_dengar", "TERGANGGU", listOf("berisik", "diem", "keras")),
                Gesture("memegang_telinga", "Tangan memegang daun telinga", "Sakit_telinga/berpikir/ragu", "SAKIT", listOf("sakit", "aduh", "telinga")),
                Gesture("menggaruk_telinga", "Jari menggaruk belakang telinga", "Bingung/gatal", "BINGUNG", listOf("bingung", "gatal"))
            ),
            relatedObjects = listOf("headphone", "earphone", "headset", "earplug", "helm"),
            fatigueSigns = listOf("telinga_berdenging", "pendengaran_berkurang", "telinga_terasa_penuh"),
            dangerSigns = listOf("suara_terlalu_keras", "telinga_kemasukan_air", "infeksi_telinga", "gendang_telinga_pecah"),
            painIndicators = listOf("memegang_telinga", "mengerutkan_wajah", "menjauhkan_diri_dari_suara")
        ),
        "hidung" to BodyPart("Hidung", BodyCategory.WAJAH,
            functions = listOf("mencium", "bernapas", "menyaring_udara", "membedakan_bau", "merasakan_aroma"),
            commonGestures = listOf(
                Gesture("mengerutkan_hidung", "Hidung mengerut ke atas", "Tidak_suka/jijik", "JIJIK", listOf("bau", "jijik", "ih", "busuk")),
                Gesture("menghirup_dalam", "Napas dalam lewat hidung", "Mencium_aroma/menikmati", "SENANG", listOf("wangi", "enak", "harum")),
                Gesture("bersin", "Hidung mengeluarkan udara keras", "Alergi/masuk_angin/debu", "SAKIT", listOf("bersin", "flu", "pilek")),
                Gesture("mengucek_hidung", "Tangan mengucek hidung", "Gatal/alergi/flu", "SAKIT", listOf("gatal", "pilek", "bersin"))
            ),
            relatedObjects = listOf("parfum", "makanan", "bunga", "asap", "tisu", "masker"),
            fatigueSigns = listOf("pilek", "tersumbat", "mimisan", "bersin_terus"),
            dangerSigns = listOf("menghirup_asap_berbahaya", "alergi_debu", "menghirup_gas_beracun", "benda_asing_di_hidung"),
            painIndicators = listOf("mengucek_hidung", "menutup_hidung", "bersin_berulang", "napas_tersengal")
        ),
        
        // ===== TANGAN =====
        "tangan" to BodyPart("Tangan", BodyCategory.TANGAN,
            functions = listOf("memegang", "mendorong", "menarik", "memutar", "menekan", "menggenggam", "menulis", "menunjuk", "melambai", "mengangkat", "melempar", "menangkap", "memeluk", "menyentuh", "meraba", "mencubit", "menampar"),
            commonGestures = listOf(
                Gesture("menunjuk", "Jari telunjuk mengarah ke sesuatu", "Menunjukkan/menyalahkan/mengarahkan", "NETRAL", listOf("itu", "nih", "lihat", "sana")),
                Gesture("melambai", "Tangan bergerak kiri kanan", "Menyapa/pamit/memanggil", "SENANG", listOf("hai", "dah", "bye", "halo")),
                Gesture("mengepal", "Jari menggenggam erat", "Marah/tegang/bertekad/semangat", "MARAH", listOf("marah", "semangat", "tegang")),
                Gesture("tangan_terbuka", "Telapak tangan menghadap ke atas", "Menyerah/meminta/tidak_tahu", "PASRAH", listOf("nyerah", "minta", "gatau")),
                Gesture("menggaruk_kepala", "Tangan menggaruk kepala", "Bingung/berpikir/lupa", "BINGUNG", listOf("bingung", "pusing", "lupa")),
                Gesture("melipat_tangan", "Tangan disilangkan di dada", "Bertahan/menutup_diri/dingin", "DEFENSIF", listOf("nggak_mau", "diem", "dingin")),
                Gesture("menepuk_dahi", "Tangan menepuk dahi", "Ingat/lupa/facepalm", "TERKEJUT", listOf("ingat", "lupa", "aduh", "facepalm")),
                Gesture("tangan_di_pinggang", "Tangan bertumpu di pinggang", "Percaya_diri/kesal/marah", "PERCAYA_DIRI", listOf("siap", "kesal", "marah")),
                Gesture("mengusap_wajah", "Tangan mengusap wajah dari atas ke bawah", "Lelah/stres/putus_asa", "STRES", listOf("capek", "stres", "putus_asa")),
                Gesture("mengepal_di_dada", "Tangan mengepal di depan dada", "Bertekad/semangat/doa", "SEMANGAT", listOf("semangat", "bisa", "doa"))
            ),
            relatedObjects = listOf("gelas", "engkol", "palu", "obeng", "kunci", "smartphone", "pintu", "meja", "buku", "alat_tulis"),
            fatigueSigns = listOf("tangan_pegal", "tangan_kram", "tangan_gemetar", "tangan_kesemutan", "genggaman_melemah"),
            dangerSigns = listOf("tangan_terlalu_dekat_mesin", "tangan_terjepit", "tangan_terpotong", "tangan_terbakar", "tangan_basah_pegang_listrik"),
            painIndicators = listOf("memegang_tangan_sakit", "menarik_tangan_cepat", "mengibaskan_tangan", "meringis_sambil_memegang_tangan")
        ),
        "jari" to BodyPart("Jari", BodyCategory.JARI,
            functions = listOf("menunjuk", "menekan", "memutar", "menggenggam", "mengetik", "mencubit", "meraba", "menghitung", "menggaruk", "memainkan"),
            commonGestures = listOf(
                Gesture("jempol_keatas", "Ibu jari mengarah ke atas", "Setuju/oke/bagus/hebat", "SENANG", listOf("oke", "good", "sip", "like")),
                Gesture("jempol_kebawah", "Ibu jari mengarah ke bawah", "Tidak_setuju/buruk/gagal", "KECEWA", listOf("jelek", "nggak", "gagal", "dislike")),
                Gesture("telunjuk_ke_bibir", "Telunjuk di depan bibir", "Minta_diam/rahasia", "HATI_HATI", listOf("diem", "ssstt", "rahasia")),
                Gesture("mengetuk_jari", "Jari mengetuk permukaan", "Tidak_sabar/bosan/berpikir", "BOSAN", listOf("cepetan", "lama", "bosan")),
                Gesture("menggenggam_jari", "Jari menggenggam sesuatu kecil", "Hati_hati/presisi", "FOKUS", listOf("pelan", "hati_hati", "presisi")),
                Gesture("menjentikkan_jari", "Jari menjentik", "Eureka/ide_baru/memanggil", "ANTUSIAS", listOf("ide", "eureka", "panggil")),
                Gesture("menyilangkan_jari", "Jari disilangkan", "Berharap/doa/berbohong_di_belakang", "BERHARAP", listOf("semoga", "doa", "hope"))
            ),
            relatedObjects = listOf("tombol", "keyboard", "engkol_kecil", "sekrup", "kabel", "pena", "smartphone"),
            fatigueSigns = listOf("jari_pegal", "jari_kram", "jari_mati_rasa", "jari_kesemutan"),
            dangerSigns = listOf("jari_terjepit", "jari_terpotong", "jari_terbakar", "jari_terkena_listrik"),
            painIndicators = listOf("mengibaskan_jari", "memegang_jari", "memasukkan_jari_ke_mulut", "meringis")
        ),
        "lengan" to BodyPart("Lengan", BodyCategory.LENGAN,
            functions = listOf("mendorong", "menarik", "mengangkat", "memeluk", "merentang", "menahan", "membawa"),
            commonGestures = listOf(
                Gesture("melipat_lengan", "Lengan disilangkan di dada", "Bertahan/menutup_diri/dingin/berpikir", "DEFENSIF", listOf("nggak", "dingin", "mikir")),
                Gesture("merentangkan_lengan", "Lengan terbuka lebar", "Menyambut/heran/tidak_tahu", "TERBUKA", listOf("halo", "gatau", "berapa")),
                Gesture("lengan_di_belakang", "Lengan diletakkan di belakang", "Santal/berwibawa/merahasiakan", "TENANG", listOf("santai", "tenang"))
            ),
            relatedObjects = listOf("beban", "tas", "alat_berat"),
            fatigueSigns = listOf("lengan_pegal", "lengan_kram", "lengan_lemah"),
            dangerSigns = listOf("lengan_terjepit_mesin", "lengan_terkilir", "lengan_terkena_percikan"),
            painIndicators = listOf("memegang_lengan", "mengerang", "tidak_bisa_menggerakkan_lengan")
        ),
        
        // ===== KAKI =====
        "kaki" to BodyPart("Kaki", BodyCategory.KAKI,
            functions = listOf("berjalan", "berlari", "berdiri", "menendang", "menginjak", "melompat", "menaiki_tangga", "mundur", "jinjit", "jongkok", "berlutut"),
            commonGestures = listOf(
                Gesture("menghentakkan_kaki", "Kaki dihentakkan ke lantai", "Kesal/marah/frustasi", "MARAH", listOf("kesal", "sebel", "marah")),
                Gesture("kaki_goyang", "Kaki bergoyang-goyang", "Gelisah/bosan/tidak_sabar", "BOSAN", listOf("bosan", "gelisah", "lama")),
                Gesture("mundur_perlahan", "Kaki mundur perlahan", "Takut/ragu/hati_hati", "TAKUT", listOf("takut", "ragu", "hati_hati")),
                Gesture("jinjit", "Berdiri di ujung jari kaki", "Melihat_lebih_tinggi/hati_hati", "PENASARAN", listOf("lihat", "atas", "tinggi")),
                Gesture("jongkok", "Kaki ditekuk penuh", "Memeriksa_bawah/lelah/istirahat", "FOKUS", listOf("bawah", "periksa", "capek")),
                Gesture("berdiri_tegak", "Kaki lurus, badan tegak", "Siap/waspada/hormat", "SIAP", listOf("siap", "serius", "hormat"))
            ),
            relatedObjects = listOf("lantai", "pedal", "tangga", "sepatu", "sendal", "jalan"),
            fatigueSigns = listOf("kaki_pegal", "kaki_kram", "kaki_bengkak", "kaki_kesemutan"),
            dangerSigns = listOf("kaki_tersandung", "kaki_tertimpa", "lantai_licin_terpeleset", "kaki_terkena_paku"),
            painIndicators = listOf("memegang_kaki", "berjalan_pincang", "melompat_satu_kaki", "meringis_saat_berjalan")
        ),
        "lutut" to BodyPart("Lutut", BodyCategory.LUTUT,
            functions = listOf("menekuk", "berlutut", "menopang_badan", "menyerap_benturan"),
            commonGestures = listOf(
                Gesture("berlutut", "Lutut menyentuh lantai", "Memeriksa/menyerah/berdoa", "FOKUS", listOf("periksa", "bawah", "doa")),
                Gesture("menepuk_lutut", "Tangan menepuk lutut", "Tertawa/puas/lega", "SENANG", listOf("wkwk", "lucu", "puas")),
                Gesture("lutut_gemetar", "Lutut bergetar", "Takut/gugup/dingin", "TAKUT", listOf("takut", "gugup", "dingin"))
            ),
            relatedObjects = listOf("lantai", "bantalan_lutut", "celana"),
            fatigueSigns = listOf("lutut_sakit", "lutut_kaku", "lutut_bunyi"),
            dangerSigns = listOf("lutut_terbentur", "lutut_terkilir", "berlutut_terlalu_lama"),
            painIndicators = listOf("memegang_lutut", "berjalan_kaku", "tidak_bisa_menekuk_lutut")
        ),
        
        // ===== BADAN =====
        "badan" to BodyPart("Badan", BodyCategory.BADAN,
            functions = listOf("duduk", "berbaring", "membungkuk", "berputar", "merangkak", "bersandar", "meregang", "menjaga_keseimbangan"),
            commonGestures = listOf(
                Gesture("membungkuk", "Badan membungkuk ke depan", "Fokus/bekerja/lelah/memeriksa", "FOKUS", listOf("kerja", "fokus", "periksa")),
                Gesture("bersandar", "Badan bersandar ke belakang", "Santai/lelah/puas", "LELAH", listOf("capek", "santai", "selesai")),
                Gesture("duduk_tegak", "Badan tegak saat duduk", "Siap/waspada/serius", "FOKUS", listOf("siap", "serius", "waspada")),
                Gesture("membusungkan_dada", "Dada membusung ke depan", "Bangga/percaya_diri/menantang", "BANGGA", listOf("bangga", "percaya_diri", "siap")),
                Gesture("meringkuk", "Badan meringkuk ke dalam", "Takut/sedih/kedinginan", "TAKUT", listOf("takut", "dingin", "sedih"))
            ),
            relatedObjects = listOf("kursi", "meja", "sofa", "tempat_tidur", "lantai"),
            fatigueSigns = listOf("badan_pegal", "badan_sakit", "postur_buruk", "otot_kaku"),
            dangerSigns = listOf("badan_terbentur", "badan_tertimpa", "postur_membungkuk_terlalu_lama", "jatuh_dari_ketinggian"),
            painIndicators = listOf("memegang_pinggang", "meregangkan_badan", "meringis_saat_bergerak")
        ),
        "punggung" to BodyPart("Punggung", BodyCategory.PUNGGUNG,
            functions = listOf("menopang_tubuh", "membungkuk", "bersandar", "melindungi_tulang_belakang"),
            commonGestures = listOf(
                Gesture("meregangkan_punggung", "Punggung diregangkan ke belakang", "Lelah/baru_bangun/pegal", "LELAH", listOf("capek", "pegal", "bangun")),
                Gesture("menggaruk_punggung", "Tangan menggaruk punggung", "Gatal/bingung", "BINGUNG", listOf("gatal", "bingung")),
                Gesture("membungkukkan_punggung", "Punggung membungkuk", "Bekerja/lelah/tua", "LELAH", listOf("kerja", "capek", "tua"))
            ),
            relatedObjects = listOf("kursi_ergonomis", "bantal_punggung", "kasur", "meja"),
            fatigueSigns = listOf("sakit_punggung", "punggung_kaku", "saraf_terjepit", "nyeri_tulang_belakang"),
            dangerSigns = listOf("membungkuk_terlalu_lama", "angkat_beban_terlalu_berat", "jatuh_terlentang"),
            painIndicators = listOf("memegang_punggung", "berjalan_membungkuk", "sulit_berdiri_tegak")
        ),
        "perut" to BodyPart("Perut", BodyCategory.PERUT,
            functions = listOf("mencerna_makanan", "merasa_lapar", "merasa_kenyang", "merasa_mual", "menyimpan_energi"),
            commonGestures = listOf(
                Gesture("memegang_perut", "Tangan di perut", "Lapar/sakit/mual/kenyang", "SAKIT", listOf("lapar", "sakit", "mual", "kenyang")),
                Gesture("perut_bunyi", "Perut berbunyi krucuk", "Lapar", "NETRAL", listOf("lapar", "krucuk")),
                Gesture("memegang_perut_tertawa", "Tangan di perut saat tertawa", "Tertawa_terbahak_bahak", "SENANG", listOf("wkwk", "lucu_banget", "ngakak")),
                Gesture("meringkuk_perut", "Badan meringkuk memegang perut", "Sakit_perut_parah", "SAKIT", listOf("sakit", "perut", "aduh"))
            ),
            relatedObjects = listOf("makanan", "minuman", "obat", "botol_air"),
            fatigueSigns = listOf("perut_sakit", "mual", "kembung", "maag", "diare"),
            dangerSigns = listOf("makanan_basi", "minum_es_malam", "makan_terlalu_pedas", "racun", "usus_buntu"),
            painIndicators = listOf("memegang_perut_kesakitan", "membungkuk_menahan_sakit", "meringis", "berbaring_meringkuk")
        ),
        "dada" to BodyPart("Dada", BodyCategory.DADA,
            functions = listOf("bernapas", "melindungi_jantung_paru", "berdebar", "menjaga_postur"),
            commonGestures = listOf(
                Gesture("memegang_dada", "Tangan di dada", "Terkejut/nyeri/sesak", "TERKEJUT", listOf("kaget", "sakit", "sesak")),
                Gesture("dada_naik_turun", "Dada naik turun cepat", "Marah/lelah/stres/baru_lari", "MARAH", listOf("marah", "capek", "lari")),
                Gesture("menepuk_dada", "Tangan menepuk dada", "Percaya_diri/berjanji/aku", "BANGGA", listOf("percaya", "janji", "aku")),
                Gesture("dada_membusung", "Dada membusung ke depan", "Bangga/siap/menantang", "BANGGA", listOf("bangga", "siap", "tantang"))
            ),
            relatedObjects = listOf("baju", "jaket", "stetoskop", "alat_ekg"),
            fatigueSigns = listOf("sesak_napas", "dada_berdebar", "nyeri_dada", "napas_pendek"),
            dangerSigns = listOf("dada_tertimpa_benda_berat", "terkena_sengatan_listrik", "serangan_jantung", "tidak_bisa_bernapas"),
            painIndicators = listOf("memegang_dada_sesak", "napas_tersengal", "wajah_pucat", "berkeringat_dingin")
        )
    )
    
    fun getBodyPart(name: String): BodyPart? = allBodyParts[name.lowercase()]
    
    fun getPartsByCategory(category: BodyCategory): List<BodyPart> = 
        allBodyParts.values.filter { it.category == category }
    
    fun getGestureMeaning(gestureName: String): String? =
        allBodyParts.values.flatMap { it.commonGestures }.find { it.name == gestureName }?.meaning
    
    fun detectGestureFromTriggerWords(text: String): List<Gesture> {
        val lower = text.lowercase()
        return allBodyParts.values
            .flatMap { it.commonGestures }
            .filter { gesture -> gesture.triggerWords.any { lower.contains(it) } }
    }
}