package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.AirConditionStatusDTO
import com.aposiamp.smartliving.data.model.DehumidifierStatusDTO
import com.aposiamp.smartliving.data.model.DeviceHistoryDTO
import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO
import com.aposiamp.smartliving.data.model.DeviceModeDTO
import com.aposiamp.smartliving.data.model.DeviceStateDTO
import com.aposiamp.smartliving.data.model.ThermostatStatusDTO

class DeviceDataSource(
    private val apiService: DeviceApiService
) {
    suspend fun checkIfDeviceExists(deviceIdAndTypeDTO: DeviceIdAndTypeDTO): Boolean {
        return try {
            val response = apiService.checkIfDeviceExists(deviceIdAndTypeDTO.deviceId, deviceIdAndTypeDTO.deviceType.name)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getThermostatStatus(deviceId: String): ThermostatStatusDTO? {
        return try {
            val response = apiService.getThermostatStatus(deviceId)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getAirConditionStatus(deviceId: String): AirConditionStatusDTO? {
        return try {
            val response = apiService.getAirConditionStatus(deviceId)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getDehumidifierStatus(deviceId: String): DehumidifierStatusDTO? {
        return try {
            val response = apiService.getDehumidifierStatus(deviceId)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateDeviceState(deviceId: String, deviceStateDTO: DeviceStateDTO): DeviceHistoryDTO? {
        return try {
            val response = apiService.updateDeviceState(deviceId, deviceStateDTO)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateDeviceMode(deviceId: String, deviceModeDTO: DeviceModeDTO): Boolean {
        return try {
            val response = apiService.updateDeviceMode(deviceId, deviceModeDTO)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}