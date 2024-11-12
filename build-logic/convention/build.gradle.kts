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
    }
}