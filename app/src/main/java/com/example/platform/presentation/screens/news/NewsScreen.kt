package com.example.platform.presentation.screens.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.platform.R
import com.example.platform.domain.models.top_headlines.Article
import com.example.platform.presentation.navigation.BottomNavigationItems
import com.example.platform.presentation.screens.main.BottomBarScreen
import com.example.platform.presentation.theme.MainBlack
import com.example.platform.presentation.theme.publicoFontFamily

var backTo: String = BottomNavigationItems.HomeScreen.rout

@Composable
fun NewsScreen(data: Article, navController: NavController) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .clickable {
                        navController.navigate(backTo) {
                            popUpTo(backTo) {
                                saveState = true
                            }
                        }
                    },
                painter = painterResource(R.drawable.back_icon),
                contentDescription = ""
            )

            Image(
                modifier = Modifier
                    .padding(end = 25.dp)
                    .fillMaxWidth(0.1f)
                    .clickable {

                    },
                painter = painterResource(R.drawable.bookmark_icon),
                contentDescription = ""
            )
        }

        Image(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.28f)
                .background(Color(0xFFEEEEEE)),
            painter = rememberAsyncImagePainter(data.urlToImage),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.padding(15.dp),
            text = data.title,
            fontFamily = publicoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = MainBlack
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.3f),
                text = "By " + if (data.author == null || data.author == "") data.source.name
                else data.author,
                fontSize = 15.sp,
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray
            )

            Text(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = data.publishedAt.substring(0, 10) + " " + data.publishedAt.substring(11, 16),
                fontSize = 15.sp,
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray
            )
        }

        Text(
            modifier = Modifier.padding(15.dp),
            text = if (data.description != null) data.description else "",
            fontFamily = publicoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.Black
        )

        Button(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .height(60.dp), onClick = {
                uriHandler.openUri(data.url)
            }, colors = ButtonDefaults.buttonColors(containerColor = MainBlack)
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = "Read completely",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}