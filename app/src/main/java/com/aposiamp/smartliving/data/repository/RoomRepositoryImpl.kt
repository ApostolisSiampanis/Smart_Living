package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.RoomDataDTO
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.domain.repository.RoomRepository

class RoomRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource
) : RoomRepository {
    override suspend fun setRoomData(userId: String, spaceId: String, roomDataDTO: RoomDataDTO) {
        firebaseDataSource.setRoomData(userId, spaceId, roomDataDTO)
    }

    override suspend fun getRoomList(userId: String, spaceId: String): List<RoomDataDTO>? {
        return firebaseDataSource.getRoomList(userId, spaceId)
    }
}