import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    kotlinMultiplatform()
    androidLibrary()
    jetBrainCompose()
    composeCompiler()
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "CoreData"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Ktor
            implementation(libs.ktor.client.core)

            // Voyager
            implementation(libs.koin.core)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.koin)

            // Internal
            implementation(project(Module.Core.DOMAIN))
            implementation(project(Module.Core.COMMON))
        }
    }
}

android {
    compileSdk = 34
    namespace = "com.cdev.kmmsharedui"

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}