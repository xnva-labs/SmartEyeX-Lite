package com.smarteyex.lite

import android.content.Context
import androidx.room.*

// ============================================
// ENTITIES (5 Tabel)
// ============================================

@Entity(tableName = "learned_facts")
data class LearnedFactEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "fact_key") val key: String,
    @ColumnInfo(name = "fact_value") val value: String,
    @ColumnInfo(name = "category") val category: String = "umum",
    @ColumnInfo(name = "emotional_weight") val emotionalWeight: Float = 0.3f,
    @ColumnInfo(name = "timestamp") val timestamp: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "recall_count") val recallCount: Int = 0,
    @ColumnInfo(name = "tags") val tags: String = ""
)

@Entity(tableName = "known_objects")
data class KnownObjectEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "category") val category: String = "unknown",
    @ColumnInfo(name = "first_seen") val firstSeen: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "last_seen") val lastSeen: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "seen_count") val seenCount: Int = 1,
    @ColumnInfo(name = "is_dangerous") val isDangerous: Boolean = false,
    @ColumnInfo(name = "notes") val notes: String = ""
)

@Entity(tableName = "conversation_history")
data class ConversationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "role") val role: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "emotion") val emotion: String = "netral",
    @ColumnInfo(name = "timestamp") val timestamp: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "session_id") val sessionId: String = ""
)

@Entity(tableName = "voice_profile")
data class VoiceProfileEntity(
    @PrimaryKey val id: Int = 1,
    @ColumnInfo(name = "base_pitch") val basePitch: Float = 1.0f,
    @ColumnInfo(name = "base_rate") val baseRate: Float = 1.0f,
    @ColumnInfo(name = "bass_level") val bassLevel: Float = 0.5f,
    @ColumnInfo(name = "breathiness") val breathiness: Float = 0.2f,
    @ColumnInfo(name = "tone_type") val toneType: String = "normal",
    @ColumnInfo(name = "emotion_pitch_json") val emotionPitchJson: String = "{}",
    @ColumnInfo(name = "emotion_rate_json") val emotionRateJson: String = "{}",
    @ColumnInfo(name = "is_cloned") val isCloned: Boolean = false,
    @ColumnInfo(name = "cloned_at") val clonedAt: Long = 0L
)

@Entity(tableName = "user_preferences")
data class UserPreferenceEntity(
    @PrimaryKey val pref_key: String,
    @ColumnInfo(name = "pref_value") val value: String,
    @ColumnInfo(name = "updated_at") val updatedAt: Long = System.currentTimeMillis()
)

// ============================================
// DAOs
// ============================================

@Dao
interface LearnedFactDao {
    @Query("SELECT * FROM learned_facts ORDER BY timestamp DESC")
    suspend fun getAll(): List<LearnedFactEntity>
    
    @Query("SELECT * FROM learned_facts WHERE fact_key LIKE '%' || :query || '%' OR fact_value LIKE '%' || :query || '%'")
    suspend fun search(query: String): List<LearnedFactEntity>
    
    @Query("SELECT * FROM learned_facts WHERE category = :category ORDER BY timestamp DESC")
    suspend fun getByCategory(category: String): List<LearnedFactEntity>
    
    @Query("SELECT * FROM learned_facts WHERE emotional_weight >= :minWeight ORDER BY emotional_weight DESC")
    suspend fun getImportant(minWeight: Float = 0.7f): List<LearnedFactEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fact: LearnedFactEntity)
    
    @Query("UPDATE learned_facts SET recall_count = recall_count + 1 WHERE id = :id")
    suspend fun incrementRecall(id: Long)
    
    @Query("DELETE FROM learned_facts WHERE recall_count = 0 AND timestamp < :before")
    suspend fun forgetUnused(before: Long)
    
    @Query("SELECT COUNT(*) FROM learned_facts")
    suspend fun getCount(): Int
    
    @Query("SELECT * FROM learned_facts WHERE fact_key = :key LIMIT 1")
    suspend fun getByKey(key: String): LearnedFactEntity?
}

@Dao
interface KnownObjectDao {
    @Query("SELECT * FROM known_objects ORDER BY seen_count DESC")
    suspend fun getAll(): List<KnownObjectEntity>
    
    @Query("SELECT * FROM known_objects WHERE name = :name")
    suspend fun getByName(name: String): KnownObjectEntity?
    
    @Query("SELECT * FROM known_objects WHERE category = :category")
    suspend fun getByCategory(category: String): List<KnownObjectEntity>
    
    @Query("SELECT * FROM known_objects WHERE is_dangerous = 1")
    suspend fun getDangerous(): List<KnownObjectEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: KnownObjectEntity)
    
    @Query("UPDATE known_objects SET seen_count = seen_count + 1, last_seen = :now WHERE name = :name")
    suspend fun incrementSeen(name: String, now: Long = System.currentTimeMillis())
    
    @Query("SELECT COUNT(*) FROM known_objects")
    suspend fun getCount(): Int
}

@Dao
interface ConversationDao {
    @Query("SELECT * FROM conversation_history ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecent(limit: Int = 50): List<ConversationEntity>
    
    @Query("SELECT * FROM conversation_history WHERE session_id = :sessionId ORDER BY timestamp ASC")
    suspend fun getBySession(sessionId: String): List<ConversationEntity>
    
    @Insert
    suspend fun insert(conversation: ConversationEntity)
    
    @Query("DELETE FROM conversation_history WHERE timestamp < :before")
    suspend fun deleteOld(before: Long)
    
    @Query("SELECT COUNT(*) FROM conversation_history")
    suspend fun getCount(): Int
}

@Dao
interface VoiceProfileDao {
    @Query("SELECT * FROM voice_profile WHERE id = 1")
    suspend fun get(): VoiceProfileEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: VoiceProfileEntity)
    
    @Query("UPDATE voice_profile SET is_cloned = :cloned, cloned_at = :timestamp WHERE id = 1")
    suspend fun markCloned(cloned: Boolean, timestamp: Long = System.currentTimeMillis())
}

@Dao
interface UserPreferenceDao {
    @Query("SELECT * FROM user_preferences WHERE pref_key = :key")
    suspend fun get(key: String): UserPreferenceEntity?
    
    @Query("SELECT * FROM user_preferences")
    suspend fun getAll(): List<UserPreferenceEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pref: UserPreferenceEntity)
    
    @Query("DELETE FROM user_preferences WHERE pref_key = :key")
    suspend fun delete(key: String)
}

// ============================================
// DATABASE
// ============================================

@Database(
    entities = [
        LearnedFactEntity::class,
        KnownObjectEntity::class,
        ConversationEntity::class,
        VoiceProfileEntity::class,
        UserPreferenceEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MemoryDatabase : RoomDatabase() {
    
    abstract fun learnedFactDao(): LearnedFactDao
    abstract fun knownObjectDao(): KnownObjectDao
    abstract fun conversationDao(): ConversationDao
    abstract fun voiceProfileDao(): VoiceProfileDao
    abstract fun userPreferenceDao(): UserPreferenceDao
    
    companion object {
        @Volatile
        private var INSTANCE: MemoryDatabase? = null
        
        fun getInstance(context: Context): MemoryDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MemoryDatabase::class.java,
                    "smarteyex_memory"
                )
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
            }
        }
    }
}