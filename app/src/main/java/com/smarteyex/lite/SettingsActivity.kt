package com.smarteyex.lite

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var apiKeyInput: EditText
    private lateinit var saveApiKeyButton: Button
    private lateinit var apiKeyStatus: TextView
    private lateinit var clearMemoryButton: Button
    private lateinit var exportChatButton: Button
    private lateinit var darkModeSwitch: SwitchMaterial
    private lateinit var fontSizeSeekBar: SeekBar
    private lateinit var fontSizeValue: TextView
    private lateinit var voiceLanguageSpinner: Spinner
    private lateinit var aboutButton: Button
    private lateinit var versionText: TextView

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initViews()
        loadSettings()
        setupListeners()
    }

    private fun initViews() {
        backButton = findViewById(R.id.backButton)
        apiKeyInput = findViewById(R.id.apiKeyInput)
        saveApiKeyButton = findViewById(R.id.saveApiKeyButton)
        apiKeyStatus = findViewById(R.id.apiKeyStatus)
        clearMemoryButton = findViewById(R.id.clearMemoryButton)
        exportChatButton = findViewById(R.id.exportChatButton)
        darkModeSwitch = findViewById(R.id.darkModeSwitch)
        fontSizeSeekBar = findViewById(R.id.fontSizeSeekBar)
        fontSizeValue = findViewById(R.id.fontSizeValue)
        voiceLanguageSpinner = findViewById(R.id.voiceLanguageSpinner)
        aboutButton = findViewById(R.id.aboutButton)
        versionText = findViewById(R.id.versionText)

        versionText.text = "Version 1.0.0"
    }

    private fun loadSettings() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)

        // API Key
        val savedKey = prefs.getString("gemini_api_key", "")
        if (!savedKey.isNullOrEmpty()) {
            apiKeyInput.setText(savedKey)
            apiKeyStatus.text = "✅ API Key Terpasang"
            apiKeyStatus.setTextColor(getColor(android.R.color.holo_green_dark))
        } else {
            apiKeyStatus.text = "⚠️ Belum ada API Key"
            apiKeyStatus.setTextColor(getColor(android.R.color.holo_orange_dark))
        }

        // Dark Mode
        val isDarkMode = prefs.getBoolean("dark_mode", true)
        darkModeSwitch.isChecked = isDarkMode

        // Font Size
        val fontSize = prefs.getInt("font_size", 14)
        fontSizeSeekBar.progress = fontSize - 10
        fontSizeValue.text = "${fontSize}sp"

        // Voice Language
        val languageIndex = prefs.getInt("voice_language", 0)
        voiceLanguageSpinner.setSelection(languageIndex)
    }

    private fun setupListeners() {
        backButton.setOnClickListener {
            finish()
        }

        saveApiKeyButton.setOnClickListener {
            saveApiKey()
        }

        clearMemoryButton.setOnClickListener {
            showClearMemoryDialog()
        }

        exportChatButton.setOnClickListener {
            exportChatHistory()
        }

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            applyDarkMode(isChecked)
        }

        fontSizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val fontSize = progress + 10
                fontSizeValue.text = "${fontSize}sp"
                if (fromUser) {
                    saveFontSize(fontSize)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        voiceLanguageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                saveVoiceLanguage(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        aboutButton.setOnClickListener {
            showAboutDialog()
        }
    }

    private fun saveApiKey() {
        val apiKey = apiKeyInput.text.toString().trim()
        if (apiKey.isNotEmpty()) {
            val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
            prefs.edit().putString("gemini_api_key", apiKey).apply()

            apiKeyStatus.text = "✅ API Key Terpasang"
            apiKeyStatus.setTextColor(getColor(android.R.color.holo_green_dark))

            Toast.makeText(this, "API Key saved! Restart app to apply.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "API Key cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showClearMemoryDialog() {
        AlertDialog.Builder(this)
            .setTitle("🧠 HAPUS SEMUA MEMORY")
            .setMessage("Yakin ingin menghapus semua riwayat chat? AI akan lupa semua percakapan yang pernah terjadi.\n\n⚠️ TINDAKAN INI TIDAK DAPAT DIBATALKAN!")
            .setPositiveButton("HAPUS") { _, _ ->
                clearAllMemory()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun clearAllMemory() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        prefs.edit().putString("chat_history", "[]").apply()

        Toast.makeText(this, "✅ Semua memory berhasil dihapus!", Toast.LENGTH_LONG).show()
    }

    private fun exportChatHistory() {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        val json = prefs.getString("chat_history", "[]")

        try {
            val fileName = "SmartEyeX_Chat_${System.currentTimeMillis()}.json"
            val file = File(getExternalFilesDir(null), fileName)
            FileWriter(file).use { writer ->
                writer.write(json)
            }

            Toast.makeText(this, "✅ Chat history exported to: ${file.absolutePath}", Toast.LENGTH_LONG).show()

            // Share intent
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/json"
                putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
            }
            startActivity(Intent.createChooser(shareIntent, "Share Chat History"))

        } catch (e: Exception) {
            Toast.makeText(this, "Export failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun applyDarkMode(isDark: Boolean) {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        prefs.edit().putBoolean("dark_mode", isDark).apply()

        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Recreate activity to apply theme
        recreate()
    }

    private fun saveFontSize(size: Int) {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        prefs.edit().putInt("font_size", size).apply()
        // Apply to MainActivity via static variable or broadcast
        Toast.makeText(this, "Font size: ${size}sp (restart chat to apply)", Toast.LENGTH_SHORT).show()
    }

    private fun saveVoiceLanguage(position: Int) {
        val prefs = getSharedPreferences("SmartEyeX", MODE_PRIVATE)
        prefs.edit().putInt("voice_language", position).apply()

        val language = when (position) {
            0 -> "Bahasa Indonesia"
            1 -> "English"
            else -> "Auto"
        }
        Toast.makeText(this, "Voice language: $language", Toast.LENGTH_SHORT).show()
    }

    private fun showAboutDialog() {
        AlertDialog.Builder(this)
            .setTitle("✨ SMARTEYEX LITE ✨")
            .setMessage("""
                Version 1.0.0
                
                AI Personal Operating System
                
                🔥 Fitur:
                • Smart Chat dengan Memory Permanen
                • Vision Engine (Kamera + ML Kit)
                • Voice Mode (STT + TTS)
                • Personality Engine
                • Life Journal
                • Reminder System
                
                🚀 Dibuat dengan ❤️ untuk Bung X
                
                📂 GitHub: xnva-labs/SmartEyeX-Lite
            """.trimIndent())
            .setPositiveButton("OK", null)
            .show()
    }
}