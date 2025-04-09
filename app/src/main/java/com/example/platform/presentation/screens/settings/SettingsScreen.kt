package com.example.platform.presentation.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.platform.R
import com.example.platform.presentation.theme.MainBlack
import com.example.platform.presentation.theme.publicoFontFamily

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth(),
            text = "Settings",
            fontFamily = publicoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MainBlack
        )

        Spacer(modifier = Modifier.height(20.dp))

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0XFFEEEEEE))
        )

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Application and Content language",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MainBlack
            )

            Text(
                text = "us",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = Color.LightGray
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0XFFEEEEEE))
        )

        // ----

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Your coordinates",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MainBlack
            )

            Text(
                text = "0.0 0.0",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = Color.LightGray
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(MainBlack)
        )

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "About us",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MainBlack
            )

            Image(
                modifier = Modifier
                    .size(18.dp)
                    .scale(scaleX = -1f, scaleY = 1f),
                painter = painterResource(R.drawable.back_icon),
                contentDescription = ""
            )
        }
    }
}