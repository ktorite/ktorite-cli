plugins {
    kotlin("jvm") version "2.4.0"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.ktorite:ktorite:1.0.0")
    implementation("ch.qos.logback:logback-classic:1.5.34")
}

application {
    mainClass.set("{{projectName}}.ApplicationKt")
}
