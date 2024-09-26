package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.TemperatureDTO

interface ThermostatRepository {
    suspend fun updateTemperature(deviceId: String, temperatureDTO: TemperatureDTO): Boolean
}