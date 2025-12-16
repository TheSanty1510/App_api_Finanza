package com.example.appfinanzas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appfinanzas.network.RetrofitClient
import com.example.appfinanzas.repository.NetworkTransactionRepository
import com.example.appfinanzas.repository.TransactionRepository

class ViewModelFactory(private val repository: TransactionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(NewTransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewTransactionViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(TransactionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// Singleton factory para no tener que instanciar el repositorio cada vez
object AppViewModelProvider {
    val Factory: ViewModelProvider.Factory by lazy {
        ViewModelFactory(NetworkTransactionRepository(RetrofitClient.api))
    }
}