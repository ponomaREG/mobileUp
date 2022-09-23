private object PresentationsVersions {

    const val APP_COMPAT = "1.5.1"
    const val MATERIAL = "1.6.1"
    const val CONSTRAINT = "2.1.4"
    const val SWIPE_REFRESH = "1.1.0"
    const val GLIDE = "4.13.2"

    const val LIFECYCLE = "2.5.1"

    const val NAVIGATION = "2.5.2"
}


object PresentationsDependency {

    const val PLUGIN_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"

    const val CLASSPATH_SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${PresentationsVersions.NAVIGATION}"

    const val APP_COMPAT = "androidx.appcompat:appcompat:${PresentationsVersions.APP_COMPAT}"
    const val MATERIAL = "com.google.android.material:material:${PresentationsVersions.MATERIAL}"
    const val CONSTRAINT = "androidx.constraintlayout:constraintlayout:${PresentationsVersions.CONSTRAINT}"
    const val SWIPE_REFRESH = "androidx.swiperefreshlayout:swiperefreshlayout:${PresentationsVersions.SWIPE_REFRESH}"

    // Navigation
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${PresentationsVersions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${PresentationsVersions.NAVIGATION}"

    // Lifecycle
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${PresentationsVersions.LIFECYCLE}"

    const val GLIDE = "com.github.bumptech.glide:glide:${PresentationsVersions.GLIDE}"
    const val KAPT_GLIDE = "com.github.bumptech.glide:compiler:${PresentationsVersions.GLIDE}"
}
