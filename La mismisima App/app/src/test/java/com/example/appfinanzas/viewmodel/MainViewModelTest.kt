package com.example.appfinanzas.viewmodel

import com.example.appfinanzas.model.Transaction
import com.example.appfinanzas.model.TransactionType
import com.example.appfinanzas.repository.TransactionRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: TransactionRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        coEvery { repository.getSummary() } returns emptyMap() // Provide a default answer
        coEvery { repository.getRecentTransactions() } returns emptyList() // Provide a default answer
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadData should update uiState correctly`() = runTest {
        // Given
        val summary = mapOf("income" to 1000.0, "expenses" to 500.0, "balance" to 500.0)
        val transactions = listOf(
            Transaction("1", "Salary", "Salary", 1000.0, TransactionType.INCOME, "2023-10-26"),
            Transaction("2", "Rent", "Rent", 500.0, TransactionType.EXPENSE, "2023-10-25")
        )

        coEvery { repository.getSummary() } returns summary
        coEvery { repository.getRecentTransactions() } returns transactions

        // When
        viewModel.loadData()
        testDispatcher.scheduler.advanceUntilIdle() // Ensure all coroutines have completed

        // Then
        val uiState = viewModel.uiState.value
        assertEquals(1000.0, uiState.income, 0.0)
        assertEquals(500.0, uiState.expenses, 0.0)
        assertEquals(500.0, uiState.balance, 0.0)
        assertEquals(transactions, uiState.recentTransactions)
    }
}