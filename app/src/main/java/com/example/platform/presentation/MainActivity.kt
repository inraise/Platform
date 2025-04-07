package com.example.platform.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.platform.presentation.navigation.SetupNavigationGraph
import com.example.platform.presentation.screens.main.BottomBarScreen
import com.example.platform.presentation.screens.search.SearchViewModel
import com.example.platform.presentation.theme.PlatformTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(500)
            keepSplashScreen = false
        }
        enableEdgeToEdge()
        setContent {
            PlatformTheme {
                val navHost = rememberNavController()
                var buttonsVisible by remember { mutableStateOf(true) }

                Scaffold(bottomBar = {
                    if (buttonsVisible) {
                        BottomBarScreen(
                            navController = navHost,
                            state = buttonsVisible,
                            modifier = Modifier
                        )
                    }
                }) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        SetupNavigationGraph(
                            navHostController = navHost,
                            searchViewModel = searchViewModel
                        ) { isVisible ->
                            buttonsVisible = isVisible
                        }
                    }
                }
            }
        }
    }
}