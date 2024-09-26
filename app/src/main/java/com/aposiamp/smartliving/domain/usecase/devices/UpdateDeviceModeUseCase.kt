package com.aposiamp.smartliving.domain.usecase.devices

import com.aposiamp.smartliving.data.model.DeviceModeDTO
import com.aposiamp.smartliving.domain.model.DeviceMode
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class UpdateDeviceModeUseCase(
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(deviceID: String, deviceMode: DeviceMode): Boolean {
        val deviceModeDTO = DeviceModeDTO(deviceMode)
        return deviceRepository.updateDeviceMode(deviceID, deviceModeDTO)
    }
}