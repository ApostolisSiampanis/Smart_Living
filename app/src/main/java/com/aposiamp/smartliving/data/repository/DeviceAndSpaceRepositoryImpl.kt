package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.SpaceDataDTO
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.domain.repository.DeviceAndSpaceRepository

class DeviceAndSpaceRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource
) : DeviceAndSpaceRepository {
    override suspend fun setDevicesSpaceData(userId: String, spaceDataDTO: SpaceDataDTO) {
        firebaseDataSource.setDevicesSpaceData(userId, spaceDataDTO)
    }

    override suspend fun getDevicesSpaceName(userId: String): SpaceDataDTO {
        return firebaseDataSource.getDevicesSpaceName(userId)
    }

    override suspend fun checkIfSpaceDataExists(userId: String): Boolean {
        return firebaseDataSource.checkIfSpaceDataExists(userId)
    }
}