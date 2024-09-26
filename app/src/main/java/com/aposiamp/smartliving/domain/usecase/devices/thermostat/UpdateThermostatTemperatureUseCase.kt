package com.aposiamp.smartliving.domain.usecase.devices.thermostat

import com.aposiamp.smartliving.data.model.TemperatureDTO
import com.aposiamp.smartliving.domain.repository.ThermostatRepository

class UpdateThermostatTemperatureUseCase(
    private val thermostatRepository: ThermostatRepository
) {
    suspend fun execute(deviceId: String, temperature: Int): Boolean {
        val temperatureDTO = TemperatureDTO(temperature)
        return thermostatRepository.updateTemperature(deviceId, temperatureDTO)
    }
}