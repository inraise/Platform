package com.example.platform.domain.repository

import com.example.platform.domain.models.location.LocationModel
import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.domain.models.weather.WeatherModel

interface DomainRepository {
    suspend fun getTopHeadlines(category: String): TopHeadlinesModel
    suspend fun getSearchNews(q: String): TopHeadlinesModel
    suspend fun getWeather(lat: Double, lon: Double): WeatherModel
    suspend fun getUserLocation(lat: Double, lon: Double): List<LocationModel>
}