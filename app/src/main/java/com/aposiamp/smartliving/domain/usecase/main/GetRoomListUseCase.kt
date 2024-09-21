package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.mapper.RoomDataMapper
import com.aposiamp.smartliving.domain.model.RoomData
import com.aposiamp.smartliving.domain.repository.RoomRepository
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase

class GetRoomListUseCase(
    private val roomRepository: RoomRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(spaceId: String): List<RoomData> {
        val currentUser = getCurrentUserUseCase.execute()
        val uid = currentUser?.uid ?: throw Exception("User not logged in")

        val roomListDTO = roomRepository.getRoomList(uid, spaceId)
        val roomList = RoomDataMapper.fromDtoList(roomListDTO ?: emptyList())
        return roomList
    }
}