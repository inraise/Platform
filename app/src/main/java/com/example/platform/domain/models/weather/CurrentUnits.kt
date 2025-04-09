package com.example.platform.domain.models.weather

data class CurrentUnits(
    val temperature_2m: String = "",
    val relative_humidity_2m: String = "",
    val rain: String = "",
    val wind_speed_10m: String = "",
    val apparent_temperature: String = ""
)