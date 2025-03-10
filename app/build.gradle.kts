plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlinParcelize)
}

android {
    namespace = "ru.hiringapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.hiringapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packagingOptions {
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
        resources.excludes.add("META-INF/DEPENDENCIES")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":base"))
    implementation(project(":base-feature"))
    implementation(project(":domain"))
    implementation(project(":transport"))
    implementation(project(":network"))
    implementation(project(":uikit"))

    implementation(project(":search:feature"))
    implementation(project(":favourites:feature"))
    implementation(project(":feedback:feature"))
    implementation(project(":messages:feature"))
    implementation(project(":profile:feature"))
    implementation(project(":main:feature"))
    implementation(project(":main:interactor"))

    implementation(project(":offers:common-interactor"))
    implementation(project(":offers:common-feature"))
    implementation(project(":vacancy:common-feature"))
    implementation(project(":vacancy:common-interactor"))

    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.bundles.hilt)
    kapt(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    kapt(libs.hiltAndroidCompiler)
}