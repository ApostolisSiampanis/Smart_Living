package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.domain.model.DeviceData
import com.aposiamp.smartliving.presentation.model.DeviceDataUiModel
import com.aposiamp.smartliving.presentation.model.SimpleDeviceDataUiModel

object DeviceDataUiMapper {
    private fun fromDomain(domain: DeviceData): DeviceDataUiModel {
        return DeviceDataUiModel(
            deviceId = domain.deviceId,
            deviceType = domain.deviceType,
            deviceName = domain.deviceName
        )
    }

    fun fromDomainList(domainList: List<DeviceData>): List<DeviceDataUiModel> {
        return domainList.map { fromDomain(it) }
    }

    private fun toSimpleDeviceDataUiModel(device: DeviceDataUiModel): SimpleDeviceDataUiModel {
        return SimpleDeviceDataUiModel(
            deviceId = device.deviceId,
            deviceName = device.deviceName
        )
    }

    fun toSimpleDeviceDataUiModelList(devices: List<DeviceDataUiModel>): List<SimpleDeviceDataUiModel> {
        return devices.map { toSimpleDeviceDataUiModel(it) }
    }
}