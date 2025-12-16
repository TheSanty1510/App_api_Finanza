package com.app

//import `import org`.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDate
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.javatime.month
import org.jetbrains.exposed.sql.javatime.year
import org.jetbrains.exposed.sql.selectAll


class ExposedTransactionRepository {

    // Obtener transacciones (filtrando por mes si se envía)
    suspend fun getAllTransactions(monthStr: String?): List<TransactionDto> = DatabaseFactory.dbQuery {
        val query = TransactionsTable.selectAll()

        if (monthStr != null && monthStr.isNotEmpty()) {
            try {
                // Asumiendo que desde Android envías "2025-08" (YYYY-MM)
                val parts = monthStr.split("-")
                if (parts.size == 2) {
                    val yearInt = parts[0].toInt()
                    val monthInt = parts[1].toInt()

                    // Filtramos por año Y mes
                    query.andWhere {
                        (TransactionsTable.date.year() eq yearInt) and
                                (TransactionsTable.date.month() eq monthInt)
                    }
                }
            } catch (e: Exception) {
                // Si el formato es incorrecto, devolvemos todo o lista vacía
                println("Error parseando mes: $monthStr")
            }
        }

        // Convertimos de Fila SQL -> Objeto Kotlin (DTO)
        query.map { row ->
            TransactionDto(
                id = row[TransactionsTable.id].toString(), // Convertimos Int a String para tu App
                detail = row[TransactionsTable.detail],
                description = row[TransactionsTable.description],
                amount = row[TransactionsTable.amount],
                type = row[TransactionsTable.type],
                date = row[TransactionsTable.date].toString()
            )
        }
    }

    // Guardar una nueva transacción
    suspend fun addTransaction(t: TransactionDto) = DatabaseFactory.dbQuery {
        TransactionsTable.insert {
            it[detail] = t.detail
            it[description] = t.description
            it[amount] = t.amount
            it[type] = t.type
            // Convertimos el String "2025-10-20" a LocalDate
            it[date] = LocalDate.parse(t.date) 
        }
    }
    
    // Obtener resumen (Suma de ingresos y gastos)
    suspend fun getSummary(): Map<String, Double> = DatabaseFactory.dbQuery {
        val income = TransactionsTable.selectAll().where { TransactionsTable.type eq "INCOME" }
            .sumOf { it[TransactionsTable.amount] }
            
        val expenses = TransactionsTable.selectAll().where { TransactionsTable.type eq "EXPENSE" }
            .sumOf { it[TransactionsTable.amount] }

        mapOf(
            "income" to income,
            "expenses" to expenses,
            "balance" to (income - expenses) // Expenses suele ser negativo en tu lógica?
        )
    }
}