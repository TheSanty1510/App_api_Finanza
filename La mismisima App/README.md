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
