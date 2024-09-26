package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.HumidityLevelDTO
import com.aposiamp.smartliving.data.source.remote.DehumidifierDataSource
import com.aposiamp.smartliving.domain.repository.DehumidifierRepository

class DehumidifierRepositoryImpl(
    private val dehumidifierDataSource: DehumidifierDataSource
) : DehumidifierRepository {
    override suspend fun updateHumidityLevel(dehumidifierId: String, humidityLevelDTO: HumidityLevelDTO): Boolean {
        return dehumidifierDataSource.updateHumidityLevel(dehumidifierId, humidityLevelDTO)
    }
}