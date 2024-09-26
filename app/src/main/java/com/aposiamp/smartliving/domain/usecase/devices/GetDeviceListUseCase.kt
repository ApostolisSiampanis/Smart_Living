package com.aposiamp.smartliving.domain.usecase.devices

import com.aposiamp.smartliving.domain.mapper.DeviceDataMapper
import com.aposiamp.smartliving.domain.model.DeviceData
import com.aposiamp.smartliving.domain.repository.DeviceRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class GetDeviceListUseCase(
    private val deviceRepository: DeviceRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(spaceId: String, roomId: String): List<DeviceData> {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        val deviceListDTO = deviceRepository.getDeviceList(uid, spaceId, roomId)
        val deviceList = DeviceDataMapper.fromDtoList(deviceListDTO ?: emptyList())
        return deviceList
    }
}