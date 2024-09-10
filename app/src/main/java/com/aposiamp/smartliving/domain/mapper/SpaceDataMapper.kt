package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.SpaceDataDTO
import com.aposiamp.smartliving.domain.model.SpaceData

object SpaceDataMapper {
    fun toDto(domain: SpaceData): SpaceDataDTO {
        return SpaceDataDTO(
            spaceName = domain.spaceName
        )
    }
}