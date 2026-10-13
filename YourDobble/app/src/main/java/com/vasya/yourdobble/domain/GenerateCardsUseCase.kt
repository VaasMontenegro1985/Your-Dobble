package com.vasya.yourdobble.domain

import com.vasya.yourdobble.model.CardsData


fun generateCardsUseCase(allWords: List<String>): CardsData {
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