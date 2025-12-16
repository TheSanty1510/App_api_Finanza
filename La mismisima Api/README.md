# Finanza-App

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
