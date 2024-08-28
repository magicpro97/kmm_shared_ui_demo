import org.jetbrains.kotlin.gradle.dsl.JvmTarget

repositories {
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
    kotlin("jvm") version "2.0.20"
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(kotlin("gradle-plugin"))
}