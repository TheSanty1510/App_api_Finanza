# Finanza-Api

Este proyecto fue creado usando el [Generador de Proyectos de Ktor](https://start.ktor.io).


## Características

Aquí hay una lista de las características incluidas en este proyecto:

| Nombre                                                                 | Descripción                                                                        |
| ------------------------------------------------------------------------|------------------------------------------------------------------------------------ |
| [Enrutamiento](https://start.ktor.io/p/routing)                        | Proporciona un DSL de enrutamiento estructurado                                     |
| [Páginas de Estado](https://start.ktor.io/p/status-pages)              | Proporciona manejo de excepciones para las rutas                                   |
| [Registro de Llamadas](https://start.ktor.io/p/call-logging)           | Registra las solicitudes de los clientes                                           |
| [Negociación de Contenido](https://start.ktor.io/p/content-negotiation)| Proporciona conversión de contenido automática según las cabeceras Content-Type y Accept |
| [kotlinx.serialization](https://start.ktor.io/p/kotlinx-serialization) | Maneja la serialización JSON usando la librería kotlinx.serialization              |

## Componentes Utilizados

*   **Ktor**: Framework principal para construir la API.
*   **Routing**: Para definir las rutas y endpoints.
*   **Status Pages**: Para el manejo de errores.
*   **Content Negotiation** con **kotlinx.serialization**: Para la serialización y deserialización de JSON.
*   **Exposed**: Como biblioteca de acceso a bases de datos.

## Construcción y Ejecución

Para construir o ejecutar el proyecto, usa una de las siguientes tareas:

| Tarea                                   | Descripción                                                          |
| -----------------------------------------|---------------------------------------------------------------------- |
| `./gradlew test`                        | Ejecutar las pruebas                                                 |
| `./gradlew build`                       | Construir todo                                                       |
| `./gradlew buildFatJar`                 | Construir un JAR ejecutable del servidor con todas las dependencias incluidas |
| `./gradlew buildImage`                  | Construir la imagen de docker para usar con el fat JAR               |
| `./gradlew publishImageToLocalRegistry` | Publicar la imagen de docker localmente                              |
| `./gradlew run`                         | Ejecutar el servidor                                                 |
| `./gradlew runDocker`                   | Ejecutar usando la imagen de docker local                            |

Si el servidor se inicia correctamente, verás la siguiente salida:

```
2024-12-04 14:32:45.584 [main] INFO  Application - La aplicación se inició en 0.303 segundos.
2024-12-04 14:32:45.682 [main] INFO  Application - Respondiendo en http://0.0.0.0:8080
```

# App Finanzas

Esta es una aplicación de finanzas personales para Android que te permite llevar un registro de tus ingresos y gastos.

## Funcionalidades

*   Registrar nuevos ingresos y gastos.
*   Ver una lista de todas las transacciones.
*   Ver un resumen de tus finanzas en la pantalla principal.

## Componentes Utilizados

*   **Jetpack Compose:** Para la construcción de la interfaz de usuario.
*   **Navigation Compose:** Para la navegación entre las diferentes pantallas de la aplicación.
*   **ViewModel:** Para separar la lógica de la interfaz de usuario y gestionar el estado.
*   **Retrofit:** Para realizar peticiones a una API REST y obtener los datos financieros.
*   **Coroutines:** Para gestionar las operaciones asíncronas, como las peticiones a la API.

## Estructura de la Aplicación

La aplicación se compone de las siguientes pantallas:

*   **MainScreen:** La pantalla principal que muestra un resumen de las finanzas y botones para navegar a las pantallas de nuevos ingresos, nuevos gastos y transacciones.
*   **TransactionsScreen:** Muestra un listado de todos los movimientos (ingresos y gastos).
*   **NewIncomeScreen:** Un formulario para registrar un nuevo ingreso.
*   **NewExpenseScreen:** Un formulario para registrar un nuevo gasto.

## Cómo Empezar

1.  Clona este repositorio.
2.  Ábrelo en Android Studio.
3.  Ejecuta la aplicación en un emulador o en un dispositivo físico.
