
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

dependencies {
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("com.ninja-squad:springmockk:${Dependency.springMockkVersion}")

    // restdocs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("com.epages:restdocs-api-spec-mockmvc:${Dependency.restdocsapiSpecVersion}")
    testImplementation("com.epages:restdocs-api-spec-model:${Dependency.restdocsapiSpecVersion}")

}
