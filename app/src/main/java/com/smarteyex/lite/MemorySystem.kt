package com.smarteyex.lite

import java.util.*

class MemorySystem {
    
    // ========== MEMORY STORAGE ==========
    private val shortTermMemory = mutableListOf<Memory>()      // 1 bulan
    private val longTermMemory = mutableListOf<Memory>()       // permanen
    private val episodicMemory = mutableListOf<Memory>()       // kejadian spesifik
    private val emotionalMemory = mutableListOf<Memory>()      // emosional kuat
    private val workingMemory = mutableListOf<Memory>()        // konteks saat ini (maks 10)
    
    private val SHORT_TERM_MAX = 200
    private val WORKING_MEMORY_MAX = 10
    private val SHORT_TERM_DURATION = 30L * 24 * 60 * 60 * 1000 // 30 hari (1 bulan)
    private val CONSOLIDATION_THRESHOLD = 5 // Minimal 5x recall untuk konsolidasi
    
    private var lastConsolidation = System.currentTimeMillis()
    private var lastForget = System.currentTimeMillis()
    
    /**
     * SIMPAN MEMORI BARU
     */
    fun remember(
        content: String,
        type: MemoryType = MemoryType.SHORT_TERM,
        emotionalWeight: Float = 0.3f,
        tags: List<String> = emptyList()
    ): Memory {
        val memory = Memory(
            id = UUID.randomUUID().toString(),
            type = type,
            content = content,
            emotionalWeight = emotionalWeight.coerceIn(0f, 1f),
            tags = tags,
            timestamp = System.currentTimeMillis(),
            lastRecalled = System.currentTimeMillis(),
            recallCount = 0
        )
        
        when {
            emotionalWeight >= 0.7f -> {
                memory.copy(type = MemoryType.EMOTIONAL)
                emotionalMemory.add(0, memory)
                longTermMemory.add(0, memory) // Emosional langsung long-term
            }
            type == MemoryType.EPISODIC -> episodicMemory.add(0, memory)
            type == MemoryType.LONG_TERM -> longTermMemory.add(0, memory)
            else -> {
                shortTermMemory.add(0, memory)
                workingMemory.add(0, memory)
                if (workingMemory.size > WORKING_MEMORY_MAX) {
                    workingMemory.removeAt(workingMemory.lastIndex)
                }
            }
        }
        
        // Trigger konsolidasi & lupa
        autoConsolidate()
        autoForget()
        
        return memory
    }
    
    /**
     * RECALL - Cari memori berdasarkan query
     */
    fun recall(query: String): List<Memory> {
        val lower = query.lowercase()
        val results = mutableListOf<Memory>()
        
        // Cari di semua storage
        val allMemories = (longTermMemory + shortTermMemory + emotionalMemory + episodicMemory)
            .distinctBy { it.id }
        
        for (memory in allMemories) {
            val matchScore = calculateMatchScore(memory, lower)
            if (matchScore > 0f) {
                // Update recall stats
                val updated = memory.copy(
                    lastRecalled = System.currentTimeMillis(),
                    recallCount = memory.recallCount + 1,
                    emotionalWeight = (memory.emotionalWeight + 0.05f).coerceAtMost(1f)
                )
                results.add(updated)
                strengthenMemory(updated.id)
            }
        }
        
        // Urutkan berdasarkan relevansi
        return results.sortedByDescending { 
            calculateMatchScore(it, lower) + (it.emotionalWeight * 0.5f) + (it.recallCount * 0.1f)
        }
    }
    
    /**
     * RECALL BY CONTEXT - Cari memori berdasarkan tag konteks
     */
    fun recallByContext(contextTags: List<String>): List<Memory> {
        val allMemories = (longTermMemory + shortTermMemory + emotionalMemory + episodicMemory)
            .distinctBy { it.id }
        
        return allMemories.filter { memory ->
            memory.tags.any { tag -> contextTags.any { ctx -> tag.contains(ctx, true) || ctx.contains(tag, true) } }
        }.sortedByDescending { it.emotionalWeight + (it.recallCount * 0.1f) }
    }
    
    /**
     * RECALL RECENT - Memori terbaru
     */
    fun recallRecent(count: Int = 10): List<Memory> {
        return (shortTermMemory + longTermMemory)
            .distinctBy { it.id }
            .sortedByDescending { it.timestamp }
            .take(count)
    }
    
    /**
     * RECALL EMOTIONAL - Memori emosional kuat
     */
    fun recallEmotional(minWeight: Float = 0.7f): List<Memory> {
        return emotionalMemory.filter { it.emotionalWeight >= minWeight }
            .sortedByDescending { it.emotionalWeight }
    }
    
    /**
     * KONSOLIDASI - Short-term → Long-term (otomatis)
     */
    private fun autoConsolidate() {
        val now = System.currentTimeMillis()
        if (now - lastConsolidation < 3600000) return // Minimal 1 jam sekali
        lastConsolidation = now
        
        val toConsolidate = shortTermMemory.filter { memory ->
            memory.recallCount >= CONSOLIDATION_THRESHOLD ||
            memory.emotionalWeight >= 0.6f ||
            memory.tags.any { it in listOf("penting", "bahaya", "keselamatan", "preferensi") }
        }
        
        for (memory in toConsolidate) {
            if (longTermMemory.none { it.id == memory.id }) {
                longTermMemory.add(0, memory.copy(type = MemoryType.LONG_TERM))
            }
        }
    }
    
    /**
     * LUPA - Hapus short-term yang lemah (otomatis)
     */
    private fun autoForget() {
        val now = System.currentTimeMillis()
        if (now - lastForget < 21600000) return // Minimal 6 jam sekali
        lastForget = now
        
        // Hapus short-term yang sudah lewat 1 bulan DAN jarang di-recall
        shortTermMemory.removeAll { memory ->
            val age = now - memory.timestamp
            age > SHORT_TERM_DURATION && memory.recallCount < 2 && memory.emotionalWeight < 0.5f
        }
        
        // Batasi jumlah
        if (shortTermMemory.size > SHORT_TERM_MAX) {
            val toRemove = shortTermMemory
                .sortedBy { it.emotionalWeight + (it.recallCount * 0.2f) }
                .take(shortTermMemory.size - SHORT_TERM_MAX)
            shortTermMemory.removeAll(toRemove)
        }
    }
    
    /**
     * PERKUAT MEMORI
     */
    fun strengthenMemory(id: String) {
        updateMemoryById(id) { it.copy(
            recallCount = it.recallCount + 1,
            emotionalWeight = (it.emotionalWeight + 0.02f).coerceAtMost(1f),
            lastRecalled = System.currentTimeMillis()
        )}
    }
    
    /**
     * HUBUNGKAN DUA MEMORI
     */
    fun associateMemories(id1: String, id2: String) {
        updateMemoryById(id1) { it.copy(relatedMemories = it.relatedMemories + id2) }
        updateMemoryById(id2) { it.copy(relatedMemories = it.relatedMemories + id1) }
    }
    
    /**
     * FLASHBACK - Trigger memori lama berdasarkan kata/kondisi
     */
    fun flashback(trigger: String): List<Memory> {
        val lower = trigger.lowercase()
        return (longTermMemory + emotionalMemory + episodicMemory)
            .distinctBy { it.id }
            .filter { memory ->
                memory.content.lowercase().contains(lower) ||
                memory.tags.any { it.lowercase().contains(lower) }
            }
            .sortedByDescending { it.emotionalWeight }
            .take(5)
    }
    
    /**
     * RINGKASAN HARI INI
     */
    fun getTodaysSummary(): String {
        val today = System.currentTimeMillis() - 86400000
        val todayMemories = (shortTermMemory + longTermMemory + episodicMemory)
            .distinctBy { it.id }
            .filter { it.timestamp > today }
            .sortedByDescending { it.timestamp }
        
        if (todayMemories.isEmpty()) return "Hari ini belum banyak yang terjadi."
        
        val activities = todayMemories.filter { it.type == MemoryType.EPISODIC }
        val facts = todayMemories.filter { it.type == MemoryType.FACT }
        val emotions = todayMemories.filter { it.emotionalWeight >= 0.6f }
        
        return buildString {
            append("📅 RINGKASAN HARI INI\n")
            append("━━━━━━━━━━━━━━━━━━\n")
            
            if (activities.isNotEmpty()) {
                append("\n📌 KEJADIAN:\n")
                activities.take(5).forEach { append("  • ${it.content}\n") }
            }
            
            if (facts.isNotEmpty()) {
                append("\n🧠 FAKTA BARU:\n")
                facts.take(5).forEach { append("  • ${it.content}\n") }
            }
            
            if (emotions.isNotEmpty()) {
                append("\n❤️ MOMEN BERKESAN:\n")
                emotions.take(3).forEach { append("  • ${it.content}\n") }
            }
            
            append("\n━━━━━━━━━━━━━━━━━━")
            append("\nTotal: ${todayMemories.size} memori baru")
        }
    }
    
    /**
     * DAPATKAN PREFERENSI BUNG
     */
    fun getPreferences(): Map<String, String> {
        val prefs = mutableMapOf<String, String>()
        val allMemories = longTermMemory.filter { it.tags.contains("preferensi") || it.tags.contains("kesukaan") }
        for (memory in allMemories) {
            val parts = memory.content.split(":", "suka", "tidak_suka").map { it.trim() }
            if (parts.size >= 2) {
                prefs[parts[0]] = parts[1]
            }
        }
        return prefs
    }
    
    /**
     * CEK APAKAH SUDAH TAHU
     */
    fun alreadyKnows(query: String): Memory? {
        val lower = query.lowercase()
        return (longTermMemory + shortTermMemory)
            .distinctBy { it.id }
            .find { it.content.lowercase().contains(lower) || lower.contains(it.content.lowercase()) }
    }
    
    // ========== PRIVATE HELPERS ==========
    
    private fun calculateMatchScore(memory: Memory, query: String): Float {
        var score = 0f
        
        // Konten match
        if (memory.content.lowercase().contains(query)) score += 1f
        if (query.contains(memory.content.lowercase())) score += 0.8f
        
        // Tag match
        memory.tags.forEach { tag ->
            if (query.contains(tag.lowercase())) score += 0.5f
            if (tag.lowercase().contains(query)) score += 0.4f
        }
        
        // Related memories (tidak langsung)
        // Sudah include dalam recall count & emotional weight
        
        return score
    }
    
    private fun updateMemoryById(id: String, transform: (Memory) -> Memory) {
        val lists = listOf(shortTermMemory, longTermMemory, emotionalMemory, episodicMemory, workingMemory)
        for (list in lists) {
            val index = list.indexOfFirst { it.id == id }
            if (index >= 0) {
                list[index] = transform(list[index])
            }
        }
    }
    
    // ========== STATISTIK ==========
    
    fun getMemoryStats(): String {
        return """
            📊 STATISTIK MEMORI
            Short-Term: ${shortTermMemory.size} (1 bulan)
            Long-Term: ${longTermMemory.size}
            Episodic: ${episodicMemory.size}
            Emotional: ${emotionalMemory.size}
            Working: ${workingMemory.size}
            Total: ${getTotalMemories()}
        """.trimIndent()
    }
    
    fun getTotalMemories(): Int {
        return (shortTermMemory + longTermMemory + episodicMemory + emotionalMemory)
            .distinctBy { it.id }.size
    }
    
    fun getMemoryByType(type: MemoryType): List<Memory> {
        return when (type) {
            MemoryType.SHORT_TERM -> shortTermMemory.toList()
            MemoryType.LONG_TERM -> longTermMemory.toList()
            MemoryType.EPISODIC -> episodicMemory.toList()
            MemoryType.EMOTIONAL -> emotionalMemory.toList()
            else -> emptyList()
        }
    }
}