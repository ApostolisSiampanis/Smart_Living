package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.mapper.PeriodMapper
import com.aposiamp.smartliving.domain.model.Period
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class GetPeriodDataUseCase(
    private val deviceRepository: DeviceRepository
) {
    suspend fun execute(deviceId: String, period: Period): Double {
        val collectionName = PeriodMapper.toCollectionName(period)
        return deviceRepository.getPeriodData(deviceId, collectionName)
    }
}