private object CoreVersions {
    const val CORE_KTX = "1.7.0"

    const val HILT = "2.38.1"
    const val KOTLIN = "1.6.4"
}

object PublicCoreVersions {
    const val APPLICATION_ANDROID = "7.2.2"
    const val KOTLIN_ANDROID = "1.6.10"
}

object CoreDependency {

    const val PLUGIN_APPLICATION = "com.android.application"
    const val PLUGIN_KOTLIN = "org.jetbrains.kotlin.android"
    const val PLUGIN_HILT = "dagger.hilt.android.plugin"
    const val PLUGIN_KAPT = "kotlin-kapt"
    const val PLUGIN_PARCELIZE = "kotlin-parcelize"
    const val PLUGIN_ANDROID_LIBRARY = "com.android.library"

    const val CLASSPATH_HILT = "com.google.dagger:hilt-android-gradle-plugin:${CoreVersions.HILT}"


    const val CORE_KTX = "androidx.core:core-ktx:${CoreVersions.CORE_KTX}"

    const val COROUTINE_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${CoreVersions.KOTLIN}"
    const val COROUTINE_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${CoreVersions.KOTLIN}"

    const val HILT = "com.google.dagger:hilt-android:${CoreVersions.HILT}"
    const val KAPT_HILT = "com.google.dagger:hilt-android-compiler:${CoreVersions.HILT}"
}
