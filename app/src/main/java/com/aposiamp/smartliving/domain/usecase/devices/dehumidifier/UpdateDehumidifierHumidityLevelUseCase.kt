package com.aposiamp.smartliving.domain.usecase.devices.dehumidifier

import com.aposiamp.smartliving.data.model.HumidityLevelDTO
import com.aposiamp.smartliving.domain.repository.DehumidifierRepository

class UpdateDehumidifierHumidityLevelUseCase(
    private val dehumidifierRepository: DehumidifierRepository
) {
    suspend fun execute(dehumidifierId: String, humidityLevel: Int): Boolean {
        val humidityLevelDTO = HumidityLevelDTO(humidityLevel)
        return dehumidifierRepository.updateHumidityLevel(dehumidifierId, humidityLevelDTO)
    }
}
