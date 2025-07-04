package org.ktorite.projectHandler

import org.ktorite.helpers.loadManifest
import org.ktorite.helpers.loadTemplate
import java.io.File

fun createProject(projectName: String,projectDirectory: String) {
  val targetDir = File(projectName)
  if (targetDir.exists()) {
    println("Project $projectName already exists!")
    return
  }
  targetDir.mkdirs()
  val manifest = loadManifest("/templates/projectTemplate/project-manifest.json")
  manifest.forEach { (templateFile, targetPath) ->
    val content = loadTemplate("/templates/projectTemplate/$templateFile")
      .replace("{{projectName}}", projectName)
    val outputFile = File(targetDir, targetPath)
    outputFile.parentFile.mkdirs()
    outputFile.writeText(content)
    println("Created File: ${outputFile.path}")
  }

  println("Project '$projectName' created successfully.")
}
