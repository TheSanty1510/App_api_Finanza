package com.example.appfinanzas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfinanzas.model.Transaction
import com.example.appfinanzas.model.TransactionType
import com.example.appfinanzas.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class NewTransactionUiState(
    val amount: String = "",
    val description: String = "",
    val category: String = "",
    val isSaved: Boolean = false
)

class NewTransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(NewTransactionUiState())
    val uiState = _uiState.asStateFlow()

    fun saveTransaction(type: TransactionType) {
        val currentState = _uiState.value

        if (currentState.amount.isBlank() || currentState.description.isBlank()) return

        val newTransaction = Transaction(
            id = "", // La API genera el ID
            description = currentState.description,
            amount = currentState.amount.toDoubleOrNull() ?: 0.0,
            date = LocalDate.now().toString(),
            type = type,
            detail = currentState.category.ifEmpty { "General" }
        )

        viewModelScope.launch {
            try {
                repository.saveTransaction(newTransaction)
                _uiState.update { it.copy(isSaved = true) } // Indica que se guardó
            } catch (e: Exception) {
                // Manejar error
            }
        }
    }

    fun onAmountChanged(amount: String) {
        _uiState.update { it.copy(amount = amount) }
    }

    fun onDescriptionChanged(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun onCategoryChanged(category: String) {
        _uiState.update { it.copy(category = category) }
    }
}