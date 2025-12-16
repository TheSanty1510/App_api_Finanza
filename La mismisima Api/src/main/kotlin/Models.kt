package com.app

import kotlinx.serialization.Serializable // Necesario para @Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

// 1. El DTO (Lo que viaja por internet)
@Serializable
data class TransactionDto(
    val id: String,
    val detail: String,
    val description: String,
    val amount: Double,
    val type: String,
    val date: String
)

// 2. La Tabla (Lo que se guarda en base de datos)
object TransactionsTable : Table("transactions") {
    val id = integer("id").autoIncrement()
    val detail = varchar("detail", 255)
    val description = text("description")
    val amount = double("amount")
    val type = varchar("type", 10)
    val date = date("date")

    override val primaryKey = PrimaryKey(id)
}