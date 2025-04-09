package com.example.platform.presentation.screens.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platform.domain.models.location.LocationModel
import com.example.platform.domain.models.weather.WeatherModel
import com.example.platform.domain.usecase.weather.LoadWeatherUseCase
import com.example.platform.domain.usecase.weather.UserLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherUseCase: LoadWeatherUseCase) :
    ViewModel() {
    private var weather by mutableStateOf(WeatherModel())

    val weatherValue
        get() = weather

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            weather = weatherUseCase.invoke(lat, lon).copy()
        }
    }
}