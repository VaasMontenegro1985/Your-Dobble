package com.vasya.yourdobble.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp

@Composable
fun ModeSelectScreen(
    onModeEndlessClick: () -> Unit,
    onModeTimerClick: () -> Unit,
    onModeSprintClick: () -> Unit,
    ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button(onClick = onModeEndlessClick){
            Text("Бесконечный режим")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onModeTimerClick){
            Text("Игра на время")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onModeSprintClick){
            Text("Забег")
        }
    }
}