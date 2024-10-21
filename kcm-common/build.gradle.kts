
plugins {
    kotlin("multiplatform")
}
dependencies{
    commonMainImplementation(kotlin("stdlib-common"))
    commonMainImplementation(kotlin("stdlib"))
}
kotlin {

    explicitApi()

    jvm()

    js {
        browser {
            webpackTask {
                output.libraryTarget = "commonjs2"
            }
            binaries.executable()
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
            }
        }
    }
}
