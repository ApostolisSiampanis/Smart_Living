package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.AirDirectionDTO

class AirConditionDataSource(
    private val airConditionApiService: AirConditionApiService
) {
    suspend fun updateAirDirection(airConditionId: String, airDirection: AirDirectionDTO): Boolean {
        return try {
            val response = airConditionApiService.updateAirDirection(airConditionId, airDirection)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}