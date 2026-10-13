package com.vasya.yourdobble.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vasya.yourdobble.data.repository.ProgressRepository
import com.vasya.yourdobble.data.repository.WordsRepository
import com.vasya.yourdobble.ui.screen.ModeSelectScreen
import com.vasya.yourdobble.ui.screen.StartScreen
import com.vasya.yourdobble.ui.screen.games.EndlessGameScreen
import com.vasya.yourdobble.viewmodel.EndlessGameViewModel
import com.vasya.yourdobble.ui.screen.games.TimerGameScreen
import com.vasya.yourdobble.viewmodel.TimerGameViewModel
import com.vasya.yourdobble.ui.screen.games.SprintGameScreen
import com.vasya.yourdobble.viewmodel.SprintGameViewModel
import com.vasya.yourdobble.viewmodel.factory.EndlessGameViewModelFactory
import com.vasya.yourdobble.viewmodel.factory.TimerGameViewModelFactory
import com.vasya.yourdobble.viewmodel.factory.SprintGameViewModelFactory

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val wordsRepository = remember {
        WordsRepository(context)
    }

    val progressRepository = remember {
        ProgressRepository(context)
    }

    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable("start"){
            StartScreen(
                onSoloClick = {
                    navController.navigate("mode_select")
                },
                onLocalMultiClick = {
                    navController.navigate("mode_select")
                },
                onOnlineMultiClick = {
                    navController.navigate("mode_select")
                },
                onHowToPlayClick = {
                    navController.navigate("rules")
                },
                onSettingsClick = {
                    navController.navigate("settings")
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
            val factory = EndlessGameViewModelFactory(wordsRepository, progressRepository)
            val viewModel: EndlessGameViewModel = viewModel(factory = factory)

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
            val factory = TimerGameViewModelFactory(wordsRepository, progressRepository)
            val viewModel: TimerGameViewModel = viewModel(factory = factory)

            TimerGameScreen(
                viewModel = viewModel,
                onRestartClick = {
                    navController.navigate("timer") {
                        popUpTo("timer") { inclusive = true }
                    }
                }
            )
        }

        composable("sprint"){
            val factory = SprintGameViewModelFactory(wordsRepository, progressRepository)
            val viewModel: SprintGameViewModel = viewModel(factory = factory)

            SprintGameScreen(
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
        composable("settings"){
            // Rules()
        }
    }
}