package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.AutoCompletePredictionData
import com.aposiamp.smartliving.data.model.PlaceDataDTO

interface PlacesRepository {
    suspend fun getAutocompletePredictions(query: String) : List<AutoCompletePredictionData>
    suspend fun getLocationFromPlaceId(placeId: String): PlaceDataDTO
}