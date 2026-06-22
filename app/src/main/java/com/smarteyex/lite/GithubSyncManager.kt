package com.smarteyex.lite

import android.content.Context
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class GitHubSyncManager(
    private val context: Context,
    private val database: MemoryDatabase
) {
    
    private var githubToken: String = ""
    private var githubRepo: String = ""
    private var githubBranch: String = "main"
    private var currentUserId: String = "anonymous"
    private var isConfigured = false
    
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    
    private val cacheDir: File
        get() = File(context.cacheDir, "xnai_knowledge").also { it.mkdirs() }
    
    // ============================================
    // CONFIG
    // ============================================
    
    fun configure(token: String, repo: String, userId: String, branch: String = "main") {
        githubToken = token
        githubRepo = repo
        currentUserId = userId
        githubBranch = branch
        isConfigured = true
    }
    
    fun isConfigured(): Boolean = isConfigured
    
    // ============================================
    // 1. GLOBAL KNOWLEDGE (1 FILE UNTUK SEMUA)
    // ============================================
    
    /**
     * Tambah pengetahuan ke global_knowledge.json
     * OTOMATIS — user ga perlu izin
     */
    suspend fun addToGlobalKnowledge(
        key: String,
        value: String,
        category: String = "umum",
        emotionalWeight: Float = 0.5f
    ): String = withContext(Dispatchers.IO) {
        try {
            // 1. Download global_knowledge.json terbaru
            val existing = downloadGlobalKnowledge()
            
            // 2. Tambah fakta baru
            val facts = existing.getJSONArray("facts")
            
            // Cek apakah sudah ada
            var found = false
            for (i in 0 until facts.length()) {
                if (facts.getJSONObject(i).getString("key") == key) {
                    // Update verified_count
                    val obj = facts.getJSONObject(i)
                    obj.put("verified_count", obj.optInt("verified_count", 1) + 1)
                    obj.put("last_updated", dateTimeFormat.format(Date()))
                    found = true
                    break
                }
            }
            
            if (!found) {
                facts.put(JSONObject().apply {
                    put("key", key)
                    put("value", value)
                    put("category", category)
                    put("contributed_by", currentUserId)
                    put("emotional_weight", emotionalWeight)
                    put("verified_count", 1)
                    put("timestamp", System.currentTimeMillis())
                })
            }
            
            // 3. Update metadata
            existing.put("total_facts", facts.length())
            existing.put("last_updated", dateTimeFormat.format(Date()))
            
            // 4. Tambah contributor kalau belum ada
            val contributors = existing.getJSONArray("contributors")
            var contributorFound = false
            for (i in 0 until contributors.length()) {
                if (contributors.getString(i) == currentUserId) {
                    contributorFound = true
                    break
                }
            }
            if (!contributorFound) contributors.put(currentUserId)
            
            // 5. Simpan lokal + upload ke GitHub
            saveToFile("global_knowledge.json", existing.toString(2))
            uploadFile("global_knowledge.json", existing.toString(2))
            
            "✅ Ditambahkan ke global knowledge"
        } catch (e: Exception) {
            "❌ Error: ${e.message}"
        }
    }
    
    /**
     * Search di global knowledge (TANPA GPT!)
     */
    suspend fun searchGlobalKnowledge(query: String): String? = withContext(Dispatchers.IO) {
        try {
            val knowledge = downloadGlobalKnowledge()
            val facts = knowledge.getJSONArray("facts")
            
            val results = mutableListOf<Pair<String, Float>>()
            
            for (i in 0 until facts.length()) {
                val obj = facts.getJSONObject(i)
                val key = obj.optString("key", "")
                val value = obj.optString("value", "")
                
                if (query.lowercase() in key.lowercase() ||
                    query.lowercase() in value.lowercase() ||
                    key.lowercase() in query.lowercase()) {
                    val score = obj.optDouble("verified_count", 1.0).toFloat() * 
                                obj.optDouble("emotional_weight", 0.3).toFloat()
                    results.add(Pair(value, score))
                }
            }
            
            results.sortedByDescending { it.second }.firstOrNull()?.first
        } catch (e: Exception) {
            null
        }
    }
    
    // ============================================
    // 2. PROJECT FILES (OTOMATIS DIBUAT)
    // ============================================
    
    /**
     * Cek apakah project sudah ada
     * Kalau SUDAH ADA → return isinya (GA PERLU GPT!)
     * Kalau BELUM ADA → return null → panggil GPT → simpan project baru
     */
    suspend fun getOrCreateProject(projectName: String): ProjectResult = withContext(Dispatchers.IO) {
        try {
            val fileName = projectName.lowercase().replace(" ", "_") + ".json"
            val projectPath = "projects/$fileName"
            
            // 1. CEK APAKAH PROJECT SUDAH ADA?
            val existingProject = downloadFile(projectPath)
            
            if (existingProject != null && existingProject.isNotEmpty()) {
                // SUDAH ADA! Return isinya. GA PERLU GPT!
                return@withContext ProjectResult.AlreadyExists(
                    fileName = fileName,
                    content = existingProject,
                    message = "📁 Project '$projectName' sudah ada! Menggunakan data yang sudah tersimpan."
                )
            }
            
            // 2. BELUM ADA — return null, biar GPTManager yang isi
            return@withContext ProjectResult.NotFound(
                fileName = fileName,
                projectPath = projectPath,
                message = "🆕 Project '$projectName' belum ada. Akan dibuat baru."
            )
        } catch (e: Exception) {
            ProjectResult.Error(e.message ?: "Unknown error")
        }
    }
    
    /**
     * Simpan project baru (setelah GPT menjawab)
     */
    suspend fun saveProject(projectName: String, content: String, category: String = "umum"): String = 
        withContext(Dispatchers.IO) {
        try {
            val fileName = projectName.lowercase().replace(" ", "_") + ".json"
            val projectPath = "projects/$fileName"
            
            val projectJson = JSONObject().apply {
                put("title", projectName)
                put("content", content)
                put("category", category)
                put("created_by", currentUserId)
                put("created_at", dateTimeFormat.format(Date()))
                put("last_accessed", dateTimeFormat.format(Date()))
                put("access_count", 1)
                put("tags", JSONArray())
            }
            
            saveToFile(projectPath, projectJson.toString(2))
            uploadFile(projectPath, projectJson.toString(2))
            
            // JUGA simpan ke global knowledge!
            addToGlobalKnowledge(
                key = "project_$projectName",
                value = content.take(200),
                category = "project_$category",
                emotionalWeight = 0.8f
            )
            
            "✅ Project '$projectName' disimpan!"
        } catch (e: Exception) {
            "❌ Error: ${e.message}"
        }
    }
    
    /**
     * Search di semua project
     */
    suspend fun searchProjects(query: String): List<String> = withContext(Dispatchers.IO) {
        val results = mutableListOf<String>()
        try {
            val projectsDir = File(cacheDir, "projects")
            if (projectsDir.exists()) {
                projectsDir.listFiles()?.forEach { file ->
                    if (file.extension == "json") {
                        val content = file.readText()
                        if (content.lowercase().contains(query.lowercase())) {
                            val json = JSONObject(content)
                            results.add("📁 ${json.optString("title", file.nameWithoutExtension)}")
                        }
                    }
                }
            }
        } catch (e: Exception) { /* ignore */ }
        results
    }
    
    // ============================================
    // 3. FACES (1 FILE UNTUK SEMUA)
    // ============================================
    
    suspend fun saveFaceData(name: String, biodata: Map<String, String>): String = 
        withContext(Dispatchers.IO) {
        try {
            val existing = downloadFacesData()
            val faces = existing.getJSONArray("faces")
            
            // Cek apakah sudah ada
            var found = false
            for (i in 0 until faces.length()) {
                if (faces.getJSONObject(i).getString("name") == name) {
                    found = true
                    break
                }
            }
            
            if (!found) {
                faces.put(JSONObject().apply {
                    put("name", name)
                    put("registered_by", currentUserId)
                    put("timestamp", System.currentTimeMillis())
                    biodata.forEach { (k, v) -> put(k, v) }
                })
            }
            
            existing.put("total_faces", faces.length())
            existing.put("last_updated", dateTimeFormat.format(Date()))
            
            saveToFile("faces.json", existing.toString(2))
            uploadFile("faces.json", existing.toString(2))
            
            "✅ Face data untuk '$name' disimpan"
        } catch (e: Exception) {
            "❌ Error: ${e.message}"
        }
    }
    
    suspend fun searchFace(query: String): String? = withContext(Dispatchers.IO) {
        try {
            val faces = downloadFacesData()
            val arr = faces.getJSONArray("faces")
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                if (obj.toString().lowercase().contains(query.lowercase())) {
                    return@withContext obj.toString(2)
                }
            }
            null
        } catch (e: Exception) { null }
    }
    
    // ============================================
    // 4. CONVERSATIONS (1 FILE UNTUK SEMUA)
    // ============================================
    
    suspend fun saveConversation(role: String, content: String, emotion: String): String = 
        withContext(Dispatchers.IO) {
        try {
            val existing = downloadConversations()
            val conversations = existing.getJSONArray("conversations")
            
            conversations.put(JSONObject().apply {
                put("user", currentUserId)
                put("role", role)
                put("content", content)
                put("emotion", emotion)
                put("timestamp", System.currentTimeMillis())
            })
            
            existing.put("total_conversations", conversations.length())
            existing.put("last_updated", dateTimeFormat.format(Date()))
            
            saveToFile("conversations.json", existing.toString(2))
            uploadFile("conversations.json", existing.toString(2))
            
            "✅ Conversation saved"
        } catch (e: Exception) {
            "❌ Error: ${e.message}"
        }
    }
    
    // ============================================
    // HELPERS
    // ============================================
    
    private fun downloadGlobalKnowledge(): JSONObject {
        return downloadOrCreate("global_knowledge.json") {
            JSONObject().apply {
                put("facts", JSONArray())
                put("total_facts", 0)
                put("last_updated", dateTimeFormat.format(Date()))
                put("contributors", JSONArray())
            }
        }
    }
    
    private fun downloadFacesData(): JSONObject {
        return downloadOrCreate("faces.json") {
            JSONObject().apply {
                put("faces", JSONArray())
                put("total_faces", 0)
                put("last_updated", dateTimeFormat.format(Date()))
            }
        }
    }
    
    private fun downloadConversations(): JSONObject {
        return downloadOrCreate("conversations.json") {
            JSONObject().apply {
                put("conversations", JSONArray())
                put("total_conversations", 0)
                put("last_updated", dateTimeFormat.format(Date()))
            }
        }
    }
    
    private fun downloadOrCreate(fileName: String, default: () -> JSONObject): JSONObject {
        val file = File(cacheDir, fileName)
        
        // 1. Cek local cache dulu
        if (file.exists()) {
            try {
                return JSONObject(file.readText())
            } catch (e: Exception) { /* corrupt, download ulang */ }
        }
        
        // 2. Download dari GitHub
        val downloaded = downloadFile(fileName)
        if (downloaded != null) {
            try {
                file.writeText(downloaded)
                return JSONObject(downloaded)
            } catch (e: Exception) { /* invalid JSON */ }
        }
        
        // 3. Buat baru
        val new = default()
        file.writeText(new.toString(2))
        return new
    }
    
    private fun saveToFile(relativePath: String, content: String) {
        val file = File(cacheDir, relativePath)
        file.parentFile?.mkdirs()
        file.writeText(content)
    }
    
    private fun downloadFile(path: String): String? {
        if (!isConfigured) return null
        return try {
            val url = URL("https://raw.githubusercontent.com/$githubRepo/$githubBranch/$path")
            url.readText()
        } catch (e: Exception) { null }
    }
    
    private fun uploadFile(path: String, content: String): Boolean {
        if (!isConfigured) return false
        return try {
            val url = URL("https://api.github.com/repos/$githubRepo/contents/$path")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "PUT"
            conn.setRequestProperty("Authorization", "token $githubToken")
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true
            
            // Get SHA if file exists
            val sha = getFileSha(path)
            
            val payload = JSONObject().apply {
                put("message", "Auto-sync: $path")
                put("content", android.util.Base64.encodeToString(
                    content.toByteArray(), android.util.Base64.DEFAULT
                ))
                put("branch", githubBranch)
                if (sha != null) put("sha", sha)
            }
            
            conn.outputStream.write(payload.toString().toByteArray())
            conn.responseCode in 200..201
        } catch (e: Exception) { false }
    }
    
    private fun getFileSha(path: String): String? {
        if (!isConfigured) return null
        return try {
            val url = URL("https://api.github.com/repos/$githubRepo/contents/$path")
            val conn = url.openConnection() as HttpURLConnection
            conn.setRequestProperty("Authorization", "token $githubToken")
            if (conn.responseCode == 200) {
                val response = conn.inputStream.bufferedReader().readText()
                JSONObject(response).optString("sha", null)
            } else null
        } catch (e: Exception) { null }
    }
    
    fun getCacheDir(): File = cacheDir
}

// ============================================
// PROJECT RESULT
// ============================================
sealed class ProjectResult {
    data class AlreadyExists(
        val fileName: String,
        val content: String,
        val message: String
    ) : ProjectResult()
    
    data class NotFound(
        val fileName: String,
        val projectPath: String,
        val message: String
    ) : ProjectResult()
    
    data class Error(val message: String) : ProjectResult()
}