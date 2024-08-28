import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun KotlinDependencyHandler.implementationCoreCommon() {
    implementation(project(":core:common"))
}

fun KotlinDependencyHandler.implementationCore() {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:utils"))
    implementation(project(":core:common"))
}

fun KotlinDependencyHandler.implementationFeatureUser() {
    implementation(project(":feature:user"))
}