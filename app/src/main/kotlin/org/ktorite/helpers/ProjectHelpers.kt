package org.ktorite.helpers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

fun loadManifest(resourcePath: String): Map<String, String> {
    val inputStream = object {}.javaClass.getResourceAsStream(resourcePath) ?: return emptyMap()
    val json = inputStream.bufferedReader().readText()
    return jacksonObjectMapper().readValue(json)
}

fun loadTemplate(resourcePath: String): String {
    val inputStream = object {}.javaClass.getResourceAsStream(resourcePath)
    return inputStream.bufferedReader().readText()
}

fun loadProjectNameFromSettings(): String {
    val settingsFile = File("settings.gradle.kts")
    if (!settingsFile.exists()) {
        return ""
    }
    val line = settingsFile.readLines().find { it.contains("rootProject.name") }
        ?: error("Unknown Error")
    val regex = Regex("""rootProject\.name\s*=\s*["'](.+)["']""")
    val match = regex.find(line)
        ?: error("Could not get project name from settings.gradle.kts")
    return match.groupValues[1]
}
