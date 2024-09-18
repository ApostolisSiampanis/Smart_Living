package com.aposiamp.smartliving.data.model

import com.google.firebase.firestore.PropertyName

data class PlaceDataDTO(
    @get:PropertyName("placeId") @set:PropertyName("placeId") var placeId: String? = null,
    @get:PropertyName("name") @set:PropertyName("name") var name: String? = null,
    @get:PropertyName("fullAddress") @set:PropertyName("fullAddress") var fullAddress: String? = null,
    @get:PropertyName("location") @set:PropertyName("location") var location: LocationDataDTO? = null
)
