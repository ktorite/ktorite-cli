package org.ktorite.projectHandler

import org.ktorite.helpers.loadManifest
import org.ktorite.helpers.loadProjectNameFromSettings
import org.ktorite.helpers.loadTemplate
import java.io.File

fun createApp(appName: String) {
    val projectName = loadProjectNameFromSettings().ifBlank {
        println("No parent project found")
        return
    }
    val appTargetDir = File("src/main/kotlin/${projectName}/apps/$appName")
    if (appTargetDir.exists()) {
        println("'$appName' already exists in this project.")
        return
    }
    appTargetDir.mkdirs()

    val manifest = loadManifest("/templates/appTemplate/app-manifest.json")
    manifest.forEach { (templateFile, targetPath) ->
        val content = loadTemplate("/templates/appTemplate/$templateFile")
            .replace("{{appName}}", appName)
            .replace("{{projectName}}",projectName)
        val outputFile = File(appTargetDir, targetPath)
        outputFile.parentFile.mkdirs()
        outputFile.writeText(content)

        println("Created File: ${outputFile.path}")
    }

    println("Ktorite App '$appName' created successfully.")
}
