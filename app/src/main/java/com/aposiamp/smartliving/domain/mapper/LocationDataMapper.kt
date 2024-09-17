package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.LocationDataDTO
import com.aposiamp.smartliving.domain.model.LocationData

object LocationDataMapper {
    fun fromDto(dto: LocationDataDTO): LocationData {
        return LocationData(
            latitude = dto.latitude,
            longitude = dto.longitude
        )
    }

    fun toDto(domain: LocationData): LocationDataDTO {
        return LocationDataDTO(
            latitude = domain.latitude,
            longitude = domain.longitude
        )
    }
}