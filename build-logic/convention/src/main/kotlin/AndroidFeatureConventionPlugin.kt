import com.valorantwiki.valorantwikiapp.addAndroidTestDependencies
import com.valorantwiki.valorantwikiapp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("valorantwikiapp.android.library.compose")
            }

            dependencies {
                add("implementation", project(":feature:common"))
                add("testImplementation", project(":test:unit"))
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                add("testImplementation", libs.findLibrary("kotlinx.coroutines.test").get())
                add("testImplementation", libs.findLibrary("turbine").get())
            }
            addAndroidTestDependencies()
        }
    }
}