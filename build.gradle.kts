import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
    id("maven-publish")
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.6"
}

val kotlinVersion: String by project
val velocityVersion: String by project
val coroutinesVersion: String by project
val serializationVersion: String by project

group = "com.velocitypowered"
version = System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let {
    "$it-dev"
} ?: "$velocityVersion+$kotlinVersion-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-cbor:$serializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:$coroutinesVersion")
    implementation("net.kyori:adventure-extra-kotlin:4.12.0")

    compileOnly("com.velocitypowered:velocity-api:$velocityVersion")
    kapt("com.velocitypowered:velocity-api:$velocityVersion")
}
publishing {
    publications {
        create<MavenPublication>("velocity-language-kotlin") {
            from(components["java"])
        }
    }
    repositories {
        if (System.getenv("CI_JOB_TOKEN") != null) {
            maven {
                name = "GitLab"
                val projectId = System.getenv("CI_PROJECT_ID")
                val apiV4 = System.getenv("CI_API_V4_URL")
                url = uri("$apiV4/projects/$projectId/packages/maven")
                authentication {
                    create("token", HttpHeaderAuthentication::class.java) {
                        credentials(HttpHeaderCredentials::class.java) {
                            name = "Job-Token"
                            value = System.getenv("CI_JOB_TOKEN")
                        }
                    }
                }
            }
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/HuebCraftDev/velocity-language-kotlin")
            credentials {
                username = System.getenv("GH_USR")
                password = System.getenv("GH_TOKEN")
            }
        }
    }
}

val templateSrc = project.rootDir.resolve("src/main/templates")
val templateDest = project.buildDir.resolve("generated/templates")
java {
    withSourcesJar()
    withJavadocJar()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    sourceSets {
        main {
            java.srcDir(templateDest)
        }
    }
}
tasks.build {
    dependsOn(tasks.shadowJar.get())
}
tasks {

    create<Copy>("generateTemplates") {
        val props = mapOf("version" to project.version as String)
        inputs.properties(props)
        from(templateSrc)
        into(templateDest)
        expand(props)
    }
    withType<KotlinCompile> {
        dependsOn("generateTemplates")
        kotlinOptions.jvmTarget = "17"
    }

    withType<Jar> {
        dependsOn("generateTemplates")
    }
}
rootProject.idea.project {
    this as ExtensionAware
    configure<org.jetbrains.gradle.ext.ProjectSettings> {
        this as ExtensionAware
        configure<org.jetbrains.gradle.ext.TaskTriggersConfig> {
            afterSync(tasks["generateTemplates"])
        }
    }
}
