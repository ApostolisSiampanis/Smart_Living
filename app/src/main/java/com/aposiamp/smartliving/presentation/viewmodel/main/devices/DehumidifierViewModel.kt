package com.aposiamp.smartliving.presentation.viewmodel.main.devices

import androidx.lifecycle.ViewModel
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.DeviceMode
import com.aposiamp.smartliving.domain.model.DeviceModeItem
import com.aposiamp.smartliving.domain.model.DeviceState
import com.aposiamp.smartliving.domain.model.DeviceStateItem
import com.aposiamp.smartliving.presentation.model.DeviceModeUiItem
import com.aposiamp.smartliving.presentation.model.DeviceStateUiItem
import com.aposiamp.smartliving.presentation.ui.theme.DryBlue
import com.aposiamp.smartliving.presentation.ui.theme.LightOrange
import com.aposiamp.smartliving.presentation.ui.theme.PrussianBlue
import com.aposiamp.smartliving.presentation.ui.theme.Purple40

class DehumidifierViewModel : ViewModel() {
    private val deviceStates = listOf(
        DeviceStateItem(DeviceState.OFF),
        DeviceStateItem(DeviceState.ON)
    )

    val uiDeviceStates: List<DeviceStateUiItem> = deviceStates.map {
        when (it.state) {
            DeviceState.OFF -> DeviceStateUiItem(
                icon = R.drawable.power,
                text = R.string.turn_off,
                state = it.state
            )
            DeviceState.ON -> DeviceStateUiItem(
                icon = R.drawable.power,
                text = R.string.turn_on,
                state = it.state
            )
        }
    }

    private val deviceModes = listOf(
        DeviceModeItem(DeviceMode.AUTO),
        DeviceModeItem(DeviceMode.HUMIDITY),
        DeviceModeItem(DeviceMode.DRY),
        DeviceModeItem(DeviceMode.SILENT)
    )

    val uiDeviceModes: List<DeviceModeUiItem> = deviceModes.mapNotNull {
        when (it.mode) {
            DeviceMode.AUTO -> DeviceModeUiItem(
                icon = R.drawable.auto,
                text = R.string.auto,
                mode = it.mode,
                primaryColor = LightOrange,
                secondaryColor = LightOrange
            )
            DeviceMode.HUMIDITY -> DeviceModeUiItem(
                icon = R.drawable.dry,
                text = R.string.humidity,
                mode = it.mode,
                primaryColor = DryBlue,
                secondaryColor = DryBlue
            )
            DeviceMode.DRY -> DeviceModeUiItem(
                icon = R.drawable.clothes,
                text = R.string.dry,
                mode = it.mode,
                primaryColor = Purple40,
                secondaryColor = Purple40
            )
            DeviceMode.SILENT -> DeviceModeUiItem(
                icon = R.drawable.silent,
                text = R.string.silent,
                mode = it.mode,
                primaryColor = PrussianBlue,
                secondaryColor = PrussianBlue
            )
            else -> null
        }
    }
}