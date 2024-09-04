package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.EnvironmentalDataDTO
import com.aposiamp.smartliving.data.source.local.EnvironmentalSensorDataSource
import com.aposiamp.smartliving.data.source.remote.FirestoreDataSource
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository
import com.aposiamp.smartliving.utils.DateUtils

class EnvironmentalSensorRepositoryImpl(
    private val environmentalSensorDataSource: EnvironmentalSensorDataSource,
    private val firestoreDataSource: FirestoreDataSource
) : EnvironmentalSensorRepository {
    override suspend fun getEnvironmentalData(): EnvironmentalDataDTO {
        val temperature = environmentalSensorDataSource.getTemperatureData()
        val humidity = environmentalSensorDataSource.getHumidityData()
        val timestamp = DateUtils.getCurrentDateUTC()

        return EnvironmentalDataDTO(
            temperature = temperature,
            humidity = humidity,
            timestamp = timestamp
        )
    }

    override suspend fun setEnvironmentalData(uid: String, environmentalDataDTO: EnvironmentalDataDTO) {
        firestoreDataSource.setEnvironmentalData(uid, environmentalDataDTO)
    }
}