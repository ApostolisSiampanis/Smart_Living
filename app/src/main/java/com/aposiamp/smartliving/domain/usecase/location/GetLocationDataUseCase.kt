package com.aposiamp.smartliving.domain.usecase.location

import com.aposiamp.smartliving.domain.mapper.LocationDataMapper
import com.aposiamp.smartliving.domain.model.LocationData
import com.aposiamp.smartliving.domain.repository.LocationRepository

class GetLocationDataUseCase(
    private val locationRepository: LocationRepository
) {
    suspend fun execute(): LocationData {
        val locationDataDTO = locationRepository.getLocationData()
        val locationData = LocationDataMapper.fromDto(locationDataDTO)
        return locationData
    }
}