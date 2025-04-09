package com.example.platform.domain.usecase.weather

import com.example.platform.data.repository.DomainRepositoryImpl
import com.example.platform.domain.models.weather.WeatherModel
import javax.inject.Inject

class LoadWeatherUseCase @Inject constructor(
    private val domainRepository: DomainRepositoryImpl
) : WeatherBaseUsaCase<WeatherModel>() {
    override suspend fun invoke(lat: Double, lon: Double): WeatherModel =
        domainRepository.getWeather(lat, lon)
}