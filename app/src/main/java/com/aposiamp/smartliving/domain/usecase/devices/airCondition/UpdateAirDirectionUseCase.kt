package com.aposiamp.smartliving.domain.usecase.devices.airCondition

import com.aposiamp.smartliving.data.model.AirDirectionDTO
import com.aposiamp.smartliving.domain.repository.AirConditionRepository

class UpdateAirDirectionUseCase(
    private val airConditionRepository: AirConditionRepository
) {
    suspend fun execute(airConditionId: String, airDirection: Int): Boolean {
        val airDirectionDTO = AirDirectionDTO(airDirection)
        return airConditionRepository.updateAirDirection(airConditionId, airDirectionDTO)
    }
}