plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.zicure.common"
    compileSdk = 33

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {

    api("androidx.core:core-ktx:1.9.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.10.0")
    testApi("junit:junit:4.13.2")
    androidTestApi("androidx.test.ext:junit:1.1.5")
    androidTestApi("androidx.test.espresso:espresso-core:3.5.1")

    // Implement compose
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    api(composeBom)
    androidTestApi(composeBom)

    // Material Design 3
    api("androidx.compose.material3:material3")

    // Android Studio Preview support
    api("androidx.compose.ui:ui-tooling-preview")
    debugApi("androidx.compose.ui:ui-tooling")

    // UI Tests
    androidTestApi("androidx.compose.ui:ui-test-junit4")
    debugApi("androidx.compose.ui:ui-test-manifest")

    // Included automatically by material
    api("androidx.compose.material:material-icons-core")
    // Add full set of material icons
    api("androidx.compose.material:material-icons-extended")
    // Add window size utils
    api("androidx.compose.material3:material3-window-size-class")

    api("androidx.activity:activity-compose:1.8.0")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    api("androidx.compose.runtime:runtime-livedata")
    api("androidx.compose.runtime:runtime-rxjava2")
}