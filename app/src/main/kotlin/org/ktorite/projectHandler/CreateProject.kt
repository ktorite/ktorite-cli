package org.ktorite.projectHandler

import org.ktorite.helpers.loadManifest
import org.ktorite.helpers.loadTemplate
import java.io.File

fun createProject(projectName: String, projectDirectory: String) {
  val targetDir = File(projectDirectory)
  if (targetDir.exists()) {
    println("Project '$projectName' already exists at $projectDirectory!")
    return
  }
  targetDir.mkdirs()

  val manifest = loadManifest("/templates/projectTemplate/project-manifest.json")
  manifest.forEach { (templateFile, targetPath) ->
    val content = loadTemplate("/templates/projectTemplate/$templateFile")
      .replace("{{projectName}}", projectName)
    val outputPath = targetPath.replace("{{projectName}}", projectName)
    val outputFile = File(targetDir, outputPath)
    outputFile.parentFile.mkdirs()
    outputFile.writeText(content)
    println("Created File: ${outputFile.path}")
  }

  // Copy Gradle wrapper from bundled resources
  val wrapperDir = File(targetDir, "gradle/wrapper")
  wrapperDir.mkdirs()

  fun writeResource(path: String, destFile: File) {
    val bytes = object {}.javaClass.getResourceAsStream(path)?.readAllBytes()
    if (bytes != null) {
      destFile.writeBytes(bytes)
      println("Created File: ${destFile.path}")
    }
  }

  writeResource("/wrapper/gradle-wrapper.jar", File(wrapperDir, "gradle-wrapper.jar"))
  writeResource("/wrapper/gradle-wrapper.properties", File(wrapperDir, "gradle-wrapper.properties"))

  val gradlewFile = File(targetDir, "gradlew")
  writeResource("/wrapper/gradlew", gradlewFile)
  gradlewFile.setExecutable(true)

  writeResource("/wrapper/gradlew.bat", File(targetDir, "gradlew.bat"))

  println("Project '$projectName' created successfully at $projectDirectory.")
}
