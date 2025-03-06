plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlin.kapt)
    id("kotlinx-serialization")
}

android {
    namespace = "ru.hiringapp.transport"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    api(project(":base"))
    api(project(":domain"))

    api(libs.retrofit2)
    api(libs.bundles.room)
    kapt(libs.androidxRoomCompiler)
    implementation(libs.androidxRoomCommon)

    implementation(libs.bundles.hilt)
    kapt(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    kapt(libs.hiltAndroidCompiler)
}