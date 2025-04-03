package com.example.platform.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.platform.presentation.screens.main.MainScreen

sealed class Screens(val rout: String) {
    object MainScreenType : Screens(rout = "main_screen")
}

@Composable
fun SetupNavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.MainScreenType.rout) {
        composable(Screens.MainScreenType.rout) {
            MainScreen(navController = navHostController)
        }
    }
}