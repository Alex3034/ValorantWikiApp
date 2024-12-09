plugins {
    id("valorantwikiapp.android.application")
    id("valorantwikiapp.android.application.compose")
    id("valorantwikiapp.di.library.compose")
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.valorantwiki.valorantwikiapp"

    defaultConfig {
        applicationId = "com.valorantwiki.valorantwikiapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.valorantwiki.valorantwikiapp.di.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":domain:agent"))
    implementation(project(":domain:region"))
    implementation(project(":framework:core"))
    implementation(project(":framework:agent"))
    implementation(project(":framework:region"))
    implementation(project(":feature:agent"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:common"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.location)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.androidx.room.ktx)
    kspAndroidTest(libs.androidx.room.compiler)
}