package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.DehumidifierStatusDTO
import com.aposiamp.smartliving.domain.model.DehumidifierStatusData

object DehumidifierStatusMapper {
    fun toDomain(dto: DehumidifierStatusDTO): DehumidifierStatusData {
        return DehumidifierStatusData(
            state = dto.state,
            mode = dto.mode,
            humidityLevel = dto.humidityLevel,
            fanSpeed = dto.fanSpeed
        )
    }
}