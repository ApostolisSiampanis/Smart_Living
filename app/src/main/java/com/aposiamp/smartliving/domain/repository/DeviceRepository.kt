package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO

interface DeviceRepository {
    suspend fun checkIfDeviceExists(deviceIdAndTypeDTO: DeviceIdAndTypeDTO): Boolean
}