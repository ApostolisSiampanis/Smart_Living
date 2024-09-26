package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.TemperatureDTO

class ThermostatDataSource(
    private val apiService: ThermostatApiService
) {
    suspend fun updateTemperature(deviceId: String, temperatureDTO: TemperatureDTO): Boolean {
        return try {
            val response = apiService.updateTemperature(deviceId, temperatureDTO)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}