package com.aposiamp.smartliving.presentation.model

import androidx.compose.ui.graphics.Color
import com.aposiamp.smartliving.domain.model.ThermostatMode

data class ThermostatModeUiItem(
    val icon: Int,
    val text: Int,
    val mode: ThermostatMode,
    val primaryColor: Color,
    val secondaryColor: Color
)
