package com.aposiamp.smartliving.domain.usecase.places

import com.aposiamp.smartliving.domain.mapper.AutocompletePredictionDataMapper
import com.aposiamp.smartliving.domain.model.AutoCompletePrediction
import com.aposiamp.smartliving.domain.repository.PlacesRepository

class GetAutoCompleteSuggestionsUseCase(
    private val placesRepository: PlacesRepository
) {
    suspend fun execute(query: String): List<AutoCompletePrediction> {
        val predictionsData =  placesRepository.getAutocompletePredictions(query)
        val predictions = AutocompletePredictionDataMapper.toDomainList(predictionsData)
        return predictions
    }
}