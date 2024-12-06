plugins {
    id("valorantwikiapp.android.feature")
    id("valorantwikiapp.di.library.compose")
}


android {
    namespace = "com.valorantwiki.valorantwikiapp.ui.agent"
}

dependencies {

    implementation(project(":domain:agent"))
    testImplementation(project(":test:unit"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}