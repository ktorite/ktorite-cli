package {{projectName}}

import org.jetbrains.exposed.v1.core.*

object User : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val email = varchar("email", 255).uniqueIndex()
    override val primaryKey = PrimaryKey(id)
}
