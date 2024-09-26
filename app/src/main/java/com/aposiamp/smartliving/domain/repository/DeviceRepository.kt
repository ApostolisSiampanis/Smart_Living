package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.DeviceDataDTO
import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO
import com.aposiamp.smartliving.data.model.ThermostatStatusDTO

interface DeviceRepository {
    suspend fun checkIfDeviceExists(deviceIdAndTypeDTO: DeviceIdAndTypeDTO): Boolean
    suspend fun setDeviceData(userId: String, spaceId: String, roomId: String, deviceDataDTO: DeviceDataDTO)
    suspend fun getDeviceList(userId: String, spaceId: String, roomId: String): List<DeviceDataDTO>?
    suspend fun getThermostatStatus(deviceId: String): ThermostatStatusDTO?
}