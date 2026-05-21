package com.vasya.yourdobble.ui.screen.games

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

data class CardsData(
    val referenceCard: List<String>,
    val playerCard: List<String>,
    val commonWord: String
)

@Composable
fun EndlessGameScreen(
    viewModel: GameViewModel,
    onRestartClick: () -> Unit
) {

    val score = viewModel.score
    val timeLeft = viewModel.timeLeft
    val isGameOver = viewModel.isGameOver
    val cards = viewModel.cards

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

@Composable
fun CardWords(
    words: List<String>,
    onWordClick: (String) -> Unit
){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        words.forEach { word ->
            Button(
                onClick = {onWordClick(word) },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(word)
            }
        }
    }
}

fun generateCards(allWords: List<String>): CardsData {
    val cardSize = 4

    val commonWord = allWords.random()

    val otherWords = allWords
        .filter { it != commonWord }
        .shuffled()

    val referenceUniqueWords = otherWords.take(cardSize - 1)

    val playerUniqueWords = otherWords
        .drop(cardSize - 1)
        .take(cardSize - 1)

    val referenceCard = (referenceUniqueWords + commonWord).shuffled()
    val playerCard = (playerUniqueWords + commonWord).shuffled()

    return CardsData(
        referenceCard = referenceCard,
        playerCard = playerCard,
        commonWord = commonWord
    )
}