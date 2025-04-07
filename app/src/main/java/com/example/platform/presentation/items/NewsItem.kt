package com.example.platform.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.platform.domain.models.top_headlines.Article
import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.presentation.navigation.Screens
import com.example.platform.presentation.screens.news.backTo
import com.example.platform.presentation.theme.MainBlack
import com.example.platform.presentation.theme.MainOrange
import com.example.platform.presentation.theme.publicoFontFamily

var dataNews: Article = Article()

@Composable
fun NewsItem(navController: NavController, data: Article, backToScreen: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clickable {
                    dataNews = data
                    backTo = backToScreen
                    navController.navigate(Screens.NewsScreenType.rout) {
                        popUpTo(Screens.NewsScreenType.rout) {
                            saveState = true
                        }
                    }
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(120.dp)
                    .background(Color(0xFFEEEEEE)),
                painter = rememberAsyncImagePainter(data.urlToImage),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = data.title,
                    fontFamily = publicoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = MainBlack
                )

                Text(
                    text = "By " + if (data.author == null || data.author == "") data.source.name
                    else data.author,
                    fontSize = 12.sp,
                    fontFamily = publicoFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.LightGray
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = data.source.name,
                        fontSize = 13.sp,
                        fontFamily = publicoFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = MainOrange
                    )

                    Text(
                        text = data.publishedAt.substring(11, 16),
                        fontSize = 12.sp,
                        fontFamily = publicoFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.LightGray
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFD5D1C9))
        )
    }
}