package com.aposiamp.smartliving.domain.usecase.devices

import com.aposiamp.smartliving.domain.mapper.DeviceHistoryMapper
import com.aposiamp.smartliving.domain.model.DeviceHistoryData
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class SetDeviceHistoryUseCase(
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(deviceId: String, deviceHistoryData: DeviceHistoryData) {
        val deviceHistoryDTO = DeviceHistoryMapper.toDTO(deviceHistoryData)
        deviceRepository.setDeviceHistory(deviceId, deviceHistoryDTO)
    }
}