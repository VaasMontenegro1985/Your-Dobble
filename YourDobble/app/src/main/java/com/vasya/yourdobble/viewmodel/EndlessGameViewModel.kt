package com.vasya.yourdobble.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasya.yourdobble.data.repository.ProgressRepository
import com.vasya.yourdobble.data.repository.WordsRepository
import com.vasya.yourdobble.domain.generateCardsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class EndlessGameViewModel(
    wordsRepository: WordsRepository,
    private val progressRepository: ProgressRepository
) : ViewModel() {

    private val allWords =wordsRepository.getWords()

    var score by mutableStateOf(0)
        private set

    var timeLeft by mutableStateOf(10)
        private set

    var isGameOver by mutableStateOf(false)
        private set

    var cards by mutableStateOf(generateCardsUseCase(allWords))
        private set

    var bestScore by mutableStateOf(progressRepository.getBestEndlessScore())
        private set


    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (timeLeft > 0 && isActive) {
                delay(1000)
                timeLeft--
            }

            if (timeLeft <= 0 && !isGameOver) {
                finishGame()
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
        finishGame()
    }

    fun restart() {
        score = 0
        timeLeft = 10
        isGameOver = false
        cards = generateCardsUseCase(allWords)
    }

    private fun finishGame() {
        isGameOver = true

        if (score > bestScore) {
            bestScore = score
            progressRepository.saveBestEndlessScore(score)
        }
    }
}

