package com.aposiamp.smartliving.presentation.model

import com.aposiamp.smartliving.domain.model.DeviceType

data class DeviceDataUiModel(
    val deviceId: String?,
    val deviceType: DeviceType?,
    val deviceName: String?
)
