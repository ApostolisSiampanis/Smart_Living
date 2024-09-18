package com.aposiamp.smartliving.domain.model

data class PlaceData(
    val placeId: String?,
    val name: String?,
    val fullAddress: String?,
    val location: LocationData?
)
