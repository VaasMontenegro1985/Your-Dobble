package com.vasya.lovewithinsanity.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vasya.lovewithinsanity.screens.LibraryScreen
import com.vasya.lovewithinsanity.screens.SplashScreen
import com.vasya.lovewithinsanity.screens.StoryScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash"){
            SplashScreen(navController)
        }

        composable("library") {
            LibraryScreen(navController)
        }

        composable("story") {
            StoryScreen(navController)
        }
    }
}
