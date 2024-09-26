package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.AirConditionStatusDTO
import com.aposiamp.smartliving.domain.model.AirConditionStatusData

object AirConditionStatusMapper {
    fun toDomain(dto: AirConditionStatusDTO): AirConditionStatusData {
        return AirConditionStatusData(
            state = dto.state,
            mode = dto.mode,
            airDirection = dto.airDirection,
            fanSpeed = dto.fanSpeed,
            temperature = dto.temperature
        )
    }
}