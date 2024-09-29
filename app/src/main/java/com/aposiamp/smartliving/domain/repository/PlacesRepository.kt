package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.AutoCompletePredictionDTO
import com.aposiamp.smartliving.data.model.PlaceDataDTO

interface PlacesRepository {
    suspend fun getAutocompletePredictions(query: String) : List<AutoCompletePredictionDTO>
    suspend fun getLocationFromPlaceId(placeId: String): PlaceDataDTO
}