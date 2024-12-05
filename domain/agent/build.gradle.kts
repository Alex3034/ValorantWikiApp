plugins {
    id("valorantwikiapp.jvm.library")
    id("valorantwikiapp.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.kotlinx.coroutines.core)
}