package com.example.platform.presentation.screens.weather

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.platform.R
import com.example.platform.presentation.items.DailyItem
import com.example.platform.presentation.navigation.BottomNavigationItems
import com.example.platform.presentation.screens.news.backTo
import com.example.platform.presentation.theme.MainBlack
import com.example.platform.presentation.theme.MainOrange
import com.example.platform.presentation.theme.publicoFontFamily

sealed class Weather(val image: Int, val title: String) {
    object Sunny : Weather(
        image = R.drawable.sunny,
        title = "Clear Sky"
    )

    object Cloudy : Weather(
        image = R.drawable.cloudy,
        title = "Cloudy"
    )

    object Rain : Weather(
        image = R.drawable.rain,
        title = "Rain"
    )

    object Fog : Weather(
        image = R.drawable.fog,
        title = "Fog"
    )

    object Drizzle : Weather(
        image = R.drawable.drizzle,
        title = "Drizzle"
    )

    object Snow : Weather(
        image = R.drawable.snow,
        title = "Snow"
    )

    object Thunder : Weather(
        image = R.drawable.thunder,
        title = "Thunder"
    )
}

@Composable
fun WeatherScreen(
    navController: NavController,
    locationViewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel,
    location: Double
) {
    if (location != 0.0) {
        val element: Weather =
            when (weatherViewModel.weatherValue.current.weather_code) {
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .clickable {
                            navController.navigate(BottomNavigationItems.HomeScreen.rout) {
                                popUpTo(BottomNavigationItems.HomeScreen.rout) {
                                    saveState = true
                                }
                            }
                        },
                    painter = painterResource(R.drawable.back_icon),
                    contentDescription = ""
                )
            }

            Text(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                text = "Weather",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MainBlack
            )

            Text(
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 15.dp)
                    .fillMaxWidth(),
                text = locationViewModel.locationValue.name + ", " +
                        locationViewModel.locationValue.country,
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp,
                color = MainOrange
            )

            Image(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(15.dp)
                    .fillMaxWidth(0.85f),
                painter = painterResource(element.image),
                contentDescription = ""
            )

            Text(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                text = "${element.title} | ${weatherViewModel.weatherValue.current.temperature_2m}" +
                        weatherViewModel.weatherValue.current_units.temperature_2m,
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = MainBlack,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${weatherViewModel.weatherValue.current.wind_speed_10m}" +
                                weatherViewModel.weatherValue.current_units.wind_speed_10m,
                        fontFamily = publicoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MainBlack,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Wind Speed",
                        fontFamily = publicoFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = MainBlack,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                        .width(3.dp)
                        .background(Color(0xFFEEEEEE))
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${weatherViewModel.weatherValue.current.relative_humidity_2m}" +
                                weatherViewModel.weatherValue.current_units.relative_humidity_2m,
                        fontFamily = publicoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MainBlack,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Humidity",
                        fontFamily = publicoFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = MainBlack,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                        .width(3.dp)
                        .background(Color(0xFFEEEEEE))
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${weatherViewModel.weatherValue.current.apparent_temperature}" +
                                weatherViewModel.weatherValue.current_units.apparent_temperature,
                        fontFamily = publicoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MainBlack,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Apparent",
                        fontFamily = publicoFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = MainBlack,
                        textAlign = TextAlign.Center
                    )
                }
            }

            LazyRow(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            ) {
                items(weatherViewModel.weatherValue.daily.time) { item ->
                    val index = weatherViewModel.weatherValue.daily.time.indexOf(item)
                    DailyItem(
                        time = item,
                        tempMin = weatherViewModel.weatherValue.daily.temperature_2m_min[index],
                        tempMax = weatherViewModel.weatherValue.daily.temperature_2m_max[index],
                        code = weatherViewModel.weatherValue.daily.weather_code[index]
                    )
                }
            }
        }
    } else {
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
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .clickable {
                            navController.navigate(BottomNavigationItems.HomeScreen.rout) {
                                popUpTo(BottomNavigationItems.HomeScreen.rout) {
                                    saveState = true
                                }
                            }
                        },
                    painter = painterResource(R.drawable.back_icon),
                    contentDescription = ""
                )
            }

            Text(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                text = "Weather",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MainBlack
            )

            Text(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                text = "Sorry, there were problems loading the location.",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MainOrange,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                text = "Please check your settings or restart the application.",
                fontFamily = publicoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = MainBlack,
                textAlign = TextAlign.Center
            )
        }
    }
}