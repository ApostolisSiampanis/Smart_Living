package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.FanSpeedDTO
import com.aposiamp.smartliving.data.model.HumidityLevelDTO

class DehumidifierDataSource(
    private val apiService: DehumidifierApiService
) {
    suspend fun updateHumidityLevel(dehumidifierId: String, humidityLevelDTO: HumidityLevelDTO): Boolean {
        return try {
            val response = apiService.updateHumidityLevel(dehumidifierId, humidityLevelDTO)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateFanSpeed(dehumidifierId: String, fanSpeedDTO: FanSpeedDTO): Boolean {
        return try {
            val response = apiService.updateFanSpeed(dehumidifierId, fanSpeedDTO)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}