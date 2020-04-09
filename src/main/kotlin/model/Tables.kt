package model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date


object Employee : Table() {
    val tabNumber = integer("tab_number")
    val name = varchar("fio", 64)
    val gender = char("gender")
    val inn = varchar("inn", 12)
    val birthDate = date("birth_date")
    val position = varchar("position", 64) references Position.positionName

    override val primaryKey = PrimaryKey(tabNumber)
}

object Position : Table() {
    val positionName = varchar("position_name", 64)
    val salary = integer("salary")

    override val primaryKey = PrimaryKey(positionName)
}