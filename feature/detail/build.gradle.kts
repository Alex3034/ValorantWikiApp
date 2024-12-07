plugins {
    id("valorantwikiapp.android.feature")
    id("valorantwikiapp.di.library.compose")
}

android {
    namespace = "com.valorantwiki.valorantwikiapp.ui.detail"
}

dependencies {

    implementation(project(":domain:agent"))
}