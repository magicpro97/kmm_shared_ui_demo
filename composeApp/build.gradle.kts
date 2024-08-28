import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlinMultiplatform()
    androidApp()
    jetBrainCompose()
    composeCompiler()
    kotlinSerialization()
    realm()
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
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            // internal
            implementation(project(Module.Core.COMMON))
            implementation(project(Module.Core.DATA))
            implementation(project(Module.Core.DOMAIN))
            implementation(project(Module.Core.UTILS))
            implementation(project(Module.Feature.USER))
            implementation(project(Module.Feature.HOME))

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
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            // Realm
            implementation(libs.realm)

            // Voyager
            implementation(libs.koin.core)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.koin)

            // Kamel
            implementation(libs.kamel)
        }
    }
}

compose.resources {
    publicResClass = true
    generateResClass = always
}

android {
    namespace = "com.cdev.kmmsharedui"
    compileSdk = 34

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.cdev.kmmsharedui"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL_DEV", Config.Debug.BASE_URL)
        buildConfigField("String", "BASE_URL_PROD", Config.Release.BASE_URL)
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    flavorDimensions += "env"
    productFlavors {
        create("dev") {
            dimension = "env"
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "KMM Demo - Dev")
        }
        create("prod") {
            dimension = "env"
            resValue("string", "app_name", "KMM Demo")
        }
    }

    dependencies {
        debugImplementation(libs.androidx.compose.ui.tooling)
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0,TimeUnit.SECONDS)
}
