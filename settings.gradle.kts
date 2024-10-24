plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "kk-console-music"

include(":kcm-common")
include(":kcm-app")

includeBuild("Ankot")
