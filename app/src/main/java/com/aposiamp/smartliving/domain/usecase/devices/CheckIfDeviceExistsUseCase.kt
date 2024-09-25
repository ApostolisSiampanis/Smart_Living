package com.aposiamp.smartliving.domain.usecase.devices

import com.aposiamp.smartliving.domain.mapper.DeviceIdAndTypeMapper
import com.aposiamp.smartliving.domain.model.DeviceIdAndTypeData
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class CheckIfDeviceExistsUseCase(
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(deviceIdAndTypeData: DeviceIdAndTypeData): Boolean {
        val deviceIdAndTypeDTO = DeviceIdAndTypeMapper.toDto(deviceIdAndTypeData)
        return deviceRepository.checkIfDeviceExists(deviceIdAndTypeDTO)
    }
}