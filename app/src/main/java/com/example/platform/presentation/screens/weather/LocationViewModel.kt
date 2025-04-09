package com.example.platform.presentation.screens.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platform.domain.models.location.LocationModel
import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.domain.usecase.LoadSearchNewsUseCase
import com.example.platform.domain.usecase.weather.UserLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val locationUseCase: UserLocationUseCase) :
    ViewModel() {
    private var location by mutableStateOf(LocationModel())

    val locationValue
        get() = location

    fun getLocation(lat: Double, lon: Double) {
        viewModelScope.launch {
            location = locationUseCase.invoke(lat, lon)[0].copy()
        }
    }
}