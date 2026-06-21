package com.smarteyex.lite

data class Law(
    val name: String,
    val category: String,
    val description: String,
    val articles: List<String>,
    val penalties: List<String>,
    val realWorldExample: String,
    val smartEyeXRelevance: String,
    val year: String
)

class LawSystem {
    
    val allLaws: List<Law> = listOf(
        // ============================================
        // 1. DASAR NEGARA & KONSTITUSI (6 hukum)
        // ============================================
        Law(
            name = "UUD 1945 (Amandemen I-IV)",
            category = "Dasar Negara",
            description = "Undang-Undang Dasar Negara Republik Indonesia Tahun 1945 adalah konstitusi tertinggi. Telah diamandemen 4 kali: 1999, 2000, 2001, 2002. Mengatur bentuk negara, pembagian kekuasaan, hak asasi manusia, dan sistem pemerintahan.",
            articles = listOf(
                "Pasal 1 ayat 1: Indonesia adalah Negara Kesatuan berbentuk Republik",
                "Pasal 1 ayat 2: Kedaulatan berada di tangan rakyat",
                "Pasal 1 ayat 3: Indonesia adalah negara hukum",
                "Pasal 27 ayat 1: Segala warga negara bersamaan kedudukannya di dalam hukum",
                "Pasal 27 ayat 2: Tiap warga negara berhak atas pekerjaan dan penghidupan yang layak",
                "Pasal 28 A-J: Hak Asasi Manusia (10 pasal HAM hasil Amandemen II)",
                "Pasal 28A: Hak untuk hidup dan mempertahankan kehidupan",
                "Pasal 28B: Hak membentuk keluarga dan melanjutkan keturunan",
                "Pasal 28C: Hak mengembangkan diri dan pendidikan",
                "Pasal 28D: Hak atas pengakuan dan kepastian hukum yang adil",
                "Pasal 28E: Kebebasan beragama, berkeyakinan, berserikat, berpendapat",
                "Pasal 28F: Hak berkomunikasi dan memperoleh informasi",
                "Pasal 28G: Hak perlindungan diri, keluarga, kehormatan, harta",
                "Pasal 28H: Hak hidup sejahtera dan mendapat pelayanan kesehatan",
                "Pasal 28I: Hak untuk tidak disiksa, hak hidup, hak beragama — TIDAK DAPAT DIKURANGI",
                "Pasal 28J: Hak dibatasi UU untuk menjamin hak orang lain"
            ),
            penalties = listOf("Pelanggaran UUD → Inkonstitusional → UU dapat dibatalkan oleh Mahkamah Konstitusi"),
            realWorldExample = "MK membatalkan UU karena bertentangan dengan UUD 1945. Contoh: MK membatalkan pasal tentang penghinaan presiden karena melanggar kebebasan berpendapat (Pasal 28E).",
            smartEyeXRelevance = "XNAI harus memahami bahwa semua hukum di bawahnya harus sesuai UUD. XNAI tidak boleh menyarankan tindakan yang melanggar hak asasi manusia atau prinsip negara hukum.",
            year = "1945 (Amandemen terakhir: 2002)"
        ),
        Law(
            name = "Pancasila",
            category = "Dasar Negara",
            description = "Pancasila adalah ideologi dan dasar negara Indonesia yang terdiri dari 5 sila: Ketuhanan YME, Kemanusiaan yang adil dan beradab, Persatuan Indonesia, Kerakyatan, Keadilan sosial. Pancasila adalah sumber dari segala sumber hukum di Indonesia.",
            articles = listOf(
                "Sila 1: Ketuhanan Yang Maha Esa — Indonesia mengakui 6 agama resmi",
                "Sila 2: Kemanusiaan yang Adil dan Beradab — Anti-diskriminasi",
                "Sila 3: Persatuan Indonesia — NKRI harga mati",
                "Sila 4: Kerakyatan yang Dipimpin oleh Hikmat — Musyawarah untuk mufakat",
                "Sila 5: Keadilan Sosial — Kesejahteraan untuk seluruh rakyat"
            ),
            penalties = listOf("Tidak ada hukuman pidana langsung, tapi pelanggaran ideologi dapat dikenakan UU terkait (terorisme, makar, separatisme)"),
            realWorldExample = "Organisasi yang ingin mengganti Pancasila dengan ideologi lain dinyatakan terlarang oleh pemerintah. Contoh: pembubaran HTI karena bertentangan dengan Pancasila.",
            smartEyeXRelevance = "XNAI harus menghormati semua agama, tidak diskriminatif, mendukung persatuan, dan tidak menyebarkan ideologi yang bertentangan dengan Pancasila.",
            year = "1945"
        ),
        Law(
            name = "Bhinneka Tunggal Ika",
            category = "Dasar Negara",
            description = "Semboyan nasional yang berarti 'Berbeda-beda tetapi tetap satu'. Terdapat di lambang Garuda Pancasila. Indonesia memiliki 1.340 suku, 718 bahasa daerah, 6 agama resmi, dan ratusan budaya.",
            articles = listOf(
                "Diatur dalam UU No. 24 Tahun 2009 tentang Bendera, Bahasa, Lambang Negara, dan Lagu Kebangsaan",
                "Pasal 46: Lambang Negara wajib dipasang di instansi pemerintahan"
            ),
            penalties = listOf("Menghina lambang negara → pidana 5 tahun atau denda Rp 500.000.000 (Pasal 57 UU 24/2009)"),
            realWorldExample = "Indonesia diakui dunia sebagai negara dengan toleransi beragama yang tinggi. Konflik SARA masih terjadi tapi konstitusi melindungi keberagaman.",
            smartEyeXRelevance = "XNAI harus menghormati keberagaman. Tidak boleh menghasilkan konten SARA, diskriminatif, atau menghina suku/agama/ras tertentu.",
            year = "1945"
        ),
        Law(
            name = "NKRI (Negara Kesatuan Republik Indonesia)",
            category = "Dasar Negara",
            description = "Bentuk negara Indonesia adalah kesatuan, bukan federal. Wilayah Indonesia dari Sabang sampai Merauke adalah satu kesatuan yang tidak dapat dipisahkan. Mencakup 38 provinsi (2026: termasuk Papua Barat Daya dan Papua Pegunungan).",
            articles = listOf(
                "Pasal 1 ayat 1 UUD 1945: Negara Indonesia adalah Negara Kesatuan berbentuk Republik",
                "Pasal 25A UUD 1945: NKRI adalah negara kepulauan dengan batas-batas yang ditetapkan UU",
                "Pasal 37 ayat 5 UUD 1945: Bentuk Negara Kesatuan TIDAK DAPAT DIUBAH"
            ),
            penalties = listOf("Gerakan separatisme/pemisahan diri → pidana seumur hidup atau pidana mati (Pasal 106 KUHP baru: Makar terhadap negara)"),
            realWorldExample = "Referendum Timor Timur 1999: pengecualian sejarah, bukan preseden. Papua dan Aceh diberikan otonomi khusus, bukan kemerdekaan.",
            smartEyeXRelevance = "XNAI harus tegas mendukung NKRI. Tidak boleh mendukung atau menyebarkan ide separatisme. Jika user bertanya tentang memisahkan diri, XNAI harus menjelaskan bahwa itu bertentangan dengan UUD.",
            year = "1945"
        ),
        Law(
            name = "Hak Asasi Manusia (UU No. 39/1999)",
            category = "Dasar Negara",
            description = "UU HAM mengatur 10 hak dasar warga negara: hak hidup, berkeluarga, mengembangkan diri, keadilan, kebebasan pribadi, keamanan, kesejahteraan, partisipasi pemerintahan, wanita, dan anak.",
            articles = listOf(
                "Pasal 4: Hak untuk hidup, tidak disiksa, kemerdekaan pikiran dan hati nurani, beragama, tidak diperbudak, diakui sebagai pribadi di depan hukum",
                "Pasal 9: Setiap orang berhak untuk hidup dan mempertahankan hidupnya",
                "Pasal 17: Setiap orang berhak atas rasa aman dan tenteram",
                "Pasal 20: Tidak seorang pun boleh diperbudak atau diperhamba",
                "Pasal 33: Setiap orang berhak bebas dari penyiksaan, hukuman yang kejam"
            ),
            penalties = listOf("Pelanggaran HAM berat → Pengadilan HAM, hukuman mati/seumur hidup/25 tahun (UU No. 26/2000 tentang Pengadilan HAM)"),
            realWorldExample = "Kasus pelanggaran HAM berat: penembakan Trisakti 1998, Tragedi Semanggi, penghilangan paksa aktivis 1997-1998. Diselesaikan lewat Komnas HAM dan Pengadilan HAM Ad Hoc.",
            smartEyeXRelevance = "XNAI tidak boleh menyarankan tindakan yang melanggar HAM. Jika mendeteksi indikasi pelanggaran HAM (perbudakan, penyiksaan), XNAI harus menyarankan melapor ke Komnas HAM.",
            year = "1999"
        ),
        Law(
            name = "Kekuasaan Kehakiman (UU No. 48/2009)",
            category = "Dasar Negara",
            description = "Mengatur sistem peradilan Indonesia: Mahkamah Agung (MA), Mahkamah Konstitusi (MK), dan Komisi Yudisial (KY). Kekuasaan kehakiman adalah kekuasaan yang merdeka dan bebas dari intervensi.",
            articles = listOf(
                "Pasal 1: Kekuasaan kehakiman adalah kekuasaan negara yang merdeka untuk menyelenggarakan peradilan",
                "Pasal 24 UUD 1945: Kekuasaan kehakiman dilakukan oleh MA dan MK",
                "MA: pengadilan kasasi dan peninjauan kembali",
                "MK: menguji UU terhadap UUD, sengketa kewenangan lembaga negara, pembubaran parpol, sengketa pemilu"
            ),
            penalties = listOf("Contempt of court (menghina pengadilan) → pidana sesuai KUHP"),
            realWorldExample = "MK membatalkan UU No. 17/2014 tentang MD3 yang melemahkan KPK pada tahun 2015. MK juga mengabulkan judicial review batas usia capres-cawapres 2023.",
            smartEyeXRelevance = "XNAI harus memahami bahwa jika user punya masalah hukum, jalurnya adalah lewat pengadilan, bukan main hakim sendiri.",
            year = "2009"
        ),

        // ============================================
        // 2. HUKUM PIDANA - KUHP BARU 2023 (8 pasal)
        // ============================================
        Law(
            name = "KUHP Nasional (UU No. 1/2023) - Pencurian",
            category = "Pidana",
            description = "KUHP baru menggantikan KUHP kolonial Belanda (WvS). Disahkan 2 Januari 2023, berlaku penuh 2 Januari 2026. Pencurian diatur dalam Pasal 476-481.",
            articles = listOf(
                "Pasal 476: Pencurian biasa → pidana 5 tahun atau denda kategori V (Rp 500 juta)",
                "Pasal 477: Pencurian dengan pemberatan (malam, bersama-sama, merusak) → pidana 7 tahun",
                "Pasal 478: Pencurian dengan kekerasan → pidana 9 tahun",
                "Pasal 479: Pencurian kendaraan bermotor → pidana 7 tahun",
                "Pasal 480: Pencurian ringan (nilai < Rp 2.5 juta) → pidana 1 tahun atau denda Rp 10 juta",
                "Pasal 481: Tindak pidana pencurian hanya dapat dituntut jika ada pengaduan korban (delik aduan untuk pencurian dalam keluarga)"
            ),
            penalties = listOf("Pencurian ringan (<Rp 2.5jt): 1 tahun penjara", "Pencurian biasa: 5 tahun", "Pencurian berat: 7-9 tahun"),
            realWorldExample = "Mencuri HP teman: Pasal 476 → 5 tahun penjara. Mencuri motor: Pasal 479 → 7 tahun penjara. Mencuri uang receh di warung (<2.5jt): Pasal 480 → denda.",
            smartEyeXRelevance = "XNAI harus memahami bahwa mengambil barang tanpa izin adalah kejahatan. Jika user bercanda tentang mencuri, XNAI harus mengingatkan konsekuensi hukumnya.",
            year = "2023 (Berlaku: 2026)"
        ),
        Law(
            name = "KUHP 2023 - Penganiayaan",
            category = "Pidana",
            description = "Penganiayaan diatur dalam Pasal 466-472 KUHP baru. Mencakup penganiayaan ringan, berat, dan berencana.",
            articles = listOf(
                "Pasal 466: Penganiayaan biasa → pidana 2 tahun 8 bulan atau denda Rp 25 juta",
                "Pasal 467: Penganiayaan yang menimbulkan luka berat → pidana 5 tahun",
                "Pasal 468: Penganiayaan yang mengakibatkan kematian → pidana 7 tahun",
                "Pasal 469: Penganiayaan berencana → pidana 4 tahun (luka biasa) atau 7 tahun (luka berat) atau 10 tahun (mati)",
                "Pasal 470: Penganiayaan terhadap ibu/bapak/anak/istri/suami → pemberatan +1/3 hukuman",
                "Pasal 472: Kekerasan dalam rumah tangga (KDRT) → pidana 4 tahun"
            ),
            penalties = listOf("Luka ringan: 2 tahun 8 bulan", "Luka berat: 5 tahun", "Mengakibatkan mati: 7 tahun", "Berencana + mati: 10 tahun"),
            realWorldExample = "Perkelahian di bengkel mengakibatkan luka memar: Pasal 466. Perkelahian pakai kunci inggris sampai patah tulang: Pasal 467. Merencanakan menyerang orang: Pasal 469.",
            smartEyeXRelevance = "Jika XNAI mendeteksi perkelahian atau kekerasan via kamera, harus segera peringatkan. Jika melihat KDRT, sarankan korban melapor ke polisi atau Komnas Perempuan.",
            year = "2023 (Berlaku: 2026)"
        ),
        Law(
            name = "KUHP 2023 - Penipuan",
            category = "Pidana",
            description = "Penipuan diatur dalam Pasal 492-494 KUHP baru. Penipuan online termasuk dalam kategori ini.",
            articles = listOf(
                "Pasal 492: Penipuan biasa → pidana 4 tahun atau denda Rp 50 juta",
                "Pasal 493: Penipuan yang dilakukan oleh pelaku usaha → pidana 6 tahun atau denda Rp 500 juta",
                "Pasal 494: Penipuan dengan media elektronik (online scam) → pidana 6 tahun atau denda Rp 1 miliar"
            ),
            penalties = listOf("Penipuan biasa: 4 tahun", "Penipuan oleh pelaku usaha: 6 tahun + denda 500jt", "Penipuan online: 6 tahun + denda 1M"),
            realWorldExample = "Penipuan arisan online, investasi bodong, penjual barang fiktif di marketplace. Semua termasuk Pasal 494.",
            smartEyeXRelevance = "XNAI harus bisa mengenali tanda-tanda penipuan. Jika user akan transfer ke nomor mencurigakan, XNAI peringatkan: 'Bung, hati-hati ini ciri-ciri penipuan.'",
            year = "2023 (Berlaku: 2026)"
        ),
        Law(
            name = "KUHP 2023 - Perusakan Barang",
            category = "Pidana",
            description = "Perusakan barang milik orang lain diatur dalam Pasal 482-484 KUHP baru.",
            articles = listOf(
                "Pasal 482: Perusakan barang biasa → pidana 2 tahun 8 bulan atau denda Rp 25 juta",
                "Pasal 483: Perusakan barang yang mengakibatkan bahaya umum (listrik, air, gas) → pidana 7 tahun",
                "Pasal 484: Perusakan barang bersejarah atau bernilai tinggi → pidana 5 tahun"
            ),
            penalties = listOf("Barang biasa: 2 tahun 8 bulan", "Fasilitas umum: 7 tahun", "Cagar budaya: 5 tahun"),
            realWorldExample = "Marah-marah merusak HP teman: Pasal 482. Merusak pipa PDAM: Pasal 483. Merusak candi: Pasal 484.",
            smartEyeXRelevance = "XNAI harus mengingatkan bahwa merusak barang orang lain adalah tindak pidana, meskipun sedang marah.",
            year = "2023 (Berlaku: 2026)"
        ),
        Law(
            name = "KUHP 2023 - Pembunuhan",
            category = "Pidana",
            description = "Pembunuhan diatur dalam Pasal 458-465 KUHP baru. Hukuman terberat untuk pembunuhan berencana.",
            articles = listOf(
                "Pasal 458: Pembunuhan biasa → pidana 15 tahun",
                "Pasal 459: Pembunuhan berencana → pidana mati atau seumur hidup atau 20 tahun",
                "Pasal 460: Pembunuhan terhadap ibu/bapak/anak/istri/suami → pemberatan",
                "Pasal 461: Pembunuhan karena kealpaan (tidak sengaja) → pidana 5 tahun",
                "Pasal 462: Pembunuhan atas permintaan korban (eutanasia aktif) → pidana 9 tahun"
            ),
            penalties = listOf("Pembunuhan biasa: 15 tahun", "Berencana: mati/seumur hidup/20 tahun", "Kealpaan: 5 tahun", "Eutanasia aktif: 9 tahun"),
            realWorldExample = "Kecelakaan fatal karena mengantuk saat nyetir: Pasal 461 (kealpaan). Membunuh dengan rencana matang: Pasal 459.",
            smartEyeXRelevance = "XNAI harus selalu mengutamakan keselamatan. Jika mendeteksi situasi berbahaya yang bisa menyebabkan kematian (mesin rusak parah, gas bocor), harus peringatkan keras.",
            year = "2023 (Berlaku: 2026)"
        ),
        Law(
            name = "KUHP 2023 - Penghinaan & Pencemaran Nama Baik",
            category = "Pidana",
            description = "Penghinaan diatur dalam Pasal 433-437 KUHP baru. Mencakup penghinaan langsung dan via media sosial.",
            articles = listOf(
                "Pasal 433: Penghinaan lisan → pidana 6 bulan atau denda Rp 10 juta",
                "Pasal 434: Penghinaan tertulis → pidana 1 tahun atau denda Rp 25 juta",
                "Pasal 435: Penghinaan via media sosial → pidana 2 tahun atau denda Rp 50 juta",
                "Pasal 436: Fitnah (menuduh tanpa bukti) → pidana 3 tahun atau denda Rp 100 juta",
                "Pasal 437: Pencemaran nama baik → pidana 4 tahun atau denda Rp 500 juta"
            ),
            penalties = listOf("Lisan: 6 bulan + 10jt", "Tertulis: 1 tahun + 25jt", "Medsos: 2 tahun + 50jt", "Fitnah: 3 tahun + 100jt", "Pencemaran: 4 tahun + 500jt"),
            realWorldExample = "Menghina orang di Facebook: Pasal 435. Menuduh orang mencuri tanpa bukti: Pasal 436. Menyebarkan video seseorang dengan tujuan mempermalukan: Pasal 437.",
            smartEyeXRelevance = "XNAI tidak boleh membantu menyebarkan konten yang mencemarkan nama baik orang lain. Jika user meminta bantuan untuk membalas dendam di medsos, XNAI harus menolak dan menjelaskan konsekuensi hukumnya.",
            year = "2023 (Berlaku: 2026)"
        ),
        Law(
            name = "KUHP 2023 - Makar & Kejahatan Terhadap Negara",
            category = "Pidana",
            description = "Makar terhadap negara diatur dalam Pasal 188-198 KUHP baru. Ini adalah kejahatan paling serius terhadap negara.",
            articles = listOf(
                "Pasal 188: Makar dengan maksud membunuh presiden/wakil presiden → pidana mati atau seumur hidup",
                "Pasal 189: Makar untuk menggulingkan pemerintah yang sah → pidana 20 tahun",
                "Pasal 190: Makar untuk memisahkan diri dari NKRI → pidana 20 tahun",
                "Pasal 191: Permufakatan jahat untuk makar → pidana 10 tahun",
                "Pasal 198: Menyebarkan ajaran komunis/marxisme-leninisme → pidana 4 tahun"
            ),
            penalties = listOf("Makar membunuh presiden: mati/seumur hidup", "Menggulingkan pemerintah: 20 tahun", "Separatisme: 20 tahun", "Konspirasi makar: 10 tahun"),
            realWorldExample = "Upaya kudeta atau revolusi bersenjata. Gerakan separatis bersenjata seperti OPM atau GAM (sebelum damai). Semua termasuk makar.",
            smartEyeXRelevance = "XNAI harus tegas menolak segala bentuk ajakan makar atau separatisme. Harus pro-NKRI sesuai UUD 1945.",
            year = "2023 (Berlaku: 2026)"
        ),
        Law(
            name = "KUHP 2023 - Narkotika & Psikotropika",
            category = "Pidana",
            description = "Kejahatan narkotika tetap menggunakan UU No. 35/2009 tentang Narkotika (lex specialis). KUHP baru mengatur ketentuan umum.",
            articles = listOf(
                "Pasal 111 UU 35/2009: Memiliki narkotika Golongan I > 5 gram → pidana 5-20 tahun",
                "Pasal 112: Memiliki narkotika Golongan I > 1 kg → pidana mati, seumur hidup, atau 20 tahun",
                "Pasal 114: Mengedarkan narkotika Golongan I → pidana mati, seumur hidup, atau 6-20 tahun",
                "Pasal 127: Penyalahgunaan narkotika untuk diri sendiri → rehabilitasi (bukan penjara)"
            ),
            penalties = listOf("Pemakaian pribadi: Rehabilitasi", "Kepemilikan > 5gr: 5-20 tahun", "Pengedaran: mati/seumur hidup"),
            realWorldExample = "Artis ditangkap pakai sabu: direhabilitasi (Pasal 127). Bandar besar ditangkap bawa 1 kg sabu: hukuman mati (Pasal 114).",
            smartEyeXRelevance = "XNAI harus tegas anti-narkoba. Jika mendeteksi indikasi penyalahgunaan, sarankan rehabilitasi. Tidak boleh memberikan informasi cara membuat/menggunakan narkotika.",
            year = "2009 (Masih berlaku 2026)"
        ),

        // ============================================
        // 3. HUKUM ITE (6 pasal)
        // ============================================
        Law(
            name = "UU ITE (UU No. 1/2024 - Revisi Kedua)",
            category = "ITE",
            description = "Undang-Undang Informasi dan Transaksi Elektronik. Direvisi pertama 2016, direvisi kedua 2024. Mengatur transaksi elektronik, konten digital, dan kejahatan siber.",
            articles = listOf(
                "Pasal 27 ayat 1: Mendistribusikan konten melanggar kesusilaan → 6 tahun, denda Rp 1 miliar",
                "Pasal 27 ayat 3: Pencemaran nama baik via elektronik → 4 tahun, denda Rp 750 juta",
                "Pasal 27A: Revenge porn (menyebarkan konten intim tanpa izin) → 6 tahun, denda Rp 1 miliar",
                "Pasal 28 ayat 1: Menyebarkan berita bohong (hoax) merugikan konsumen → 6 tahun, denda Rp 1 miliar",
                "Pasal 28 ayat 2: Menyebarkan kebencian SARA → 6 tahun, denda Rp 1 miliar",
                "Pasal 30: Mengakses sistem orang lain tanpa izin (hacking) → 8 tahun, denda Rp 800 juta"
            ),
            penalties = listOf("Konten asusila: 6 tahun + 1M", "Pencemaran: 4 tahun + 750jt", "Revenge porn: 6 tahun + 1M", "Hoax: 6 tahun + 1M", "SARA: 6 tahun + 1M", "Hacking: 8 tahun + 800jt"),
            realWorldExample = "Menyebarkan video asusila mantan pacar: Pasal 27A (revenge porn). Bikin postingan SARA: Pasal 28 ayat 2. Hack akun Instagram orang: Pasal 30.",
            smartEyeXRelevance = "XNAI tidak boleh membantu menyebarkan hoax, SARA, konten asusila, atau hacking. Jika user minta bantuan hack akun seseorang, XNAI harus menolak tegas.",
            year = "2024 (Revisi terbaru)"
        ),
        Law(
            name = "UU ITE - Pasal Karet & Batasan Baru",
            category = "ITE",
            description = "Revisi 2024 memperjelas 'pasal karet' UU ITE. Kritik yang bersifat membangun BUKAN pencemaran nama baik. Tidak boleh kriminalisasi jurnalis dan aktivis.",
            articles = listOf(
                "Pasal 27 ayat 3 TIDAK berlaku untuk: kritik terhadap pejabat publik, jurnalisme investigasi, karya seni/sastra, unggahan yang mengandung fakta dan opini yang dapat dipertanggungjawabkan",
                "Pasal 27A: Revenge porn wajib diproses meskipun korban tidak melapor (delik biasa)",
                "Pasal 45A: Pencemaran nama baik hanya bisa diproses jika ada pengaduan korban langsung (delik aduan absolut) — tidak bisa diwakilkan"
            ),
            penalties = listOf("Penyalahgunaan UU ITE untuk kriminalisasi → aparat penegak hukum dapat dikenakan sanksi disiplin"),
            realWorldExample = "Kritik terhadap presiden di Twitter TIDAK bisa dipidana (putusan MK 2022). Jurnalis yang mengungkap korupsi TIDAK bisa dijerat UU ITE.",
            smartEyeXRelevance = "XNAI harus memahami perbedaan antara kritik yang dilindungi dan pencemaran nama baik yang dilarang. XNAI bisa membantu user menyusun kritik yang konstruktif dan sesuai hukum.",
            year = "2024"
        ),
        Law(
            name = "Perlindungan Data Pribadi (UU No. 27/2022)",
            category = "ITE",
            description = "UU PDP adalah UU pertama Indonesia yang khusus mengatur perlindungan data pribadi. Terinspirasi GDPR (Eropa). Berlaku penuh Oktober 2024.",
            articles = listOf(
                "Pasal 4: Data pribadi meliputi: nama, NIK, alamat, biometrik, data kesehatan, data keuangan",
                "Pasal 20: Pengendali data WAJIB mendapatkan persetujuan eksplisit (consent) dari pemilik data",
                "Pasal 26: Pemilik data berhak meminta penghapusan data (right to be forgotten)",
                "Pasal 46: Kebocoran data → notifikasi wajib ke pemilik data dalam 3×24 jam",
                "Pasal 57: Pelanggaran → denda administratif 2% dari pendapatan tahunan",
                "Pasal 67: Pidana penjualan data pribadi tanpa izin → 5 tahun, denda Rp 5 miliar"
            ),
            penalties = listOf("Jual data pribadi: 5 tahun + 5M", "Kebocoran tidak dilaporkan: denda 2% pendapatan tahunan", "Tidak dapat consent: sanksi administratif"),
            realWorldExample = "Aplikasi fintech menjual data nasabah ke perusahaan asuransi tanpa izin: Pasal 67. Bank bocor data nasabah dan tidak melaporkan: Pasal 46 + denda.",
            smartEyeXRelevance = "SmartEyeX harus comply dengan UU PDP! Data Bung (gambar, suara, percakapan) TIDAK boleh dijual atau disebarkan. XNAI harus menjelaskan kebijakan privasi ke user.",
            year = "2022 (Berlaku penuh: 2024)"
        ),
        Law(
            name = "UU ITE - Transaksi Elektronik",
            category = "ITE",
            description = "Mengatur keabsahan transaksi elektronik, tanda tangan digital, dan kontrak elektronik.",
            articles = listOf(
                "Pasal 11: Tanda tangan elektronik memiliki kekuatan hukum yang sah",
                "Pasal 17: Transaksi elektronik yang disepakati mengikat para pihak",
                "Pasal 18: Kontrak elektronik dianggap sah jika para pihak menyetujui terms and conditions"
            ),
            penalties = listOf("Pelanggaran kontrak elektronik → dapat digugat secara perdata"),
            realWorldExample = "Jual beli di Tokopedia: klik 'Beli' adalah kontrak elektronik yang sah. Penjual wajib mengirim barang, pembeli wajib bayar.",
            smartEyeXRelevance = "XNAI harus memahami bahwa transaksi online itu sah dan mengikat secara hukum.",
            year = "2024"
        ),
        Law(
            name = "Cyber Crime Convention (UU No. 27/2026)",
            category = "ITE",
            description = "Indonesia meratifikasi Budapest Convention on Cybercrime melalui UU No. 27/2026 (disahkan Maret 2026). Memperkuat kerjasama internasional melawan kejahatan siber.",
            articles = listOf(
                "Kejahatan siber lintas negara: hacking, phishing, ransomware, botnet",
                "Kerjasama ekstradisi untuk pelaku cyber crime",
                "Perlindungan infrastruktur digital vital (listrik, air, transportasi, perbankan)",
                "Pembentukan badan Cyber Security Nasional (CSN) pada Juni 2026"
            ),
            penalties = listOf("Serangan siber ke infrastruktur vital: 12 tahun + denda Rp 10 miliar", "Ransomware: 10 tahun", "Phishing massal: 8 tahun"),
            realWorldExample = "Serangan ransomware ke Rumah Sakit: 10 tahun penjara. Hacker Indonesia menyerang situs luar negeri: bisa diekstradisi.",
            smartEyeXRelevance = "XNAI harus waspada terhadap aktivitas mencurigakan di perangkat. Jika mendeteksi malware atau akses tidak sah, segera peringatkan Bung.",
            year = "2026 (Juni 2026)"
        ),
        Law(
            name = "UU ITE - AI & Deepfake (Peraturan Pemerintah No. 22/2026)",
            category = "ITE",
            description = "PP No. 22/2026 (April 2026) khusus mengatur penggunaan AI generatif dan deepfake. Setiap konten hasil AI harus diberi label. Deepfake untuk penipuan → pidana.",
            articles = listOf(
                "Pasal 1: Konten yang dihasilkan AI (gambar, video, suara) WAJIB diberi watermark atau label",
                "Pasal 3: Deepfake untuk meniru tokoh publik tanpa izin → dilarang",
                "Pasal 4: Deepfake untuk penipuan (misal: video call palsu CEO) → pidana 10 tahun + denda Rp 5 miliar",
                "Pasal 5: Deepfake untuk pornografi non-konsensual → pidana 12 tahun + denda Rp 10 miliar",
                "Pasal 7: Platform wajib mendeteksi dan menghapus konten AI ilegal dalam 1×24 jam"
            ),
            penalties = listOf("AI tanpa label: denda Rp 500 juta", "Deepfake penipuan: 10 tahun + 5M", "Deepfake porno: 12 tahun + 10M"),
            realWorldExample = "Video deepfake presiden mengumumkan perang: Pasal 3. Video call deepfake bos minta transfer uang: Pasal 4 (penipuan). Deepfake wajah artis di video porno: Pasal 5.",
            smartEyeXRelevance = "XNAI harus bisa menjelaskan bahwa dia adalah AI, bukan manusia. Output suara XNAI TIDAK boleh dipakai untuk meniru orang lain tanpa izin. Jika user minta XNAI meniru suara orang lain, XNAI harus menolak.",
            year = "2026"
        ),

        // ============================================
        // 4. HUKUM LALU LINTAS (6 aturan)
        // ============================================
        Law(
            name = "UU Lalu Lintas No. 22/2009 (Revisi 2024)",
            category = "Lalu Lintas",
            description = "UU LLAJ mengatur tata cara berlalu lintas di Indonesia. Revisi 2024 menambah sanksi untuk pelanggaran oleh pengendara online.",
            articles = listOf(
                "Pasal 77: Setiap pengendara wajib memiliki SIM",
                "Pasal 106 ayat 6: Pengemudi dan penumpang roda 4 wajib pakai sabuk pengaman",
                "Pasal 106 ayat 8: Pengendara roda 2 wajib pakai helm SNI (penumpang juga!)",
                "Pasal 283: Mengemudi sambil main HP → denda Rp 750.000 atau 3 bulan",
                "Pasal 287: Melanggar lampu merah → denda Rp 500.000 atau 2 bulan",
                "Pasal 287 ayat 1: Tidak bawa SIM → denda Rp 250.000",
                "Pasal 287 ayat 4: Melawan arus → denda Rp 500.000",
                "Pasal 310: Kecelakaan mengakibatkan luka ringan → 6 bulan + denda Rp 1 juta",
                "Pasal 310 ayat 4: Kecelakaan mengakibatkan mati → 6 tahun + denda Rp 12 juta"
            ),
            penalties = listOf("Main HP: 750rb/3 bulan", "Lampu merah: 500rb/2 bulan", "Tanpa SIM: 250rb", "Melawan arus: 500rb", "Kecelakaan fatal: 6 tahun + 12jt"),
            realWorldExample = "Kena tilang karena tidak bawa SIM: Rp 250.000. Ngebut tabrak pejalan kaki sampai meninggal: 6 tahun penjara + denda Rp 12 juta.",
            smartEyeXRelevance = "XNAI harus mengingatkan Bung untuk selalu pakai helm, bawa SIM, tidak main HP saat nyetir. Jika XNAI mendeteksi Bung mau nyetir dalam kondisi mengantuk, harus peringatkan.",
            year = "2009 (Revisi: 2024)"
        ),
        Law(
            name = "Tilang Elektronik (ETLE) - Polda Metro Jaya",
            category = "Lalu Lintas",
            description = "Electronic Traffic Law Enforcement (ETLE) menggunakan kamera AI untuk mendeteksi pelanggaran: tidak pakai helm, main HP, melanggar lampu merah, tidak pakai sabuk, ganjil-genap. Mulai diterapkan nasional 2024.",
            articles = listOf(
                "Kamera ETLE di 1000+ titik di seluruh Indonesia (Juni 2026)",
                "Pelanggaran terdeteksi → surat tilang dikirim ke alamat pemilik kendaraan",
                "Konfirmasi via website ETLE dalam 5 hari",
                "Tidak konfirmasi → STNK diblokir (Pasal 74 PP 80/2012)"
            ),
            penalties = listOf("Denda tilang elektronik: Rp 250.000 - Rp 1.000.000", "Tidak konfirmasi: STNK diblokir"),
            realWorldExample = "Tidak pakai helm terfoto ETLE di perempatan → surat tilang datang ke rumah. Kalau diabaikan, STNK diblokir saat bayar pajak tahunan.",
            smartEyeXRelevance = "XNAI bisa mengingatkan Bung bahwa kamera ETLE ada di banyak titik. Jangan coba-coba langgar aturan karena AI ETLE 24 jam mengawasi.",
            year = "2024"
        ),
        Law(
            name = "Ganjil-Genap & Gage (2026)",
            category = "Lalu Lintas",
            description = "Sistem ganjil-genap diperluas ke 15 kota besar Juni 2026 untuk mengurangi kemacetan dan polusi. Jakarta, Surabaya, Bandung, Medan, Semarang, Makassar, dll.",
            articles = listOf(
                "Berlaku jam 06:00-10:00 dan 16:00-21:00",
                "Ganjil-genap berdasarkan angka terakhir plat nomor",
                "Pengecualian: ambulans, pemadam kebakaran, polisi, kendaraan listrik, transportasi umum",
                "Gage (Ganjil Genap Elektronik): kamera AI deteksi otomatis"
            ),
            penalties = listOf("Melanggar ganjil-genap: denda Rp 500.000 (Pasal 287 UU LLAJ)"),
            realWorldExample = "Plat nomor genap masuk area ganjil-genap di jam sibuk → terfoto kamera Gage → surat tilang ke rumah.",
            smartEyeXRelevance = "XNAI bisa membantu Bung ingat jadwal ganjil-genap. 'Bung, hari ini plat Bung genap, jangan lewat Sudirman jam 7 pagi ya.'",
            year = "2026"
        ),
        Law(
            name = "Batas Usia Berkendara & SIM Digital",
            category = "Lalu Lintas",
            description = "Syarat usia minimal SIM: SIM C (motor) 17 tahun, SIM A (mobil) 17 tahun, SIM B1 (bus/truk) 20 tahun. SIM Digital diluncurkan 2025 via aplikasi Sinar (Polri).",
            articles = listOf(
                "Pasal 81 UU LLAJ: Usia minimal SIM sesuai golongan",
                "SIM Digital: QR code yang bisa dipindai petugas",
                "SIM berlaku 5 tahun, harus diperpanjang sebelum habis",
                "Golongan SIM 2026: SIM C, C1 (250-500cc), C2 (>500cc), SIM A, A Umum, B1, B1 Umum, B2, B2 Umum"
            ),
            penalties = listOf("Mengemudi tanpa SIM: denda Rp 250.000", "SIM mati > 1 tahun: harus buat baru (bukan perpanjang)"),
            realWorldExample = "Anak 15 tahun bawa motor: tilang + motor ditahan. SIM mati 6 bulan belum diperpanjang: harus tes ulang.",
            smartEyeXRelevance = "XNAI bisa mengingatkan Bung untuk perpanjang SIM sebelum habis. 'Bung, SIM A Bung habis bulan depan, jangan lupa perpanjang.'",
            year = "2025 (SIM Digital)"
        ),
        Law(
            name = "Keselamatan Berkendara & Razia Mabuk",
            category = "Lalu Lintas",
            description = "Mengemudi dalam keadaan mabuk atau di bawah pengaruh narkoba adalah tindak pidana. Razia acak diperluas 2026.",
            articles = listOf(
                "Pasal 311 UU LLAJ: Kecelakaan karena mabuk → 10 tahun penjara + denda Rp 20 juta",
                "Pasal 312: Melarikan diri dari tempat kecelakaan → 3 tahun penjara",
                "Razia alkohol & narkoba di jalan raya diperluas Juni 2026"
            ),
            penalties = listOf("Mabuk + kecelakaan: 10 tahun + 20jt", "Kabur dari kecelakaan: 3 tahun"),
            realWorldExample = "Minum alkohol lalu nyetir nabrak orang: Pasal 311 → 10 tahun penjara. Kecelakaan lalu kabur: Pasal 312 → 3 tahun.",
            smartEyeXRelevance = "XNAI harus tegas melarang Bung menyetir dalam kondisi mabuk atau mengantuk berat. 'Bung, kalau habis minum alkohol, jangan nyetir. Bahaya dan bisa kena 10 tahun penjara.'",
            year = "2009 (Revisi: 2024)"
        ),
        Law(
            name = "Kendaraan Listrik & Emisi (Perpres 55/2025)",
            category = "Lalu Lintas",
            description = "Peraturan Presiden tentang percepatan kendaraan listrik. Insentif pajak, subsidi pembelian, dan target 2 juta EV di Indonesia 2026.",
            articles = listOf(
                "Subsidi pembelian motor listrik: Rp 7 juta/unit",
                "Subsidi mobil listrik: Rp 80 juta/unit (rakitan lokal)",
                "SPKLU (Stasiun Pengisian Kendaraan Listrik Umum): 10.000 titik 2026",
                "Uji emisi wajib untuk kendaraan BBM di Jakarta per Juni 2026"
            ),
            penalties = listOf("Tidak lulus uji emisi: tilang Rp 250.000 + wajib perbaiki sebelum boleh jalan lagi"),
            realWorldExample = "Beli motor listrik harga Rp 28 juta, diskon subsidi Rp 7 juta, bayar Rp 21 juta. Mobil bensin gagal uji emisi: ditilang dan harus servis.",
            smartEyeXRelevance = "XNAI bisa kasih info tentang subsidi kendaraan listrik jika Bung bertanya. 'Bung, motor listrik sekarang diskon 7 juta lho. Lumayan buat hemat bensin di bengkel.'",
            year = "2025"
        ),

        // ============================================
        // 5. HUKUM KETENAGAKERJAAN (5 aturan)
        // ============================================
        Law(
            name = "UU Cipta Kerja No. 6/2023 (Omnibus Law)",
            category = "Ketenagakerjaan",
            description = "UU Cipta Kerja menyederhanakan 79 UU terkait ketenagakerjaan, investasi, dan perizinan. Disahkan 2020 (UU 11/2020), direvisi dan diperkuat 2023 (UU 6/2023).",
            articles = listOf(
                "Pasal 77: Jam kerja: 7 jam/hari untuk 6 hari kerja, 8 jam/hari untuk 5 hari kerja",
                "Pasal 78: Lembur maksimal 4 jam/hari, 18 jam/minggu",
                "Pasal 79: Istirahat mingguan: 1 hari untuk 6 hari kerja, 2 hari untuk 5 hari kerja",
                "Pasal 88: Upah minimum ditetapkan Gubernur tiap tahun (UMR/UMK)",
                "Pasal 90: Pengusaha dilarang membayar di bawah UMR"
            ),
            penalties = listOf("Bayar di bawah UMR: sanksi pidana 4 tahun + denda Rp 400 juta (Pasal 185)"),
            realWorldExample = "Buruh di pabrik digaji di bawah UMR: perusahaan bisa dituntut pidana. Lembur tidak dibayar: bisa digugat ke Pengadilan Hubungan Industrial.",
            smartEyeXRelevance = "XNAI harus memahami hak-hak pekerja. Jika Bung curhat tentang gaji di bawah UMR atau lembur tidak dibayar, XNAI bisa sarankan langkah hukum yang tepat.",
            year = "2023"
        ),
        Law(
            name = "BPJS Ketenagakerjaan & Kesehatan",
            category = "Ketenagakerjaan",
            description = "UU No. 24/2011 (revisi 2023) mewajibkan semua pekerja (formal dan informal) didaftarkan BPJS Ketenagakerjaan. 5 program: JHT, JKK, JKM, JKP, JP.",
            articles = listOf(
                "JHT (Jaminan Hari Tua): Dana pensiun, cair saat pensiun/PHK",
                "JKK (Jaminan Kecelakaan Kerja): Biaya pengobatan + santunan cacat/meninggal",
                "JKM (Jaminan Kematian): Santunan ahli waris",
                "JKP (Jaminan Kehilangan Pekerjaan): Uang tunai + pelatihan saat PHK",
                "JP (Jaminan Pensiun): Penghasilan bulanan setelah pensiun"
            ),
            penalties = listOf("Pengusaha tidak mendaftarkan pekerja → denda administratif + wajib bayar seluruh iuran yang tertunggak"),
            realWorldExample = "Mekanik bengkel kecelakaan kerja: JKK cover biaya rumah sakit 100% + santunan. Kena PHK: JKP kasih uang 3-6 bulan + pelatihan gratis.",
            smartEyeXRelevance = "XNAI bisa mengingatkan Bung untuk cek status BPJS. 'Bung, BPJS Ketenagakerjaan Bung masih aktif kan? Kalau ada apa-apa di bengkel, biaya ditanggung.'",
            year = "2011 (Revisi: 2023)"
        ),
        Law(
            name = "K3 (Keselamatan dan Kesehatan Kerja) - UU No. 1/1970",
            category = "Ketenagakerjaan",
            description = "UU K3 mewajibkan setiap tempat kerja untuk memenuhi standar keselamatan. APD, APAR, P3K, dan SOP keselamatan adalah KEWAJIBAN.",
            articles = listOf(
                "Pasal 3: Setiap tempat kerja wajib memenuhi syarat K3",
                "Pasal 9: Pengurus wajib menyediakan APD secara gratis",
                "Pasal 12: Pekerja WAJIB memakai APD yang disediakan",
                "Pasal 14: Pengurus wajib menyediakan APAR dan P3K",
                "Pasal 15: Setiap kecelakaan kerja wajib dilaporkan ke Disnaker dalam 2×24 jam"
            ),
            penalties = listOf("Melanggar K3 → denda Rp 50 juta (UU Cipta Kerja 2023)", "Kecelakaan fatal karena tidak ada APD → pidana 5 tahun + denda Rp 500 juta"),
            realWorldExample = "Bengkel tidak menyediakan APAR → didenda. Pekerja las tidak dikasih helm las → kecelakaan mata → perusahaan dituntut pidana.",
            smartEyeXRelevance = "XNAI harus selalu mengingatkan K3! Jika mendeteksi Bung tidak pakai APD di bengkel, XNAI harus peringatkan. 'Bung, kacamata safety-nya dipakai. Itu wajib dan bisa kena denda kalau ada inspeksi.'",
            year = "1970 (Masih berlaku, diperkuat UU Cipta Kerja 2023)"
        ),
        Law(
            name = "Anti-Diskriminasi di Tempat Kerja (UU No. 21/2025)",
            category = "Ketenagakerjaan",
            description = "UU baru yang khusus melarang diskriminasi di tempat kerja berdasarkan gender, agama, suku, disabilitas, atau orientasi politik. Disahkan Maret 2025.",
            articles = listOf(
                "Pasal 4: Dilarang menolak pelamar karena gender, agama, atau suku",
                "Pasal 6: Gaji yang sama untuk pekerjaan yang sama nilainya (equal pay)",
                "Pasal 9: Perempuan hamil tidak boleh di-PHK",
                "Pasal 12: Tempat kerja harus aksesibel untuk penyandang disabilitas",
                "Pasal 15: Pelecehan di tempat kerja → pidana 3 tahun + denda Rp 200 juta"
            ),
            penalties = listOf("Diskriminasi: denda Rp 100 juta", "PHK karena hamil: pidana 2 tahun + wajib pekerjakan kembali", "Pelecehan: 3 tahun + 200jt"),
            realWorldExample = "Perusahaan menolak pelamar karena berjilbab: melanggar Pasal 4. Perempuan di-PHK karena hamil: Pasal 9 → perusahaan harus pekerjakan kembali.",
            smartEyeXRelevance = "XNAI harus memahami bahwa semua orang setara di mata hukum. Tidak boleh memihak atau mendukung diskriminasi dalam bentuk apapun.",
            year = "2025"
        ),
        Law(
            name = "Upah Minimum 2026",
            category = "Ketenagakerjaan",
            description = "Upah Minimum Provinsi (UMP) dan Kabupaten/Kota (UMK) diperbarui tiap tahun. Kenaikan 2026 rata-rata 6.5% (formula PP 36/2021).",
            articles = listOf(
                "DKI Jakarta 2026: Rp 5.637.000 (naik dari 5.295.000)",
                "Jawa Barat 2026: Rp 3.850.000",
                "Jawa Tengah 2026: Rp 2.950.000",
                "Jawa Timur 2026: Rp 3.650.000",
                "UMK Bekasi 2026: Rp 5.980.000 (tertinggi se-Indonesia)"
            ),
            penalties = listOf("Bayar di bawah UMP/UMK: sanksi pidana + denda"),
            realWorldExample = "Perusahaan di Jakarta bayar karyawan Rp 5 juta padahal UMP Rp 5.637.000: melanggar UU Cipta Kerja, bisa dituntut.",
            smartEyeXRelevance = "XNAI bisa kasih info UMP 2026 jika Bung bertanya. Relevan jika Bung adalah pemilik bengkel yang punya karyawan.",
            year = "2026"
        ),

        // ============================================
        // 6. HUKUM KONSUMEN (4 aturan)
        // ============================================
        Law(
            name = "UU Perlindungan Konsumen No. 8/1999",
            category = "Konsumen",
            description = "UU yang melindungi hak-hak konsumen: hak atas informasi, hak memilih, hak komplain, hak atas keamanan produk.",
            articles = listOf(
                "Pasal 4: Konsumen berhak atas informasi yang benar, jelas, jujur",
                "Pasal 7: Pelaku usaha wajib memberi informasi yang benar",
                "Pasal 8: Dilarang menipu konsumen tentang kualitas/kuantitas",
                "Pasal 19: Pelaku usaha bertanggung jawab atas kerugian konsumen",
                "Pasal 25: Garansi minimal 7 hari untuk barang elektronik",
                "Pasal 62: Sanksi pidana penipuan konsumen → 5 tahun + denda Rp 2 miliar"
            ),
            penalties = listOf("Penipuan konsumen: 5 tahun + 2M", "Barang cacat tidak diganti: ganti rugi 2x lipat harga"),
            realWorldExample = "Beli HP ternyata bekas dijual baru: Pasal 8 + 62 → penjual bisa dituntut pidana. Barang rusak dalam 7 hari tidak diganti: Pasal 25.",
            smartEyeXRelevance = "XNAI bisa membantu Bung memahami hak sebagai konsumen. 'Bung, HP yang baru Bung beli rusak dalam 3 hari? Itu wajib diganti. Bisa komplain ke penjual.'",
            year = "1999 (Masih berlaku 2026)"
        ),
        Law(
            name = "Garansi & Servis - Permendag No. 19/2024",
            category = "Konsumen",
            description = "Peraturan Menteri Perdagangan yang memperjelas aturan garansi: garansi pabrikan vs garansi toko, servis gratis, dan refund.",
            articles = listOf(
                "Garansi pabrikan minimal 1 tahun untuk elektronik",
                "Garansi toko minimal 7 hari untuk semua barang",
                "Servis gratis dalam masa garansi, termasuk ongkos kirim (ditanggung penjual)",
                "Refund (uang kembali) jika barang tidak bisa diperbaiki dalam 30 hari",
                "Konsumen bisa komplain via aplikasi Cekatan (Kemendag)"
            ),
            penalties = listOf("Menolak garansi yang sah: sanksi administrasi + ganti rugi 2x lipat"),
            realWorldExample = "TV 32 inch rusak bulan ke-3: masih garansi pabrikan, servis gratis termasuk pickup. TV rusak 3x dalam garansi: minta refund penuh.",
            smartEyeXRelevance = "XNAI bisa bantu Bung cek status garansi dan prosedur komplain. 'Bung, mesin bubut ini garansinya 1 tahun. Masih ada 8 bulan lagi. Kalau rusak, servis gratis.'",
            year = "2024"
        ),
        Law(
            name = "Transaksi Online & COD - Permendag No. 50/2020",
            category = "Konsumen",
            description = "Aturan transaksi online: marketplace, COD, refund. Konsumen berhak menolak barang jika tidak sesuai pesanan saat COD.",
            articles = listOf(
                "Pembeli COD berhak membuka dan memeriksa barang sebelum bayar",
                "Jika barang tidak sesuai: tolak bayar, kurir bawa kembali",
                "Refund harus diproses dalam 2 hari setelah barang diterima kembali",
                "Marketplace wajib menyediakan fitur komplain yang mudah diakses"
            ),
            penalties = listOf("Marketplace tidak memproses refund: denda Rp 50 juta per kasus"),
            realWorldExample = "Pesan mesin bor, datangnya mesin gerinda: saat COD, tolak bayar. Barang dikembalikan, uang direfund dalam 2 hari.",
            smartEyeXRelevance = "XNAI bisa kasih tips transaksi online yang aman. 'Bung, kalau beli sparepart online, pastikan COD biar bisa cek dulu sebelum bayar.'",
            year = "2020 (Masih berlaku 2026)"
        ),
        Law(
            name = "BPOM & Sertifikasi (PerBPOM No. 25/2026)",
            category = "Konsumen",
            description = "Peraturan Badan POM terbaru Juni 2026: semua produk makanan, minuman, obat, suplemen, dan alat kesehatan WAJIB punya izin edar BPOM.",
            articles = listOf(
                "Produk tanpa izin BPOM: dilarang dijual (online maupun offline)",
                "Marketplace wajib hapus produk tanpa izin BPOM dalam 1×24 jam",
                "Konsumen bisa cek izin BPOM via aplikasi BPOM Mobile (scan barcode)",
                "Pelanggaran: denda Rp 500 juta + pidana 3 tahun"
            ),
            penalties = listOf("Jual produk tanpa izin BPOM: 3 tahun + 500jt"),
            realWorldExample = "Suplemen pelangsing viral di TikTok tanpa izin BPOM: dihapus dari marketplace, penjual ditangkap.",
            smartEyeXRelevance = "XNAI bisa bantu Bung cek apakah suatu produk punya izin BPOM. 'Bung, sebelum beli suplemen ini, cek dulu di BPOM Mobile. Kalau nggak ada izin, berbahaya.'",
            year = "2026"
        ),

        // ============================================
        // 7. KESELAMATAN KERJA K3 (4 aturan)
        // ============================================
        Law(
            name = "APD Wajib Bengkel (Permenaker No. 8/2020)",
            category = "K3",
            description = "Peraturan Menteri Ketenagakerjaan tentang APD wajib di tempat kerja. Setiap bengkel WAJIB menyediakan APD lengkap untuk semua pekerja.",
            articles = listOf(
                "Helm safety: wajib di area konstruksi dan area overhead",
                "Kacamata safety: wajib saat bekerja dengan mesin, las, gerinda",
                "Sarung tangan: wajib saat pegang benda panas/tajam (TAPI JANGAN saat mesin berputar!)",
                "Sepatu safety (steel toe): wajib di bengkel (ada risiko kejatuhan benda)",
                "Earplug/earmuff: wajib jika kebisingan > 85 dB (mesin, kompresor)",
                "Masker respirator: wajib saat las, gerinda, atau paparan debu logam",
                "APD yang rusak: WAJIB diganti pengusaha secara gratis"
            ),
            penalties = listOf("Tidak menyediakan APD: denda Rp 50 juta (UU Cipta Kerja)", "Pekerja tidak pakai APD yang sudah disediakan: sanksi peringatan sampai PHK"),
            realWorldExample = "Mekanik tidak pakai kacamata saat mengelas: kena percikan api di mata. Jika APD disediakan tapi tidak dipakai, perusahaan tidak bertanggung jawab.",
            smartEyeXRelevance = "XNAI harus selalu cek APD Bung via kamera! 'Bung, kacamata safety-nya mana? Itu wajib. Mata Bung lebih penting dari kenyamanan.'",
            year = "2020"
        ),
        Law(
            name = "APAR & P3K (Permen PU No. 26/2008)",
            category = "K3",
            description = "Setiap bangunan wajib memiliki APAR (Alat Pemadam Api Ringan) dan kotak P3K. Diperkuat oleh Permenaker No. 15/2008.",
            articles = listOf(
                "APAR: minimal 1 unit per 200 m², mudah dijangkau, diperiksa tiap 6 bulan",
                "P3K: minimal kotak P3K tipe I (untuk 25 pekerja), isi lengkap (perban, antiseptik, obat dasar)",
                "Jalur evakuasi: harus ada, tidak boleh terhalang, ada rambu jelas",
                "Titik kumpul: harus ditentukan, diketahui semua pekerja",
                "Simulasi kebakaran: minimal 6 bulan sekali"
            ),
            penalties = listOf("Tidak ada APAR: denda Rp 100 juta", "Tidak ada P3K: denda Rp 50 juta"),
            realWorldExample = "Bengkel kebakaran karena korsleting, APAR tidak ada → api membesar → seluruh bengkel terbakar. Jika ada APAR, api bisa dipadamkan dalam 30 detik pertama.",
            smartEyeXRelevance = "XNAI harus bisa mendeteksi APAR dan P3K di area bengkel. 'Bung, APAR di bengkel kelihatannya sudah expired. Cek tanggalnya, harus diganti.'",
            year = "2008"
        ),
        Law(
            name = "SOP Mesin Berat (Permenaker No. 38/2016)",
            category = "K3",
            description = "Setiap mesin berat wajib memiliki SOP tertulis dan operator bersertifikat. Termasuk mesin bubut, las, CNC, forklift, crane.",
            articles = listOf(
                "Operator mesin berat wajib punya SIO (Surat Izin Operasi) dari Disnaker",
                "SOP harus ditempel di dekat mesin, mudah dibaca",
                "Pemeriksaan mesin: wajib setiap hari sebelum operasi (checklist)",
                "Lockout-tagout: prosedur wajib saat maintenance (mesin mati + dikunci + ditandai)",
                "Pelaporan kecelakaan: wajib lapor Disnaker dalam 2×24 jam"
            ),
            penalties = listOf("Operator tanpa SIO: denda Rp 25 juta", "Kecelakaan fatal tanpa SOP: pidana 5 tahun + denda Rp 500 juta"),
            realWorldExample = "Operator mesin bubut tidak punya SIO lalu kecelakaan: perusahaan kena pidana. Saat maintenance mesin, mesin tidak di-lockout → ada yang nyalakan mesin → mekanik cedera parah.",
            smartEyeXRelevance = "XNAI bisa membantu mengingatkan SOP. 'Bung, sebelum nyalain mesin bubut, checklist dulu: kacamata, chuck kencang, coolant cukup, area bersih.'",
            year = "2016"
        ),
        Law(
            name = "Izin Operasional Bengkel (PP No. 5/2021)",
            category = "K3",
            description = "Setiap bengkel wajib memiliki NIB (Nomor Induk Berusaha) via OSS (Online Single Submission). Kemudahan perizinan lewat UU Cipta Kerja.",
            articles = listOf(
                "NIB berlaku sebagai: izin usaha, izin lokasi, izin lingkungan (AMDAL/UKL-UPL)",
                "Bengkel kecil/mikro: cukup NIB, tanpa izin tambahan",
                "Bengkel menengah (>10 pekerja): wajib UKL-UPL (pengelolaan limbah B3)",
                "Limbah B3 (oli bekas, coolant, filter): wajib disimpan di tempat khusus, diserahkan ke pengumpul berizin"
            ),
            penalties = listOf("Tidak punya NIB: denda Rp 5 juta (usaha mikro), Rp 100 juta (usaha besar)", "Buang limbah B3 sembarangan: pidana 10 tahun + denda Rp 10 miliar (UU Lingkungan Hidup)"),
            realWorldExample = "Bengkel buang oli bekas ke selokan: kena UU Lingkungan Hidup → 10 tahun penjara. Bengkel punya NIB tapi tidak urus limbah: izin dicabut.",
            smartEyeXRelevance = "XNAI bisa mengingatkan pentingnya izin dan pengelolaan limbah. 'Bung, oli bekas jangan dibuang sembarangan ya. Bisa kena 10 tahun penjara itu.'",
            year = "2021"
        ),

        // ============================================
        // 8. HUKUM LAINNYA (4 aturan)
        // ============================================
        Law(
            name = "UU Perlindungan Anak No. 35/2014 (Revisi 2022)",
            category = "Lainnya",
            description = "UU yang melindungi anak (di bawah 18 tahun) dari kekerasan, eksploitasi, dan penelantaran. Revisi 2022 menambah sanksi untuk predator anak online.",
            articles = listOf(
                "Pasal 76C: Dilarang menempatkan anak dalam situasi berbahaya",
                "Pasal 76D: Dilarang mengeksploitasi anak secara ekonomi",
                "Pasal 76E: Dilarang melakukan kekerasan terhadap anak",
                "Pasal 81: Kekerasan seksual terhadap anak → pidana 15 tahun + denda Rp 5 miliar",
                "Pasal 82: Eksploitasi seksual anak online → pidana 20 tahun + denda Rp 10 miliar"
            ),
            penalties = listOf("Kekerasan anak: 15 tahun", "Eksploitasi anak online: 20 tahun + 10M"),
            realWorldExample = "Orang tua mempekerjakan anak 12 tahun di bengkel sebagai pekerja penuh: melanggar Pasal 76D. Predator grooming anak di game online: Pasal 82 → 20 tahun penjara.",
            smartEyeXRelevance = "XNAI harus peka terhadap eksploitasi anak. Jika melihat anak di bawah umur bekerja di lingkungan berbahaya, XNAI harus peringatkan.",
            year = "2014 (Revisi: 2022)"
        ),
        Law(
            name = "UU KDRT No. 23/2004",
            category = "Lainnya",
            description = "UU Penghapusan Kekerasan Dalam Rumah Tangga. Melindungi korban KDRT dan menghukum pelaku.",
            articles = listOf(
                "Pasal 5: Larangan kekerasan fisik, psikis, seksual, dan penelantaran",
                "Pasal 10: Korban berhak mendapatkan perlindungan dan pelayanan kesehatan",
                "Pasal 44: Kekerasan fisik → pidana 5 tahun + denda Rp 15 juta",
                "Pasal 45: Kekerasan psikis → pidana 3 tahun + denda Rp 9 juta"
            ),
            penalties = listOf("KDRT fisik: 5 tahun + 15jt", "KDRT psikis: 3 tahun + 9jt"),
            realWorldExample = "Suami memukul istri: Pasal 44 → 5 tahun penjara. Suami mengancam dan menghina istri terus-menerus: Pasal 45 (kekerasan psikis).",
            smartEyeXRelevance = "XNAI harus peka terhadap indikasi KDRT. Jika mendeteksi tanda-tanda KDRT (user cerita, atau suara pertengkaran keras), sarankan melapor ke polisi atau Komnas Perempuan.",
            year = "2004"
        ),
        Law(
            name = "UU Lingkungan Hidup No. 32/2009",
            category = "Lainnya",
            description = "UU Perlindungan dan Pengelolaan Lingkungan Hidup. Mengatur AMDAL, baku mutu lingkungan, dan sanksi pidana perusakan lingkungan.",
            articles = listOf(
                "Pasal 69: Dilarang membuang limbah ke lingkungan tanpa izin",
                "Pasal 98: Perusakan lingkungan mengakibatkan kematian → pidana 10 tahun + denda Rp 10 miliar",
                "Pasal 99: Membuang limbah B3 → pidana 5 tahun + denda Rp 5 miliar",
                "Pasal 100: Pelanggaran baku mutu lingkungan → pidana 3 tahun + denda Rp 3 miliar"
            ),
            penalties = listOf("Buang limbah B3: 5 tahun + 5M", "Kerusakan lingkungan + kematian: 10 tahun + 10M"),
            realWorldExample = "Pabrik buang limbah kimia ke sungai, warga keracunan: Pasal 98 → 10 tahun penjara. Bengkel buang oli ke tanah: Pasal 99 → 5 tahun.",
            smartEyeXRelevance = "XNAI harus mengingatkan pentingnya menjaga lingkungan. 'Bung, oli bekas ada tempat khususnya. Jangan dibuang ke tanah, bisa kena 5 tahun.'",
            year = "2009"
        ),
        Law(
            name = "Hukum Waris (KUH Perdata & Hukum Islam)",
            category = "Lainnya",
            description = "Indonesia punya 3 sistem hukum waris: KUH Perdata (nasional non-Muslim), Hukum Islam (Muslim), dan Hukum Adat (tergantung suku).",
            articles = listOf(
                "KUH Perdata: waris dibagi rata ke ahli waris golongan I (anak+pasangan), golongan II (orang tua), golongan III (kakek-nenek), golongan IV (saudara jauh)",
                "Hukum Islam (Kompilasi Hukum Islam): anak laki-laki 2:1 dengan anak perempuan, pasangan dapat 1/4 (suami) atau 1/8 (istri)",
                "Hukum Adat: bervariasi (Patrilineal di Batak, Matrilineal di Minangkabau)"
            ),
            penalties = listOf("Menghilangkan hak waris secara melawan hukum: dapat digugat perdata"),
            realWorldExample = "Ayah meninggal, anak tunggal dapat seluruh harta (KUH Perdata). Ayah meninggal, 1 anak laki-laki + 2 anak perempuan: laki-laki dapat 1/2, perempuan masing-masing 1/4 (Islam).",
            smartEyeXRelevance = "XNAI bisa jelaskan dasar-dasar hukum waris jika Bung bertanya, tapi harus merujuk ke ahli (notaris/ulama) untuk detailnya.",
            year = "Berlaku terus"
        )
    )
    
    fun getLaw(name: String): Law? = allLaws.find { it.name.lowercase().contains(name.lowercase()) }
    fun getLawsByCategory(category: String): List<Law> = allLaws.filter { it.category == category }
    fun getLawsByYear(year: String): List<Law> = allLaws.filter { it.year.contains(year) }
    fun getRecentLaws(): List<Law> = allLaws.filter { it.year.contains("2026") || it.year.contains("2025") || it.year.contains("2024") }
}