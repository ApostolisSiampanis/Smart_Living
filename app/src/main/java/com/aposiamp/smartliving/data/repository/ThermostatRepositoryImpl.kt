package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.TemperatureDTO
import com.aposiamp.smartliving.data.source.remote.ThermostatDataSource
import com.aposiamp.smartliving.domain.repository.ThermostatRepository

class ThermostatRepositoryImpl(
    private val thermostatDataSource: ThermostatDataSource
) : ThermostatRepository {
    override suspend fun updateTemperature(deviceId: String, temperatureDTO: TemperatureDTO): Boolean {
        return thermostatDataSource.updateTemperature(deviceId, temperatureDTO)
    }
}