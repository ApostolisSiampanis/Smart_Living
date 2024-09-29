package com.aposiamp.smartliving.domain.usecase.places

import com.aposiamp.smartliving.domain.mapper.AutocompletePredictionDataMapper
import com.aposiamp.smartliving.domain.model.AutoCompletePredictionData
import com.aposiamp.smartliving.domain.repository.PlacesRepository

class GetAutoCompleteSuggestionsUseCase(
    private val placesRepository: PlacesRepository
) {
    suspend fun execute(query: String): List<AutoCompletePredictionData> {
        val predictionsData =  placesRepository.getAutocompletePredictions(query)
        val predictions = AutocompletePredictionDataMapper.toDomainList(predictionsData)
        return predictions
    }
}