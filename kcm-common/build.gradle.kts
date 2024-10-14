
plugins {
    kotlin("multiplatform")
}

kotlin {

    explicitApi()

    jvm()

    js {
        browser {

        }
    }
}
