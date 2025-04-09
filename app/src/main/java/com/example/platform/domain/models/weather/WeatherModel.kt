package com.example.platform.domain.models.weather

data class WeatherModel(
    val current_units: CurrentUnits = CurrentUnits(),
    val current: Current = Current(),
    val daily: Daily = Daily()
)