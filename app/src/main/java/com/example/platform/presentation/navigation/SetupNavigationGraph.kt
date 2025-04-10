package com.example.platform.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.platform.presentation.items.dataNews
import com.example.platform.presentation.screens.main.MainScreen
import com.example.platform.presentation.screens.news.NewsScreen
import com.example.platform.presentation.screens.search.SearchScreen
import com.example.platform.presentation.screens.search.SearchViewModel
import com.example.platform.presentation.screens.settings.SettingsScreen
import com.example.platform.presentation.screens.weather.LocationViewModel
import com.example.platform.presentation.screens.weather.WeatherScreen
import com.example.platform.presentation.screens.weather.WeatherViewModel

sealed class Screens(val rout: String) {
    object NewsScreenType : Screens(rout = "news_screen")
    object WeatherScreenType : Screens(rout = "weather_screen")
}

@Composable
fun SetupNavigationGraph(
    navHostController: NavHostController,
    locationViewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel,
    searchViewModel: SearchViewModel,
    lat: Double,
    lon: Double,
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
            SettingsScreen(lat, lon)
        }
        composable(Screens.NewsScreenType.rout) {
            onBottomBarVisibilityChanged(false)
            NewsScreen(dataNews, navHostController)
        }
        composable(Screens.WeatherScreenType.rout) {
            onBottomBarVisibilityChanged(false)
            WeatherScreen(navHostController, locationViewModel, weatherViewModel, lat + lon)
        }
    }
}