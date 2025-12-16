package com.example.appfinanzas.viewmodel

import com.example.appfinanzas.model.Transaction
import com.example.appfinanzas.model.TransactionType
import com.example.appfinanzas.repository.TransactionRepository
import io.mockk.coEvery
import io.mockk.coVerify
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
class TransactionsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: TransactionRepository
    private lateinit var viewModel: TransactionsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadTransactions should update transactions list on init`() = runTest {
        // Given
        val initialMonth = "Agosto"
        val transactions = listOf(Transaction("1", "Transaction 1", "Transaction 1", 100.0, TransactionType.EXPENSE, "2023-08-01"))
        coEvery { repository.getAllTransactions(initialMonth) } returns transactions

        // When
        viewModel = TransactionsViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(transactions, viewModel.uiState.value.transactions)
    }

    @Test
    fun `onMonthChanged should reload transactions for the new month`() = runTest {
        // Given
        val initialMonth = "Agosto"
        val newMonth = "Septiembre"
        val initialTransactions = listOf(Transaction("1", "Trans 1", "Trans 1", 100.0, TransactionType.EXPENSE, "2023-08-01"))
        val newTransactions = listOf(Transaction("2", "Trans 2", "Trans 2", 200.0, TransactionType.INCOME, "2023-09-01"))
        coEvery { repository.getAllTransactions(initialMonth) } returns initialTransactions
        coEvery { repository.getAllTransactions(newMonth) } returns newTransactions

        // When
        viewModel = TransactionsViewModel(repository) // Carga inicial
        testDispatcher.scheduler.advanceUntilIdle()
        viewModel.onMonthChanged(newMonth)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        coVerify { repository.getAllTransactions(newMonth) }
        assertEquals(newTransactions, viewModel.uiState.value.transactions)
        assertEquals(newMonth, viewModel.uiState.value.selectedMonth)
    }
}