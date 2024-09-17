package com.aposiamp.smartliving.domain.usecase.places

import com.aposiamp.smartliving.domain.mapper.PlaceDataMapper
import com.aposiamp.smartliving.domain.model.PlaceData
import com.aposiamp.smartliving.domain.repository.PlacesRepository

class GetLocationFromPlaceIdUseCase(
    private val placesRepository: PlacesRepository
) {
    suspend fun execute(placeId: String): PlaceData {
        val placeDataDTO = placesRepository.getLocationFromPlaceId(placeId)
        val placeData = PlaceDataMapper.fromDto(placeDataDTO)
        return placeData
    }
}