package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO
import com.aposiamp.smartliving.domain.model.DeviceIdAndTypeData

object DeviceIdAndTypeMapper {
    fun toDto(data: DeviceIdAndTypeData): DeviceIdAndTypeDTO {
        return DeviceIdAndTypeDTO(
            deviceId = data.deviceId,
            deviceType = data.deviceType
        )
    }
}