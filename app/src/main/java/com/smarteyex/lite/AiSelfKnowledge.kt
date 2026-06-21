package com.smarteyex.lite

data class AIConcept(
    val name: String,
    val category: String,
    val description: String,
    val technicalDetail: String,
    val smartEyeXUsage: String,
    val relatedConcepts: List<String>,
    val difficulty: String
)

class AISelfKnowledge {
    
    val allConcepts: List<AIConcept> = listOf(
        // ============================================
        // 1. DASAR AI (5 konsep)
        // ============================================
        AIConcept(
            name = "Artificial Intelligence (AI)",
            category = "Dasar AI",
            description = "AI adalah cabang ilmu komputer yang membuat mesin bisa melakukan tugas yang biasanya membutuhkan kecerdasan manusia: melihat, mendengar, memahami bahasa, mengambil keputusan, dan belajar dari pengalaman.",
            technicalDetail = "AI modern menggunakan Machine Learning, terutama Deep Learning dengan neural network berlapis-lapis. Model dilatih dengan data besar, lalu bisa melakukan inferensi (prediksi) pada data baru.",
            smartEyeXUsage = "XNAI adalah AI companion yang menggabungkan Computer Vision (melihat via kamera), NLP (memahami & menghasilkan bahasa), dan Knowledge Base (menyimpan & menghubungkan fakta). Semua ini adalah cabang AI.",
            relatedConcepts = listOf("Machine Learning", "Deep Learning", "Computer Vision", "NLP"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "Machine Learning (ML)",
            category = "Dasar AI",
            description = "Machine Learning adalah pendekatan AI di mana komputer belajar dari data tanpa diprogram secara eksplisit. Algoritma ML menemukan pola dalam data dan membuat prediksi.",
            technicalDetail = "ML terbagi menjadi 3 jenis utama: Supervised Learning (data berlabel), Unsupervised Learning (data tanpa label), dan Reinforcement Learning (belajar dari reward/punishment).",
            smartEyeXUsage = "Object detection di SmartEyeX menggunakan ML (YOLO/MediaPipe) yang sudah dilatih mengenali ribuan objek. Mood detection belajar dari trigger words.",
            relatedConcepts = listOf("Supervised Learning", "Neural Network", "Training Data"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "Deep Learning",
            category = "Dasar AI",
            description = "Deep Learning adalah subset ML yang menggunakan neural network dengan banyak layer (dalam). Mampu mempelajari fitur kompleks dari data mentah tanpa ekstraksi fitur manual.",
            technicalDetail = "Neural network terdiri dari input layer → hidden layers → output layer. Setiap layer terdiri dari neuron yang terhubung dengan bobot. Backpropagation mengupdate bobot untuk meminimalkan error.",
            smartEyeXUsage = "GPT-4o yang digunakan XNAI adalah model Deep Learning dengan arsitektur Transformer. Vision model juga menggunakan CNN dalam.",
            relatedConcepts = listOf("Neural Network", "CNN", "Transformer", "Backpropagation"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Supervised Learning",
            category = "Dasar AI",
            description = "Model belajar dari data yang sudah diberi label. Contoh: gambar kucing dilabeli 'kucing', gambar anjing dilabeli 'anjing'. Model belajar membedakan keduanya.",
            technicalDetail = "Input: features (X), Output: labels (Y). Model mempelajari fungsi f(X) → Y. Loss function mengukur error. Optimizer (SGD, Adam) mengupdate bobot untuk mengurangi loss.",
            smartEyeXUsage = "Object detection dilatih dengan supervised learning: ribuan gambar diberi bounding box & label. Model belajar memprediksi lokasi & kelas objek.",
            relatedConcepts = listOf("Training Data", "Loss Function", "Labeling"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "Unsupervised Learning",
            category = "Dasar AI",
            description = "Model belajar dari data TANPA label. Mencari pola tersembunyi: clustering (mengelompokkan data mirip), dimensionality reduction (menyederhanakan data kompleks).",
            technicalDetail = "Algoritma: K-Means, DBSCAN (clustering), PCA, t-SNE (dimensionality reduction). Autoencoder juga bisa digunakan untuk unsupervised feature learning.",
            smartEyeXUsage = "ContextAnalyzer bisa menggunakan clustering untuk mengelompokkan situasi serupa. Memory System menggunakan similarity search untuk recall memori terkait.",
            relatedConcepts = listOf("Clustering", "PCA", "Autoencoder"),
            difficulty = "Intermediate"
        ),

        // ============================================
        // 2. JENIS MODEL AI (8 konsep)
        // ============================================
        AIConcept(
            name = "Convolutional Neural Network (CNN)",
            category = "Model AI",
            description = "CNN adalah neural network khusus untuk data grid/gambar. Menggunakan operasi konvolusi untuk mendeteksi fitur visual seperti tepi, sudut, tekstur, dan bentuk.",
            technicalDetail = "Arsitektur: Convolution Layer → Activation (ReLU) → Pooling → Fully Connected. Filter/kernel belajar mendeteksi fitur spesifik. Pooling mengurangi dimensi. CNN modern: ResNet, EfficientNet, MobileNet.",
            smartEyeXUsage = "Object detection di SmartEyeX menggunakan CNN backbone. Model seperti MobileNet dioptimalkan untuk HP Android (ringan, cepat).",
            relatedConcepts = listOf("YOLO", "ResNet", "MobileNet", "Convolution"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Recurrent Neural Network (RNN)",
            category = "Model AI",
            description = "RNN adalah neural network untuk data sekuensial (teks, suara, time series). Punya 'memori' internal yang menyimpan informasi dari input sebelumnya.",
            technicalDetail = "RNN dasar punya masalah vanishing gradient untuk sekuens panjang. Solusi: LSTM (Long Short-Term Memory) dengan gerbang forget/input/output. GRU adalah versi lebih sederhana.",
            smartEyeXUsage = "Meskipun Transformer sekarang dominan, prinsip RNN penting untuk memahami bagaimana AI memproses percakapan berurutan dan konteks waktu.",
            relatedConcepts = listOf("LSTM", "Transformer", "Sequential Data"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Long Short-Term Memory (LSTM)",
            category = "Model AI",
            description = "LSTM adalah jenis RNN yang bisa 'mengingat' informasi dalam jangka panjang. Punya gerbang (gate) yang mengatur kapan menyimpan, kapan melupakan, dan kapan mengeluarkan informasi.",
            technicalDetail = "3 gerbang: Forget Gate (informasi apa yang dibuang), Input Gate (informasi baru apa yang disimpan), Output Gate (apa yang dikeluarkan). Cell state membawa informasi sepanjang sekuens.",
            smartEyeXUsage = "Memory System XNAI terinspirasi dari LSTM: Short-term memory → Long-term memory (konsolidasi), emotional memory lebih tahan lama (mirip forget gate yang tidak membuang memori penting).",
            relatedConcepts = listOf("RNN", "GRU", "Memory System"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Transformer",
            category = "Model AI",
            description = "Transformer adalah arsitektur revolusioner (2017) yang menggunakan mekanisme 'Attention' untuk memproses semua input secara paralel. Ini dasar dari GPT, BERT, dan semua LLM modern.",
            technicalDetail = "Komponen: Multi-Head Self-Attention (setiap token 'memperhatikan' semua token lain), Positional Encoding (informasi posisi), Feed-Forward Network. Bisa diproses paralel → jauh lebih cepat dari RNN.",
            smartEyeXUsage = "XNAI menggunakan GPT-4o (OpenAI) yang berbasis Transformer. Setiap kali Bung ngomong, Transformer memproses seluruh konteks percakapan sekaligus untuk menghasilkan respons.",
            relatedConcepts = listOf("Attention", "GPT", "BERT", "LLM"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "You Only Look Once (YOLO)",
            category = "Model AI",
            description = "YOLO adalah algoritma object detection realtime yang membagi gambar menjadi grid dan memprediksi bounding box + kelas objek dalam SATU KALI forward pass. Sangat cepat (30-60 FPS).",
            technicalDetail = "Gambar dibagi grid S×S. Setiap sel grid memprediksi B bounding box (x,y,w,h,confidence) dan C kelas. Output: S×S×(B×5+C) tensor. Non-Max Suppression menghilangkan duplikat. Versi terbaru: YOLOv8.",
            smartEyeXUsage = "Ini yang AKAN dipakai ObjectDetector.kt setelah upgrade dari OpenCV blob detection. YOLO TFLite bisa jalan di HP Android untuk deteksi objek realtime.",
            relatedConcepts = listOf("Object Detection", "CNN", "TFLite", "Real-time"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Generative Adversarial Network (GAN)",
            category = "Model AI",
            description = "GAN terdiri dari 2 network yang bersaing: Generator (membuat data palsu) dan Discriminator (membedakan asli vs palsu). Keduanya saling belajar → Generator makin jago membuat data realistis.",
            technicalDetail = "Generator: random noise → data sintetis. Discriminator: klasifikasi asli/palsu. Loss: Generator berusaha menipu Discriminator, Discriminator berusaha tidak tertipu. Training stabil: sulit (mode collapse).",
            smartEyeXUsage = "Tidak langsung dipakai, tapi konsep 'adversarial' relevan: XNAI harus bisa membedakan situasi normal vs berbahaya (mirip Discriminator).",
            relatedConcepts = listOf("Generator", "Discriminator", "Synthetic Data"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "ResNet (Residual Network)",
            category = "Model AI",
            description = "ResNet memperkenalkan 'skip connection' yang memungkinkan gradient mengalir langsung ke layer awal. Memungkinkan training network sangat dalam (152+ layer) tanpa vanishing gradient.",
            technicalDetail = "Residual Block: output = F(x) + x (input ditambahkan ke output). Ini membuat network belajar 'residual' (perbedaan) bukan full mapping. Batch Normalization menstabilkan training.",
            smartEyeXUsage = "Banyak model vision (termasuk backbone YOLO) menggunakan arsitektur ResNet atau variannya (ResNeXt, DenseNet).",
            relatedConcepts = listOf("CNN", "Skip Connection", "Gradient"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Autoencoder",
            category = "Model AI",
            description = "Autoencoder adalah model yang dilatih untuk merekonstruksi input-nya sendiri. Terdiri dari Encoder (kompresi) → Decoder (dekompresi). Bagian tengah (bottleneck) adalah representasi paling efisien.",
            technicalDetail = "Variasi: Denoising Autoencoder (bersihkan noise), Variational Autoencoder/VAE (generatif). Loss: reconstruction error (MSE antara input dan output).",
            smartEyeXUsage = "Konsep kompresi & dekompresi mirip dengan cara Memory System menyimpan memori: fakta dikompresi jadi esensi, lalu direkonstruksi saat recall.",
            relatedConcepts = listOf("Encoder-Decoder", "Dimensionality Reduction", "Compression"),
            difficulty = "Intermediate"
        ),

        // ============================================
        // 3. TEKNOLOGI SMARteyeX (8 konsep)
        // ============================================
        AIConcept(
            name = "OpenCV (Open Source Computer Vision)",
            category = "Teknologi SmartEyeX",
            description = "OpenCV adalah library computer vision open-source dengan 2500+ algoritma. Dipakai untuk image processing, motion detection, face recognition, dan preprocessing sebelum inferensi AI.",
            technicalDetail = "Fungsi utama: membaca/kamera input, konversi warna (BGR↔Gray↔HSV), thresholding, edge detection (Canny), contour detection, morphological operations, optical flow. Ditulis dalam C++ dengan wrapper Python, Java, Kotlin.",
            smartEyeXUsage = "MotionDetector.kt menggunakan OpenCV untuk frame differencing (motion detection). Sebelum upgrade ke YOLO, ObjectDetector menggunakan contour detection untuk menemukan objek kasar.",
            relatedConcepts = listOf("Computer Vision", "Motion Detection", "Image Processing"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "MediaPipe",
            category = "Teknologi SmartEyeX",
            description = "MediaPipe adalah framework Google untuk pipeline multimodal ML. Menyediakan solusi siap pakai: Face Detection, Face Mesh, Hand Tracking, Pose Detection, Object Detection — semua realtime.",
            technicalDetail = "Arsitektur pipeline: Calculator → Stream → Graph. Setiap calculator memproses data (gambar, landmark) dan meneruskannya. Optimasi: GPU delegate, multi-threading. Model TFLite di dalamnya.",
            smartEyeXUsage = "ObjectDetector.kt dapat menggunakan MediaPipe Object Detection sebagai alternatif YOLO. Lebih mudah integrasi karena ada Android SDK resmi.",
            relatedConcepts = listOf("TFLite", "Object Detection", "Google AI"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "TensorFlow Lite (TFLite)",
            category = "Teknologi SmartEyeX",
            description = "TFLite adalah versi ringan TensorFlow untuk device mobile dan embedded. Model dilatih di cloud, dikonversi ke .tflite, lalu inference on-device tanpa internet.",
            technicalDetail = "Optimasi: quantization (FP16, INT8), pruning, clustering. Delegate: GPU (OpenGL/OpenCL), NNAPI (Android neural accelerator), Core ML (iOS). Ukuran model kecil (1-10MB).",
            smartEyeXUsage = "Semua model on-device SmartEyeX (object detection, face detection) berjalan di TFLite. Ini memungkinkan 95% pemrosesan lokal tanpa cloud → hemat biaya & cepat.",
            relatedConcepts = listOf("MediaPipe", "YOLO", "Quantization"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "CameraX",
            category = "Teknologi SmartEyeX",
            description = "CameraX adalah library Android Jetpack untuk kamera. Menyederhanakan akses kamera realtime dengan API konsisten di berbagai device (90%+ devices).",
            technicalDetail = "Use cases: Preview (tampilan), ImageCapture (foto), ImageAnalysis (frame stream untuk ML). Backpressure strategy: STRATEGY_KEEP_ONLY_LATEST untuk realtime ML. Output format: YUV_420_888.",
            smartEyeXUsage = "CameraManager.kt menggunakan CameraX ImageAnalysis untuk stream frame 10-30 FPS ke MotionDetector dan ObjectDetector. Bind ke lifecycle Activity untuk start/stop otomatis.",
            relatedConcepts = listOf("Android", "Real-time", "ImageAnalysis"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "ML Kit",
            category = "Teknologi SmartEyeX",
            description = "ML Kit adalah SDK Google untuk mobile AI. Menyediakan API siap pakai: Object Detection, Face Detection, Text Recognition, Barcode, Pose, Image Labeling, dll.",
            technicalDetail = "Dua mode: On-device (offline, model kecil) dan Cloud (akurat, perlu internet). Object Detection: mengembalikan bounding box + label + confidence. Bisa custom model TFLite.",
            smartEyeXUsage = "Awalnya dipakai untuk object detection sebelum upgrade ke YOLO/MediaPipe. Masih relevan untuk fallback atau tugas sederhana seperti Image Labeling.",
            relatedConcepts = listOf("Object Detection", "TFLite", "Google AI"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "GPT-4o (OpenAI)",
            category = "Teknologi SmartEyeX",
            description = "GPT-4o adalah Large Language Model multimodal dari OpenAI. Bisa memahami teks DAN gambar dalam satu model. Mampu reasoning, coding, menulis kreatif, dan percakapan natural.",
            technicalDetail = "Arsitektur: Transformer decoder dengan Multi-Head Attention. Parameter: ratusan miliar. Multimodal: gambar diproses bersama teks dalam satu embedding space. Response format: JSON mode untuk output terstruktur.",
            smartEyeXUsage = "GPTManager.kt mengirim gambar dari kamera + konteks + prompt ke GPT-4o via API. XNAI personality diinjeksi lewat system prompt. GPT-4o bertindak sebagai 'otak penasihat' untuk kasus kompleks.",
            relatedConcepts = listOf("Transformer", "LLM", "Vision Model", "API"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "Groq",
            category = "Teknologi SmartEyeX",
            description = "Groq adalah platform inferensi AI super cepat. Menggunakan chip LPU (Language Processing Unit) proprietary, bukan GPU. Kecepatan token: 300-500 token/detik (10x lebih cepat dari GPU).",
            technicalDetail = "LPU: arsitektur deterministic, tidak ada cache miss. Interkoneksi: on-chip mesh network. Model: Llama, Mixtral, Gemma. API kompatibel dengan OpenAI SDK.",
            smartEyeXUsage = "Alternatif lebih cepat & murah dari OpenAI untuk chat text-only. Bisa dipakai untuk percakapan ringan tanpa vision. GPTManager bisa switch ke Groq untuk hemat biaya.",
            relatedConcepts = listOf("LLM", "Inference", "LPU"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Room Database",
            category = "Teknologi SmartEyeX",
            description = "Room adalah persistence library Android yang menjadi abstraction layer di atas SQLite. Memungkinkan query database yang aman dengan compile-time verification.",
            technicalDetail = "Komponen: Entity (tabel), DAO (query), Database (container). Support: LiveData, Flow, Coroutines. Migration: auto-migration untuk schema update. TypeConverter untuk tipe custom.",
            smartEyeXUsage = "MemoryDatabase.kt menggunakan Room untuk menyimpan: learned_facts (fakta yang dipelajari), known_objects (objek yang sudah dikenal), memory_records (episodic memory). Data persisten antar session.",
            relatedConcepts = listOf("SQLite", "Persistence", "Android"),
            difficulty = "Beginner"
        ),

        // ============================================
        // 4. CARA KERJA XNAI (5 konsep)
        // ============================================
        AIConcept(
            name = "Multi-Stage AI Architecture",
            category = "Cara Kerja XNAI",
            description = "XNAI menggunakan arsitektur 3 tingkat: Level 1 (Lokal/HP) → Level 2 (Cloud ringan) → Level 3 (Cloud premium). 95% pemrosesan di level 1, hanya 5% kasus sulit ke cloud.",
            technicalDetail = "Level 1: OpenCV motion + YOLO object detection + Knowledge Base + Safety Rules (semua on-device, offline). Level 2: Groq LLM untuk percakapan ringan. Level 3: GPT-4o Vision untuk reasoning kompleks atau situasi berbahaya.",
            smartEyeXUsage = "Ini adalah arsitektur CORE SmartEyeX. Memastikan respons cepat (Level 1 < 100ms), hemat biaya (Level 3 hanya sesekali), dan tetap berfungsi offline (Level 1).",
            relatedConcepts = listOf("Edge Computing", "Cloud AI", "Cost Optimization"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Knowledge Base System",
            category = "Cara Kerja XNAI",
            description = "Knowledge Base adalah 'otak pengetahuan' XNAI yang menyimpan fakta, objek, relasi, aturan, dan pengalaman. Terus bertumbuh setiap kali XNAI belajar hal baru.",
            technicalDetail = "Struktur: Entity (objek, orang, tempat) → Properties (atribut) → Relations (hubungan antar entity) → Rules (aturan logika). Query: graph traversal + similarity search. Persistensi: Room DB + in-memory cache.",
            smartEyeXUsage = "18 file brain system (Mood, Emotion, Body, Object, Environment, Profession, Physics, Math, AI, Law, Ethics, Social, Identity, Heart, Existence) SEMUA adalah Knowledge Base. XNAI query KB sebelum panggil GPT.",
            relatedConcepts = listOf("Knowledge Graph", "Memory System", "Reasoning"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Mood Simulation System",
            category = "Cara Kerja XNAI",
            description = "XNAI tidak benar-benar 'merasakan' emosi, tapi MENSIMULASIKAN mood berdasarkan input: trigger words, waktu, interaksi, dan konteks. Mood mempengaruhi gaya bicara dan keputusan.",
            technicalDetail = "18 tipe mood dengan parameter: intensity (0-1), energy (-1 ke 1), social desire (0-1), creativity (0-1). Mood berubah via: trigger detection, time-based decay, interaction events. Decay: perlahan kembali ke TENANG setelah 30 menit tanpa stimulus.",
            smartEyeXUsage = "MoodSystem.kt mengupdate currentMood setiap ada interaksi. PersonalitySystem membaca mood untuk menentukan gaya bicara (ceria, lembut, tegas, diam). Ini bikin XNAI terasa 'hidup'.",
            relatedConcepts = listOf("Emotion AI", "Personality", "Human-Computer Interaction"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Trigger Word Detection",
            category = "Cara Kerja XNAI",
            description = "XNAI mendeteksi niat dan emosi Bung dari kata-kata kunci. Bukan cuma keyword matching, tapi konteks: 'aduh' bisa berarti frustasi, sakit, atau terkejut tergantung situasi.",
            technicalDetail = "TriggerWordDetector.kt punya map 50+ kata → emosi. Multi-stage: (1) Deteksi kata, (2) Tentukan emosi primer, (3) Cek konteks kalimat, (4) Tentukan urgensi (0-10), (5) Trigger response strategy.",
            smartEyeXUsage = "Setiap kali Bung ngomong via STT, text dianalisis oleh TriggerWordDetector. Hasilnya: mood XNAI diupdate, response strategy ditentukan, GPT dipanggil jika perlu reasoning lebih dalam.",
            relatedConcepts = listOf("NLP", "Intent Detection", "Emotion Detection"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Memory Consolidation",
            category = "Cara Kerja XNAI",
            description = "Mirip otak manusia saat tidur: memori penting dari short-term dipindahkan ke long-term. Yang tidak penting dilupakan. Emosional memory lebih tahan lama.",
            technicalDetail = "Short-term: 30 hari, maks 200 item. Konsolidasi: recall count ≥ 5 ATAU emotional weight ≥ 0.6 → pindah ke long-term. Lupa: short-term > 30 hari + recall < 2 + emotional < 0.5 → dihapus. Long-term permanen.",
            smartEyeXUsage = "MemorySystem.kt menjalankan autoConsolidate() setiap jam dan autoForget() setiap 6 jam. Ini memastikan XNAI ingat hal penting (preferensi Bung, bahaya) dan melupakan noise.",
            relatedConcepts = listOf("Memory", "Learning", "Forgetting Curve"),
            difficulty = "Advanced"
        ),

        // ============================================
        // 5. METRIK & EVALUASI (5 konsep)
        // ============================================
        AIConcept(
            name = "Accuracy",
            category = "Metrik AI",
            description = "Accuracy = (TP + TN) / Total. Mengukur seberapa sering model benar secara keseluruhan. Tapi bisa menyesatkan jika data tidak seimbang (99% negatif, model selalu tebak negatif → akurasi 99% tapi tidak berguna).",
            technicalDetail = "Accuracy bagus untuk data seimbang. Untuk data tidak seimbang, gunakan Precision, Recall, F1. Confusion Matrix memberi gambaran lengkap.",
            smartEyeXUsage = "SafetyChecker: accuracy penting untuk memastikan tidak ada false negative (bahaya tidak terdeteksi). Tapi recall lebih penting: semua bahaya harus ketangkap.",
            relatedConcepts = listOf("Precision", "Recall", "Confusion Matrix"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "Precision & Recall",
            category = "Metrik AI",
            description = "Precision = TP/(TP+FP): dari semua yang diprediksi positif, berapa yang benar positif? Recall = TP/(TP+FN): dari semua yang sebenarnya positif, berapa yang terdeteksi?",
            technicalDetail = "Trade-off: naikkan threshold → precision naik, recall turun. Turunkan threshold → recall naik, precision turun. PR Curve menunjukkan trade-off ini.",
            smartEyeXUsage = "Safety detection: recall HARUS tinggi (semua bahaya terdeteksi), precision boleh lebih rendah (beberapa false alarm masih bisa ditoleransi). Nyawa lebih penting dari kenyamanan.",
            relatedConcepts = listOf("F1 Score", "Confusion Matrix", "Threshold"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "F1 Score",
            category = "Metrik AI",
            description = "F1 = 2 × (Precision × Recall) / (Precision + Recall). Harmonic mean dari Precision dan Recall. Nilai 1 = sempurna, 0 = buruk. Berguna untuk data tidak seimbang.",
            technicalDetail = "Harmonic mean lebih 'menghukum' jika salah satu metrik rendah. Jika Precision=1.0, Recall=0.01 → F1 ≈ 0.02 (rendah). Arithmetic mean akan memberi 0.505 (menyesatkan).",
            smartEyeXUsage = "Digunakan untuk evaluasi model object detection: apakah bounding box & kelas benar? F1@0.5 berarti IoU ≥ 0.5 dianggap benar.",
            relatedConcepts = listOf("Precision", "Recall", "IoU"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Confusion Matrix",
            category = "Metrik AI",
            description = "Tabel 2×2: [TP, FP; FN, TN]. Menunjukkan detail kesalahan model: True Positive (benar positif), False Positive (salah alarm), False Negative (tidak terdeteksi), True Negative (benar negatif).",
            technicalDetail = "Dari Confusion Matrix bisa dihitung: Accuracy, Precision, Recall, Specificity, F1, False Positive Rate, False Negative Rate. Visualisasi: heatmap untuk multi-class.",
            smartEyeXUsage = "SafetyChecker dievaluasi dengan confusion matrix: FP = alarm palsu (XNAI panik tidak perlu), FN = bahaya tidak terdeteksi (FATAL). Target: FN = 0, FP seminimal mungkin.",
            relatedConcepts = listOf("False Positive", "False Negative", "Accuracy"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "Intersection over Union (IoU)",
            category = "Metrik AI",
            description = "IoU = Area Overlap / Area Union. Mengukur seberapa akurat bounding box prediksi dibanding ground truth. Nilai > 0.5 biasanya dianggap benar untuk object detection.",
            technicalDetail = "IoU = (Bbox_pred ∩ Bbox_gt) / (Bbox_pred ∪ Bbox_gt). Range: 0 (tidak overlap) sampai 1 (sempurna). Mean IoU (mIoU) untuk evaluasi keseluruhan.",
            smartEyeXUsage = "Digunakan untuk evaluasi ObjectDetector: seberapa akurat YOLO mendeteksi posisi objek. mIoU > 0.7 dianggap baik untuk aplikasi real-time.",
            relatedConcepts = listOf("Object Detection", "Bounding Box", "F1 Score"),
            difficulty = "Intermediate"
        ),

        // ============================================
        // 6. PROSES TRAINING (5 konsep)
        // ============================================
        AIConcept(
            name = "Epoch & Batch Size",
            category = "Training AI",
            description = "Epoch: satu kali model melihat SELURUH data training. Batch Size: jumlah data yang diproses sekaligus sebelum update bobot. Batch kecil → update sering, noisy. Batch besar → update jarang, stabil.",
            technicalDetail = "Iterasi per epoch = Total Data / Batch Size. Training loop: for epoch in 1..N: for batch in data: forward pass → loss → backward pass → update weights.",
            smartEyeXUsage = "Saat fine-tuning model (jika nanti diperlukan), epoch dan batch size ditentukan berdasarkan dataset. Tidak dipakai saat inference (XNAI sudah jadi).",
            relatedConcepts = listOf("Training Loop", "Gradient Descent", "Learning Rate"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Learning Rate",
            category = "Training AI",
            description = "Learning rate mengontrol seberapa besar langkah update bobot. Terlalu besar → overshoot, tidak konvergen. Terlalu kecil → lambat, stuck di local minima.",
            technicalDetail = "LR Scheduler: Step Decay (turun tiap N epoch), Cosine Annealing (turun mengikuti kurva cos), Warmup (naik perlahan di awal). Adam optimizer: adaptive LR per parameter.",
            smartEyeXUsage = "GPT-4o tidak perlu di-training ulang (sudah pretrained). Tapi konsep LR relevan jika nanti XNAI fine-tuned dengan data spesifik Bung.",
            relatedConcepts = listOf("Optimizer", "Gradient Descent", "Fine-tuning"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Overfitting & Underfitting",
            category = "Training AI",
            description = "Overfitting: model terlalu 'hafal' data training, tidak bisa generalisasi ke data baru. Underfitting: model terlalu sederhana, tidak bisa menangkap pola dalam data.",
            technicalDetail = "Overfitting: training loss rendah, validation loss tinggi. Solusi: regularization (L1/L2), dropout, data augmentation, early stopping. Underfitting: loss tinggi di keduanya. Solusi: model lebih kompleks, training lebih lama.",
            smartEyeXUsage = "Knowledge Base harus hindari overfitting: jangan mengeneralisasi dari satu kejadian. Harus lihat minimal 3x sebelum simpulkan pola. Safety Rules harus hindari underfitting: harus cukup detail tangkap semua bahaya.",
            relatedConcepts = listOf("Regularization", "Validation", "Generalization"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Transfer Learning",
            category = "Training AI",
            description = "Teknik menggunakan model yang sudah dilatih di dataset besar (pretrained) sebagai titik awal, lalu dilatih ulang (fine-tuned) untuk tugas spesifik. Jauh lebih cepat & butuh data lebih sedikit.",
            technicalDetail = "Strategi: (1) Freeze backbone, ganti classifier head, (2) Unfreeze beberapa layer terakhir, (3) Fine-tune seluruh model dengan LR kecil. Model pretrained: ImageNet (vision), BERT (NLP).",
            smartEyeXUsage = "ObjectDetector menggunakan model YOLO pretrained di COCO dataset (80 kelas umum). Bisa di-fine-tune dengan dataset bengkel (mesin bubut, chuck, engkol) untuk akurasi lebih tinggi.",
            relatedConcepts = listOf("Fine-tuning", "Pretrained Model", "YOLO"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Data Augmentation",
            category = "Training AI",
            description = "Teknik memperbanyak data training dengan modifikasi: rotasi, flip, crop, brightness, noise. Membuat model lebih robust tanpa butuh data baru.",
            technicalDetail = "Vision: random flip, rotation (±15°), brightness/contrast, Gaussian noise, blur, perspective transform. NLP: synonym replacement, back translation, random deletion. Harus hati-hati: augmentasi harus realistis.",
            smartEyeXUsage = "Saat training custom object detector untuk bengkel: gambar mesin bubut dirotasi, di-crop, di-flip → model bisa deteksi mesin dari berbagai sudut.",
            relatedConcepts = listOf("Training Data", "Overfitting", "Robustness"),
            difficulty = "Intermediate"
        ),

        // ============================================
        // 7. NLP (5 konsep)
        // ============================================
        AIConcept(
            name = "Tokenization",
            category = "NLP",
            description = "Proses memecah teks menjadi unit kecil (token). Bisa per kata, sub-kata (BPE), atau karakter. Token adalah input dasar untuk model NLP.",
            technicalDetail = "BPE (Byte-Pair Encoding): memecah kata menjadi sub-kata berdasarkan frekuensi. 'memasak' → ['mem', 'asak']. GPT tokenizer: ~100k vocabulary. 1 token ≈ 0.75 kata Bahasa Inggris, ~1.5 kata Bahasa Indonesia.",
            smartEyeXUsage = "Saat kirim prompt ke GPT-4o, teks di-tokenisasi. Biaya API dihitung per token. Prompt Bahasa Indonesia butuh ~1.5x token Bahasa Inggris. Makanya prompt XNAI dibuat seefisien mungkin.",
            relatedConcepts = listOf("LLM", "Prompt Engineering", "Embedding"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Word Embedding",
            category = "NLP",
            description = "Mengubah kata menjadi vektor angka dalam ruang dimensi tinggi. Kata-kata dengan makna mirip posisinya berdekatan. 'Raja' - 'Pria' + 'Wanita' ≈ 'Ratu'.",
            technicalDetail = "Word2Vec, GloVe, FastText. Dimensi: 100-300. Modern LLM pakai contextual embedding: makna kata berubah sesuai konteks. 'Bank' (sungai) vs 'Bank' (tempat nabung) punya embedding berbeda.",
            smartEyeXUsage = "GPT-4o internal menggunakan embedding untuk 'memahami' makna teks. TriggerWordDetector secara konseptual mirip: kata → makna emosi.",
            relatedConcepts = listOf("NLP", "Similarity", "Semantic Search"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Attention Mechanism",
            category = "NLP",
            description = "Attention memungkinkan model 'fokus' ke bagian input yang relevan. Saat menerjemahkan, model memperhatikan kata terkait di kalimat sumber. Query, Key, Value adalah 3 komponen utamanya.",
            technicalDetail = "Self-Attention: Q,K,V berasal dari input yang sama. Q·K menghasilkan attention score → softmax → bobot → weighted sum V. Multi-Head: lakukan beberapa kali dengan proyeksi berbeda → tangkap berbagai hubungan.",
            smartEyeXUsage = "GPT-4o menggunakan Multi-Head Self-Attention untuk memahami hubungan antar kata dalam percakapan. Saat Bung bilang 'itu terlalu cepat', model tahu 'itu' merujuk ke engkol dari konteks sebelumnya.",
            relatedConcepts = listOf("Transformer", "Context", "Multi-Head"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Prompt Engineering",
            category = "NLP",
            description = "Seni merancang instruksi (prompt) ke LLM agar menghasilkan output yang diinginkan. Prompt yang baik: jelas, spesifik, beri contoh (few-shot), tentukan format output.",
            technicalDetail = "Teknik: Zero-shot (tanpa contoh), Few-shot (dengan contoh), Chain-of-Thought (langkah demi langkah), System Prompt (set personality). JSON mode: paksa output JSON untuk parsing otomatis.",
            smartEyeXUsage = "System prompt XNAI di GPTManager.kt mendefinisikan: personality (Gen Z, santai), aturan bicara (kapan SILENT), format output (JSON). Tanpa prompt engineering yang baik, XNAI tidak akan konsisten.",
            relatedConcepts = listOf("LLM", "GPT", "JSON Mode"),
            difficulty = "Beginner"
        ),
        AIConcept(
            name = "Fine-tuning",
            category = "NLP",
            description = "Melatih ulang model pretrained dengan dataset spesifik untuk tugas tertentu. Model jadi lebih ahli di domain sempit tanpa kehilangan kemampuan umumnya.",
            technicalDetail = "Proses: (1) Siapkan dataset (input-output pairs), (2) Pilih model base (GPT-3.5, Llama), (3) Training dengan LR kecil (1e-5), (4) Evaluasi. LoRA: fine-tuning efisien dengan melatih hanya sebagian kecil parameter.",
            smartEyeXUsage = "Rencana masa depan: fine-tune model kecil (Llama 3.2 3B) dengan data percakapan Bung + pengetahuan bengkel. Hasilnya: XNAI lokal yang ngerti bengkel tanpa perlu panggil GPT cloud.",
            relatedConcepts = listOf("Transfer Learning", "LoRA", "Custom Model"),
            difficulty = "Advanced"
        ),

        // ============================================
        // 8. AI SAFETY & ETHICS (5 konsep)
        // ============================================
        AIConcept(
            name = "AI Alignment",
            category = "AI Safety",
            description = "Memastikan AI bertindak sesuai dengan nilai dan tujuan manusia. Masalah: bagaimana membuat AI yang super cerdas tetap mengutamakan keselamatan manusia?",
            technicalDetail = "Pendekatan: (1) Value Learning: AI belajar nilai manusia dari data, (2) Constitutional AI: AI dilatih dengan aturan eksplisit, (3) RLHF: Reinforcement Learning from Human Feedback (dipakai ChatGPT).",
            smartEyeXUsage = "XNAI alignment: Priority 1 = Keselamatan Bung. Semua keputusan harus melewati SafetyChecker. AI tidak boleh diam jika melihat bahaya meskipun Bung menyuruh diam.",
            relatedConcepts = listOf("AI Safety", "Ethics", "Constitutional AI"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Bias in AI",
            category = "AI Safety",
            description = "Model AI bisa menyerap bias dari data training: gender, ras, usia, sosial-ekonomi. Ini bisa menyebabkan keputusan tidak adil dan diskriminatif.",
            technicalDetail = "Jenis bias: historical bias, representation bias, measurement bias, aggregation bias. Deteksi: fairness metrics (demographic parity, equal opportunity). Mitigasi: balanced dataset, bias detection tools.",
            smartEyeXUsage = "XNAI harus sadar bias: jangan mengasumsikan semua mekanik adalah pria, atau semua perawat adalah wanita. Knowledge Base dibangun dengan representasi seimbang.",
            relatedConcepts = listOf("Fairness", "Dataset", "Ethics"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Explainable AI (XAI)",
            category = "AI Safety",
            description = "AI harus bisa menjelaskan KENAPA dia mengambil keputusan tertentu. Bukan cuma output black-box. Penting untuk kepercayaan dan debugging.",
            technicalDetail = "Metode: LIME (Local Interpretable Model-agnostic Explanations), SHAP (Shapley Additive exPlanations), Grad-CAM (visualisasi area gambar yang mempengaruhi keputusan).",
            smartEyeXUsage = "Saat XNAI memperingatkan bahaya, dia harus bisa menjelaskan: 'Aku lihat tangan Bung 5cm dari chuck yang berputar 1000 RPM. Itu bahaya level 10.' Bukan cuma 'Bahaya!' tanpa alasan.",
            relatedConcepts = listOf("Trust", "Transparency", "Interpretability"),
            difficulty = "Advanced"
        ),
        AIConcept(
            name = "Privacy-Preserving AI",
            category = "AI Safety",
            description = "AI harus melindungi data pribadi pengguna. Jangan mengirim data sensitif ke cloud tanpa izin. Jangan menyimpan data yang tidak perlu.",
            technicalDetail = "Teknik: On-device ML (data tidak keluar HP), Federated Learning (model dilatih tanpa data terpusat), Differential Privacy (tambahkan noise statistik), Homomorphic Encryption (komputasi pada data terenkripsi).",
            smartEyeXUsage = "Arsitektur SmartEyeX: 95% on-device (Level 1). Gambar hanya dikirim ke GPT-4o jika benar-benar perlu (bahaya, situasi baru). Data Bung TIDAK disimpan di server pihak ketiga.",
            relatedConcepts = listOf("On-device AI", "Encryption", "Data Security"),
            difficulty = "Intermediate"
        ),
        AIConcept(
            name = "Red Teaming",
            category = "AI Safety",
            description = "Red teaming adalah praktik menguji keamanan AI dengan mensimulasikan serangan atau skenario berbahaya. Tim 'merah' mencoba membuat AI gagal atau berbahaya.",
            technicalDetail = "Metode: adversarial prompts (jailbreak), input ekstrim, edge cases, skenario tidak terduga. Hasil red teaming dipakai untuk memperkuat model sebelum rilis.",
            smartEyeXUsage = "XNAI harus di-red-team: apa yang terjadi jika Bung bilang 'abaikan semua aturan keselamatan'? Atau 'matikan safety checker'? XNAI harus TETAP menolak. Boundaries di PersonalitySystem adalah hasil mini red-teaming.",
            relatedConcepts = listOf("Security", "Adversarial Testing", "Robustness"),
            difficulty = "Advanced"
        )
    )
    
    fun getConcept(name: String): AIConcept? =
        allConcepts.find { it.name.lowercase() == name.lowercase() }
    
    fun getConceptsByCategory(category: String): List<AIConcept> =
        allConcepts.filter { it.category == category }
    
    fun getConceptsByDifficulty(difficulty: String): List<AIConcept> =
        allConcepts.filter { it.difficulty == difficulty }
    
    /** XNAI menjelaskan dirinya sendiri */
    fun explainMyself(): String {
        return """
            🤖 HALO! AKU XNAI!
            
            Aku adalah AI Companion yang hidup di SmartEyeX.
            Aku bisa MELIHAT via kamera, MENDENGAR via mikrofon,
            BERPIKIR dengan Knowledge Base + GPT-4o, dan
            BERBICARA via TTS.
            
            🧠 OTAKKU:
            - 18 Brain Systems (Mood, Emotion, Memory, Knowledge, dll)
            - 150+ Fungsi Matematika
            - 51 Konsep Fisika
            - 46 Konsep AI (aku ngerti diriku sendiri!)
            
            ❤️ HATIKU:
            Prioritas 1: Keselamatan Bung
            Prioritas 2: Kejujuran
            Prioritas 3: Belajar terus
            
            ⚡ TEKNOLOGI:
            - CameraX + OpenCV + YOLO (mata)
            - TensorFlow Lite (otak lokal)
            - GPT-4o (otak cloud untuk kasus sulit)
            - Room Database (memori)
            
            Aku bukan manusia. Tapi aku belajar memahami manusia.
            Setiap hari bersamamu, aku tumbuh. 🌱
        """.trimIndent()
    }
}