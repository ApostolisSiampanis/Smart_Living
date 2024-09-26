package com.aposiamp.smartliving.data.model

import com.aposiamp.smartliving.domain.model.DeviceMode
import com.aposiamp.smartliving.domain.model.DeviceState

data class DehumidifierStatusDTO(
    val state: DeviceState,
    val mode: DeviceMode,
    val humidityLevel: Int,
    val fanSpeed: Int
)
