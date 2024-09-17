package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.LocationDataDTO
import com.aposiamp.smartliving.data.model.PlaceDataDTO
import com.aposiamp.smartliving.domain.model.PlaceData

object PlaceDataMapper {
    fun fromDto(dto: PlaceDataDTO): PlaceData {
        return PlaceData(
            placeId = dto.placeId,
            name = dto.name,
            fullAddress = dto.fullAddress,
            location = LocationDataMapper.fromDto(
                LocationDataDTO(
                    latitude = dto.location?.latitude,
                    longitude = dto.location?.longitude
                )
            )
        )
    }
}