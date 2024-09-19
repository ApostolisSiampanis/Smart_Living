package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.mapper.RoomDataMapper
import com.aposiamp.smartliving.domain.model.RoomData
import com.aposiamp.smartliving.domain.repository.RoomRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class SetRoomDataUseCase(
    private val roomRepository: RoomRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(spaceId: String, roomData: RoomData) {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        roomRepository.setRoomData(
            uid,
            spaceId,
            RoomDataMapper.toDto(roomData)
        )
    }
}