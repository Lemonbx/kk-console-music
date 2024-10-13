import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "7.1.2"
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
    jvmToolchain(8)
}
tasks.withType<ShadowJar>{
    manifest {
        attributes(
            "Manifest-Version" to 1.0,
            "Main-Class" to "com.luoyuer.MainKt"
        )
    }
}
