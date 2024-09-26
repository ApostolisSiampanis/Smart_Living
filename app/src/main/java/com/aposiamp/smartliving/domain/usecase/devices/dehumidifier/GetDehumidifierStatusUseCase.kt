package com.aposiamp.smartliving.domain.usecase.devices.dehumidifier

import com.aposiamp.smartliving.domain.mapper.DehumidifierStatusMapper
import com.aposiamp.smartliving.domain.model.DehumidifierStatusData
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class GetDehumidifierStatusUseCase(
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(deviceId: String): DehumidifierStatusData {
        val dehumidifierStatusDTO = deviceRepository.getDehumidifierStatus(deviceId)
        val dehumidifierStatus = DehumidifierStatusMapper.toDomain(dehumidifierStatusDTO!!)
        return dehumidifierStatus
    }
}