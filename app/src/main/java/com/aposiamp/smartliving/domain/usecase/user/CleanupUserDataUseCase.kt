package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.repository.CleanupRepository

class CleanupUserDataUseCase(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val cleanupRepository: CleanupRepository
) {
    suspend fun execute(): Boolean {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")
        return cleanupRepository.cleanUpUserData(uid)
    }
}