plugins {
    `kotlin-dsl`
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin{
    plugins {
        register("androidApplication"){
            id = "valorantwikiapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("diLibrary"){
            id = "valorantwikiapp.di.library"
            implementationClass = "DiLibraryConventionPlugin"
        }
        register("diLibraryCompose"){
            id = "valorantwikiapp.di.library.compose"
            implementationClass = "DiLibraryComposeConventionPlugin"
        }
        register("jvmLibrary") {
            id = "valorantwikiapp.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}