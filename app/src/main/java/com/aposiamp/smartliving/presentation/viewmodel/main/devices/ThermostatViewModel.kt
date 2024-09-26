package com.aposiamp.smartliving.presentation.viewmodel.main.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.DeviceMode
import com.aposiamp.smartliving.domain.model.DeviceModeItem
import com.aposiamp.smartliving.domain.model.DeviceState
import com.aposiamp.smartliving.domain.model.DeviceStateItem
import com.aposiamp.smartliving.domain.model.ThermostatStatusData
import com.aposiamp.smartliving.domain.usecase.devices.UpdateDeviceModeUseCase
import com.aposiamp.smartliving.domain.usecase.devices.UpdateDeviceStateUseCase
import com.aposiamp.smartliving.domain.usecase.devices.thermostat.GetThermostatStatusUseCase
import com.aposiamp.smartliving.presentation.model.DeviceModeUiItem
import com.aposiamp.smartliving.presentation.model.DeviceStateUiItem
import com.aposiamp.smartliving.presentation.ui.theme.Blue
import com.aposiamp.smartliving.presentation.ui.theme.BrightBlue
import com.aposiamp.smartliving.presentation.ui.theme.LightOrange
import com.aposiamp.smartliving.presentation.ui.theme.Orange
import com.aposiamp.smartliving.presentation.ui.theme.RedOrange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThermostatViewModel(
    private val getThermostatStatusUseCase: GetThermostatStatusUseCase,
    private val updateDeviceStateUseCase: UpdateDeviceStateUseCase,
    private val updateDeviceModeUseCase: UpdateDeviceModeUseCase
) : ViewModel() {
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
        DeviceModeItem(DeviceMode.COOL),
        DeviceModeItem(DeviceMode.HEAT)
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
            DeviceMode.COOL -> DeviceModeUiItem(
                icon = R.drawable.cool,
                text = R.string.cool,
                mode = it.mode,
                primaryColor = Blue,
                secondaryColor = BrightBlue
            )
            DeviceMode.HEAT -> DeviceModeUiItem(
                icon = R.drawable.heat,
                text = R.string.heat,
                mode = it.mode,
                primaryColor = Orange,
                secondaryColor = RedOrange
            )
            else -> null
        }
    }

    private val _deviceStatus = MutableStateFlow<ThermostatStatusData?>(null)
    val deviceStatus: StateFlow<ThermostatStatusData?> = _deviceStatus

    fun fetchDeviceStatus(deviceId: String) {
        viewModelScope.launch {
            _deviceStatus.value = getThermostatStatusUseCase.execute(deviceId)
        }
    }

    fun updateDeviceState(deviceId: String, deviceState: DeviceState) {
        viewModelScope.launch {
            updateDeviceStateUseCase.execute(deviceId, deviceState)
        }
    }

    fun updateDeviceMode(deviceId: String, deviceMode: DeviceMode) {
        viewModelScope.launch {
            updateDeviceModeUseCase.execute(deviceId, deviceMode)
        }
    }
}