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
version = ConstantDataTransfer.VERSION
java.sourceCompatibility = JavaVersion.toVersion(Dependency.targetJvmVersion)

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Dependency.springCloudVersion}")
    }
}

dependencies {

    // OpenFeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("io.github.openfeign:feign-okhttp:${Dependency.openFeignVersion}")
    implementation("io.github.openfeign:feign-jackson:${Dependency.openFeignVersion}")
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

