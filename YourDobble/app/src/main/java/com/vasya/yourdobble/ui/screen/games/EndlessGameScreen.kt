package com.vasya.yourdobble.ui.screen.games

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.vasya.yourdobble.ui.components.CardWords
import com.vasya.yourdobble.viewmodel.EndlessGameViewModel
@Composable
fun EndlessGameScreen(
    viewModel: EndlessGameViewModel,
    onRestartClick: () -> Unit
) {

    val score = viewModel.score
    val timeLeft = viewModel.timeLeft
    val isGameOver = viewModel.isGameOver
    val cards = viewModel.cards
    val bestScore = viewModel.bestScore

    if (isGameOver) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("Игра окончена")
            Text("Счёт: $score")

            Button(
                onClick = {
                    viewModel.restart()
                    onRestartClick()
                }
            ) {
                Text("Играть снова")
            }
        }

    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Рекорд: $bestScore")
            Text("Счёт: $score")
            Text("Время: $timeLeft")

            Spacer(modifier = Modifier.height(24.dp))

            Text("Найди совпадение")
            CardWords(words = cards.referenceCard, onWordClick = {})

            Spacer(modifier = Modifier.height(24.dp))

            Text("Твоя карточка:")

            CardWords(
                words = cards.playerCard,
                onWordClick = { clickedWord ->
                    if (clickedWord == cards.commonWord) {
                        viewModel.onCorrectClick()
                    } else {
                        viewModel.onWrongClick()
                    }
                }
            )
        }
    }
}
