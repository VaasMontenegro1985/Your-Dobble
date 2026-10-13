package com.vasya.yourdobble.ui.screen.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vasya.yourdobble.data.repository.WordsRepository
import com.vasya.yourdobble.domain.generateCardsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GameViewModel(
    private val repository: WordsRepository
) : ViewModel() {

    private val allWords = repository.getWords()

    fun getWords(): List<String> {
        return allWords
    }

    var score by mutableStateOf(0)
        private set

    var timeLeft by mutableStateOf(10)
        private set

    var isGameOver by mutableStateOf(false)
        private set

    var cards by mutableStateOf(generateCardsUseCase(allWords))
        private set

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (isActive) {

                delay(1000)

                if (isGameOver) continue

                if (timeLeft > 0) {
                    timeLeft--
                } else {
                    isGameOver = true
                }
            }
        }
    }
    fun onCorrectClick() {
        if (isGameOver) return

        score++

        val newLimit = maxOf(4, 10 - score / 10)
        timeLeft = newLimit

        cards = generateCardsUseCase(allWords)
    }

    fun onWrongClick() {
        isGameOver = true
    }

    fun restart() {
        score = 0
        timeLeft = 10
        isGameOver = false
        cards = generateCardsUseCase(allWords)
    }
}

