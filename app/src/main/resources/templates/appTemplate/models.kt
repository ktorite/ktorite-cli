package {{projectName}}.apps.{{appName}}

import org.jetbrains.exposed.v1.core.*

object SampleModel : Table("{{appName}}_sample") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    override val primaryKey = PrimaryKey(id)
}
