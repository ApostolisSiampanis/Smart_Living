package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.DeviceDataDTO
import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO
import com.aposiamp.smartliving.data.source.remote.DeviceDataSource
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class DeviceRepositoryImpl(
    private val deviceDataSource: DeviceDataSource,
    private val firebaseDataSource: FirebaseDataSource
) : DeviceRepository {
    override suspend fun checkIfDeviceExists(deviceIdAndTypeDTO: DeviceIdAndTypeDTO): Boolean {
        return deviceDataSource.checkIfDeviceExists(deviceIdAndTypeDTO)
    }

    override suspend fun setDeviceData(userId: String, spaceId: String, roomId: String, deviceDataDTO: DeviceDataDTO) {
        firebaseDataSource.setDeviceData(userId, spaceId, roomId, deviceDataDTO)
    }

    override suspend fun getDeviceList(userId: String, spaceId: String, roomId: String): List<DeviceDataDTO>? {
        return firebaseDataSource.getDeviceList(userId, spaceId, roomId)
    }
}