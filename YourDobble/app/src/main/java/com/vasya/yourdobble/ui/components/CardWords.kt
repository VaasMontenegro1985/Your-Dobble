package com.vasya.yourdobble.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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