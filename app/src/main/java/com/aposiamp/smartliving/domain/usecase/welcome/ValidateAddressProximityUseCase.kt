package com.aposiamp.smartliving.domain.usecase.welcome

import com.aposiamp.smartliving.domain.model.PlaceData
import com.aposiamp.smartliving.domain.usecase.location.GetLocationDataUseCase
import com.aposiamp.smartliving.domain.usecase.places.GetLocationFromPlaceIdUseCase
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class ValidateAddressProximityUseCase(
    private val getLocationDataUseCase: GetLocationDataUseCase,
    private val getLocationFromPlaceIdUseCase: GetLocationFromPlaceIdUseCase
) {
    suspend fun execute(placeId: String): PlaceData? {
        val currentLocation = getLocationDataUseCase.execute()
        val addressLocation = getLocationFromPlaceIdUseCase.execute(placeId)

        val distance = calculateDistance (
            currentLocation.latitude ?: 0.0,
            currentLocation.longitude ?: 0.0,
            addressLocation.location?.latitude ?: 0.0,
            addressLocation.location?.longitude ?: 0.0
        )
        return if (distance <= 1000) addressLocation else null
    }

    private fun calculateDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val earthRadius = 6371000.0 // meters
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadius * c
    }
}