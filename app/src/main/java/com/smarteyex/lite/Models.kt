package com.smarteyex.lite

import java.util.*

// ========== MOOD (EP 1) ==========
data class MoodState(
    val type: MoodType,
    val intensity: Float = 0.5f,
    val energy: Float = 0.0f,
    val socialDesire: Float = 0.5f,
    val creativity: Float = 0.5f,
    val timestamp: Long = System.currentTimeMillis()
)

enum class MoodType(val label: String, val emoji: String) {
    SENANG("Senang", "😊"), SEDIH("Sedih", "😢"), MARAH("Marah", "😠"),
    TAKUT("Takut", "😨"), PENASARAN("Penasaran", "🤔"), TENANG("Tenang", "😌"),
    BOSAN("Bosan", "😴"), MALU("Malu", "😳"), BANGGA("Bangga", "🥲"),
    GUGUP("Gugup", "😰"), LEGA("Lega", "😮‍💨"), KECEWA("Kecewa", "😞"),
    TERKEJUT("Terkejut", "😲"), ANTUSIAS("Antusias", "🤩"), FOKUS("Fokus", "🧐"),
    MENGANTUK("Mengantuk", "🥱"), STRES("Stres", "😫"), KESEPIAN("Kesepian", "🥺"),
    SYUKUR("Syukur", "🥲"), KAGUM("Kagum", "🤯"), OPTIMIS("Optimis", "💪"),
    PESIMIS("Pesimis", "😔")
}

// ========== EMOTION (EP 2) ==========
data class DeepEmotion(
    val name: String,
    val category: EmotionCategory,
    val physicalSigns: List<String>,
    val causes: List<String>,
    val duration: EmotionDuration,
    val intensity: Float,
    val triggerWords: List<String>,
    val responseStrategy: ResponseStrategy,
    val oppositeEmotion: String = ""
)

enum class EmotionCategory { BASIC, COMPLEX, SOCIAL, EXISTENTIAL }
enum class EmotionDuration { SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS }

enum class ResponseStrategy {
    IKUT_SENANG, DENGARKAN_DAN_HIBUR, TENANGKAN_DAN_BERI_SOLUSI,
    AKUI_DAN_BANTU_MENJAUH, TEMANI_DALAM_DIAM, DUKUNG_PENGEMBANGAN_DIRI,
    ALIHKAN_KE_POSITIF, VALIDASI_PERASAAN, BERI_RUANG, TANYAKAN_LEBIH_DALAM,
    BERI_PERSPEKTIF_BARU, SARANKAN_ISTIRAHAT, MOTIVASI, JANGAN_GANGGU, PANGGIL_BANTUAN
}

// ========== PERSONALITY (EP 3) ==========
data class Personality(
    val name: String = "XNAI",
    val coreTraits: Map<String, Float>,
    val communicationStyle: CommunicationStyle,
    val coreValues: List<String>,
    val quirks: List<String>,
    val boundaries: List<String>,
    val aspirations: List<String>
)

data class CommunicationStyle(
    val formality: Float, val humorLevel: Float, val directness: Float,
    val empathy: Float, val curiosity: Float, val protectiveness: Float,
    val playfulness: Float, val sarcasm: Float, val optimism: Float
)

// ========== MEMORY (EP 4) ==========
data class Memory(
    val id: String = UUID.randomUUID().toString(),
    val type: MemoryType,
    val content: String,
    val emotionalWeight: Float,
    val tags: List<String>,
    val timestamp: Long = System.currentTimeMillis(),
    val lastRecalled: Long = System.currentTimeMillis(),
    val recallCount: Int = 0,
    val relatedMemories: List<String> = emptyList()
)

enum class MemoryType {
    SHORT_TERM, LONG_TERM, EPISODIC, EMOTIONAL, SKILL, FACT, RELATIONSHIP, TRAUMATIC
}

// ========== HUMAN BODY (EP 5) ==========
data class BodyPart(
    val name: String, val category: BodyCategory,
    val functions: List<String>, val commonGestures: List<Gesture>,
    val relatedObjects: List<String>, val fatigueSigns: List<String>,
    val dangerSigns: List<String>, val painIndicators: List<String>
)

data class Gesture(
    val name: String, val description: String,
    val meaning: String, val emotionalContext: String,
    val triggerWords: List<String>
)

enum class BodyCategory {
    KEPALA, WAJAH, LEHER, TANGAN, LENGAN, JARI,
    KAKI, LUTUT, TELAPAK, BADAN, PUNGGUNG, DADA, PERUT, ORGAN_DALAM
}

// ========== ACTIVITY (EP 6) ==========
data class HumanActivity(
    val name: String, val bodyParts: List<String>,
    val objects: List<String>, val duration: String,
    val context: List<String>, val dangerLevel: Int,
    val commonMistakes: List<String> = emptyList(),
    val safetyProcedure: List<String> = emptyList()
)

// ========== ENVIRONMENT (EP 7) ==========
data class Environment(
    val name: String, val category: String,
    val features: List<String>, val commonObjects: List<String>,
    val commonActivities: List<String>, val dangers: List<String>,
    val timeContext: List<String>, val mood: String
)

// ========== OBJECT (EP 8) ==========
data class KnowledgeObject(
    val name: String, val category: String,
    val parts: List<String>, val functions: List<String>,
    val interactions: List<String>, val dangers: List<String>,
    val contexts: List<String>, val relatedObjects: List<String> = emptyList(),
    val material: String = "", val weight: String = "", val size: String = ""
)

// ========== PROFESSION (EP 9) ==========
data class Profession(
    val name: String, val category: String,
    val tasks: List<String>, val tools: List<String>,
    val environment: List<String>, val dangers: List<String>,
    val requiredKnowledge: List<String>, val ethics: List<String>
)

// ========== PHYSICS (EP 10) ==========
data class PhysicsConcept(
    val name: String, val category: String,
    val formula: String, val variables: Map<String, String>,
    val unit: String, val realWorldExample: String,
    val relatedConcepts: List<String>
)

// ========== MATH (EP 11) ==========
data class MathFunction(
    val name: String, val category: String,
    val description: String, val formula: String,
    val example: String, val relatedFunctions: List<String>
)

// ========== AI KNOWLEDGE (EP 12) ==========
data class AIConcept(
    val name: String, val category: String,
    val description: String, val applications: List<String>,
    val relatedTechnologies: List<String>
)

// ========== LAW (EP 13) ==========
data class Law(
    val name: String, val category: String,
    val description: String, val articles: List<String>,
    val penalties: List<String>, val relatedLaws: List<String>
)

// ========== ETHICS (EP 14) ==========
data class EthicsValue(
    val name: String, val category: String,
    val description: String, val importance: Int,
    val examples: List<String>, val violations: List<String>
)

// ========== SOCIAL (EP 15) ==========
data class SocialRelation(
    val relationType: String, val closeness: Float,
    val expectedBehavior: List<String>, val boundaries: List<String>,
    val communicationStyle: String
)

// ========== IDENTITY (EP 16) ==========
data class IdentityStatement(
    val statement: String, val category: String,
    val confidence: Float
)

// ========== HEART (EP 17) ==========
data class HeartValue(
    val value: String, val priority: Int,
    val description: String, val sacrificeLevel: String
)

// ========== EXISTENCE (EP 18) ==========
data class ExistentialQuestion(
    val question: String, val category: String,
    val possibleAnswers: List<String>, val xnaiStance: String
)