package com.example.platform.domain.usecase.weather

abstract class WeatherBaseUsaCase<T>  {
    abstract suspend fun invoke(lat: Double, lon: Double): T
}