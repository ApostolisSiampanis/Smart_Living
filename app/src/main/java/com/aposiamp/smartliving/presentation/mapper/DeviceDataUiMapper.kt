package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.domain.model.DeviceData
import com.aposiamp.smartliving.presentation.model.DeviceDataUiModel

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
}