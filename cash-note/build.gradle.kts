import com.epages.restdocs.apispec.gradle.OpenApi3Extension

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
}

apply(plugin = "org.jetbrains.kotlin.plugin.spring")
apply(plugin = "com.epages.restdocs-api-spec")

group = "com.groovyarea.assignment-data"
version = ConstantCashNote.VERSION
java.sourceCompatibility = JavaVersion.toVersion(Dependency.targetJvmVersion)

dependencies {
    // swagger-ui
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

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
tasks {

    val openApi3OutputDirectory = "build/apiSpec"

    configure<OpenApi3Extension> {
        setServer(System.getenv("API_SERVER_URL") ?: "http://localhost:8000")
        title = ConstantCashNote.SERVICE_NAME
        description = "${ConstantCashNote.SERVICE_NAME} API"
        version = Constant.VERSION
        format = "yaml"
        outputDirectory = openApi3OutputDirectory
        outputFileNamePrefix = "swagger"
    }

    this.register("copyDocs") {
        doLast {
            copy {
                from(openApi3OutputDirectory)
                into("src/main/resources/static/docs")
            }
        }
        dependsOn("openapi3")
    }

    clean {
        delete("${project.layout.projectDirectory}/src/main/resources/static")
    }

    build {
        dependsOn("copyDocs")
    }
}


