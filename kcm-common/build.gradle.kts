
plugins {
    kotlin("multiplatform")
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
    }
}
