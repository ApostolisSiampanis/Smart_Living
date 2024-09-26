package com.aposiamp.smartliving.domain.usecase.devices.airCondition

import com.aposiamp.smartliving.domain.mapper.AirConditionStatusMapper
import com.aposiamp.smartliving.domain.model.AirConditionStatusData
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class GetAirConditionStatusUseCase(
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(deviceId: String): AirConditionStatusData {
        val airConditionStatusDTO = deviceRepository.getAirConditionStatus(deviceId)
        val airConditionStatus = AirConditionStatusMapper.toDomain(airConditionStatusDTO!!)
        return airConditionStatus
    }
}