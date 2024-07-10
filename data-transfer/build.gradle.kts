
plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
}

apply(plugin = "org.jetbrains.kotlin.plugin.spring")
apply(plugin = "com.epages.restdocs-api-spec")

group = "com.groovyarea.assignment-data"
version = ConstantDataTransfer.VERSION
java.sourceCompatibility = JavaVersion.toVersion(Dependency.targetJvmVersion)
