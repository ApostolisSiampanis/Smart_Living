package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.AirDirectionDTO
import com.aposiamp.smartliving.data.source.remote.AirConditionDataSource
import com.aposiamp.smartliving.domain.repository.AirConditionRepository

class AirConditionRepositoryImpl(
    private val airConditionDataSource: AirConditionDataSource
) : AirConditionRepository {
    override suspend fun updateAirDirection(airConditionId: String, airDirectionDTO: AirDirectionDTO): Boolean {
        return airConditionDataSource.updateAirDirection(airConditionId, airDirectionDTO)
    }
}