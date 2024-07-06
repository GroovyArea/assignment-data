plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "assignment-data"

include("data-receiver")
include("data-transfer")
include("cash-note")
