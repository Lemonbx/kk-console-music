
plugins {
    kotlin("multiplatform") version "2.0.0" apply false
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

