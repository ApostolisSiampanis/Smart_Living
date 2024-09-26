package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.AirDirectionDTO
import com.aposiamp.smartliving.data.model.FanSpeedDTO

interface AirConditionRepository {
    suspend fun updateAirDirection(airConditionId: String, airDirectionDTO: AirDirectionDTO): Boolean
    suspend fun updateFanSpeed(airConditionId: String, fanSpeedDTO: FanSpeedDTO): Boolean
}