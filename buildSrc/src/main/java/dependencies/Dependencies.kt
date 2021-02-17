package dependencies

object Dependencies {

    // GRADLE PROJECT
    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    val material_design = "com.google.android.material:material:${Versions.material_design}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val navigation_component_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_component}"
    val navigation_component_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_component}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val coroutine_kotlin_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    val coroutine_play_services = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutine_play_services}"
    val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    val coil = "io.coil-kt:coil:${Versions.coil}"
    val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hilt_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewmodel}"
    val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
    val preferences = "androidx.preference:preference-ktx:${Versions.preferences}"
}