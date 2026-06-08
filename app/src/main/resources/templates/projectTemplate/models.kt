package {{projectName}}

import org.jetbrains.exposed.v1.core.*

object Product : Table("products") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val price = double("price")
    override val primaryKey = PrimaryKey(id)
}
