package com.aposiamp.smartliving.domain.usecase.sensor

import com.aposiamp.smartliving.domain.mapper.EnvironmentalDataMapper
import com.aposiamp.smartliving.domain.model.EnvironmentalData
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository

class GetEnvironmentalDataUseCase(
    private val environmentalSensorRepository: EnvironmentalSensorRepository
) {
    suspend fun execute(): EnvironmentalData {
        val environmentalDataDTO = environmentalSensorRepository.getEnvironmentalData()
        val environmentalData = EnvironmentalDataMapper.fromDto(environmentalDataDTO)
        return environmentalData
    }
}