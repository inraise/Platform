package com.example.platform.domain.models.weather

data class Current(
    val temperature_2m: Double = 0.0,
    val relative_humidity_2m: Int = 0,
    val weather_code: Int = 0,
    val rain: Double = 0.0,
    val wind_speed_10m: Double = 0.0,
    val apparent_temperature: Double = 0.0
)