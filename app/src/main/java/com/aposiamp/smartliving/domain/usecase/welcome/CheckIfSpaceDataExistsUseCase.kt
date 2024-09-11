package com.aposiamp.smartliving.domain.usecase.welcome

import com.aposiamp.smartliving.domain.repository.DeviceAndSpaceRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class CheckIfSpaceDataExistsUseCase(
    private val deviceAndSpaceRepository: DeviceAndSpaceRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(): Boolean {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        return deviceAndSpaceRepository.checkIfSpaceDataExists(uid)
    }
}