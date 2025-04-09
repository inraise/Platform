package com.example.platform.di

import com.example.platform.data.remote.LocationApi
import com.example.platform.data.remote.NewsApi
import com.example.platform.data.remote.WeatherApi
import com.example.platform.domain.models.location.LocationModel
import com.example.platform.domain.models.weather.WeatherModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Provides
    @Singleton
    fun provideRetrofit(): NewsApi {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofitWeather(): WeatherApi {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofitLocation(): LocationApi {
        return retrofit2.Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/geo/1.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}