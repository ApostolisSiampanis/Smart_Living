package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.HumidityLevelDTO

interface DehumidifierRepository {
    suspend fun updateHumidityLevel(dehumidifierId: String, humidityLevelDTO: HumidityLevelDTO): Boolean
}