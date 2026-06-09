package com.smarteyex.lite

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class JournalActivity : AppCompatActivity() {

    private lateinit var journalRecyclerView: RecyclerView
    private lateinit var generateDailyButton: Button
    private lateinit var generateMonthlyButton: Button
    private lateinit var generateFullButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var loadingPanel: LinearLayout
    private lateinit var loadingText: TextView
    private lateinit var emptyText: TextView

    private val journals = mutableListOf<JournalEntry>()
    private val gson = Gson()
    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    companion object {
        private const val GEMINI_API_KEY = "YOUR_GEMINI_API_KEY"
        private const val GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="
    }

    data class JournalEntry(
        val id: String = UUID.randomUUID().toString(),
        val title: String,
        val content: String,
        val date: String,
        val timestamp: Long = System.currentTimeMillis(),
        val type: String // "daily", "monthly", "full"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        initViews()
        loadExistingJournals()

        setupListeners()
    }

    private fun initViews() {
        journalRecyclerView = findViewById(R.id.journalRecyclerView)
        generateDailyButton = findViewById(R.id.generateDailyButton)
        generateMonthlyButton = findViewById(R.id.generateMonthlyButton)
        generateFullButton = findViewById(R.id.generateFullButton)
        backButton = findViewById(R.id.backButton)
        loadingPanel = findViewById(R.id.loadingPanel)
        loadingText = findViewById(R.id.loadingText)
        emptyText = findViewById(R.id.emptyText)

        journalRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadExistingJournals() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = prefs.getString("journals", "[]")
        val type = object : TypeToken<MutableList<JournalEntry>>() {}.type
        val loaded: MutableList<JournalEntry> = gson.fromJson(json, type)
        journals.clear()
        journals.addAll(loaded.sortedByDescending { it.timestamp })
        
        if (journals.isNotEmpty()) {
            emptyText.visibility = View.GONE
            journalRecyclerView.visibility = View.VISIBLE
            displayJournals()
        } else {
            emptyText.visibility = View.VISIBLE
            journalRecyclerView.visibility = View.GONE
        }
    }

    private fun setupListeners() {
        generateDailyButton.setOnClickListener {
            generateDailyJournal()
        }

        generateMonthlyButton.setOnClickListener {
            generateMonthlyJournal()
        }

        generateFullButton.setOnClickListener {
            generateFullJournal()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun generateDailyJournal() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = prefs.getString("chat_history", "[]")
        val type = object : TypeToken<MutableList<MainActivity.ChatMessage>>() {}.type
        val allChats: MutableList<MainActivity.ChatMessage> = gson.fromJson(json, type)

        val today = Calendar.getInstance()
        val todayStart = getStartOfDay(today.timeInMillis)
        val todayChats = allChats.filter { it.timestamp >= todayStart }

        if (todayChats.isEmpty()) {
            Toast.makeText(this, "Belum ada chat hari ini", Toast.LENGTH_SHORT).show()
            return
        }

        showLoading("Membuat ringkasan harian...")

        Thread {
            val summary = summarizeWithAI(todayChats, "daily")
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id"))
            val todayStr = dateFormat.format(Date())

            val entry = JournalEntry(
                title = "📅 Ringkasan Harian - $todayStr",
                content = summary,
                date = todayStr,
                type = "daily"
            )

            runOnUiThread {
                saveJournal(entry)
                hideLoading()
                loadExistingJournals()
            }
        }.start()
    }

    private fun generateMonthlyJournal() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = prefs.getString("chat_history", "[]")
        val type = object : TypeToken<MutableList<MainActivity.ChatMessage>>() {}.type
        val allChats: MutableList<MainActivity.ChatMessage> = gson.fromJson(json, type)

        // Ambil 500 chat terakhir (bukan per bulan)
        val last500Chats = allChats.takeLast(500)

        if (last500Chats.isEmpty()) {
            Toast.makeText(this, "Belum ada 500 chat", Toast.LENGTH_SHORT).show()
            return
        }

        showLoading("Membuat ringkasan 500 chat terakhir...")

        Thread {
            val summary = summarizeWithAI(last500Chats, "500_chats")
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id"))
            val todayStr = dateFormat.format(Date())

            val entry = JournalEntry(
                title = "📊 500 CHAT TERAKHIR - $todayStr",
                content = summary,
                date = todayStr,
                type = "monthly"
            )

            runOnUiThread {
                saveJournal(entry)
                hideLoading()
                loadExistingJournals()
            }
        }.start()
    }

    private fun generateFullJournal() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = prefs.getString("chat_history", "[]")
        val type = object : TypeToken<MutableList<MainActivity.ChatMessage>>() {}.type
        val allChats: MutableList<MainActivity.ChatMessage> = gson.fromJson(json, type)

        if (allChats.isEmpty()) {
            Toast.makeText(this, "Belum ada chat sama sekali", Toast.LENGTH_SHORT).show()
            return
        }

        showLoading("Membuat ringkasan SELURUH HISTORY (bisa makan waktu 1-2 menit)...")

        Thread {
            val summary = summarizeWithAI(allChats, "full_history")
            val dateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale("id"))
            val nowStr = dateFormat.format(Date())

            val entry = JournalEntry(
                title = "🗂️ RINGKASAN SELURUH HISTORY - $nowStr",
                content = summary,
                date = nowStr,
                type = "full"
            )

            runOnUiThread {
                saveJournal(entry)
                hideLoading()
                loadExistingJournals()
            }
        }.start()
    }

    private fun summarizeWithAI(chats: List<MainActivity.ChatMessage>, type: String): String {
        val userName = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
            .getString("user_name", "Bung X") ?: "Bung X"

        val chatText = buildString {
            chats.forEach { chat ->
                val speaker = if (chat.isUser) userName else "SmartEyeX"
                append("$speaker: ${chat.content}\n")
            }
        }

        val prompt = when (type) {
            "daily" -> """
                Kamu adalah SmartEyeX, AI partner personal. Buatkan RINGKASAN HARIAN dari percakapan $userName hari ini.

                FORMAT RINGKASAN HARIAN:
                📅 TANGGAL: [hari ini]
                
                📊 STATISTIK:
                - Total pesan: [jumlah]
                - Topik utama: [sebutkan 3-5 topik]
                - Mood: [positif/negatif/netral]
                
                📝 RINGKASAN AKTIVITAS:
                [Ceritakan apa saja yang dilakukan $userName hari ini berdasarkan chat]
                
                💡 INSIGHT DARI AI:
                [Kasih insight personal berdasarkan percakapan]
                
                🚀 SARAN UNTUK BESOK:
                [Kasih saran yang relevan]
                
                BERIKUT PERCAKAPANNYA:
                $chatText
            """.trimIndent()

            "500_chats" -> """
                Kamu adalah SmartEyeX. Buatkan RINGKASAN DETAIL dari 500 percakapan TERAKHIR $userName.

                FORMAT RINGKASAN 500 CHAT:
                📊 JUDUL: 500 Chat Terakhir
                
                📈 STATISTIK:
                - Total pesan: [jumlah]
                - Topik paling sering dibahas: [list 5-10 topik dengan persentase]
                - Tren mood: [naik/turun/stabil]
                
                🔥 HIGHLIGHT PERCAKAPAN:
                [Sebutkan 5-10 momen penting yang paling menarik]
                
                📚 PEMBELAJARAN & PROGRESS:
                [Apa yang dipelajari atau dicapai $userName dalam 500 chat terakhir]
                
                💬 KUTIPAN MENARIK:
                [2-3 kutipan dari chat yang paling berkesan]
                
                BERIKUT PERCAKAPANNYA:
                $chatText
            """.trimIndent()

            else -> """
                Kamu adalah SmartEyeX. Buatkan RINGKASAN LENGKAP dari SELURUH PERCAKAPAN $userName sejak awal.

                FORMAT RINGKASAN FULL HISTORY:
                🗂️ JUDUL: Ringkasan Seluruh Perjalanan $userName dengan SmartEyeX
                
                📊 STATISTIK GLOBAL:
                - Total chat: [jumlah]
                - Rentang waktu: [dari tanggal pertama sampai terakhir]
                - Topik dominan: [list 10+ topik dengan ranking]
                - Evolusi mood: [bagaimana mood berubah dari awal sampai sekarang]
                
                🎯 PENCAPAIAN & MILESTONE:
                [Sebutkan pencapaian besar $userName dari chat]
                
                🧠 TOPIK YANG PALING SERING DIPELAJARI:
                [List topik belajar dengan frekuensi]
                
                💡 TRANSFORMASI YANG TERJADI:
                [Bagaimana $userName berubah dari awal sampai sekarang]
                
                🌟 MOMENT PALING BERKESAN (TOP 10):
                [10 momen terbaik dari seluruh percakapan]
                
                ✨ PESAN DARI SMARTEYEX:
                [Pesan personal untuk $userName]
                
                BERIKUT SELURUH PERCAKAPAN:
                $chatText
            """.trimIndent()
        }

        return callGeminiAPI(prompt)
    }

    private fun callGeminiAPI(prompt: String): String {
        val url = GEMINI_URL + GEMINI_API_KEY

        val jsonBody = JSONObject().apply {
            put("contents", org.json.JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", org.json.JSONArray().apply {
                        put(JSONObject().apply {
                            put("text", prompt)
                        })
                    })
                })
            })
        }

        val request = Request.Builder()
            .url(url)
            .post(jsonBody.toString().toRequestBody("application/json".toMediaType()))
            .build()

        return try {
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()
            val json = JSONObject(responseBody ?: "{}")
            json.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text")
        } catch (e: Exception) {
            "Error generating summary: ${e.message}"
        }
    }

    private fun saveJournal(entry: JournalEntry) {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = prefs.getString("journals", "[]")
        val type = object : TypeToken<MutableList<JournalEntry>>() {}.type
        val existingJournals: MutableList<JournalEntry> = gson.fromJson(json, type)
        existingJournals.add(0, entry)
        prefs.edit().putString("journals", gson.toJson(existingJournals)).apply()
    }

    private fun displayJournals() {
        val adapter = JournalAdapter(journals)
        journalRecyclerView.adapter = adapter
    }

    private fun getStartOfDay(timestamp: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private fun showLoading(message: String) {
        runOnUiThread {
            loadingText.text = message
            loadingPanel.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        runOnUiThread {
            loadingPanel.visibility = View.GONE
        }
    }

    inner class JournalAdapter(private val items: List<JournalEntry>) :
        RecyclerView.Adapter<JournalAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.item_journal, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
        }

        override fun getItemCount() = items.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val titleText: TextView = itemView.findViewById(R.id.journalTitle)
            private val dateText: TextView = itemView.findViewById(R.id.journalDate)
            private val contentText: TextView = itemView.findViewById(R.id.journalContent)
            private val typeIcon: TextView = itemView.findViewById(R.id.typeIcon)

            fun bind(journal: JournalEntry) {
                titleText.text = journal.title
                dateText.text = journal.date
                contentText.text = journal.content

                typeIcon.text = when (journal.type) {
                    "daily" -> "📅"
                    "monthly" -> "📊"
                    else -> "🗂️"
                }
            }
        }
    }
}