import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlinMultiplatform()
    androidLibrary()
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
            baseName = "CoreUtils"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.core)

            // internal
            implementation(project(Module.Core.DOMAIN))
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