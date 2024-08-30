package com.aposiamp.smartliving.domain.usecase.sensor

import com.aposiamp.smartliving.data.model.EnvironmentalDataDTO
import com.aposiamp.smartliving.domain.model.EnvironmentalData
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class GetEnvironmentalDataUseCase(
    private val environmentalSensorRepository: EnvironmentalSensorRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(): EnvironmentalData {
        val environmentalData = EnvironmentalData(
            temperature = environmentalSensorRepository.getEnvironmentalData().temperature,
            humidity = environmentalSensorRepository.getEnvironmentalData().humidity,
            timestamp = environmentalSensorRepository.getEnvironmentalData().timestamp
        )
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")
        environmentalSensorRepository.setEnvironmentalData(
            uid,
            EnvironmentalDataDTO(
                temperature = environmentalData.temperature,
                humidity = environmentalData.humidity,
                timestamp = environmentalData.timestamp
            )
        )
        return environmentalData
    }
}