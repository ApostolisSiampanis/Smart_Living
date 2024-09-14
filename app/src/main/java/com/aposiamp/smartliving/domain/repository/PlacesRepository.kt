package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.AutoCompletePredictionData

interface PlacesRepository {
    suspend fun getAutocompletePredictions(query: String) : List<AutoCompletePredictionData>
}