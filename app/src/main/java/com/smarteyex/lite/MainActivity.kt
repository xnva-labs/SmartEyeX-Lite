package com.smarteyex.lite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var inputMessage: EditText
    private lateinit var sendButton: Button
    private lateinit var chatDisplay: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        inputMessage = findViewById(R.id.inputMessage)
        sendButton = findViewById(R.id.sendButton)
        chatDisplay = findViewById(R.id.chatDisplay)
        
        sendButton.setOnClickListener {
            val userMessage = inputMessage.text.toString()
            if (userMessage.isNotEmpty()) {
                chatDisplay.append("Lo: $userMessage\n")
                inputMessage.text.clear()
                
                // Sementara pake reply palsu, nanti diganti API AI
                chatDisplay.append("SmartEyeX: Masih mode testing Bung, nanti gw konek ke AI!\n\n")
            }
        }
    }
}
