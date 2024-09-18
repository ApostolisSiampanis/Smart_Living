package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.domain.model.LocationData
import com.aposiamp.smartliving.presentation.model.LocationDataUiModel

object LocationDataUiMapper {
    fun fromDomain(domain: LocationData): LocationDataUiModel {
        return LocationDataUiModel(
            latitude = domain.latitude,
            longitude = domain.longitude
        )
    }
}