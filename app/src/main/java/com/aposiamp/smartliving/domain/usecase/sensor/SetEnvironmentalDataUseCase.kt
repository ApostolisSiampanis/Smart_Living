package com.aposiamp.smartliving.domain.usecase.sensor

import com.aposiamp.smartliving.domain.mapper.EnvironmentalDataMapper
import com.aposiamp.smartliving.domain.model.EnvironmentalData
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class SetEnvironmentalDataUseCase(
    private val environmentalSensorRepository: EnvironmentalSensorRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(placeId: String, environmentalData: EnvironmentalData) {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        environmentalSensorRepository.setEnvironmentalData(
            uid,
            placeId,
            EnvironmentalDataMapper.toDto(environmentalData)
        )
    }
}