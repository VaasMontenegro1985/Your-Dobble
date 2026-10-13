package com.vasya.yourdobble.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vasya.yourdobble.data.repository.WordsRepository
import com.vasya.yourdobble.viewmodel.SprintGameViewModel

class SpintGameViewModelFactory (
    private val repository: WordsRepository
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SprintGameViewModel::class.java)){
            @Suppress
            return SprintGameViewModel(repository) as T
        }
        throw is
    }
}