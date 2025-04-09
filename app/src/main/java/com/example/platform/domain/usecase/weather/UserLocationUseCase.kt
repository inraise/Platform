package com.example.platform.domain.usecase.weather

import com.example.platform.data.repository.DomainRepositoryImpl
import com.example.platform.domain.models.location.LocationModel
import javax.inject.Inject

class UserLocationUseCase @Inject constructor(
    private val domainRepository: DomainRepositoryImpl
) : WeatherBaseUsaCase<List<LocationModel>>() {
    override suspend fun invoke(lat: Double, lon: Double): List<LocationModel> =
        domainRepository.getUserLocation(lat, lon)
}