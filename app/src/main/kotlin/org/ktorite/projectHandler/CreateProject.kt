package org.ktorite.projectHandler

import org.ktorite.helpers.loadManifest
import org.ktorite.helpers.loadTemplate
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

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

  // Copy Gradle wrapper from CLI's own wrapper
  val wrapperDir = File(targetDir, "gradle/wrapper")
  wrapperDir.mkdirs()

  val sourceJar = File("../gradle/wrapper/gradle-wrapper.jar")
  val sourceProps = File("../gradle/wrapper/gradle-wrapper.properties")
  if (sourceJar.exists()) {
    Files.copy(sourceJar.toPath(), File(wrapperDir, "gradle-wrapper.jar").toPath(), StandardCopyOption.REPLACE_EXISTING)
    println("Created File: ${File(wrapperDir, "gradle-wrapper.jar")}")
  }
  if (sourceProps.exists()) {
    sourceProps.copyTo(File(wrapperDir, "gradle-wrapper.properties"), overwrite = true)
    println("Created File: ${File(wrapperDir, "gradle-wrapper.properties")}")
  }

  // Copy gradlew scripts from CLI root
  val gradlew = File("../gradlew")
  val gradlewBat = File("../gradlew.bat")
  if (gradlew.exists()) {
    val dest = File(targetDir, "gradlew")
    gradlew.copyTo(dest, overwrite = true)
    dest.setExecutable(true)
    println("Created File: ${dest.path}")
  }
  if (gradlewBat.exists()) {
    gradlewBat.copyTo(File(targetDir, "gradlew.bat"), overwrite = true)
    println("Created File: ${File(targetDir, "gradlew.bat")}")
  }

  println("Project '$projectName' created successfully at $projectDirectory.")
}
