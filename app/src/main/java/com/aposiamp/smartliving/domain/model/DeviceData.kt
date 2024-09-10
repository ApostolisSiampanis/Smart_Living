package com.aposiamp.smartliving.domain.model

data class DeviceData(
    val deviceName: String,
    val deviceType: String,
    val deviceState: DeviceState,
    val deviceMode: DeviceMode
    // TODO: Add values for each device type
)
