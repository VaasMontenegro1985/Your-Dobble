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
import com.vasya.lovewithinsanity.R
import com.vasya.lovewithinsanity.model.Story

@Composable
fun LibraryScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Выбери историю по вкусу")
        val volleyballStory = Story(
            id = "story_1",
            title = "Безумный волейбол",
            description = "Ты новый участник воллейбольной команды. Узнай сможешь ли ты выжить в схватке с сильнейшими?",
            coverResId = R.drawable.vollyevaas

        )
        Button({navController.navigate("story")}) {
            Text("Убить воина")
        }
    }
}
