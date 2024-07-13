import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version Dependency.springBootVersion
    id("io.spring.dependency-management") version Dependency.dependencyManagementVersion
    id("io.gitlab.arturbosch.detekt") version Dependency.detektVersion
    id("com.epages.restdocs-api-spec") version Dependency.restdocsapiSpecVersion
    kotlin("jvm") version Dependency.kotlinVersion
    kotlin("plugin.spring") version Dependency.kotlinVersion
    kotlin("plugin.jpa") version Dependency.kotlinVersion
    kotlin("plugin.allopen") version Dependency.kotlinVersion
    kotlin("plugin.noarg") version Dependency.kotlinVersion
    kotlin("kapt") version Dependency.kotlinVersion
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")

    group = Constant.GROUP_ID
    version = Constant.VERSION
    java.sourceCompatibility = JavaVersion.toVersion(Dependency.targetJvmVersion)

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(Dependency.targetJvmVersion)
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        // kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

        // Jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.core:jackson-databind")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Dependency.coroutineVersion}")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Dependency.coroutineVersion}")

        // Spring MVC
        implementation("org.springframework.boot:spring-boot-starter-web")

        // Spring JPA
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("mysql:mysql-connector-java:${Dependency.mySQLConnectorVersion}")

        // Querydsl
        implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
        kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
        kapt("org.springframework.boot:spring-boot-configuration-processor")

        annotationProcessor("jakarta.persistence:jakarta.persistence-api")
        annotationProcessor("jakarta.annotation:jakarta.annotation-api")

        // mapstruct
        implementation("org.mapstruct:mapstruct:${Dependency.mapstructVersion}")
        kapt("org.mapstruct:mapstruct-processor:${Dependency.mapstructVersion}")

        // detekt
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${Dependency.detektVersion}")

        // kotest
        testImplementation("io.kotest:kotest-runner-junit5:${Dependency.kotestVersion}")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:${Dependency.kotestSprintExtensions}")
        testImplementation("io.mockk:mockk:${Dependency.mockkVersion}")

        // test
        testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
        testImplementation("org.mockito:mockito-inline:5.2.0")
    }


    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = Dependency.targetJvmVersion
            }
        }

        withType<Test> {
            useJUnitPlatform()

            testLogging {
                events(STANDARD_OUT, STANDARD_ERROR)

                showStandardStreams = true
                showCauses = true
                showStackTraces = true
            }
        }
    }

    allOpen {
        annotation("jakarta.persistence.Entity")
    }

    noArg {
        annotation("jakarta.persistence.Entity")
    }

    detekt {
        autoCorrect = true
    }
}


