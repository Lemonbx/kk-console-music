
plugins {
    kotlin("multiplatform") version "1.9.23" apply false
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

