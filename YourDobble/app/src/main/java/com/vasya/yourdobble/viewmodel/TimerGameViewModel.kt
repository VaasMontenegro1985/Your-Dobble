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

class TimerGameViewModel(
    wordsRepository: WordsRepository,
    private val progressRepository: ProgressRepository
) : ViewModel() {

    private val allWords = wordsRepository.getWords()

    var score by mutableStateOf(0)
        private set

    var timeLeft by mutableStateOf(60)
        private set

    var isGameOver by mutableStateOf(false)
        private set

    var cards by mutableStateOf(generateCardsUseCase(allWords))
        private set

    var bestScore by mutableStateOf(progressRepository.getBestTimerScore())
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

        cards = generateCardsUseCase(allWords)
    }

    fun onWrongClick() {
        if (isGameOver) return
        timeLeft--
    }

    fun restart() {
        score = 0
        timeLeft = 60
        isGameOver = false
        cards = generateCardsUseCase(allWords)
    }

    private fun finishGame() {
        isGameOver = true

        if (score > bestScore) {
            bestScore = score
            progressRepository.saveBestTimerScore(score)
        }
    }
}