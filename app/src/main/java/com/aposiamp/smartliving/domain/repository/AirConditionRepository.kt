package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.AirDirectionDTO
import com.aposiamp.smartliving.data.model.FanSpeedDTO
import com.aposiamp.smartliving.data.model.TemperatureDTO

interface AirConditionRepository {
    suspend fun updateAirDirection(airConditionId: String, airDirectionDTO: AirDirectionDTO): Boolean
    suspend fun updateFanSpeed(airConditionId: String, fanSpeedDTO: FanSpeedDTO): Boolean
    suspend fun updateTemperature(airConditionId: String, temperatureDTO: TemperatureDTO): Boolean
}