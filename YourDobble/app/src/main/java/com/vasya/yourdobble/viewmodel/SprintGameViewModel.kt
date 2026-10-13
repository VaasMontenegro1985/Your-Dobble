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

class SprintGameViewModel(
    wordsRepository: WordsRepository,
    private val progressRepository: ProgressRepository
) : ViewModel() {

    private val allWords = wordsRepository.getWords()

    var time by mutableStateOf(0)
        private set

    var isGameOver by mutableStateOf(false)

    var cardsLeft by mutableStateOf(25)

    var cards by mutableStateOf(generateCardsUseCase(allWords))

    var bestTime by mutableStateOf(progressRepository.getBestSprintTime())
        private set


    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (!isGameOver && isActive) {
                delay(1000)

                if (!isGameOver) {
                    time++
                }
            }
        }
    }

    fun onCorrectClick() {
        if (isGameOver) return

        cardsLeft--

        if (cardsLeft <= 0) {
            finishGame()
            return
        }

        cards = generateCardsUseCase(allWords)
    }

    fun onWrongClick(){
        finishGame()
    }

    fun restart() {
        cardsLeft = 25
        time = 0
        isGameOver = false
        cards = generateCardsUseCase(allWords)
    }

    private fun finishGame() {
        if (isGameOver) return

        isGameOver = true

        if (time < bestTime) {
            bestTime = time
            progressRepository.saveBestSprintTime(time)
        }
    }
}



