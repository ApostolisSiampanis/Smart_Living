package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.AirConditionStatusDTO
import com.aposiamp.smartliving.data.model.DehumidifierStatusDTO
import com.aposiamp.smartliving.data.model.DeviceDataDTO
import com.aposiamp.smartliving.data.model.DeviceHistoryDTO
import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO
import com.aposiamp.smartliving.data.model.DeviceModeDTO
import com.aposiamp.smartliving.data.model.DeviceStateDTO
import com.aposiamp.smartliving.data.model.ThermostatStatusDTO

interface DeviceRepository {
    suspend fun checkIfDeviceExists(deviceIdAndTypeDTO: DeviceIdAndTypeDTO): Boolean
    suspend fun setDeviceData(userId: String, spaceId: String, roomId: String, deviceDataDTO: DeviceDataDTO)
    suspend fun getDeviceList(userId: String, spaceId: String, roomId: String): List<DeviceDataDTO>?
    suspend fun getThermostatStatus(deviceId: String): ThermostatStatusDTO?
    suspend fun getAirConditionStatus(deviceId: String): AirConditionStatusDTO?
    suspend fun getDehumidifierStatus(deviceId: String): DehumidifierStatusDTO?
    suspend fun updateDeviceState(deviceId: String, deviceStateDTO: DeviceStateDTO): DeviceHistoryDTO?
    suspend fun updateDeviceMode(deviceId: String, deviceModeDTO: DeviceModeDTO): Boolean
    suspend fun setDeviceHistory(deviceId: String, deviceHistoryDTO: DeviceHistoryDTO)
    suspend fun getPeriodData(deviceId: String, period: String): Double
}