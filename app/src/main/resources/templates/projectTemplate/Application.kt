package {{projectName}}

import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorite.Ktorite
import org.ktorite.rest.restFramework
import java.util.Properties
import java.io.File

fun main() {
    val props = File("ktorite.properties").inputStream().use { Properties().apply { load(it) } }

    Ktorite.start {
        port = 8080
        developmentMode = System.getenv("KTORITE_DEV") != "false"
        database {
            url = props.getProperty("db.url")
            driver = props.getProperty("db.driver")
            username = props.getProperty("db.user")
            password = props.getProperty("db.password")
        }
        auth {
            session {
                secret = props.getProperty("secret")
            }
        }
        registerModels(Product)
        routing {
            get("/") {
                call.respondText("Hello from {{projectName}}!")
            }
        }
        restFramework {
            registerAll()
        }
    }
}
