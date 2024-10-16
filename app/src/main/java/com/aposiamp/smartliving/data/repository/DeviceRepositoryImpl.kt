package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.AirConditionStatusDTO
import com.aposiamp.smartliving.data.model.DehumidifierStatusDTO
import com.aposiamp.smartliving.data.model.DeviceDataDTO
import com.aposiamp.smartliving.data.model.DeviceHistoryDTO
import com.aposiamp.smartliving.data.model.DeviceIdAndTypeDTO
import com.aposiamp.smartliving.data.model.DeviceModeDTO
import com.aposiamp.smartliving.data.model.DeviceStateDTO
import com.aposiamp.smartliving.data.model.ThermostatStatusDTO
import com.aposiamp.smartliving.data.source.remote.DeviceDataSource
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.data.source.remote.FirestoreDataSource
import com.aposiamp.smartliving.domain.repository.DeviceRepository

class DeviceRepositoryImpl(
    private val deviceDataSource: DeviceDataSource,
    private val firebaseDataSource: FirebaseDataSource,
    private val firestoreDataSource: FirestoreDataSource
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

    override suspend fun getThermostatStatus(deviceId: String): ThermostatStatusDTO? {
        return deviceDataSource.getThermostatStatus(deviceId)
    }

    override suspend fun getAirConditionStatus(deviceId: String): AirConditionStatusDTO? {
        return deviceDataSource.getAirConditionStatus(deviceId)
    }

    override suspend fun getDehumidifierStatus(deviceId: String): DehumidifierStatusDTO? {
        return deviceDataSource.getDehumidifierStatus(deviceId)
    }

    override suspend fun updateDeviceState(deviceId: String, deviceStateDTO: DeviceStateDTO): DeviceHistoryDTO? {
        return deviceDataSource.updateDeviceState(deviceId, deviceStateDTO)
    }

    override suspend fun updateDeviceMode(deviceId: String, deviceModeDTO: DeviceModeDTO): Boolean {
        return deviceDataSource.updateDeviceMode(deviceId, deviceModeDTO)
    }

    override suspend fun setDeviceHistory(deviceId: String, deviceHistoryDTO: DeviceHistoryDTO) {
        firestoreDataSource.setDeviceHistory(deviceId, deviceHistoryDTO)
    }

    override suspend fun getPeriodData(deviceId: String, period: String): Double {
        return firestoreDataSource.getPeriodData(deviceId, period)
    }
}