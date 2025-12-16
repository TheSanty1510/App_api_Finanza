package com.example.appfinanzas.viewmodel

import com.example.appfinanzas.model.TransactionType
import com.example.appfinanzas.repository.TransactionRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewTransactionViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: TransactionRepository
    private lateinit var viewModel: NewTransactionViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
        viewModel = NewTransactionViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `saveTransaction should call repository when data is valid`() = runTest {
        // Given
        viewModel.onAmountChanged("100.0")
        viewModel.onDescriptionChanged("Test Income")

        // When
        viewModel.saveTransaction(TransactionType.INCOME)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        coVerify { repository.saveTransaction(any()) }
        assertTrue(viewModel.uiState.value.isSaved)
    }

    @Test
    fun `saveTransaction should not call repository when data is invalid`() = runTest {
        // Given
        viewModel.onAmountChanged("") // Monto inválido
        viewModel.onDescriptionChanged("Test Income")

        // When
        viewModel.saveTransaction(TransactionType.INCOME)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        coVerify(exactly = 0) { repository.saveTransaction(any()) }
    }
}