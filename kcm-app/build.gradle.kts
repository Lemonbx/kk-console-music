
plugins {
    kotlin("jvm")
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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.9.0")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
application{
    mainClass = "com.luoyuer.MainKt"
}