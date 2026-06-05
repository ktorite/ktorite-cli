package {{projectName}}

import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorite.Ktorite

fun main() {
    Ktorite.start {
        port = 8080
        enableAdmin = true
        database {
            url = "jdbc:h2:file:./data/db;DB_CLOSE_DELAY=-1"
            driver = "org.h2.Driver"
            username = "sa"
            password = ""
        }
        registerModels(User)
        routing {
            get("/") {
                call.respondText("Hello from {{projectName}}!")
            }
        }
    }
}
