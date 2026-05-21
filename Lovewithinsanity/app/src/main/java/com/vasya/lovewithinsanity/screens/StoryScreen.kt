package com.vasya.lovewithinsanity.screens

import android.window.SplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun StoryScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ты пришёл сюда из-за суки, из-за Цитры, а за родного брата и пальцем не пошевелил!")
        Button({navController.popBackStack()}){
            Text("Вернуться домой")
        }
    }
}
