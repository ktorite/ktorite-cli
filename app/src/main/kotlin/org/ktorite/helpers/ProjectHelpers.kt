package org.ktorite.helpers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

fun loadManifest(resourcePath: String): Map<String, String> {
    val inputStream = object {}.javaClass.getResourceAsStream(resourcePath) ?: return emptyMap()
    val json = inputStream.bufferedReader().readText()
    return jacksonObjectMapper().readValue(json)
}

fun loadTemplate(resourcePath: String): String {
    val inputStream = object {}.javaClass.getResourceAsStream(resourcePath)
    return inputStream.bufferedReader().readText()
}
