# ============================================
# SMARTEYEX LITE — PROGUARD RULES
# ============================================

# === Keep semua class SmartEyeX ===
-keep class com.smarteyex.lite.** { *; }
-keepclassmembers class com.smarteyex.lite.** { *; }

# === Keep data classes (Gson/JSON serialization) ===
-keep class com.smarteyex.lite.data.model.** { *; }
-keepclassmembers class com.smarteyex.lite.data.model.** { *; }

# === Keep Room Database Entities ===
-keep class com.smarteyex.lite.LearnedFactEntity { *; }
-keep class com.smarteyex.lite.KnownObjectEntity { *; }
-keep class com.smarteyex.lite.ConversationEntity { *; }
-keep class com.smarteyex.lite.VoiceProfileEntity { *; }
-keep class com.smarteyex.lite.UserPreferenceEntity { *; }

# ============================================
# ANDROIDX
# ============================================
-keep class androidx.** { *; }
-keep interface androidx.** { *; }
-keep class androidx.camera.** { *; }
-keep interface androidx.camera.** { *; }
-keep class androidx.room.** { *; }
-keep interface androidx.room.** { *; }
-dontwarn androidx.**

# ============================================
# GOOGLE MATERIAL & ML KIT
# ============================================
-keep class com.google.android.material.** { *; }
-keep interface com.google.android.material.** { *; }
-keep class com.google.mlkit.** { *; }
-keep interface com.google.mlkit.** { *; }
-dontwarn com.google.**

# ============================================
# MEDIAPIPE
# ============================================
-keep class com.google.mediapipe.** { *; }
-keep interface com.google.mediapipe.** { *; }
-keep class com.google.mediapipe.tasks.** { *; }
-keep class com.google.mediapipe.framework.** { *; }
-dontwarn com.google.mediapipe.**

# ============================================
# OPENCV
# ============================================
-keep class org.opencv.** { *; }
-keep interface org.opencv.** { *; }
-keep class org.opencv.core.** { *; }
-keep class org.opencv.imgproc.** { *; }
-keep class org.opencv.android.** { *; }
-dontwarn org.opencv.**

# ============================================
# OKHTTP & RETROFIT
# ============================================
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keep class okio.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-dontwarn retrofit2.**

# ============================================
# GSON
# ============================================
-keep class com.google.gson.** { *; }
-keep interface com.google.gson.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn com.google.gson.**

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# ============================================
# KOTLIN
# ============================================
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }
-keep interface kotlin.** { *; }
-dontwarn kotlin.**
-dontwarn kotlinx.**

# Kotlin Coroutines
-keep class kotlinx.coroutines.** { *; }
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Kotlin Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.smarteyex.lite.**$$serializer { *; }
-keepclassmembers class com.smarteyex.lite.** {
    *** Companion;
}
-keepclasseswithmembers class com.smarteyex.lite.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# ============================================
# GLIDE
# ============================================
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
    <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
    *** rewind();
}
-dontwarn com.bumptech.glide.**

# ============================================
# LOTTIE ANIMATION
# ============================================
-keep class com.airbnb.lottie.** { *; }
-dontwarn com.airbnb.lottie.**

# ============================================
# COROUTINES
# ============================================
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

# ============================================
# ROOM DATABASE
# ============================================
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keepclassmembers @androidx.room.Entity class * { *; }
-dontwarn androidx.room.paging.**

# ============================================
# CAMERAX
# ============================================
-keep class androidx.camera.core.** { *; }
-keep class androidx.camera.camera2.** { *; }
-keep class androidx.camera.lifecycle.** { *; }
-keep class androidx.camera.view.** { *; }
-dontwarn androidx.camera.**

# ============================================
# GENERAL
# ============================================
# Keep source file names & line numbers for debugging
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Keep annotations
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses

# Remove logging in release
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
    public static int i(...);
}

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep parcelable
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep R class
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Suppress warnings
-dontwarn java.lang.invoke.*
-dontwarn javax.annotation.**
-dontwarn org.codehaus.mojo.animal_sniffer.**