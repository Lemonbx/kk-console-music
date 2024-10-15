import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}
javafx{
    version="22.0.1"
    modules("javafx.controls","javafx.graphics","javafx.fxml","javafx.web")
}

group = "com.luoyuer"
version = "1.0-SNAPSHOT"


dependencies {
    testImplementation(kotlin("test"))
    implementation("dev.reimer:progressbar-ktx:0.1.0")
    implementation("net.jthink:jaudiotagger:3.0.1")
    implementation(project(":kcm-common"))
    implementation("javazoom:jlayer:1.0.1")
    implementation("cn.hutool:hutool-all:5.8.32")

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
