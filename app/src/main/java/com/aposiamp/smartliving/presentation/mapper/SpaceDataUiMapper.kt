package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.domain.model.SpaceData
import com.aposiamp.smartliving.presentation.model.SpaceDataUiModel

object SpaceDataUiMapper {
    fun fromDomain(domain: SpaceData): SpaceDataUiModel {
        return SpaceDataUiModel(
            placeId = domain.placeId,
            placeName = domain.placeName,
            spaceName = domain.spaceName,
            fullAddress = domain.fullAddress,
            location = domain.location?.let { LocationDataUiMapper.fromDomain(it) }
        )
    }
}