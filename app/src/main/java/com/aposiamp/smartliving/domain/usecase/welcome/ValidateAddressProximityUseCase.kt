package com.aposiamp.smartliving.domain.usecase.welcome

import com.aposiamp.smartliving.domain.model.PlaceData
import com.aposiamp.smartliving.domain.usecase.location.GetLocationDataUseCase
import com.aposiamp.smartliving.domain.usecase.places.GetLocationFromPlaceIdUseCase
import com.aposiamp.smartliving.domain.utils.DistanceUtils

class ValidateAddressProximityUseCase(
    private val getLocationDataUseCase: GetLocationDataUseCase,
    private val getLocationFromPlaceIdUseCase: GetLocationFromPlaceIdUseCase
) {
    suspend fun execute(placeId: String): PlaceData? {
        val currentLocation = getLocationDataUseCase.execute()
        val addressLocation = getLocationFromPlaceIdUseCase.execute(placeId)

        val distance = DistanceUtils.calculateDistance (
            currentLocation.latitude ?: 0.0,
            currentLocation.longitude ?: 0.0,
            addressLocation.location?.latitude ?: 0.0,
            addressLocation.location?.longitude ?: 0.0
        )
        return if (distance <= 1000) addressLocation else null
    }
}