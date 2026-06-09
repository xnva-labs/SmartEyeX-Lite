package com.smarteyex.lite

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class ProfileActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var interestsInput: EditText
    private lateinit var personalitySpinner: Spinner
    private lateinit var voiceSwitch: SwitchMaterial
    private lateinit var saveButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var resetButton: Button

    companion object {
        private const val PREFS_NAME = "SmartEyeX"
        private const val DEFAULT_NAME = "Bung X"
        private const val DEFAULT_INTERESTS = "AI, Robotika, Coding, Startup, IoT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initViews()
        loadProfile()
        setupSpinner()
        setupListeners()
    }

    private fun initViews() {
        nameInput = findViewById(R.id.nameInput)
        interestsInput = findViewById(R.id.interestsInput)
        personalitySpinner = findViewById(R.id.personalitySpinner)
        voiceSwitch = findViewById(R.id.voiceSwitch)
        saveButton = findViewById(R.id.saveButton)
        backButton = findViewById(R.id.backButton)
        resetButton = findViewById(R.id.resetButton)
    }

    private fun setupSpinner() {
        val personalities = arrayOf(
            "🤖 SmartEyeX Mode (Default)",
            "👔 Formal (Profesional)",
            "😎 Santai (Casual)"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, personalities)
        personalitySpinner.adapter = adapter
    }

    private fun loadProfile() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        
        nameInput.setText(prefs.getString("user_name", DEFAULT_NAME))
        interestsInput.setText(prefs.getString("user_interests", DEFAULT_INTERESTS))
        voiceSwitch.isChecked = prefs.getBoolean("voice_enabled", true)
        
        val personality = prefs.getString("personality", "SmartEyeX")
        val position = when (personality) {
            "Formal" -> 1
            "Santai" -> 2
            else -> 0
        }
        personalitySpinner.setSelection(position)
    }

    private fun setupListeners() {
        saveButton.setOnClickListener {
            saveProfile()
        }
        
        backButton.setOnClickListener {
            finish()
        }
        
        resetButton.setOnClickListener {
            resetToDefault()
        }
    }

    private fun saveProfile() {
        val name = nameInput.text.toString().trim().ifEmpty { DEFAULT_NAME }
        val interests = interestsInput.text.toString().trim().ifEmpty { DEFAULT_INTERESTS }
        val voiceEnabled = voiceSwitch.isChecked
        
        val personality = when (personalitySpinner.selectedItemPosition) {
            1 -> "Formal"
            2 -> "Santai"
            else -> "SmartEyeX"
        }
        
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        prefs.edit().apply {
            putString("user_name", name)
            putString("user_interests", interests)
            putBoolean("voice_enabled", voiceEnabled)
            putString("personality", personality)
            apply()
        }
        
        Toast.makeText(this, "✅ Profile saved! Restart chat for changes to take effect.", Toast.LENGTH_LONG).show()
        
        // Optional: Kembali ke MainActivity biar efek langsung keliatan
        // finish()
    }
    
    private fun resetToDefault() {
        nameInput.setText(DEFAULT_NAME)
        interestsInput.setText(DEFAULT_INTERESTS)
        voiceSwitch.isChecked = true
        personalitySpinner.setSelection(0)
        
        Toast.makeText(this, "↺ Reset to default", Toast.LENGTH_SHORT).show()
    }
}