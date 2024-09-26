package com.aposiamp.smartliving.domain.model

data class ThermostatStatusData(
    val state: DeviceState,
    val mode: DeviceMode,
    val temperature: Int
)
