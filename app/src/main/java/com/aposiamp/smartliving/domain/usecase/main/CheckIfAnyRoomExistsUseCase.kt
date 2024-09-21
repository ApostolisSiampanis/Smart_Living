package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.repository.RoomRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class CheckIfAnyRoomExistsUseCase(
    private val roomRepository: RoomRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(spaceId: String): Boolean {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")
        return roomRepository.checkIfAnyRoomExists(uid, spaceId)
    }
}