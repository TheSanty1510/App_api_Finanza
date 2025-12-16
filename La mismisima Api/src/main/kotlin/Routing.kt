package com.app

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


// CAMBIO AQUÍ: Ahora la función acepta el 'repository' como parámetro
fun Application.configureRouting(repository: ExposedTransactionRepository) {

    // Manejo de errores
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        // Ruta de prueba
        get("/") {
            call.respondText("¡La API de Finanzas está viva!")
        }

        // --- RUTAS DE TRANSACCIONES ---
        route("/transactions") {

            // 1. Obtener todas (con filtro opcional por mes)
            // GET /transactions?month=2025-08
            get {
                val month = call.request.queryParameters["month"]
                val transactions = repository.getAllTransactions(month)
                call.respond(transactions)
            }

            // 2. Guardar nueva transacción
            // POST /transactions
            post {
                // Recibimos el JSON y lo convertimos a TransactionDto
                val dto = call.receive<TransactionDto>()
                repository.addTransaction(dto)
                call.respond(HttpStatusCode.Created, "Guardado exitosamente")
            }

            // 3. Obtener resumen (ingresos, gastos, balance)
            // GET /transactions/summary
            get("/summary") {
                val summary = repository.getSummary()
                call.respond(summary)
            }
        }
    }
}