plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.luoyuer"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("dev.reimer:progressbar-ktx:0.1.0")
    // https://mvnrepository.com/artifact/net.jthink/jaudiotagger
    implementation("net.jthink:jaudiotagger:3.0.1")
// https://mvnrepository.com/artifact/javazoom/jlayer
    implementation("javazoom:jlayer:1.0.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
