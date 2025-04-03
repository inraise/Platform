package com.example.platform.presentation.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.presentation.items.ErrorItem
import com.example.platform.presentation.items.LoadItem

@Composable
fun MainScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            Log.d("checkData", "Loading...")
            LoadItem()
        }

        state.data.status != "error" -> {
            Log.d("checkData", "data: ${state.data.status}")
            MainScreenContent(state.data)
        }

        state.error != null -> {
            Log.d("checkData", "Error ${state.error}")
            ErrorItem(state.error) {
                viewModel.sendEvent(MainScreenEvent.LoadingData)
            }
        }
    }
}

@Composable
fun MainScreenContent(data: TopHeadlinesModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = data.status)
    }
}