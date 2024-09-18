package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.SpaceDataDTO
import com.aposiamp.smartliving.domain.model.SpaceData

object SpaceDataMapper {
    fun fromDto(dto: SpaceDataDTO): SpaceData {
        return SpaceData(
            placeId = dto.placeId,
            placeName = dto.placeName,
            spaceName = dto.spaceName,
            fullAddress = dto.fullAddress,
            location = dto.location?.let { LocationDataMapper.fromDto(it) }
        )
    }

    fun toDto(domain: SpaceData): SpaceDataDTO {
        return SpaceDataDTO(
            placeId = domain.placeId,
            placeName = domain.placeName,
            spaceName = domain.spaceName,
            fullAddress = domain.fullAddress,
            location = domain.location?.let { LocationDataMapper.toDto(it) }
        )
    }
}