package com.aposiamp.smartliving.domain.usecase.devices.dehumidifier

import com.aposiamp.smartliving.data.model.FanSpeedDTO
import com.aposiamp.smartliving.domain.repository.DehumidifierRepository

class UpdateDehumidifierFanSpeedUseCase(
    private val dehumidifierRepository: DehumidifierRepository
) {
    suspend fun execute(dehumidifierId: String, fanSpeed: Int): Boolean {
        val fanSpeedDTO = FanSpeedDTO(fanSpeed)
        return dehumidifierRepository.updateFanSpeed(dehumidifierId, fanSpeedDTO)
    }
}