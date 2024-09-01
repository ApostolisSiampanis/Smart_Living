package com.aposiamp.smartliving.presentation.model

import com.aposiamp.smartliving.domain.model.DeviceState

data class DeviceStateUiItem(
    val icon: Int,
    val text: Int,
    val state: DeviceState
)
