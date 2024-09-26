package com.aposiamp.smartliving.data.model

import com.aposiamp.smartliving.domain.model.DeviceMode
import com.aposiamp.smartliving.domain.model.DeviceState

data class AirConditionStatusDTO(
    val state: DeviceState,
    val mode: DeviceMode,
    val airDirection: Int,
    val fanSpeed: Int,
    val temperature: Int
)
