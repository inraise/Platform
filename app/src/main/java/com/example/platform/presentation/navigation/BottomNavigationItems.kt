package com.example.platform.presentation.navigation

import com.example.platform.R

sealed class BottomNavigationItems(val rout: String, val unselectedIcon: Int, val selectedIcon: Int) {
    object HomeScreen : BottomNavigationItems(
        rout = "home_screen",
        unselectedIcon = R.drawable.home_unselected,
        selectedIcon = R.drawable.home_selected
    )
    object SearchScreen : BottomNavigationItems(
        rout = "search_screen",
        unselectedIcon = R.drawable.search_unselected,
        selectedIcon = R.drawable.search_selected
    )
    object SettingsScreen : BottomNavigationItems(
        rout = "settings_screen",
        unselectedIcon = R.drawable.settings_unselected,
        selectedIcon = R.drawable.settings_selected
    )
    object BookmarkScreen : BottomNavigationItems(
        rout = "bookmark_screen",
        unselectedIcon = R.drawable.bookmark_unselected,
        selectedIcon = R.drawable.bookmark_selected
    )
}