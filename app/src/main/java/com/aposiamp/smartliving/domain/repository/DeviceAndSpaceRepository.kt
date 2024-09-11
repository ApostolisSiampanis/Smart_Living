package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.SpaceDataDTO

interface DeviceAndSpaceRepository {
    suspend fun setDevicesSpaceData(userId: String, spaceDataDTO: SpaceDataDTO)
    suspend fun getDevicesSpaceName(userId: String): SpaceDataDTO
    suspend fun checkIfSpaceDataExists(userId: String): Boolean
}