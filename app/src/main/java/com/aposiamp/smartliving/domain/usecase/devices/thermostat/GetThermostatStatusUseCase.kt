package com.aposiamp.smartliving.domain.usecase.devices.thermostat

import com.aposiamp.smartliving.domain.mapper.ThermostatStatusMapper
import com.aposiamp.smartliving.domain.model.ThermostatStatusData
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class GetThermostatStatusUseCase(
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(deviceId: String): ThermostatStatusData {
        val thermostatStatusDTO = deviceRepository.getThermostatStatus(deviceId)
        val thermostatStatus = ThermostatStatusMapper.toDomain(thermostatStatusDTO!!)
        return thermostatStatus
    }
}