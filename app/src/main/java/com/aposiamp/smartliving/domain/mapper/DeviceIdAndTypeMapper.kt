package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO
import com.aposiamp.smartliving.data.model.DeviceTypeDTO
import com.aposiamp.smartliving.domain.model.DeviceIdAndTypeData
import com.aposiamp.smartliving.domain.model.DeviceType

object DeviceIdAndTypeMapper {
    fun toDto(data: DeviceIdAndTypeData): DeviceIdAndTypeDTO {
        return DeviceIdAndTypeDTO(
            deviceId = data.deviceId,
            deviceType = mapDeviceTypeToDto(data.deviceType)
        )
    }

    private fun mapDeviceTypeToDto(domain: DeviceType): DeviceTypeDTO {
        return when(domain){
            DeviceType.THERMOSTAT -> DeviceTypeDTO.THERMOSTAT
            DeviceType.AIR_CONDITIONER -> DeviceTypeDTO.AIR_CONDITIONER
            DeviceType.DEHUMIDIFIER -> DeviceTypeDTO.DEHUMIDIFIER
        }
    }
}