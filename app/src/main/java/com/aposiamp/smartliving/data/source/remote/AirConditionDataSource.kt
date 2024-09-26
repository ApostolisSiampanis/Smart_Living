package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.AirDirectionDTO
import com.aposiamp.smartliving.data.model.FanSpeedDTO
import com.aposiamp.smartliving.data.model.TemperatureDTO

class AirConditionDataSource(
    private val airConditionApiService: AirConditionApiService
) {
    suspend fun updateAirDirection(airConditionId: String, airDirectionDTO: AirDirectionDTO): Boolean {
        return try {
            val response = airConditionApiService.updateAirDirection(airConditionId, airDirectionDTO)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateFanSpeed(airConditionId: String, fanSpeedDTO: FanSpeedDTO): Boolean {
        return try {
            val response = airConditionApiService.updateFanSpeed(airConditionId, fanSpeedDTO)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateTemperature(airConditionId: String, temperatureDTO: TemperatureDTO): Boolean {
        return try {
            val response = airConditionApiService.updateTemperature(airConditionId, temperatureDTO)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}