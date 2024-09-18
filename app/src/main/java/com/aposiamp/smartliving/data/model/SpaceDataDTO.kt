package com.aposiamp.smartliving.data.model

import com.google.firebase.database.PropertyName

data class SpaceDataDTO(
    @get:PropertyName("placeId") @set:PropertyName("placeId") var placeId: String? = null,
    @get:PropertyName("place_name") @set:PropertyName("place_name") var placeName: String? = null,
    @get:PropertyName("space_name") @set:PropertyName("space_name") var spaceName: String? = null,
    @get:PropertyName("full_address") @set:PropertyName("full_address") var fullAddress: String? = null,
    @get:PropertyName("location") @set:PropertyName("location") var location: LocationDataDTO? = null
)
