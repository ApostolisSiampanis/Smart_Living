package com.aposiamp.smartliving.data.model

import com.aposiamp.smartliving.domain.model.DeviceMode
import com.aposiamp.smartliving.domain.model.DeviceState
import com.aposiamp.smartliving.domain.model.DeviceType

data class ThermostatStatusDTO(
    val id: String,
    val type: DeviceType,
    val state: DeviceState,
    val mode: DeviceMode,
    val temperature: Int
)
