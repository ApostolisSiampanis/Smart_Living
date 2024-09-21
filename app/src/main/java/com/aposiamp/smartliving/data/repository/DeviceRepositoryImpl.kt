package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO
import com.aposiamp.smartliving.data.source.remote.DeviceDataSource
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class DeviceRepositoryImpl(
    private val deviceDataSource: DeviceDataSource
) : DeviceRepository {
    override suspend fun checkIfDeviceExists(deviceIdAndTypeDTO: DeviceIdAndTypeDTO): Boolean {
        return deviceDataSource.checkIfDeviceExists(deviceIdAndTypeDTO)
    }
}