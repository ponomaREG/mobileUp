buildscript {
    dependencies {
        classpath(CoreDependency.CLASSPATH_HILT)
        classpath(PresentationsDependency.CLASSPATH_SAFE_ARGS)
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(CoreDependency.PLUGIN_APPLICATION) version PublicCoreVersions.APPLICATION_ANDROID apply false
    id(CoreDependency.PLUGIN_ANDROID_LIBRARY) version PublicCoreVersions.APPLICATION_ANDROID apply false
    id(CoreDependency.PLUGIN_KOTLIN) version PublicCoreVersions.KOTLIN_ANDROID apply false

}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}