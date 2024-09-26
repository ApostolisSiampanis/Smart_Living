package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.AirDirectionDTO

interface AirConditionRepository {
    suspend fun updateAirDirection(airConditionId: String, airDirectionDTO: AirDirectionDTO): Boolean
}