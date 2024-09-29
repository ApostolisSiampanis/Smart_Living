package com.aposiamp.smartliving.domain.usecase.devices

import com.aposiamp.smartliving.data.model.DeviceStateDTO
import com.aposiamp.smartliving.domain.mapper.DeviceHistoryMapper
import com.aposiamp.smartliving.domain.model.DeviceHistoryData
import com.aposiamp.smartliving.domain.model.DeviceState
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class UpdateDeviceStateUseCase(
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(deviceId: String, deviceState: DeviceState): DeviceHistoryData? {
        val deviceStateDTO = DeviceStateDTO(deviceState)
        val deviceHistoryDTO = deviceRepository.updateDeviceState(deviceId, deviceStateDTO)
        return try {
            DeviceHistoryMapper.toDomain(deviceHistoryDTO!!)
        } catch (e: Exception) {
            null
        }
    }
}