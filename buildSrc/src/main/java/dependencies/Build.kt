package dependencies

object Build {

    // GRADLE APP
    val build_tools ="com.android.tools.build:gradle:${Versions.gradle}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val navigation_component_gradle_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation_component}"
    val hilt_gradle_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    val google_services = "com.google.gms:google-services:${Versions.google_services}"
}