plugins {
    kotlin("jvm") version "2.4.0"
    kotlin("plugin.serialization") version "2.4.0"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.ktorite:ktorite-core:1.0.0")
    implementation("com.github.ktorite:ktorite-admin:v1.0.0")
    implementation("ch.qos.logback:logback-classic:1.5.34")
}

application {
    mainClass.set("{{projectName}}.ApplicationKt")
}

tasks.register("createsuperuser", JavaExec::class) {
    group = "ktorite"
    description = "Create a superuser for the admin panel"
    mainClass.set("{{projectName}}.CreateSuperuserKt")
    classpath = sourceSets.main.get().runtimeClasspath
    val argsList = project.findProperty("args")?.toString()?.split(" ")?.filter { it.isNotBlank() }.orEmpty()
    args(argsList)
}
