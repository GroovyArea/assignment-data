import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
}

apply(plugin = "org.jetbrains.kotlin.plugin.spring")

group = "com.groovyarea.assignment-data"
version = ConstantCashNote.VERSION
java.sourceCompatibility = JavaVersion.toVersion(Dependency.targetJvmVersion)

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

