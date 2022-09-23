plugins {
    id(CoreDependency.PLUGIN_APPLICATION)
    id(CoreDependency.PLUGIN_KOTLIN)
    id(CoreDependency.PLUGIN_HILT)
    id(CoreDependency.PLUGIN_KAPT)
    id(CoreDependency.PLUGIN_PARCELIZE)
    id(PresentationsDependency.PLUGIN_SAFE_ARGS)
    id(NetworkDependency.PLUGIN_SERIALIZATION) version PublicNetworkVersions.SERIALIZATION
}

android {
    compileSdk = ApplicationConfig.COMPILE_SDK

    defaultConfig {
        applicationId = ApplicationConfig.APPLICATION_ID
        minSdk = ApplicationConfig.MIN_SDK_VERSION
        targetSdk = ApplicationConfig.TARGET_SDK_VERSION
        versionCode = ApplicationConfig.VERSION_CODE
        versionName = ApplicationConfig.VERSION_NAME

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(CoreDependency.CORE_KTX)
    implementation(CoreDependency.COROUTINE_ANDROID)
    implementation(CoreDependency.COROUTINE_CORE)
    implementation(CoreDependency.HILT)
    kapt(CoreDependency.KAPT_HILT)

    implementation(PresentationsDependency.APP_COMPAT)
    implementation(PresentationsDependency.MATERIAL)
    implementation(PresentationsDependency.CONSTRAINT)
    implementation(PresentationsDependency.LIFECYCLE_VIEWMODEL)
    implementation(PresentationsDependency.NAVIGATION_FRAGMENT)
    implementation(PresentationsDependency.NAVIGATION_UI)
    implementation(PresentationsDependency.GLIDE)
    implementation(PresentationsDependency.SWIPE_REFRESH)
    kapt(PresentationsDependency.KAPT_GLIDE)

    implementation(NetworkDependency.RETROFIT)
    implementation(NetworkDependency.RETROFIT_SERIALIZATION)
    implementation(NetworkDependency.SERIALIZATION)
    implementation(NetworkDependency.LOGGING_INTERCEPTOR)
}