
plugins {
    kotlin("jvm")
}

group = "com.luoyuer"
version = "1.0-SNAPSHOT"

dependencies{
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    // api("com.ankot:ankot-core:1.0-SNAPSHOT")
}

kotlin {
    explicitApi()
    jvmToolchain(21)
    sourceSets{
        all{
            languageSettings{
                optIn("kotlin.contracts.ExperimentalContracts")
            }
        }
    }
}