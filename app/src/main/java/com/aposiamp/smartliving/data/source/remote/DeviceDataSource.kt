package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO

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
}