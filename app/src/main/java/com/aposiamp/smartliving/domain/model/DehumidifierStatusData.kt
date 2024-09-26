package com.aposiamp.smartliving.domain.model

data class DehumidifierStatusData(
    val state: DeviceState,
    val mode: DeviceMode,
    val humidityLevel: Int,
    val fanSpeed: Int
)
