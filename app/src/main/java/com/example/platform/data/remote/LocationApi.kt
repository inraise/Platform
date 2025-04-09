package com.example.platform.data.remote

import com.example.platform.domain.models.location.LocationModel
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

    @GET("reverse")
    suspend fun getUserLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("limit") limit: Int = 1,
        @Query("appid") appid: String = "846bddc7aaba499cd60058e2d06ad6e7"
    ): List<LocationModel>
}