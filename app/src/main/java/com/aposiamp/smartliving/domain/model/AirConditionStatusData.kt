package com.aposiamp.smartliving.domain.model

data class AirConditionStatusData(
    val state: DeviceState,
    val mode: DeviceMode,
    val airDirection: Int,
    val fanSpeed: Int,
    val temperature: Int
)
