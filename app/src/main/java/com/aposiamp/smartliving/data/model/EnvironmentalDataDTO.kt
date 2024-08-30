package com.aposiamp.smartliving.data.model

import com.google.firebase.firestore.PropertyName

data class EnvironmentalDataDTO(
    @get:PropertyName("temperature") @set:PropertyName("temperature") var temperature: Float? = null,
    @get:PropertyName("humidity") @set:PropertyName("humidity") var humidity: Float? = null,
    @get:PropertyName("timestamp") @set:PropertyName("timestamp") var timestamp: String? = null
)
