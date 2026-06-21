package com.smarteyex.lite

data class PhysicsConcept(
    val name: String,
    val category: String,
    val formula: String,
    val variables: Map<String, String>,
    val unit: String,
    val realWorldExample: String,
    val bengkelExample: String,
    val relatedConcepts: List<String>,
    val dangerIfViolated: String
)

class PhysicsSystem {
    
    val allConcepts: List<PhysicsConcept> = listOf(
        // ============================================
        // 1. MEKANIKA KLASIK (12 konsep)
        // ============================================
        PhysicsConcept(
            name = "Gaya", category = "Mekanika",
            formula = "F = m × a",
            variables = mapOf("F" to "Gaya", "m" to "Massa (kg)", "a" to "Percepatan (m/s²)"),
            unit = "Newton (N)",
            realWorldExample = "Mendorong mobil mogok: makin berat mobil, makin besar gaya yang dibutuhkan. Mobil 1000kg butuh gaya 2000N untuk percepatan 2 m/s².",
            bengkelExample = "Saat menekan pedal rem: kaki memberi gaya ke pedal, diteruskan ke kampas rem. Makin keras injakan, makin besar gaya pengereman.",
            relatedConcepts = listOf("Massa", "Percepatan", "Momentum", "Gesekan"),
            dangerIfViolated = "Jika gaya rem tidak cukup (rem blong), kendaraan tidak bisa berhenti → kecelakaan fatal."
        ),
        PhysicsConcept(
            name = "Momentum", category = "Mekanika",
            formula = "p = m × v",
            variables = mapOf("p" to "Momentum (kg·m/s)", "m" to "Massa (kg)", "v" to "Kecepatan (m/s)"),
            unit = "kg·m/s",
            realWorldExample = "Truk 10 ton bergerak 20 m/s punya momentum 200.000 kg·m/s. Sangat sulit dihentikan mendadak. Tabrakan truk vs motor: truk menang karena momentum jauh lebih besar.",
            bengkelExample = "Chuck mesin bubut yang berat berputar kencang menyimpan momentum besar. Saat dimatikan, chuck butuh waktu lama untuk berhenti total karena momentumnya.",
            relatedConcepts = listOf("Impuls", "Tumbukan", "Gaya", "Energi Kinetik"),
            dangerIfViolated = "Momentum benda berputar (chuck) tidak dihargai → tangan mencoba menghentikan paksa → tangan patah atau terseret mesin."
        ),
        PhysicsConcept(
            name = "Impuls", category = "Mekanika",
            formula = "J = F × Δt = Δp",
            variables = mapOf("J" to "Impuls (N·s)", "F" to "Gaya (N)", "Δt" to "Selang waktu (s)", "Δp" to "Perubahan momentum"),
            unit = "Newton-detik (N·s)",
            realWorldExample = "Airbag mobil: memperlama waktu benturan dari 0.01 detik jadi 0.1 detik. Gaya yang diterima tubuh berkurang 10x lipat. Inilah kenapa airbag menyelamatkan nyawa.",
            bengkelExample = "Saat memukul palu ke benda kerja: waktu kontak sangat singkat (0.001 detik) → gaya yang dihasilkan sangat besar. Itu sebabnya palu bisa membentuk logam.",
            relatedConcepts = listOf("Momentum", "Gaya", "Tumbukan"),
            dangerIfViolated = "Tanpa airbag/helm: waktu benturan singkat → gaya besar → cedera parah atau kematian."
        ),
        PhysicsConcept(
            name = "Energi Kinetik", category = "Mekanika",
            formula = "Ek = ½ × m × v²",
            variables = mapOf("Ek" to "Energi Kinetik (J)", "m" to "Massa (kg)", "v" to "Kecepatan (m/s)"),
            unit = "Joule (J)",
            realWorldExample = "Peluru 10 gram melaju 400 m/s: Ek = ½ × 0.01 × 160000 = 800 Joule. Cukup untuk menembus baja tipis. Kecepatan lebih berpengaruh dari massa (kuadrat!).",
            bengkelExample = "Batu gerinda berputar 3000 RPM: diameter 20cm = kecepatan linear 31.4 m/s. Jika batu pecah, pecahannya punya energi kinetik tinggi dan bisa menembus kulit.",
            relatedConcepts = listOf("Energi Potensial", "Usaha", "Kecepatan", "Momentum"),
            dangerIfViolated = "Batu gerinda retak tidak diganti → pecah saat berputar kencang → pecahan tertembak seperti peluru → fatal."
        ),
        PhysicsConcept(
            name = "Energi Potensial Gravitasi", category = "Mekanika",
            formula = "Ep = m × g × h",
            variables = mapOf("Ep" to "Energi Potensial (J)", "m" to "Massa (kg)", "g" to "Gravitasi (9.8 m/s²)", "h" to "Ketinggian (m)"),
            unit = "Joule (J)",
            realWorldExample = "Air terjun setinggi 100m: tiap 1kg air menyimpan Ep = 1 × 9.8 × 100 = 980 Joule. PLTA mengubah Ep ini jadi listrik.",
            bengkelExample = "Dongkrak hidrolik mengangkat mobil 1 ton setinggi 50cm: Ep = 1000 × 9.8 × 0.5 = 4900 Joule. Jika dongkrak gagal, energi ini berubah jadi energi kinetik → mobil jatuh.",
            relatedConcepts = listOf("Energi Kinetik", "Usaha", "Gravitasi", "Ketinggian"),
            dangerIfViolated = "Dongkrak tidak dikunci atau jack stand tidak dipasang → energi potensial berubah jadi kinetik → mobil jatuh menimpa mekanik → fatal."
        ),
        PhysicsConcept(
            name = "Energi Potensial Pegas", category = "Mekanika",
            formula = "Ep = ½ × k × x²",
            variables = mapOf("Ep" to "Energi Potensial Pegas (J)", "k" to "Konstanta pegas (N/m)", "x" to "Simpangan (m)"),
            unit = "Joule (J)",
            realWorldExample = "Shockbreaker motor: menyerap energi dari jalan bergelombang. Pegas menyimpan energi lalu melepaskannya perlahan lewat redaman oli.",
            bengkelExample = "Kunci momen (torque wrench): pegas di dalamnya terkalibrasi. Saat torsi tercapai, pegas 'klik' melepas energi. Mencegah baut terlalu kencang atau terlalu longgar.",
            relatedConcepts = listOf("Gaya Pegas", "Hukum Hooke", "Getaran"),
            dangerIfViolated = "Shockbreaker rusak (k bocor): tidak bisa menyerap getaran → kendaraan tidak stabil → kecelakaan."
        ),
        PhysicsConcept(
            name = "Hukum Kekekalan Energi", category = "Mekanika",
            formula = "Em₁ = Em₂  →  Ep₁ + Ek₁ = Ep₂ + Ek₂",
            variables = mapOf("Em" to "Energi Mekanik (J)", "Ep" to "Energi Potensial", "Ek" to "Energi Kinetik"),
            unit = "Joule (J)",
            realWorldExample = "Roller coaster: di puncak (Ep max, Ek=0) → turun (Ep→Ek) → di bawah (Ek max, Ep=0) → naik lagi (Ek→Ep). Energi total selalu sama (abaikan gesekan).",
            bengkelExample = "Flywheel mesin: menyimpan energi kinetik saat mesin menyala, melepaskannya saat mesin hampir mati. Menjaga putaran mesin tetap halus.",
            relatedConcepts = listOf("Energi Kinetik", "Energi Potensial", "Usaha"),
            dangerIfViolated = "Energi tidak bisa dimusnahkan. Rem mobil mengubah Ek menjadi panas. Rem terlalu panas (fading) → energi tidak terserap → rem blong."
        ),
        PhysicsConcept(
            name = "Gesekan Statis", category = "Mekanika",
            formula = "fs ≤ μs × N",
            variables = mapOf("fs" to "Gaya gesek statis (N)", "μs" to "Koefisien gesek statis", "N" to "Gaya normal (N)"),
            unit = "Newton (N)",
            realWorldExample = "Ban mobil di jalan kering: μs ≈ 0.8. Di jalan basah: μs ≈ 0.4. Jarak pengereman di jalan basah 2x lebih panjang!",
            bengkelExample = "Chuck menjepit benda kerja: gaya jepit menciptakan gesekan statis tinggi antara rahang dan benda. Inilah yang menahan benda tidak terlepas saat berputar.",
            relatedConcepts = listOf("Gesekan Kinetis", "Gaya Normal", "Rem"),
            dangerIfViolated = "Koefisien gesek turun (oli di lantai): μs → 0.1 → orang terpeleset. Benda kerja tidak dijepit kencang → terlepas dari chuck → terlempar."
        ),
        PhysicsConcept(
            name = "Gesekan Kinetis", category = "Mekanika",
            formula = "fk = μk × N",
            variables = mapOf("fk" to "Gaya gesek kinetis (N)", "μk" to "Koefisien gesek kinetis", "N" to "Gaya normal (N)"),
            unit = "Newton (N)",
            realWorldExample = "Rem ABS: menjaga roda tidak terkunci (masih berputar pelan) sehingga tetap pakai gesekan statis (μs > μk). Jarak pengereman lebih pendek daripada roda terkunci total.",
            bengkelExample = "Mata pisau bubut memotong logam: gesekan kinetis antara pisau dan benda kerja menghasilkan panas. Coolant diperlukan untuk mengurangi panas akibat gesekan ini.",
            relatedConcepts = listOf("Gesekan Statis", "Panas", "Rem"),
            dangerIfViolated = "Tanpa coolant: gesekan kinetis berlebih → panas berlebih → mata pisau tumpul atau benda kerja rusak."
        ),
        PhysicsConcept(
            name = "Torsi", category = "Mekanika",
            formula = "τ = r × F × sin(θ)",
            variables = mapOf("τ" to "Torsi (N·m)", "r" to "Jarak dari poros (m)", "F" to "Gaya (N)", "θ" to "Sudut antara r dan F"),
            unit = "Newton-meter (N·m)",
            realWorldExample = "Kunci roda: pipa perpanjangan menambah r → torsi lebih besar dengan gaya yang sama. Baut roda yang susah dibuka jadi mudah.",
            bengkelExample = "Engkol mesin bubut: makin panjang tuas engkol (r besar), makin ringan memutarnya. Prinsip ini dipakai untuk mengatur kecepatan potong dengan presisi.",
            relatedConcepts = listOf("Gaya", "Momen Inersia", "Kecepatan Sudut", "Kunci Momen"),
            dangerIfViolated = "Torsi berlebih pada baut: baut patah atau ulir rusak. Torsi kurang: baut longgar → komponen lepas."
        ),
        PhysicsConcept(
            name = "Momen Inersia", category = "Mekanika",
            formula = "I = Σ m × r²",
            variables = mapOf("I" to "Momen Inersia (kg·m²)", "m" to "Massa partikel (kg)", "r" to "Jarak dari sumbu putar (m)"),
            unit = "kg·m²",
            realWorldExample = "Penari es: tangan direntangkan → I besar → putaran lambat. Tangan ditarik ke dada → I kecil → putaran cepat. Momentum sudut tetap.",
            bengkelExample = "Flywheel mesin: sengaja dibuat berat di bagian luar (r besar) → I besar → menyimpan energi putaran banyak → mesin halus.",
            relatedConcepts = listOf("Torsi", "Momentum Sudut", "Kecepatan Sudut"),
            dangerIfViolated = "Flywheel retak atau tidak seimbang: getaran berlebih → bantalan rusak → flywheel bisa terlepas."
        ),
        PhysicsConcept(
            name = "Kecepatan Sudut", category = "Mekanika",
            formula = "ω = v / r = 2π × f",
            variables = mapOf("ω" to "Kecepatan sudut (rad/s)", "v" to "Kecepatan linear (m/s)", "r" to "Radius (m)", "f" to "Frekuensi (Hz)"),
            unit = "radian/detik (rad/s)",
            realWorldExample = "Kipas angin 1200 RPM: ω = 1200 × 2π/60 = 125.6 rad/s. Ujung baling-baling (r=20cm) bergerak dengan v = ω×r = 25.1 m/s!",
            bengkelExample = "Mesin bubut 1000 RPM: chuck diameter 20cm → ω = 104.7 rad/s. Kecepatan linear di permukaan chuck = 10.47 m/s. Jika tangan menyentuh, langsung terseret.",
            relatedConcepts = listOf("Kecepatan Linear", "Frekuensi", "Periode", "Torsi"),
            dangerIfViolated = "Tidak menghargai kecepatan linear di permukaan chuck: tangan mendekat → terseret masuk → kecelakaan fatal."
        ),

        // ============================================
        // 2. LISTRIK & MAGNET (8 konsep)
        // ============================================
        PhysicsConcept(
            name = "Hukum Ohm", category = "Listrik",
            formula = "V = I × R",
            variables = mapOf("V" to "Tegangan (Volt)", "I" to "Arus (Ampere)", "R" to "Hambatan (Ohm)"),
            unit = "Volt (V)",
            realWorldExample = "Charger HP 5V, kabel USB hambatan 0.5Ω, arus 2A. Tegangan jatuh di kabel = 2 × 0.5 = 1V. HP hanya terima 4V → charging lebih lambat.",
            bengkelExample = "Aki mobil 12V, starter butuh 100A. Hambatan kabel starter harus < 0.01Ω agar tegangan jatuh < 1V. Kabel starter pakai tembaga tebal.",
            relatedConcepts = listOf("Daya", "Energi Listrik", "Hukum Kirchhoff"),
            dangerIfViolated = "Kabel terlalu kecil → hambatan besar → panas berlebih → isolasi meleleh → korslet → kebakaran."
        ),
        PhysicsConcept(
            name = "Daya Listrik", category = "Listrik",
            formula = "P = V × I = I² × R = V² / R",
            variables = mapOf("P" to "Daya (Watt)", "V" to "Tegangan (V)", "I" to "Arus (A)", "R" to "Hambatan (Ω)"),
            unit = "Watt (W)",
            realWorldExample = "Setrika 350W vs lampu LED 10W. Setrika butuh arus 350/220 = 1.6A. Lampu LED hanya 0.045A. Setrika menghasilkan panas karena dayanya besar.",
            bengkelExample = "Mesin las 9000W: arus 9000/220 = 40.9A! Butuh kabel tebal dan MCB khusus. Tidak bisa dicolok ke stop kontak biasa.",
            relatedConcepts = listOf("Energi Listrik", "Hukum Ohm", "Arus"),
            dangerIfViolated = "Alat listrik melebihi daya stop kontak → kabel panas → isolasi meleleh → kebakaran listrik."
        ),
        PhysicsConcept(
            name = "Energi Listrik", category = "Listrik",
            formula = "W = P × t",
            variables = mapOf("W" to "Energi (Joule/kWh)", "P" to "Daya (Watt/kW)", "t" to "Waktu (detik/jam)"),
            unit = "kWh (kilowatt-jam)",
            realWorldExample = "AC 1000W nyala 8 jam = 8 kWh. Tarif listrik Rp 1.500/kWh → biaya Rp 12.000/hari → Rp 360.000/bulan hanya untuk AC.",
            bengkelExample = "Kompresor 3000W nyala 2 jam/hari = 6 kWh. Mesin bubut 2000W nyala 5 jam = 10 kWh. Total 16 kWh/hari → tagihan listrik bengkel besar.",
            relatedConcepts = listOf("Daya", "Waktu", "Biaya Listrik"),
            dangerIfViolated = "Lupa matikan alat listrik → energi terbuang → tagihan membengkak. Atau kabel panas terus-menerus → kebakaran."
        ),
        PhysicsConcept(
            name = "Hukum Kirchhoff Arus", category = "Listrik",
            formula = "Σ I_masuk = Σ I_keluar",
            variables = mapOf("Σ I_masuk" to "Jumlah arus masuk", "Σ I_keluar" to "Jumlah arus keluar"),
            unit = "Ampere (A)",
            realWorldExample = "Panel listrik rumah: arus dari PLN 10A masuk, terbagi ke cabang: 2A lampu + 3A kulkas + 5A AC = 10A total. Di titik percabangan, arus masuk = arus keluar.",
            bengkelExample = "Kabel power mesin bubut: arus dari sumber terbagi ke motor utama (15A), motor coolant (2A), lampu (1A). Total 18A masuk = 18A keluar di titik percabangan.",
            relatedConcepts = listOf("Hukum Ohm", "Rangkaian Paralel", "Arus"),
            dangerIfViolated = "Percabangan tidak seimbang → arus balik → korslet → MCB trip atau kebakaran."
        ),
        PhysicsConcept(
            name = "Hukum Kirchhoff Tegangan", category = "Listrik",
            formula = "Σ V_dalam_loop = 0",
            variables = mapOf("V" to "Tegangan (Volt)"),
            unit = "Volt (V)",
            realWorldExample = "Rangkaian seri: baterai 12V → resistor 3Ω & 6Ω. Tegangan jatuh: 4V + 8V = 12V. Jumlah tegangan dalam loop tertutup = nol.",
            bengkelExample = "Sistem kelistrikan motor: aki 12V → kunci kontak → coil → busi → ground. Total tegangan jatuh di semua komponen = 12V.",
            relatedConcepts = listOf("Hukum Ohm", "Rangkaian Seri", "Tegangan"),
            dangerIfViolated = "Ground putus → tegangan tidak kembali ke sumber → arus cari jalur alternatif → korslet atau sengatan listrik."
        ),
        PhysicsConcept(
            name = "Medan Magnet", category = "Listrik",
            formula = "B = F / (q × v)  atau  B = μ₀I / (2πr) untuk kawat lurus",
            variables = mapOf("B" to "Medan magnet (Tesla)", "F" to "Gaya magnet (N)", "q" to "Muatan (C)", "v" to "Kecepatan (m/s)", "I" to "Arus (A)", "r" to "Jarak (m)"),
            unit = "Tesla (T)",
            realWorldExample = "Speaker: arus listrik mengalir di kumparan dalam medan magnet permanen → gaya Lorentz menggerakkan membran → suara. Magnet neodymium kuat (1.4T) membuat speaker kecil bertenaga.",
            bengkelExample = "Motor listrik mesin bubut: arus mengalir di kumparan rotor dalam medan magnet stator → rotor berputar. Inilah prinsip semua motor listrik.",
            relatedConcepts = listOf("Gaya Lorentz", "Induksi", "Motor Listrik"),
            dangerIfViolated = "Magnet kuat dekat alat elektronik → hapus data harddisk. Magnet dekat jam tangan mekanik → rusak."
        ),
        PhysicsConcept(
            name = "Induksi Elektromagnetik", category = "Listrik",
            formula = "ε = -N × ΔΦ / Δt",
            variables = mapOf("ε" to "GGL induksi (V)", "N" to "Jumlah lilitan", "ΔΦ" to "Perubahan fluks magnet (Wb)", "Δt" to "Selang waktu (s)"),
            unit = "Volt (V)",
            realWorldExample = "Generator listrik: magnet diputar dekat kumparan → fluks berubah → listrik dihasilkan. Semakin cepat putaran, semakin besar tegangan.",
            bengkelExample = "Alternator mobil: diputar oleh mesin lewat belt. Menghasilkan listrik untuk mengisi aki dan menyalakan lampu. Putaran mesin naik → tegangan alternator naik.",
            relatedConcepts = listOf("Medan Magnet", "Fluks", "Generator", "Transformator"),
            dangerIfViolated = "Alternator rusak → aki tidak terisi → mobil mati mendadak di jalan → berbahaya."
        ),
        PhysicsConcept(
            name = "Transformator", category = "Listrik",
            formula = "Vp / Vs = Np / Ns = Is / Ip",
            variables = mapOf("Vp" to "Tegangan primer", "Vs" to "Tegangan sekunder", "Np" to "Lilitan primer", "Ns" to "Lilitan sekunder", "Ip" to "Arus primer", "Is" to "Arus sekunder"),
            unit = "Volt (V) & Ampere (A)",
            realWorldExample = "Charger HP: trafo step-down mengubah 220V AC jadi 5V DC. Lilitan primer 1000, sekunder 23 → 220/5 ≈ 44x penurunan tegangan.",
            bengkelExample = "Mesin las: trafo step-down mengubah 220V jadi 50-80V dengan arus sangat besar (40-200A). Lilitan sekunder sedikit tapi kawat sangat tebal.",
            relatedConcepts = listOf("Induksi", "Tegangan", "Arus"),
            dangerIfViolated = "Trafo rusak short circuit → arus primer melonjak → MCB trip atau trafo terbakar."
        ),

        // ============================================
        // 3. TERMODINAMIKA (8 konsep)
        // ============================================
        PhysicsConcept(
            name = "Kalor Jenis", category = "Termodinamika",
            formula = "Q = m × c × ΔT",
            variables = mapOf("Q" to "Kalor (J)", "m" to "Massa (kg)", "c" to "Kalor jenis (J/kg·°C)", "ΔT" to "Perubahan suhu (°C)"),
            unit = "Joule (J)",
            realWorldExample = "Air: c = 4200 J/kg·°C. Untuk mendidihkan 1kg air dari 25°C ke 100°C butuh Q = 1 × 4200 × 75 = 315.000 Joule. Bandingkan dengan besi: c = 450 → hanya butuh 33.750 Joule.",
            bengkelExample = "Coolant mesin: pakai air karena kalor jenisnya tinggi → menyerap banyak panas mesin tanpa naik suhu drastis. Menjaga mesin tetap dingin.",
            relatedConcepts = listOf("Kalor Laten", "Suhu", "Energi"),
            dangerIfViolated = "Coolant habis → air tidak ada → mesin overheat → piston macet → mesin jebol."
        ),
        PhysicsConcept(
            name = "Kalor Laten", category = "Termodinamika",
            formula = "Q = m × L",
            variables = mapOf("Q" to "Kalor laten (J)", "m" to "Massa (kg)", "L" to "Kalor laten (J/kg)"),
            unit = "Joule (J)",
            realWorldExample = "Es mencair: L_es = 334.000 J/kg. 1kg es 0°C → 1kg air 0°C butuh 334.000 Joule tanpa naik suhu. Energi besar untuk mengubah wujud.",
            bengkelExample = "Pengelasan: logam dipanaskan sampai meleleh (kalor laten fusi). Saat mendingin, logam melepaskan kalor laten dan membeku menyatu.",
            relatedConcepts = listOf("Kalor Jenis", "Perubahan Wujud", "Energi"),
            dangerIfViolated = "Logam las tidak cukup panas → tidak meleleh sempurna → sambungan las lemah → bisa patah."
        ),
        PhysicsConcept(
            name = "Pemuaian Panjang", category = "Termodinamika",
            formula = "ΔL = α × L₀ × ΔT",
            variables = mapOf("ΔL" to "Perubahan panjang (m)", "α" to "Koefisien muai panjang (/°C)", "L₀" to "Panjang awal (m)", "ΔT" to "Perubahan suhu (°C)"),
            unit = "meter (m)",
            realWorldExample = "Rel kereta: baja α = 12×10⁻⁶. Rel 25m dari 20°C ke 50°C (panas siang): ΔL = 12×10⁻⁶ × 25 × 30 = 9mm. Itu sebabnya ada celah antar rel.",
            bengkelExample = "Bearing (bantalan): dipanaskan sebelum dipasang ke poros. Bearing memuai → mudah dipasang. Setelah dingin → menyusut → mengikat kuat.",
            relatedConcepts = listOf("Pemuaian Volume", "Suhu", "Material"),
            dangerIfViolated = "Rel tanpa celah: memuai saat panas → melengkung → kereta anjlok. Bearing dipaksakan tanpa dipanaskan → poros tergores."
        ),
        PhysicsConcept(
            name = "Hukum Termodinamika 1", category = "Termodinamika",
            formula = "ΔU = Q - W",
            variables = mapOf("ΔU" to "Perubahan energi dalam (J)", "Q" to "Kalor masuk (J)", "W" to "Usaha keluar (J)"),
            unit = "Joule (J)",
            realWorldExample = "Mesin mobil: bensin dibakar (Q masuk) → energi dalam gas naik → gas mendorong piston (W keluar). ΔU = Q - W. Sisa energi jadi panas buang.",
            bengkelExample = "Kompresor: motor listrik beri usaha (W negatif = masuk) → udara dimampatkan → suhu naik (ΔU naik). Butuh pendingin untuk menjaga suhu.",
            relatedConcepts = listOf("Efisiensi", "Kalor", "Usaha"),
            dangerIfViolated = "Sistem pendingin kompresor gagal → ΔU terus naik → overheating → kompresor jebol atau meledak."
        ),
        PhysicsConcept(
            name = "Hukum Termodinamika 2", category = "Termodinamika",
            formula = "Entropi (ΔS) ≥ 0 untuk sistem terisolasi",
            variables = mapOf("ΔS" to "Perubahan entropi (J/K)", "Q" to "Kalor (J)", "T" to "Suhu (K)"),
            unit = "J/K",
            realWorldExample = "Tidak mungkin membuat mesin 100% efisien. Selalu ada panas terbuang. Mesin mobil: hanya 25-30% energi bensin jadi gerak, sisanya panas buang.",
            bengkelExample = "Motor listrik: lebih efisien (85-95%) daripada mesin bensin, tapi tetap ada panas dari gesekan dan hambatan listrik yang tak terhindarkan.",
            relatedConcepts = listOf("Efisiensi", "Entropi", "Hukum 1"),
            dangerIfViolated = "Mencoba membuat mesin 'abadi' (perpetual motion) → melanggar hukum fisika → tidak mungkin. Waspadai penipuan teknologi."
        ),
        PhysicsConcept(
            name = "Efisiensi Carnot", category = "Termodinamika",
            formula = "η_carnot = 1 - (Tc / Th)",
            variables = mapOf("η" to "Efisiensi", "Tc" to "Suhu dingin (K)", "Th" to "Suhu panas (K)"),
            unit = "tanpa satuan (atau %)",
            realWorldExample = "Mesin ideal antara 500°C (773K) dan 100°C (373K): η = 1 - 373/773 = 51.7%. Ini batas maksimum teoritis! Mesin nyata selalu lebih rendah.",
            bengkelExample = "Mesin diesel: suhu pembakaran ~2000°C (2273K), suhu buang ~500°C (773K). η_carnot = 1 - 773/2273 = 66%. Nyata: 35-40%.",
            relatedConcepts = listOf("Hukum 1", "Hukum 2", "Suhu"),
            dangerIfViolated = "Klaim mesin dengan efisiensi > Carnot → pasti scam. Tidak mungkin melampaui batas fisika."
        ),
        PhysicsConcept(
            name = "Konduksi", category = "Termodinamika",
            formula = "H = k × A × ΔT / L",
            variables = mapOf("H" to "Laju perpindahan kalor (W)", "k" to "Konduktivitas termal (W/m·K)", "A" to "Luas penampang (m²)", "ΔT" to "Beda suhu (K)", "L" to "Panjang (m)"),
            unit = "Watt (W)",
            realWorldExample = "Panci aluminium (k=237) lebih cepat menghantarkan panas daripada panci stainless (k=16). Makanya aluminium dipakai untuk heatsink.",
            bengkelExample = "Heatsink CPU/GPU: aluminium (k=237) dengan sirip banyak (A besar) → memaksimalkan pelepasan panas. Pasta termal mengisi celah udara (k rendah) antara chip dan heatsink.",
            relatedConcepts = listOf("Konveksi", "Radiasi", "Kalor"),
            dangerIfViolated = "Pasta termal kering → celah udara (k=0.026) → chip overheat → mati mendadak atau rusak permanen."
        ),
        PhysicsConcept(
            name = "Konveksi & Radiasi", category = "Termodinamika",
            formula = "Konveksi: H = h × A × (Ts - Tf), Radiasi: P = ε × σ × A × T⁴",
            variables = mapOf("h" to "Koefisien konveksi", "ε" to "Emisivitas", "σ" to "Konstanta Stefan-Boltzmann (5.67×10⁻⁸)", "T" to "Suhu (K)"),
            unit = "Watt (W)",
            realWorldExample = "Radiator mobil: konveksi paksa (kipas) + radiasi. Air panas dari mesin mengalir ke radiator, panas dipancarkan ke udara. Kipas mempercepat konveksi.",
            bengkelExample = "Mesin bubut: coolant disemprotkan ke mata pisau dan benda kerja → konveksi paksa membawa panas menjauh. Tanpa coolant, panas radiasi dari gesekan bisa membakar kulit.",
            relatedConcepts = listOf("Konduksi", "Kalor", "Pendinginan"),
            dangerIfViolated = "Kipas radiator mati → tidak ada konveksi paksa → mesin overheat saat macet → mesin jebol."
        ),

        // ============================================
        // 4. FLUIDA (7 konsep)
        // ============================================
        PhysicsConcept(
            name = "Tekanan", category = "Fluida",
            formula = "P = F / A",
            variables = mapOf("P" to "Tekanan (Pa)", "F" to "Gaya (N)", "A" to "Luas penampang (m²)"),
            unit = "Pascal (Pa) = N/m²",
            realWorldExample = "Sepatu hak tinggi: A = 1cm² = 10⁻⁴ m². Berat 500N → P = 5.000.000 Pa! Bisa merusak lantai kayu. Sepatu biasa A=100cm² → P=50.000 Pa.",
            bengkelExample = "Dongkrak hidrolik: piston kecil A=2cm² ditekan 100N → P=500.000 Pa. Piston besar A=200cm² → F = P×A = 10.000N! Bisa angkat 1 ton.",
            relatedConcepts = listOf("Hukum Pascal", "Gaya", "Luas"),
            dangerIfViolated = "Dongkrak ditaruh di tanah lunak (A efektif kecil) → tekanan tinggi → dongkrak ambles → mobil jatuh."
        ),
        PhysicsConcept(
            name = "Tekanan Hidrostatis", category = "Fluida",
            formula = "P = ρ × g × h",
            variables = mapOf("P" to "Tekanan hidrostatis (Pa)", "ρ" to "Massa jenis fluida (kg/m³)", "g" to "Gravitasi (9.8 m/s²)", "h" to "Kedalaman (m)"),
            unit = "Pascal (Pa)",
            realWorldExample = "Bendungan: di kedalaman 50m, tekanan air = 1000 × 9.8 × 50 = 490.000 Pa. Dinding bendungan bagian bawah harus lebih tebal daripada bagian atas.",
            bengkelExample = "Tangki coolant mesin: semakin penuh, tekanan di dasar semakin besar. Pompa coolant harus cukup kuat melawan tekanan hidrostatis untuk mensirkulasikan coolant.",
            relatedConcepts = listOf("Tekanan", "Massa Jenis", "Hukum Pascal"),
            dangerIfViolated = "Tangki terlalu penuh + tutup lemah → tekanan hidrostatis tinggi → tangki bocor atau jebol."
        ),
        PhysicsConcept(
            name = "Hukum Pascal", category = "Fluida",
            formula = "P₁ = P₂  →  F₁/A₁ = F₂/A₂",
            variables = mapOf("P" to "Tekanan", "F" to "Gaya", "A" to "Luas penampang"),
            unit = "Pascal (Pa)",
            realWorldExample = "Rem hidrolik mobil: pedal (A kecil) ditekan kaki → tekanan diteruskan ke kaliper (A besar) → gaya besar menjepit cakram. Prinsip Pascal.",
            bengkelExample = "Dongkrak botol: pompa kecil (A₁) digerakkan → tekanan tinggi → piston besar (A₂) naik. Gaya angkat = F₁ × (A₂/A₁). Rasio 100x!",
            relatedConcepts = listOf("Tekanan", "Tekanan Hidrostatis", "Rem"),
            dangerIfViolated = "Gelembung udara dalam sistem rem → udara kompresibel → tekanan tidak diteruskan sempurna → rem blong."
        ),
        PhysicsConcept(
            name = "Hukum Archimedes", category = "Fluida",
            formula = "Fa = ρ_fluida × g × V_tercelup",
            variables = mapOf("Fa" to "Gaya apung (N)", "ρ" to "Massa jenis fluida (kg/m³)", "g" to "Gravitasi", "V" to "Volume tercelup (m³)"),
            unit = "Newton (N)",
            realWorldExample = "Kapal baja: massa jenis baja 7800 kg/m³ > air 1000 kg/m³. Tapi bentuk kapal bikin volume tercelup besar → Fa > berat → mengapung.",
            bengkelExample = "Menguji kebocoran ban: ban dicelup ke air. Gelembung naik karena udara (ρ kecil) mendapat gaya apung besar dari air. Prinsip Archimedes.",
            relatedConcepts = listOf("Massa Jenis", "Tekanan Hidrostatis", "Gaya"),
            dangerIfViolated = "Kapal overload → V_tercelup > batas → Fa tidak cukup → kapal tenggelam."
        ),
        PhysicsConcept(
            name = "Debit Fluida", category = "Fluida",
            formula = "Q = A × v",
            variables = mapOf("Q" to "Debit (m³/s)", "A" to "Luas penampang (m²)", "v" to "Kecepatan aliran (m/s)"),
            unit = "m³/detik (atau liter/menit)",
            realWorldExample = "Selang air: A = 5cm², v = 2m/s → Q = 0.0005 × 2 = 0.001 m³/s = 1 liter/detik. Ember 10L penuh dalam 10 detik.",
            bengkelExample = "Coolant pump mesin bubut: debit harus cukup untuk mendinginkan mata pisau. Debit terlalu kecil → panas tidak terbawa → pisau tumpul.",
            relatedConcepts = listOf("Hukum Bernoulli", "Kecepatan", "Luas"),
            dangerIfViolated = "Pompa coolant rusak → debit nol → pisau overheat → asap & api dari gesekan → kebakaran."
        ),
        PhysicsConcept(
            name = "Hukum Bernoulli", category = "Fluida",
            formula = "P + ½ρv² + ρgh = konstan",
            variables = mapOf("P" to "Tekanan (Pa)", "ρ" to "Massa jenis (kg/m³)", "v" to "Kecepatan (m/s)", "g" to "Gravitasi", "h" to "Ketinggian (m)"),
            unit = "Pascal (Pa)",
            realWorldExample = "Pesawat terbang: sayap atas melengkung → udara lewat lebih cepat → tekanan lebih rendah (Bernoulli) → gaya angkat ke atas.",
            bengkelExample = "Spray gun cat: udara ditekan lewat pipa kecil (v tinggi) → tekanan rendah → cat tersedot dari wadah → tersemprot halus. Prinsip Bernoulli.",
            relatedConcepts = listOf("Tekanan", "Kecepatan", "Debit"),
            dangerIfViolated = "Sayap pesawat rusak → aliran udara terganggu → gaya angkat hilang → pesawat jatuh."
        ),
        PhysicsConcept(
            name = "Viskositas", category = "Fluida",
            formula = "F = η × A × (dv/dy)",
            variables = mapOf("F" to "Gaya gesek fluida (N)", "η" to "Viskositas (Pa·s)", "A" to "Luas (m²)", "dv/dy" to "Gradien kecepatan"),
            unit = "Pascal-detik (Pa·s)",
            realWorldExample = "Madu vs air: viskositas madu tinggi → mengalir lambat. Oli SAE 10W-40: 10W (winter/dingin) lebih encer, 40 (panas) lebih kental.",
            bengkelExample = "Oli mesin: viskositas harus pas. Terlalu encer → tidak bisa melumasi. Terlalu kental → gesekan besar → mesin berat berputar. SAE disesuaikan mesin.",
            relatedConcepts = listOf("Gesekan", "Fluida", "Suhu"),
            dangerIfViolated = "Oli terlalu encer di mesin panas → lapisan oli pecah → logam bergesekan langsung → mesin macet (seized)."
        ),

        // ============================================
        // 5. GELOMBANG & BUNYI (6 konsep)
        // ============================================
        PhysicsConcept(
            name = "Cepat Rambat Gelombang", category = "Gelombang",
            formula = "v = f × λ",
            variables = mapOf("v" to "Cepat rambat (m/s)", "f" to "Frekuensi (Hz)", "λ" to "Panjang gelombang (m)"),
            unit = "m/s",
            realWorldExample = "Suara di udara: v = 340 m/s. Nada A4 (440 Hz) → λ = 340/440 = 0.77m. Di dalam air: v = 1500 m/s, λ = 3.4m untuk frekuensi sama.",
            bengkelExample = "Tes kebocoran pipa dengan ultrasonik: suara frekuensi tinggi (40 kHz) λ pendek → mudah mendeteksi celah kecil. v di logam ~5000 m/s.",
            relatedConcepts = listOf("Frekuensi", "Panjang Gelombang", "Efek Doppler"),
            dangerIfViolated = "Tidak memahami cepat rambat → salah prediksi jarak sumber suara → salah antisipasi bahaya."
        ),
        PhysicsConcept(
            name = "Frekuensi & Periode", category = "Gelombang",
            formula = "f = 1/T",
            variables = mapOf("f" to "Frekuensi (Hz)", "T" to "Periode (detik)"),
            unit = "Hertz (Hz)",
            realWorldExample = "Listrik PLN: f = 50 Hz → T = 0.02 detik. Lampu LED kedip 50x per detik, tapi mata manusia tidak bisa melihat kedipan > 30 Hz.",
            bengkelExample = "Mesin bubut 1000 RPM: frekuensi putaran = 1000/60 = 16.7 Hz. Getaran mesin di frekuensi ini bisa terasa. Jika ada bunyi aneh di frekuensi berbeda → bearing rusak.",
            relatedConcepts = listOf("Periode", "Cepat Rambat", "Resonansi"),
            dangerIfViolated = "Getaran di frekuensi resonansi mesin → amplifikasi getaran → baut longgar → komponen lepas."
        ),
        PhysicsConcept(
            name = "Efek Doppler", category = "Gelombang",
            formula = "f' = f × (v ± v_o) / (v ∓ v_s)",
            variables = mapOf("f'" to "Frekuensi terdengar", "f" to "Frekuensi sumber", "v" to "Cepat rambat bunyi", "v_o" to "Kecepatan pengamat", "v_s" to "Kecepatan sumber"),
            unit = "Hertz (Hz)",
            realWorldExample = "Ambulans mendekat: f' = 440 × 340/(340-30) = 483 Hz (nada lebih tinggi). Menjauh: f' = 440 × 340/(340+30) = 404 Hz (nada lebih rendah).",
            bengkelExample = "Mobil lewat bengkel: suara mesin meraung mendekat (frekuensi naik), setelah lewat suara turun (frekuensi turun). Bisa estimasi kecepatan mobil dari perubahan nada.",
            relatedConcepts = listOf("Frekuensi", "Kecepatan", "Bunyi"),
            dangerIfViolated = "Tidak mendengar perubahan nada ambulans → tidak sadar ambulans mendekat → terlambat minggir → menghalangi emergency."
        ),
        PhysicsConcept(
            name = "Intensitas Bunyi", category = "Gelombang",
            formula = "I = P / (4π × r²)",
            variables = mapOf("I" to "Intensitas (W/m²)", "P" to "Daya sumber bunyi (W)", "r" to "Jarak dari sumber (m)"),
            unit = "W/m²",
            realWorldExample = "Speaker 100W: di jarak 1m → I = 100/(4π×1) = 7.96 W/m². Di jarak 10m → I = 0.0796 W/m². Intensitas turun 100x untuk jarak 10x!",
            bengkelExample = "Kompresor bising 80 dB di jarak 1m. Mekanik berdiri 10m → intensitas turun 100x → level bunyi turun 20 dB jadi 60 dB (masih aman). Tapi 8 jam/hari tetap perlu earplug.",
            relatedConcepts = listOf("Daya", "Jarak", "Desibel"),
            dangerIfViolated = "Bekerja dekat sumber bising tanpa earplug > 8 jam → intensitas tinggi → kerusakan pendengaran permanen (NIHL)."
        ),
        PhysicsConcept(
            name = "Resonansi", category = "Gelombang",
            formula = "f_drive = f_natural  →  amplitudo maksimum",
            variables = mapOf("f_drive" to "Frekuensi penggerak", "f_natural" to "Frekuensi natural benda"),
            unit = "Hertz (Hz)",
            realWorldExample = "Jembatan Tacoma Narrows roboh 1940: angin berhembus di frekuensi natural jembatan → resonansi → jembatan bergoyang hebat → roboh.",
            bengkelExample = "Mesin bubut: jika frekuensi putaran mesin = frekuensi natural meja → resonansi → getaran hebat → hasil bubutan tidak presisi. Solusi: ubah RPM atau tambah peredam.",
            relatedConcepts = listOf("Frekuensi", "Getaran", "Amplitudo"),
            dangerIfViolated = "Mengabaikan getaran resonansi → mesin bergoyang liar → benda kerja terlepas → kecelakaan."
        ),
        PhysicsConcept(
            name = "Ultrasonik", category = "Gelombang",
            formula = "f > 20.000 Hz (di luar pendengaran manusia)",
            variables = mapOf("f" to "Frekuensi (Hz)", "λ" to "Panjang gelombang pendek → resolusi tinggi"),
            unit = "Hertz (Hz)",
            realWorldExample = "USG medis: f = 2-18 MHz. Bisa melihat bayi dalam kandungan. Sensor parkir mobil: f = 40 kHz. Memantul ke objek, dihitung waktunya → jarak.",
            bengkelExample = "Ultrasonic cleaner: f = 20-40 kHz. Getaran menciptakan gelembung mikro yang meledak (kavitasi) → membersihkan kotoran di celah kecil sparepart.",
            relatedConcepts = listOf("Frekuensi", "Cepat Rambat", "Kavitasi"),
            dangerIfViolated = "Ultrasonic cleaner tanpa cairan → transduser overheat → rusak. Atau terlalu lama → kavitasi merusak permukaan sparepart."
        ),

        // ============================================
        // 6. OPTIK & CAHAYA (5 konsep)
        // ============================================
        PhysicsConcept(
            name = "Pemantulan", category = "Optik",
            formula = "θ_i = θ_r",
            variables = mapOf("θ_i" to "Sudut datang", "θ_r" to "Sudut pantul"),
            unit = "derajat (°)",
            realWorldExample = "Cermin datar: bayangan sama besar, terbalik kiri-kanan. Cermin cekung: bisa memperbesar (makeup) atau mengumpulkan cahaya (senter).",
            bengkelExample = "Reflektor lampu depan mobil: cermin cekung mengumpulkan cahaya bohlam ke depan. Tanpa reflektor, cahaya menyebar ke segala arah.",
            relatedConcepts = listOf("Pembiasan", "Lensa", "Cahaya"),
            dangerIfViolated = "Reflektor retak atau kotor → cahaya tidak fokus → jarak pandang pendek → bahaya saat malam."
        ),
        PhysicsConcept(
            name = "Pembiasan (Hukum Snell)", category = "Optik",
            formula = "n₁ × sin θ₁ = n₂ × sin θ₂",
            variables = mapOf("n" to "Indeks bias", "θ" to "Sudut terhadap normal"),
            unit = "tanpa satuan (indeks bias), derajat (sudut)",
            realWorldExample = "Pensil di gelas air terlihat patah: n_air = 1.33, n_udara = 1. Cahaya dari pensil dibelokkan saat keluar air → otak mengira pensil di posisi yang salah.",
            bengkelExample = "Kacamata safety: pakai polikarbonat (n = 1.58) yang lebih tipis tapi kuat. Melindungi mata dari percikan tanpa distorsi penglihatan berarti.",
            relatedConcepts = listOf("Pemantulan", "Lensa", "Dispersi"),
            dangerIfViolated = "Kacamata murahan dari plastik biasa → indeks bias tidak seragam → distorsi → salah melihat → kecelakaan kerja."
        ),
        PhysicsConcept(
            name = "Lensa Tipis", category = "Optik",
            formula = "1/f = 1/s + 1/s'",
            variables = mapOf("f" to "Jarak fokus (m)", "s" to "Jarak benda (m)", "s'" to "Jarak bayangan (m)"),
            unit = "dioptri (D) = 1/f dalam meter",
            realWorldExample = "Kacamata minus: lensa cekung menyebarkan cahaya. Orang rabun jauh (miopi) punya bayangan jatuh di depan retina. Lensa cekung memindahkan bayangan tepat di retina.",
            bengkelExample = "Kaca pembesar mekanik: lensa cembung (f kecil) memperbesar bayangan. Membantu melihat retakan halus pada sparepart atau membaca kode kecil.",
            relatedConcepts = listOf("Pemantulan", "Pembiasan", "Mikroskop"),
            dangerIfViolated = "Kacamata tidak sesuai ukuran → mata lelah → sakit kepala → salah membaca ukuran → kesalahan produksi."
        ),
        PhysicsConcept(
            name = "Dispersi Cahaya", category = "Optik",
            formula = "n(λ) berbeda untuk setiap panjang gelombang (warna)",
            variables = mapOf("λ" to "Panjang gelombang", "n" to "Indeks bias bervariasi terhadap λ"),
            unit = "tanpa satuan",
            realWorldExample = "Pelangi: air hujan membiaskan dan mendispersikan cahaya matahari. Merah (λ=700nm) dibelokkan paling sedikit, ungu (λ=400nm) paling banyak.",
            bengkelExample = "Prisma di alat ukur optik: mendispersikan cahaya untuk analisis spektrum. Bisa mendeteksi komposisi logam dari spektrum cahayanya (spektroskopi).",
            relatedConcepts = listOf("Pembiasan", "Panjang Gelombang", "Spektrum"),
            dangerIfViolated = "Kacamata non-akromatik → dispersi → tepi objek terlihat pelangi → ganggu penglihatan presisi."
        ),
        PhysicsConcept(
            name = "Difraksi", category = "Optik",
            formula = "d × sin θ = n × λ",
            variables = mapOf("d" to "Lebar celah (m)", "θ" to "Sudut difraksi", "n" to "Orde (1,2,3...)", "λ" to "Panjang gelombang (m)"),
            unit = "meter & derajat",
            realWorldExample = "CD/DVD terlihat pelangi: celah sangat rapat (d ~ 1.6µm) mendifraksikan cahaya putih → tiap warna dibelokkan ke sudut berbeda → pelangi.",
            bengkelExample = "Inspeksi retakan: menyinari benda dengan laser (λ tunggal). Pola difraksi berubah jika ada retakan mikro. Teknik NDT (Non-Destructive Testing).",
            relatedConcepts = listOf("Interferensi", "Panjang Gelombang", "Laser"),
            dangerIfViolated = "Retakan mikro tidak terdeteksi → komponen patah saat digunakan → kecelakaan fatal."
        ),

        // ============================================
        // 7. FISIKA MODERN (5 konsep)
        // ============================================
        PhysicsConcept(
            name = "Relativitas Khusus (E=mc²)", category = "Fisika Modern",
            formula = "E = m × c²",
            variables = mapOf("E" to "Energi (J)", "m" to "Massa (kg)", "c" to "Kecepatan cahaya (3×10⁸ m/s)"),
            unit = "Joule (J)",
            realWorldExample = "1 gram materi jika diubah 100% jadi energi: E = 0.001 × (3×10⁸)² = 9×10¹³ Joule = 25 juta kWh! Cukup untuk listrik 2500 rumah sebulan.",
            bengkelExample = "Meskipun tidak terpakai langsung di bengkel, prinsip ini ada di teknologi modern: GPS harus koreksi relativitas (satelit bergerak cepat, gravitasi lebih rendah) agar akurat.",
            relatedConcepts = listOf("Energi", "Massa", "Kecepatan Cahaya"),
            dangerIfViolated = "Mengabaikan koreksi relativitas pada GPS → error 10km/hari → navigasi salah → kecelakaan."
        ),
        PhysicsConcept(
            name = "Efek Fotolistrik", category = "Fisika Modern",
            formula = "E_k = h × f - W",
            variables = mapOf("E_k" to "Energi kinetik elektron (J)", "h" to "Konstanta Planck (6.63×10⁻³⁴ J·s)", "f" to "Frekuensi cahaya (Hz)", "W" to "Fungsi kerja logam (J)"),
            unit = "Joule (J) atau eV",
            realWorldExample = "Panel surya: cahaya matahari (f tinggi) mengenai silikon → elektron terlepas → listrik dihasilkan. Cahaya merah tidak cukup energi untuk melepas elektron.",
            bengkelExample = "Sensor cahaya pada mesin: fotodioda mendeteksi ada/tidaknya cahaya. Dipakai di sensor posisi, encoder, atau safety curtain mesin.",
            relatedConcepts = listOf("Foton", "Energi", "Elektron"),
            dangerIfViolated = "Safety curtain kotor → cahaya terhalang → sensor gagal → mesin tetap menyala saat ada tangan → kecelakaan."
        ),
        PhysicsConcept(
            name = "Radioaktivitas", category = "Fisika Modern",
            formula = "N = N₀ × e^(-λt)",
            variables = mapOf("N" to "Jumlah inti tersisa", "N₀" to "Jumlah inti awal", "λ" to "Konstanta peluruhan", "t" to "Waktu (s)"),
            unit = "tanpa satuan (jumlah inti)",
            realWorldExample = "Carbon-14 dating: λ_C14 = 1.21×10⁻⁴/tahun. Fosil 5730 tahun → N = N₀ × 0.5 (setengahnya). Dipakai arkeolog untuk menentukan umur fosil.",
            bengkelExample = "Radiografi industri (NDT): sinar gamma menembus logam. Retakan/internal defect terlihat di film radiografi. Mirip rontgen medis tapi untuk pipa dan las.",
            relatedConcepts = listOf("Peluruhan", "Waktu Paruh", "Radiasi"),
            dangerIfViolated = "Radiasi tanpa pelindung → kerusakan sel → kanker. Harus pakai dosimeter dan pelindung timbal."
        ),
        PhysicsConcept(
            name = "Dualitas Gelombang-Partikel", category = "Fisika Modern",
            formula = "λ = h / p  (panjang gelombang de Broglie)",
            variables = mapOf("λ" to "Panjang gelombang (m)", "h" to "Konstanta Planck", "p" to "Momentum (kg·m/s)"),
            unit = "meter (m)",
            realWorldExample = "Mikroskop elektron: elektron dipercepat (p besar) → λ sangat kecil → bisa 'melihat' objek seukuran atom. Jauh lebih detail dari mikroskop cahaya.",
            bengkelExample = "Scanning Electron Microscope (SEM): dipakai analisis patahan logam. Bisa lihat struktur mikro yang menjelaskan kenapa komponen gagal.",
            relatedConcepts = listOf("Momentum", "Gelombang", "Elektron"),
            dangerIfViolated = "Tidak menganalisis patahan dengan benar → akar masalah tidak ditemukan → komponen pengganti juga akan gagal."
        ),
        PhysicsConcept(
            name = "Prinsip Ketidakpastian Heisenberg", category = "Fisika Modern",
            formula = "Δx × Δp ≥ ℏ / 2",
            variables = mapOf("Δx" to "Ketidakpastian posisi", "Δp" to "Ketidakpastian momentum", "ℏ" to "h/2π = 1.05×10⁻³⁴ J·s"),
            unit = "meter & kg·m/s",
            realWorldExample = "Tidak mungkin tahu posisi DAN kecepatan elektron secara bersamaan dengan presisi 100%. Makin akurat kita ukur posisi, makin tidak akurat kecepatannya.",
            bengkelExample = "Keterbatasan pengukuran: saat mengukur dengan mikrometer, sentuhan alat ukur sedikit mengubah posisi benda. Untuk benda makroskopik, efeknya diabaikan.",
            relatedConcepts = listOf("Kuantum", "Pengukuran", "Elektron"),
            dangerIfViolated = "Terlalu percaya pada satu pengukuran → selalu ukur minimal 3x dan ambil rata-rata. Kalau tidak → toleransi meleset."
        )
    )
    
    fun getConcept(name: String): PhysicsConcept? =
        allConcepts.find { it.name.lowercase() == name.lowercase() }
    
    fun getConceptsByCategory(category: String): List<PhysicsConcept> =
        allConcepts.filter { it.category == category }
    
    fun getDangerousViolations(): List<Pair<String, String>> =
        allConcepts.map { it.name to it.dangerIfViolated }
    
    fun getConceptsRelatedTo(objectName: String): List<PhysicsConcept> =
        allConcepts.filter { 
            it.bengkelExample.lowercase().contains(objectName.lowercase()) ||
            it.realWorldExample.lowercase().contains(objectName.lowercase())
        }
}