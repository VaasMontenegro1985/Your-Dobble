package com.vasya.yourdobble.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vasya.yourdobble.data.repository.ProgressRepository
import com.vasya.yourdobble.data.repository.WordsRepository
import com.vasya.yourdobble.viewmodel.SprintGameViewModel

class SprintGameViewModelFactory (
    private val wordsRepository: WordsRepository,
    private val progressRepository: ProgressRepository
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SprintGameViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SprintGameViewModel(wordsRepository, progressRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}