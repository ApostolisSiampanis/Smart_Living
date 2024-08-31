package com.aposiamp.smartliving.presentation.model

import androidx.compose.ui.graphics.Color
import com.aposiamp.smartliving.domain.model.DeviceMode

data class DeviceModeUiItem(
    val icon: Int,
    val text: Int,
    val mode: DeviceMode,
    val primaryColor: Color,
    val secondaryColor: Color
)
