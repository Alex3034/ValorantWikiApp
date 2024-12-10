plugins {
    id("valorantwikiapp.android.feature")
    id("valorantwikiapp.di.library.compose")
}


android {
    namespace = "com.valorantwiki.valorantwikiapp.ui.agent"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {

    implementation(project(":domain:agent"))
}