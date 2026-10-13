package com.vasya.yourdobble.ui.screen.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vasya.yourdobble.ui.components.CardWords
import com.vasya.yourdobble.viewmodel.EndlessGameViewModel
import com.vasya.yourdobble.viewmodel.SprintGameViewModel

@Composable
fun SprintGameScreen(
    viewModel: SprintGameViewModel,
    onRestartClick: () -> Unit
) {

    val time = viewModel.time
    val isGameOver = viewModel.isGameOver
    val cards = viewModel.cards
    val bestTime = viewModel.bestTime

    if (isGameOver) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("Игра окончена")
            Text("Время: $time")

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

            Text("Рекорд: $bestTime")
            Text("Время: $time")

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
