package com.example.platform.presentation.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.platform.R
import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.presentation.items.ErrorItem
import com.example.platform.presentation.items.LoadItem
import com.example.platform.presentation.items.NewsItem
import com.example.platform.presentation.navigation.BottomNavigationItems
import com.example.platform.presentation.navigation.Screens
import com.example.platform.presentation.theme.MainBlack
import com.example.platform.presentation.theme.MainOrange
import com.example.platform.presentation.theme.publicoFontFamily

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
            MainScreenContent(state.data, navController)
        }

        state.error != null -> {
            Log.d("checkData", "Error ${state.error}")
            ErrorItem(state.error) {
                viewModel.sendEvent(MainScreenEvent.LoadingData)
            }
        }
    }
}

var category: String by mutableStateOf("general")

@Composable
fun MainScreenContent(
    data: TopHeadlinesModel,
    navController: NavController
) {
    val categoryList: List<String> =
        listOf("general", "business", "technology", "sports", "science", "entertainment", "health")


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(top = 30.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    painter = painterResource(R.drawable.main_logo),
                    contentDescription = ""
                )

                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .clickable {
                            navController.navigate(Screens.WeatherScreenType.rout) {
                                popUpTo(Screens.WeatherScreenType.rout) {
                                    saveState = true
                                }
                            }
                        },
                    painter = painterResource(R.drawable.sunny),
                    contentDescription = ""
                )
            }
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                items(categoryList) { item ->
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                                .clickable {
                                    category = item
                                    navController.navigate(BottomNavigationItems.HomeScreen.rout) {
                                        popUpTo(BottomNavigationItems.HomeScreen.rout) {
                                            saveState = false
                                        }
                                    }
                                },
                            text = item,
                            fontWeight = FontWeight.Medium,
                            fontFamily = publicoFontFamily,
                            color = if (category != item) Color.LightGray else MainBlack,
                            fontSize = 20.sp
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(165.dp)
                                .height(2.dp)
                                .background(if (category != item) Color.Black else MainOrange)
                        )
                    }
                }
            }
        }
        items(data.articles) { item ->
            NewsItem(navController = navController, item, BottomNavigationItems.HomeScreen.rout)
        }
    }
}