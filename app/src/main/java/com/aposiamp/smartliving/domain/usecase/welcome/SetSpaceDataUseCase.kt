package com.aposiamp.smartliving.domain.usecase.welcome

import com.aposiamp.smartliving.domain.mapper.SpaceDataMapper
import com.aposiamp.smartliving.domain.model.SpaceData
import com.aposiamp.smartliving.domain.repository.DeviceAndSpaceRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class SetSpaceDataUseCase(
    private val deviceAndSpaceRepository: DeviceAndSpaceRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(spaceData: SpaceData) {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        deviceAndSpaceRepository.setDevicesSpaceData(
            uid,
            SpaceDataMapper.toDto(spaceData)
        )
    }
}