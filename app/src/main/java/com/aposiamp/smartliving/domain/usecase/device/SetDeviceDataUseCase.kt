package com.aposiamp.smartliving.domain.usecase.device

import com.aposiamp.smartliving.domain.mapper.DeviceDataMapper
import com.aposiamp.smartliving.domain.model.DeviceData
import com.aposiamp.smartliving.domain.repository.DeviceRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class SetDeviceDataUseCase(
    private val deviceRepository: DeviceRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(spaceId: String, roomId: String, deviceData: DeviceData){
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        val deviceDataDTO = DeviceDataMapper.toDto(deviceData)
        deviceRepository.setDeviceData(uid, spaceId, roomId, deviceDataDTO)
    }
}