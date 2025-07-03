package org.ktorite

import com.github.ajalt.clikt.command.SuspendingCliktCommand
import com.github.ajalt.clikt.command.main
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import kotlinx.coroutines.delay

class ktorite : SuspendingCliktCommand() {
  val count by option(help = "Init").int().default(1)
  val name by argument()

  override suspend fun run() {
    for (i in 1..count) {
      echo("$name!")
      delay(1000)
    }
  }
}

suspend fun main(args: Array<String>) = ktorite().main(args)
