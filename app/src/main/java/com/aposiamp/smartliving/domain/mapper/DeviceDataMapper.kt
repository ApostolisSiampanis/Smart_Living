package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.DeviceDataDTO
import com.aposiamp.smartliving.domain.model.DeviceData

object DeviceDataMapper {
    fun toDto(domain: DeviceData): DeviceDataDTO {
        return DeviceDataDTO(
            deviceId = domain.deviceId,
            deviceName = domain.deviceName,
            deviceType = domain.deviceType
        )
    }

    private fun fromDto(dto: DeviceDataDTO): DeviceData {
        return DeviceData(
            deviceId = dto.deviceId,
            deviceName = dto.deviceName,
            deviceType = dto.deviceType
        )
    }

    fun fromDtoList(dtoList: List<DeviceDataDTO>): List<DeviceData> {
        return dtoList.map { fromDto(it) }
    }
}