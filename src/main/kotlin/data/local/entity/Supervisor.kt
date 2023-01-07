package data.local.entity

import org.jetbrains.exposed.sql.Table

object Supervisor: Table() {
    val id = integer("id")
    val name = varchar("name", 100)

    override val primaryKey = PrimaryKey(id)
}