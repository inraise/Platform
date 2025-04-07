package com.example.platform.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.platform.presentation.items.dataNews
import com.example.platform.presentation.screens.bookmark.BookmarkScreen
import com.example.platform.presentation.screens.main.MainScreen
import com.example.platform.presentation.screens.news.NewsScreen
import com.example.platform.presentation.screens.search.SearchScreen
import com.example.platform.presentation.screens.search.SearchViewModel
import com.example.platform.presentation.screens.settings.SettingsScreen

sealed class Screens(val rout: String) {
    object NewsScreenType : Screens(rout = "news_screen")
}

@Composable
fun SetupNavigationGraph(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel,
    onBottomBarVisibilityChanged: (Boolean) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavigationItems.HomeScreen.rout
    ) {
        composable(BottomNavigationItems.HomeScreen.rout) {
            onBottomBarVisibilityChanged(true)
            MainScreen(navController = navHostController)
        }
        composable(BottomNavigationItems.SearchScreen.rout) {
            onBottomBarVisibilityChanged(true)
            SearchScreen(navHostController, searchViewModel)
        }
        composable(BottomNavigationItems.SettingsScreen.rout) {
            onBottomBarVisibilityChanged(true)
            SettingsScreen()
        }
        composable(BottomNavigationItems.BookmarkScreen.rout) {
            onBottomBarVisibilityChanged(true)
            BookmarkScreen()
        }
        composable(Screens.NewsScreenType.rout) {
            onBottomBarVisibilityChanged(false)
            NewsScreen(dataNews, navHostController)
        }
    }
}