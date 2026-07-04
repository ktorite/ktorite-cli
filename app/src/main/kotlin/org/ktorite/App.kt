package org.ktorite

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import org.ktorite.projectHandler.*

class CreateProject : CliktCommand(name = "create-project") {
  private val projectName by argument(help = "Project name (must be a valid Kotlin identifier, e.g. myproj)")
  private val projectDirectory by argument(help = "Target directory for the new project, e.g. ./myproj")

  override fun help(context: Context): String = "Scaffold a new Ktorite project"
  override val printHelpOnEmptyArgs = true
  override fun run() {
    if (!projectName.matches(Regex("[a-zA-Z][a-zA-Z0-9]*"))) {
      println("Error: Project name must be a valid Kotlin identifier (letters and digits only, starting with a letter)")
      return
    }
    createProject(projectName, projectDirectory)
  }
}

class Admin : CliktCommand(name = "ktorite") {
  override fun help(context: Context): String = "Ktorite CLI - scaffold and manage Ktorite projects"
  override val printHelpOnEmptyArgs = true
  override fun run() = Unit
}

fun main(args: Array<String>) {
  Admin().subcommands(CreateProject()).main(args)
}
