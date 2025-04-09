package com.example.platform.data.repository

import com.example.platform.data.remote.LocationApi
import com.example.platform.data.remote.NewsApi
import com.example.platform.data.remote.WeatherApi
import com.example.platform.domain.models.location.LocationModel
import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.domain.models.weather.WeatherModel
import com.example.platform.domain.repository.DomainRepository
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val weatherApi: WeatherApi,
    private val locationApi: LocationApi
) : DomainRepository {
    override suspend fun getTopHeadlines(category: String): TopHeadlinesModel {
        return newsApi.getTopHeadlines(category = category)
    }

    override suspend fun getSearchNews(q: String): TopHeadlinesModel {
        return newsApi.getSearchNews(q = q)
    }

    override suspend fun getWeather(lat: Double, lon: Double): WeatherModel {
        return weatherApi.getWeather(latitude = lat, longitude = lon)
    }

    override suspend fun getUserLocation(lat: Double, lon: Double): List<LocationModel> {
        return locationApi.getUserLocation(lat, lon)
    }
}