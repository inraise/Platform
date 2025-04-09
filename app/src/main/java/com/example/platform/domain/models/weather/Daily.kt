package com.example.platform.domain.models.weather

data class Daily(
    val time: List<String> = listOf(),
    val weather_code: List<Int> = listOf(),
    val temperature_2m_max: List<Double> = listOf(),
    val temperature_2m_min: List<Double> = listOf()
)