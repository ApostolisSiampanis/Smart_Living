package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.DeviceHistoryDTO
import com.aposiamp.smartliving.domain.model.DeviceHistoryData

object DeviceHistoryMapper {
    fun toDomain(dto: DeviceHistoryDTO): DeviceHistoryData {
        return DeviceHistoryData(
            startTime = dto.startTime,
            endTime = dto.endTime,
            powerConsumption = dto.powerConsumption
        )
    }

    fun toDTO(domain: DeviceHistoryData): DeviceHistoryDTO {
        return DeviceHistoryDTO(
            startTime = domain.startTime,
            endTime = domain.endTime,
            powerConsumption = domain.powerConsumption
        )
    }
}