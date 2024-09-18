package com.aposiamp.smartliving.data.model

import com.google.firebase.firestore.PropertyName

data class LocationDataDTO(
    @get:PropertyName("latitude") @set:PropertyName("latitude") var latitude: Double? = null,
    @get:PropertyName("longitude") @set:PropertyName("longitude") var longitude: Double? = null
)
