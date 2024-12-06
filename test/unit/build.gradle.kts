plugins {
    id("valorantwikiapp.jvm.library")
}
dependencies {
    implementation(project(":domain:agent"))
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}