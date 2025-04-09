package com.example.platform.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.platform.presentation.screens.weather.Weather
import com.example.platform.presentation.theme.MainBlack
import com.example.platform.presentation.theme.MainOrange
import com.example.platform.presentation.theme.publicoFontFamily

@Composable
fun DailyItem(code: Int, tempMax: Double, tempMin: Double, time: String) {
    val element: Weather =
        when (code) {
            0 -> Weather.Sunny
            1, 2, 3 -> Weather.Cloudy
            45, 48 -> Weather.Fog
            51, 53, 55, 56, 57 -> Weather.Drizzle
            71, 73, 75, 77, 85, 86 -> Weather.Snow
            95, 96, 99 -> Weather.Thunder
            else -> Weather.Rain
        }

    Column(
        modifier = Modifier
            .padding(top = 15.dp)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time,
            fontFamily = publicoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = MainBlack,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = element.title,
            fontFamily = publicoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = MainBlack,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(element.image),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "↑ $tempMax",
            fontFamily = publicoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            color = MainBlack,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "↓ $tempMin",
            fontFamily = publicoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            color = MainOrange,
            textAlign = TextAlign.Center
        )
    }
}