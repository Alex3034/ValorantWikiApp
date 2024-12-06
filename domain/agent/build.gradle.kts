plugins {
    id("valorantwikiapp.jvm.library")
    id("valorantwikiapp.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    testImplementation(project(":test:unit"))
}