package com.example.platform.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.platform.presentation.navigation.BottomNavigationItems

@Composable
fun BottomBarScreen(
    navController: NavHostController,
    state: Boolean,
    modifier: Modifier = Modifier
) {
    val screens = listOf(
        BottomNavigationItems.HomeScreen,
        BottomNavigationItems.SearchScreen,
        BottomNavigationItems.BookmarkScreen,
        BottomNavigationItems.SettingsScreen
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    if (currentRoute == screen.rout) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(screen.selectedIcon),
                            contentDescription = ""
                        )
                    } else {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(screen.unselectedIcon),
                            contentDescription = ""
                        )
                    }
                },
                selected = currentRoute == screen.rout,
                onClick = {
                    navController.navigate(screen.rout) {
                        popUpTo(screen.rout) {
                            saveState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Black,
                    indicatorColor = Color.White
                )
            )
        }
    }
}