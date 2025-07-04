package org.ktorite

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import org.ktorite.projectHandler.*

class CreateProject : CliktCommand(name = "create-project") {
  private val projectName by argument()
  private val projectDirectory by argument()

  override fun help(context: Context): String = "Creates a ktorite project"
  override val printHelpOnEmptyArgs = true
  override fun run() {
    createProject(projectName, projectDirectory)
  }
}

class CreateApp : CliktCommand(name = "create-app") {
  private val appName by argument()
  override fun help(context: Context): String = "Creates a ktorite app"
  override val printHelpOnEmptyArgs = true
  override fun run() {
        createApp(appName)
  }
}

class Admin : CliktCommand(name = "ktorite") {
  override val printHelpOnEmptyArgs = true
  override fun run() = Unit
}

fun main(args: Array<String>) {
  Admin().subcommands(CreateProject(), CreateApp()).main(args)
}
