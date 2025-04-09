package com.example.platform.presentation.screens.search

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.platform.R
import com.example.platform.presentation.items.NewsItem
import com.example.platform.presentation.navigation.BottomNavigationItems
import com.example.platform.presentation.navigation.Screens
import com.example.platform.presentation.screens.main.category
import com.example.platform.presentation.theme.MainBlack
import com.example.platform.presentation.theme.publicoFontFamily

@Composable
fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel) {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            TextField(
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Search",
                        fontWeight = FontWeight.Medium,
                        fontFamily = publicoFontFamily,
                        color = MainBlack,
                        fontSize = 15.sp
                    )
                },
                leadingIcon = {
                    Image(
                        modifier = Modifier
                            .size(22.dp)
                            .clickable {
                                searchViewModel.getSearchNews(searchText)
                                context.hideKeyboard()
                            },
                        painter = painterResource(R.drawable.search_unselected),
                        contentDescription = ""
                    )
                },
                value = searchText, onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                textStyle = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontFamily = publicoFontFamily,
                    color = MainBlack,
                    fontSize = 15.sp
                ),
                shape = RoundedCornerShape(40.dp),
                keyboardActions = KeyboardActions(onDone = {
                    searchViewModel.getSearchNews(searchText)
                    context.hideKeyboard()
                }),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFEEEEEE),
                    textColor = MainBlack,
                    cursorColor = MainBlack,
                    placeholderColor = MainBlack,
                    focusedIndicatorColor = Color.White,
                    unfocusedLabelColor = Color(0xFFEEEEEE),
                    focusedLabelColor = MainBlack,
                    unfocusedIndicatorColor = Color.White,
                    leadingIconColor = MainBlack
                )
            )
        }

        items(searchViewModel.searchDataValue.articles) { item ->
            NewsItem(navController = navController, item, BottomNavigationItems.SearchScreen.rout)
        }
    }
}

fun Context.findActivity(): Activity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) return currentContext
        currentContext = currentContext.baseContext
    }
    return null
}

fun Context.hideKeyboard() {
    findActivity()?.let { activity ->
        val view = activity.currentFocus ?: View(activity)
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}