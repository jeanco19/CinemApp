package dependencies

object AndroidTestDependencies {

    // GRADLE PROJECT
    val instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"
    val junit = "junit:junit:${Versions.junit}"
    val androidx_test_ext = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ext}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
}