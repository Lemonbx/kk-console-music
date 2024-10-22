
plugins {
    kotlin("jvm")
}
group = "com.luoyuer"
version = "1.0-SNAPSHOT"
dependencies{
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
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