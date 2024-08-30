package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.ThermostatMode
import com.aposiamp.smartliving.domain.model.ThermostatModeItem
import com.aposiamp.smartliving.presentation.model.ThermostatModeUiItem
import com.aposiamp.smartliving.presentation.ui.theme.Blue
import com.aposiamp.smartliving.presentation.ui.theme.BrightBlue
import com.aposiamp.smartliving.presentation.ui.theme.GrayColor
import com.aposiamp.smartliving.presentation.ui.theme.Orange
import com.aposiamp.smartliving.presentation.ui.theme.RedOrange

class ThermostatViewModel : ViewModel() {
    private val modes = listOf(
        ThermostatModeItem(ThermostatMode.OFF),
        ThermostatModeItem(ThermostatMode.COOL),
        ThermostatModeItem(ThermostatMode.HEAT)
    )

    val uiModes: List<ThermostatModeUiItem> = modes.map {
        when (it.mode) {
            ThermostatMode.OFF -> ThermostatModeUiItem(
                icon = R.drawable.power,
                text = R.string.turn_off,
                mode = it.mode,
                primaryColor = GrayColor,
                secondaryColor = GrayColor
            )
            ThermostatMode.COOL -> ThermostatModeUiItem(
                icon = R.drawable.cool,
                text = R.string.cool,
                mode = it.mode,
                primaryColor = Blue,
                secondaryColor = BrightBlue
            )
            ThermostatMode.HEAT -> ThermostatModeUiItem(
                icon = R.drawable.heat,
                text = R.string.heat,
                mode = it.mode,
                primaryColor = Orange,
                secondaryColor = RedOrange
            )
        }
    }
}