package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.SpaceDataDTO

interface SpaceRepository {
    suspend fun setSpaceData(userId: String, spaceDataDTO: SpaceDataDTO)
    suspend fun getSpaceData(userId: String): SpaceDataDTO
    suspend fun checkIfSpaceDataExists(userId: String): Boolean
}