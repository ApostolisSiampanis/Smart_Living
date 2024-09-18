package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.LocationDataDTO
import com.aposiamp.smartliving.data.source.local.LocationDataSource
import com.aposiamp.smartliving.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource
) : LocationRepository {
    override suspend fun getLocationData(): LocationDataDTO {
        val location = locationDataSource.getCurrentLocation()
        return LocationDataDTO(
            latitude = location?.latitude,
            longitude = location?.longitude
        )
    }
}