import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("com.gradleup.shadow")
    id("maven-publish")
    id("org.jetbrains.gradle.plugin.idea-ext")
    `project-reports`
}

val kotlinVersion: Provider<String> = providers.gradleProperty("kotlinVersion")
val velocityVersion: Provider<String> = providers.gradleProperty("velocityVersion")
val coroutinesVersion: Provider<String> = providers.gradleProperty("coroutinesVersion")
val serializationVersion: Provider<String> = providers.gradleProperty("serializationVersion")
val atomicfuVersion: Provider<String> = providers.gradleProperty("atomicfuVersion")
val datetimeVersion: Provider<String> = providers.gradleProperty("datetimeVersion")

group = "com.velocitypowered"
version = System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let {
    "$it-dev"
} ?: "$velocityVersion+$kotlinVersion-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()

    exclusiveContent {
        filter {
            includeGroupAndSubgroups("com.velocitypowered")
            includeGroupAndSubgroups("io.papermc")
        }
        forRepository {
            maven("https://repo.papermc.io/repository/maven-public/")
        }
    }
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${serializationVersion.get()}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-cbor:${serializationVersion.get()}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion.get()}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${coroutinesVersion.get()}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:${coroutinesVersion.get()}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${coroutinesVersion.get()}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:${coroutinesVersion.get()}")
    implementation("org.jetbrains.kotlinx:atomicfu:${atomicfuVersion.get()}")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:${datetimeVersion.get()}")

    implementation("net.kyori:adventure-extra-kotlin:4.26.1")

    compileOnly("com.velocitypowered:velocity-api:${velocityVersion.get()}")
    kapt("com.velocitypowered:velocity-api:${velocityVersion.get()}")
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
        if (System.getenv("CI_COMMIT_TAG") != null &&
            System.getenv("GH_USR") != null &&
            System.getenv("GH_TOKEN") != null
        ) {
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
}

val templateSrc = project.rootDir.resolve("src/main/templates")
val templateDest = project.layout.buildDirectory.dir("generated/templates")
java {
    withSourcesJar()
    withJavadocJar()
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25

    sourceSets {
        main {
            java.srcDir(templateDest)
        }
    }
}

tasks {
    register<Copy>("generateTemplates") {
        val props = mapOf("version" to project.version as String)
        inputs.properties(props)
        from(templateSrc)
        into(templateDest)
        expand(props)
    }

    withType<KotlinCompile> {
        dependsOn("generateTemplates")
        compilerOptions.jvmTarget = JvmTarget.JVM_25
        compilerOptions.jvmTarget = JvmTarget.JVM_25
    }

    withType<Jar> {
        dependsOn("generateTemplates")
    }

    shadowJar {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    build {
        dependsOn(shadowJar)
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
