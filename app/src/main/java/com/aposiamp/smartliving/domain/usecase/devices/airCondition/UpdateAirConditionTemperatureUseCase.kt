package com.aposiamp.smartliving.domain.usecase.devices.airCondition

import com.aposiamp.smartliving.data.model.TemperatureDTO
import com.aposiamp.smartliving.domain.repository.AirConditionRepository

class UpdateAirConditionTemperatureUseCase(
    private val airConditionRepository: AirConditionRepository
) {
    suspend fun execute(airConditionId: String, temperature: Int): Boolean {
        val temperatureDTO = TemperatureDTO(temperature)
        return airConditionRepository.updateTemperature(airConditionId, temperatureDTO)
    }
}