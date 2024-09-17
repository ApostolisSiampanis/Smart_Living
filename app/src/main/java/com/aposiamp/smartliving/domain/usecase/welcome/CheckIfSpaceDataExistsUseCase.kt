package com.aposiamp.smartliving.domain.usecase.welcome

import com.aposiamp.smartliving.domain.repository.SpaceRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class CheckIfSpaceDataExistsUseCase(
    private val spaceRepository: SpaceRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(): Boolean {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        return spaceRepository.checkIfSpaceDataExists(uid)
    }
}