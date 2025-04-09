package com.example.platform.data.remote

import com.example.platform.domain.models.location.LocationModel
import com.example.platform.domain.models.weather.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,relative_humidity_2m,weather_code,rain,wind_speed_10m,apparent_temperature",
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min",
        @Query("wind_speed_unit") wind_speed_unit: String = "ms",
    ): WeatherModel
}