package com.aposiamp.smartliving.data.model

import com.aposiamp.smartliving.domain.model.DeviceType

data class DeviceDataDTO(
    val deviceId: String,
    val deviceType: DeviceType,
    val deviceName: String
)
