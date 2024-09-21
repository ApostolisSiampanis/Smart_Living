package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.RoomDataDTO

interface RoomRepository {
    suspend fun setRoomData(userId: String, spaceId: String, roomDataDTO: RoomDataDTO)
    suspend fun getRoomList(userId: String, spaceId: String): List<RoomDataDTO>?
}