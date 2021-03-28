package dependencies

object AndroidTestDependencies {

    // GRADLE PROJECT
    val instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"
    val junit = "junit:junit:${Versions.junit}"
    val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test}"
    val androidx_arch_core = "androidx.arch.core:core-testing:${Versions.androidx_arch_core}"
    val google_truth = "com.google.truth:truth:${Versions.google_truth}"
    val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Versions.mock_web_server}"
    val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    val io_mockk = "io.mockk:mockk:${Versions.io_mockk}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    val androidx_test_ext = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ext}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    val androidx_test_core = "androidx.test:core-ktx:${Versions.androidx_test_core}"
}