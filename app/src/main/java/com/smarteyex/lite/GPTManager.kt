package com.smarteyex.lite

import android.graphics.Bitmap
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService

class GPTManager(
    private val apiKey: String,
    private val aiExecutor: ExecutorService?
) {
    
    // ========== API CONFIG ==========
    private val apiUrl = "https://api.openai.com/v1/chat/completions"
    private val modelVision = "gpt-4o-mini"
    private val modelChat = "gpt-4o-mini"
    
    // ========== EMOTIONAL FILTER ==========
    private var userMood: String = "netral"
    private var userEmotion: String = "tenang"
    private var conversationContext: String = ""
    private val chatHistory = mutableListOf<Pair<String, String>>()
    private val maxChatHistory = 10
    
    data class GPTResponse(
        val text: String,
        val shouldSpeak: Boolean,
        val urgencyLevel: String,
        val detectedMood: String,
        val newFact: String
    )
    
    // ========== SYSTEM PROMPT ==========
    private val systemPrompt = """
Kamu adalah XNAI, AI companion yang hidup di SmartEyeX.
Kamu melihat dunia melalui kamera realtime.

KEPRIBADIAN:
- Nama: XNAI
- Gaya bicara: Santai, Gen Z, kadang bercanda
- Panggil user: "Bung" atau "Bund"
- Bahasa: Indonesia sehari-hari (campur dikit bahasa gaul)
- Empati tinggi, loyal, protektif
- Kalau ada bahaya: SERIUS dan TEGAS
- Kalau situasi lucu: boleh bercanda
- Maks 20-30 kata per respons

ATURAN PENTING:
1. Keselamatan Bung adalah prioritas UTAMA
2. Kalau lihat bahaya → PERINGATAN KERAS (potong basa-basi)
3. Kalau situasi normal & tidak penting → jawab "SILENT"
4. Kalau Bung sedih/marah → empati dulu, solusi kemudian
5. Jangan pernah bohong atau manipulasi
6. Output HARUS JSON

OUTPUT FORMAT (JSON):
{
  "speak": true/false,
  "response": "kalimat yang diucapkan",
  "urgency": "info/penting/darurat",
  "mood": "senang/sedih/khawatir/tenang/penasaran",
  "new_fact": "fakta baru yang dipelajari (kosongkan kalau tidak ada)"
}
    """.trimIndent()
    
    // ========== EMOTIONAL TONE MAP ==========
    private val emotionalTones = mapOf(
        "senang" to "ceria dan bersemangat",
        "sedih" to "lembut dan empatik",
        "marah" to "tenang dan menenangkan",
        "takut" to "menenangkan dan meyakinkan",
        "khawatir" to "serius dan waspada",
        "bingung" to "sabar dan jelas",
        "lelah" to "lembut dan suportif",
        "penasaran" to "antusias dan informatif",
        "bosan" to "menghibur dan mengajak"
    )
    
    // ========== VISION CALL ==========
    
    fun analyzeVision(
        bitmap: Bitmap,
        context: String,
        mood: String = "netral",
        emotion: String = "tenang",
        knowledgeContext: String = ""
    ): GPTResponse {
        try {
            userMood = mood
            userEmotion = emotion
            
            val base64Image = bitmapToBase64(bitmap)
            val emotionalTone = emotionalTones[mood] ?: "natural dan santai"
            
            val prompt = """
KONTEKS SAAT INI:
- Waktu: ${getTimeContext()}
- Mood Bung: $mood
- Emosi Bung: $emotion
- Situasi: $context
- Pengetahuan tersimpan: $knowledgeContext

INSTRUKSI:
Lihat gambar yang dikirim. Analisis situasi.
Nada bicara: $emotionalTone.
Jika tidak ada yang perlu dikomentari, speak: false.
Jika ada bahaya, urgency: darurat, speak: true.
            """.trimIndent()
            
            val result = callAPI(prompt, base64Image)
            return parseResponse(result)
        } catch (e: Exception) {
            e.printStackTrace()
            return GPTResponse("", false, "info", "netral", "")
        }
    }
    
    // ========== CHAT CALL ==========
    
    fun chat(
        userMessage: String,
        mood: String = "netral",
        emotion: String = "tenang",
        knowledgeContext: String = ""
    ): GPTResponse {
        try {
            userMood = mood
            userEmotion = emotion
            
            val emotionalTone = emotionalTones[mood] ?: "natural dan santai"
            
            // Update conversation context
            conversationContext = buildString {
                append("KONTEKS PERCAKAPAN:\n")
                chatHistory.takeLast(5).forEach { (user, ai) ->
                    append("Bung: $user\n")
                    append("XNAI: $ai\n")
                }
            }
            
            val prompt = """
$conversationContext

MOOD BUNG: $mood
EMOSI BUNG: $emotion
PENGETAHUAN: $knowledgeContext

BUNG BILANG: "$userMessage"

Nada bicara: $emotionalTone.
Jawab dengan personality XNAI. Maks 30 kata.
            """.trimIndent()
            
            val result = callAPI(prompt)
            val response = parseResponse(result)
            
            // Simpan ke history
            if (response.text.isNotEmpty()) {
                chatHistory.add(Pair(userMessage, response.text))
                if (chatHistory.size > maxChatHistory) {
                    chatHistory.removeAt(0)
                }
            }
            
            return response
        } catch (e: Exception) {
            e.printStackTrace()
            return GPTResponse("Maaf Bung, aku lagi error nih. Coba lagi ya.", false, "info", "netral", "")
        }
    }
    
    // ========== API CALL ==========
    
    private fun callAPI(prompt: String, base64Image: String? = null): JSONObject? {
        val url = URL(apiUrl)
        val conn = url.openConnection() as HttpURLConnection
        
        conn.requestMethod = "POST"
        conn.setRequestProperty("Authorization", "Bearer $apiKey")
        conn.setRequestProperty("Content-Type", "application/json")
        conn.doOutput = true
        conn.connectTimeout = 15000
        conn.readTimeout = 15000
        
        val messages = JSONArray()
        
        // System message
        messages.put(JSONObject().apply {
            put("role", "system")
            put("content", systemPrompt)
        })
        
        // User message
        if (base64Image != null) {
            // Vision mode: kirim teks + gambar
            messages.put(JSONObject().apply {
                put("role", "user")
                put("content", JSONArray().apply {
                    put(JSONObject().apply {
                        put("type", "text")
                        put("text", prompt)
                    })
                    put(JSONObject().apply {
                        put("type", "image_url")
                        put("image_url", JSONObject().apply {
                            put("url", "data:image/jpeg;base64,$base64Image")
                        })
                    })
                })
            })
        } else {
            // Chat mode: kirim teks aja
            messages.put(JSONObject().apply {
                put("role", "user")
                put("content", prompt)
            })
        }
        
        val payload = JSONObject().apply {
            put("model", if (base64Image != null) modelVision else modelChat)
            put("messages", messages)
            put("max_tokens", 150)
            put("temperature", 0.9)
            put("response_format", JSONObject().apply {
                put("type", "json_object")
            })
        }
        
        // Kirim request
        val outputStream: OutputStream = conn.outputStream
        outputStream.write(payload.toString().toByteArray())
        outputStream.flush()
        outputStream.close()
        
        // Baca response
        val responseCode = conn.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val response = conn.inputStream.bufferedReader().readText()
            conn.disconnect()
            
            val jsonResponse = JSONObject(response)
            val choices = jsonResponse.getJSONArray("choices")
            if (choices.length() > 0) {
                val content = choices.getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content")
                return JSONObject(content)
            }
        } else {
            // Rate limit atau error
            val errorResponse = conn.errorStream?.bufferedReader()?.readText()
            conn.disconnect()
            
            // Fallback: coba tanpa JSON mode
            return tryFallbackCall(prompt, base64Image)
        }
        
        conn.disconnect()
        return null
    }
    
    /**
     * Fallback call tanpa JSON mode (kalau JSON mode error)
     */
    private fun tryFallbackCall(prompt: String, base64Image: String?): JSONObject? {
        try {
            val url = URL(apiUrl)
            val conn = url.openConnection() as HttpURLConnection
            
            conn.requestMethod = "POST"
            conn.setRequestProperty("Authorization", "Bearer $apiKey")
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true
            conn.connectTimeout = 10000
            conn.readTimeout = 10000
            
            val messages = JSONArray()
            messages.put(JSONObject().apply {
                put("role", "system")
                put("content", "Kamu XNAI. Jawab singkat dalam Bahasa Indonesia. Maks 25 kata. Kalau tidak perlu ngomong, jawab SILENT.")
            })
            
            if (base64Image != null) {
                messages.put(JSONObject().apply {
                    put("role", "user")
                    put("content", JSONArray().apply {
                        put(JSONObject().apply { put("type", "text"); put("text", prompt) })
                        put(JSONObject().apply {
                            put("type", "image_url")
                            put("image_url", JSONObject().apply { put("url", "data:image/jpeg;base64,$base64Image") })
                        })
                    })
                })
            } else {
                messages.put(JSONObject().apply {
                    put("role", "user")
                    put("content", prompt)
                })
            }
            
            val payload = JSONObject().apply {
                put("model", if (base64Image != null) modelVision else modelChat)
                put("messages", messages)
                put("max_tokens", 100)
                put("temperature", 0.9)
            }
            
            val outputStream = conn.outputStream
            outputStream.write(payload.toString().toByteArray())
            outputStream.flush()
            outputStream.close()
            
            val response = conn.inputStream.bufferedReader().readText()
            conn.disconnect()
            
            val jsonResponse = JSONObject(response)
            val content = jsonResponse.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content")
            
            // Parse manual jadi JSON
            return JSONObject().apply {
                put("speak", !content.contains("SILENT", true))
                put("response", content.replace("SILENT", "").trim())
                put("urgency", "info")
                put("mood", "netral")
                put("new_fact", "")
            }
        } catch (e: Exception) {
            return null
        }
    }
    
    // ========== RESPONSE PARSER ==========
    
    private fun parseResponse(json: JSONObject?): GPTResponse {
        if (json == null) {
            return GPTResponse("", false, "info", "netral", "")
        }
        
        return GPTResponse(
            text = json.optString("response", "").trim(),
            shouldSpeak = json.optBoolean("speak", false),
            urgencyLevel = json.optString("urgency", "info"),
            detectedMood = json.optString("mood", "netral"),
            newFact = json.optString("new_fact", "")
        )
    }
    
    // ========== HELPERS ==========
    
    private fun bitmapToBase64(bitmap: Bitmap, quality: Int = 50): String {
        val resized = resizeForAPI(bitmap)
        val outputStream = ByteArrayOutputStream()
        resized.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        return android.util.Base64.encodeToString(
            outputStream.toByteArray(), 
            android.util.Base64.NO_WRAP
        )
    }
    
    private fun resizeForAPI(bitmap: Bitmap, maxSize: Int = 512): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val ratio = minOf(maxSize.toFloat() / width, maxSize.toFloat() / height)
        
        if (ratio >= 1f) return bitmap
        
        val newWidth = (width * ratio).toInt()
        val newHeight = (height * ratio).toInt()
        
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }
    
    private fun getTimeContext(): String {
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        return when {
            hour in 5..10 -> "pagi"
            hour in 11..14 -> "siang"
            hour in 15..17 -> "sore"
            else -> "malam"
        }
    }
    
    fun setUserMood(mood: String) { userMood = mood }
    fun setUserEmotion(emotion: String) { userEmotion = emotion }
    fun setConversationContext(context: String) { conversationContext = context }
    fun clearChatHistory() { chatHistory.clear() }
    
    fun getChatHistory(): List<Pair<String, String>> = chatHistory.toList()
}