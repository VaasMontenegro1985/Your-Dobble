package com.vasya.yourdobble.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vasya.yourdobble.data.repository.WordsRepository
import com.vasya.yourdobble.ui.screen.ModeSelectScreen
import com.vasya.yourdobble.ui.screen.StartScreen
import com.vasya.yourdobble.ui.screen.games.EndlessGameScreen
import com.vasya.yourdobble.ui.screen.games.GameViewModel
import com.vasya.yourdobble.ui.screen.games.GameViewModelFactory

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val repository = WordsRepository(context)

    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable("start"){
            StartScreen(
                onStartCLick = {
                    navController.navigate("mode_select")
                }
            )
        }

        composable("mode_select") {
            ModeSelectScreen(
                onModeEndlessClick = {
                    navController.navigate("endless")
                },
                onModeTimerClick = {
                    navController.navigate("timer")
                },
                onModeSprintClick = {
                    navController.navigate("sprint")
                }
            )
        }

        composable("endless"){
            val factory = GameViewModelFactory(repository)
            val viewModel: GameViewModel = viewModel(factory = factory)

            EndlessGameScreen(
                viewModel = viewModel,
                onRestartClick = {
                    navController.navigate("endless") {
                        popUpTo("endless") { inclusive = true }
                    }
                }
            )
        }

        composable("timer"){
            val factory = GameViewModelFactory(repository)
            val viewModel: GameViewModel = viewModel(factory = factory)

            EndlessGameScreen(
                viewModel = viewModel,
                onRestartClick = {
                    navController.navigate("timer") {
                        popUpTo("timer") { inclusive = true }
                    }
                }
            )
        }

        composable("sprint"){
            val factory = GameViewModelFactory(repository)
            val viewModel: GameViewModel = viewModel(factory = factory)

            EndlessGameScreen(
                viewModel = viewModel,
                onRestartClick = {
                    navController.navigate("sprint") {
                        popUpTo("sprint") { inclusive = true }
                    }
                }
            )
        }

        composable("settings"){
            // SettingsScreen()
        }
    }
}