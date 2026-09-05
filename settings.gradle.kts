rootProject.name = "velocity-language-kotlin"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        val kotlinVersion: String by settings

        kotlin("jvm") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion

        id("com.gradleup.shadow") version "9.6.1"
        id("org.jetbrains.gradle.plugin.idea-ext") version "1.4.1"
    }
}
