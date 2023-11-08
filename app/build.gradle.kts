plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.zicure.xcotv"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.zicure.xcotv"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.leanback:leanback:1.0.0")

    implementation(project(":common"))
}