package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.SpaceDataDTO
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.domain.repository.SpaceRepository

class SpaceRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource
) : SpaceRepository {
    override suspend fun setSpaceData(userId: String, spaceDataDTO: SpaceDataDTO) {
        firebaseDataSource.setSpaceData(userId, spaceDataDTO)
    }

    override suspend fun getSpaceData(userId: String): SpaceDataDTO {
        return firebaseDataSource.getSpaceData(userId)
    }

    override suspend fun checkIfSpaceDataExists(userId: String): Boolean {
        return firebaseDataSource.checkIfSpaceDataExists(userId)
    }
}