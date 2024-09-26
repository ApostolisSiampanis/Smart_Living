package com.aposiamp.smartliving.domain.model

data class ThermostatStatus(
    val state: DeviceState,
    val mode: DeviceMode,
    val temperature: Int
)
