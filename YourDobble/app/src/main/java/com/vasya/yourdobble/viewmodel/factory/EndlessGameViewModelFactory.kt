package com.vasya.yourdobble.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vasya.yourdobble.data.repository.ProgressRepository
import com.vasya.yourdobble.data.repository.WordsRepository
import com.vasya.yourdobble.viewmodel.EndlessGameViewModel

class EndlessGameViewModelFactory(
    private val wordsRepository: WordsRepository,
    private val progressRepository: ProgressRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndlessGameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EndlessGameViewModel(wordsRepository, progressRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}