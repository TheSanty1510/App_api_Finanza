package com.app // <--- CAMBIO 1: Alineamos el paquete con Application.kt

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db/finanzas_db;DB_CLOSE_DELAY=-1"

        val database = Database.connect(jdbcURL, driverClassName)

        transaction(database) {
            SchemaUtils.create(TransactionsTable)
        }
    }

    // CAMBIO 2: Agregamos <T> explícitamente en la llamada a newSuspendedTransaction
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction<T>(Dispatchers.IO) { block() }
}