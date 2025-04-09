package com.example.platform.domain.models.location

// http://api.openweathermap.org/geo/1.0/reverse?lat=60.003503&lon=30.330772&limit=1&appid=846bddc7aaba499cd60058e2d06ad6e7

data class LocationModel(
    val name: String = "",
    val country: String = ""
)