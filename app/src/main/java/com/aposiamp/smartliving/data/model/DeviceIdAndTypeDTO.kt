package com.aposiamp.smartliving.data.model

import com.aposiamp.smartliving.domain.model.DeviceType

data class DeviceIdAndTypeDTO(
    val deviceId: String,
    val deviceType: DeviceType
)
