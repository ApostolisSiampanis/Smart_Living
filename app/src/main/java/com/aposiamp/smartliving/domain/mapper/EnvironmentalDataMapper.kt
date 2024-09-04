package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.EnvironmentalDataDTO
import com.aposiamp.smartliving.domain.model.EnvironmentalData

object EnvironmentalDataMapper {
    fun fromDto(dto: EnvironmentalDataDTO): EnvironmentalData {
        return EnvironmentalData(
            temperature = dto.temperature,
            humidity = dto.humidity,
            timestamp = dto.timestamp
        )
    }

    fun toDto(domain: EnvironmentalData): EnvironmentalDataDTO {
        return EnvironmentalDataDTO(
            temperature = domain.temperature,
            humidity = domain.humidity,
            timestamp = domain.timestamp
        )
    }
}