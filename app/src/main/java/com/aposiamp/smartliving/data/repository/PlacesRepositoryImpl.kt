package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.AutoCompletePredictionData
import com.aposiamp.smartliving.data.source.remote.PlacesDataSource
import com.aposiamp.smartliving.domain.repository.PlacesRepository

class PlacesRepositoryImpl(
    private val placesDataSource: PlacesDataSource
) : PlacesRepository {
    override suspend fun getAutocompletePredictions(query: String): List<AutoCompletePredictionData> {
        return placesDataSource.getAutocompletePredictions(query)
    }
}