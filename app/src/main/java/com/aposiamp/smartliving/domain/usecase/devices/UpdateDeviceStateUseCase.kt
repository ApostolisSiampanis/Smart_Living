package com.aposiamp.smartliving.domain.usecase.devices

import com.aposiamp.smartliving.data.model.DeviceStateDTO
import com.aposiamp.smartliving.domain.model.DeviceState
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class UpdateDeviceStateUseCase(
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(deviceId: String, deviceState: DeviceState): Boolean {
        val deviceStateDTO = DeviceStateDTO(deviceState)
        return deviceRepository.updateDeviceState(deviceId, deviceStateDTO)
    }
}