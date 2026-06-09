package com.smarteyex.lite

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
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

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var inputMessage: android.widget.EditText
    private lateinit var sendButton: android.widget.ImageButton
    private lateinit var cameraButton: android.widget.ImageButton
    private lateinit var voiceButton: android.widget.ImageButton
    private lateinit var menuButton: android.widget.ImageButton
    private lateinit var settingsButton: android.widget.ImageButton

    private lateinit var chatAdapter: ChatAdapter
    private val chatMessages = mutableListOf<ChatMessage>()
    private val gson = Gson()
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private var textToSpeech: TextToSpeech? = null
    private var isVoiceEnabled = true
    private var currentPersonality = "SmartEyeX"
    private var userName = "Bung X"
    private var userInterests = mutableListOf<String>()

    companion object {
        private const val GEMINI_API_KEY = "YOUR_GEMINI_API_KEY"
        private const val GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="
    }

    data class ChatMessage(
        val id: String = UUID.randomUUID().toString(),
        val content: String,
        val isUser: Boolean,
        val timestamp: Long = System.currentTimeMillis()
    )

    inner class ChatAdapter(private val messages: List<ChatMessage>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object {
            private const val TYPE_USER = 1
            private const val TYPE_AI = 2
        }

        override fun getItemViewType(position: Int): Int {
            return if (messages[position].isUser) TYPE_USER else TYPE_AI
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return when (viewType) {
                TYPE_USER -> {
                    val view = inflater.inflate(R.layout.item_chat_user, parent, false)
                    UserViewHolder(view)
                }
                else -> {
                    val view = inflater.inflate(R.layout.item_chat_ai, parent, false)
                    AIViewHolder(view)
                }
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val message = messages[position]
            when (holder) {
                is UserViewHolder -> holder.bind(message)
                is AIViewHolder -> holder.bind(message)
            }
        }

        override fun getItemCount() = messages.size

        inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val messageText: TextView = itemView.findViewById(R.id.messageText)
            fun bind(message: ChatMessage) {
                messageText.text = message.content
            }
        }

        inner class AIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val messageText: TextView = itemView.findViewById(R.id.messageText)
            fun bind(message: ChatMessage) {
                messageText.text = message.content
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()
        setupViews()
        setupDrawer()
        setupTextToSpeech()
        loadUserProfile()
        addWelcomeMessage()
    }

    private fun setupViews() {
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navView)
        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        inputMessage = findViewById(R.id.inputMessage)
        sendButton = findViewById(R.id.sendButton)
        cameraButton = findViewById(R.id.cameraButton)
        voiceButton = findViewById(R.id.voiceButton)
        menuButton = findViewById(R.id.menuButton)
        settingsButton = findViewById(R.id.settingsButton)

        chatAdapter = ChatAdapter(chatMessages)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = chatAdapter

        sendButton.setOnClickListener {
            val message = inputMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                sendMessage(message)
            }
        }

        cameraButton.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        voiceButton.setOnClickListener {
            startVoiceInput()
        }

        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun setupDrawer() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_chat -> { }
                R.id.nav_camera -> startActivity(Intent(this, CameraActivity::class.java))
                R.id.nav_memory -> startActivity(Intent(this, MemoryActivity::class.java))
                R.id.nav_profile -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.nav_journal -> startActivity(Intent(this, JournalActivity::class.java))
                R.id.nav_reminder -> startActivity(Intent(this, ReminderActivity::class.java))
                R.id.nav_settings -> startActivity(Intent(this, SettingsActivity::class.java))
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setupTextToSpeech() {
        textToSpeech = TextToSpeech(this, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech?.language = Locale("id", "ID")
            textToSpeech?.setSpeechRate(0.9f)
        }
    }

    private fun speak(text: String) {
        if (isVoiceEnabled && textToSpeech != null) {
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "id-ID")
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Bicara ke SmartEyeX...")
        try {
            startActivityForResult(intent, 100)
        } catch (e: Exception) {
            Toast.makeText(this, "Voice input tidak tersedia", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!results.isNullOrEmpty()) {
                inputMessage.setText(results[0])
                sendMessage(results[0])
            }
        }
    }

    private fun addWelcomeMessage() {
        val welcome = when (currentPersonality) {
            "Formal" -> "Selamat pagi. Saya SmartEyeX, asisten AI pribadi Anda. Ada yang bisa saya bantu?"
            "Santai" -> "Halo $userName! Ada yang bisa gw bantu hari ini? Santai aja ya!"
            else -> "Halo $userName! 🚀 SmartEyeX siap jadi partner hidup lo. Ayo ngobrol, belajar, atau eksplor bareng! Yang kita bicarakan bakal gw ingat semua."
        }
        addMessage(ChatMessage(welcome, false))
        speak(welcome)
    }

    private fun addMessage(message: ChatMessage) {
        chatMessages.add(message)
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        chatRecyclerView.scrollToPosition(chatMessages.size - 1)
        saveToMemory(message)
    }

    private fun sendMessage(content: String) {
        addMessage(ChatMessage(content, true))
        inputMessage.text.clear()

        val loadingMessage = ChatMessage("...", false)
        chatMessages.add(loadingMessage)
        chatAdapter.notifyItemInserted(chatMessages.size - 1)

        callGeminiAPI(content)
    }

    private fun callGeminiAPI(userMessage: String) {
        val url = GEMINI_URL + GEMINI_API_KEY

        val systemPrompt = buildString {
            append("Kamu adalah SmartEyeX, AI personal partner hidup $userName. ")
            append("Kamu ramah, cerdas, dan mengingat semua percakapan. ")
            append("Minat $userName: ${userInterests.joinToString(", ")}. ")
            append("Personality mode: $currentPersonality. ")
            append("Jawab dengan natural, hangat, dan detail. Gunakan bahasa Indonesia yang santai tapi profesional. ")
            append("Selalu panggil 'Bung $userName' atau sesuai konteks. ")
            append("Jadilah partner yang membantu dalam coding, belajar, maupun curhat.")
        }

        val conversationHistory = chatMessages.takeLast(20).joinToString("\n") {
            "${if (it.isUser) userName else "SmartEyeX"}: ${it.content}"
        }

        val fullPrompt = "$systemPrompt\n\nRiwayat chat:\n$conversationHistory\n\n$userName: $userMessage\n\nSmartEyeX:"

        val jsonBody = JSONObject().apply {
            put("contents", org.json.JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", org.json.JSONArray().apply {
                        put(JSONObject().apply {
                            put("text", fullPrompt)
                        })
                    })
                })
            })
        }

        val request = Request.Builder()
            .url(url)
            .post(jsonBody.toString().toRequestBody("application/json".toMediaType()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    removeLoadingMessage()
                    val errorMsg = "Maaf $userName, gw error: ${e.message}. Cek API key atau koneksi internet ya!"
                    addMessage(ChatMessage(errorMsg, false))
                    speak(errorMsg)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val reply = try {
                    val json = JSONObject(responseBody ?: "{}")
                    json.getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text")
                } catch (e: Exception) {
                    "Halo $userName! Maaf, gw agak error nih parsing response. Coba tanya lagi ya!"
                }

                runOnUiThread {
                    removeLoadingMessage()
                    addMessage(ChatMessage(reply, false))
                    speak(reply)
                    learnFromConversation(userMessage, reply)
                }
            }
        })
    }

    private fun removeLoadingMessage() {
        if (chatMessages.lastOrNull()?.content == "...") {
            chatMessages.removeAt(chatMessages.size - 1)
            chatAdapter.notifyItemRemoved(chatMessages.size)
        }
    }

    private fun saveToMemory(message: ChatMessage) {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = prefs.getString("chat_history", "[]")
        val type = object : TypeToken<MutableList<ChatMessage>>() {}.type
        val history: MutableList<ChatMessage> = gson.fromJson(json, type)
        history.add(message)
        prefs.edit().putString("chat_history", gson.toJson(history)).apply()
    }

    private fun loadUserProfile() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        userName = prefs.getString("user_name", "Bung X") ?: "Bung X"
        currentPersonality = prefs.getString("personality", "SmartEyeX") ?: "SmartEyeX"
        isVoiceEnabled = prefs.getBoolean("voice_enabled", true)
        val interestsString = prefs.getString("user_interests", "AI, Robot, Coding, Startup") ?: ""
        userInterests = interestsString.split(",").map { it.trim() }.toMutableList()
    }

    private fun learnFromConversation(userMessage: String, aiResponse: String) {
        val keywords = listOf("suka", "hobi", "interest", "proyek", "startup", "AI", "robot", "coding")
        keywords.forEach { keyword ->
            if (userMessage.contains(keyword, ignoreCase = true)) {
                val words = userMessage.split(" ")
                words.forEach { word ->
                    if (word.length > 3 && !userInterests.contains(word)) {
                        userInterests.add(word)
                    }
                }
            }
        }
    }

    private fun checkPermissions() {
        val permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.INTERNET,
            Manifest.permission.POST_NOTIFICATIONS
        )

        permissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions.toTypedArray(), 200)
            }
        }
    }

    override fun onDestroy() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        super.onDestroy()
    }
}