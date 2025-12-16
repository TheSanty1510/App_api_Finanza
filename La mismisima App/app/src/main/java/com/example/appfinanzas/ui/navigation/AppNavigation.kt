package com.example.appfinanzas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appfinanzas.ui.screens.MainScreen
import com.example.appfinanzas.ui.screens.NewExpenseScreen
import com.example.appfinanzas.ui.screens.NewIncomeScreen
import com.example.appfinanzas.ui.screens.TransactionsScreen
import com.example.appfinanzas.viewmodel.AppViewModelProvider

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            MainScreen(
                viewModel = viewModel(factory = AppViewModelProvider.Factory),
                onNavigateToNewIncome = { navController.navigate(Screen.NewIncome.route) },
                onNavigateToNewExpense = { navController.navigate(Screen.NewExpense.route) },
                onNavigateToTransactions = { navController.navigate(Screen.Transactions.route) }
            )
        }
        composable(Screen.Transactions.route) {
            TransactionsScreen(viewModel = viewModel(factory = AppViewModelProvider.Factory))
        }
        composable(Screen.NewIncome.route) {
            NewIncomeScreen(
                viewModel = viewModel(factory = AppViewModelProvider.Factory),
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.NewExpense.route) {
            NewExpenseScreen(
                viewModel = viewModel(factory = AppViewModelProvider.Factory),
                onBack = { navController.popBackStack() }
            )
        }
    }
}