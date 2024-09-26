package com.aposiamp.smartliving.domain.usecase.devices.airCondition

import com.aposiamp.smartliving.data.model.FanSpeedDTO
import com.aposiamp.smartliving.domain.repository.AirConditionRepository

class UpdateAirConditionFanSpeedUseCase(
    private val airConditionRepository: AirConditionRepository
) {
    suspend fun execute(airConditionId: String, fanSpeed: Int): Boolean {
        val fanSpeedDTO = FanSpeedDTO(fanSpeed)
        return airConditionRepository.updateFanSpeed(airConditionId, fanSpeedDTO)
    }
}