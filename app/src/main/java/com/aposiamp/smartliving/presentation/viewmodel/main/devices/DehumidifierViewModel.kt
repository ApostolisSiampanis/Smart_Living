package com.aposiamp.smartliving.presentation.viewmodel.main.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.DehumidifierStatusData
import com.aposiamp.smartliving.domain.model.DeviceMode
import com.aposiamp.smartliving.domain.model.DeviceModeItem
import com.aposiamp.smartliving.domain.model.DeviceState
import com.aposiamp.smartliving.domain.model.DeviceStateItem
import com.aposiamp.smartliving.domain.usecase.devices.SetDeviceHistoryUseCase
import com.aposiamp.smartliving.domain.usecase.devices.UpdateDeviceModeUseCase
import com.aposiamp.smartliving.domain.usecase.devices.UpdateDeviceStateUseCase
import com.aposiamp.smartliving.domain.usecase.devices.dehumidifier.GetDehumidifierStatusUseCase
import com.aposiamp.smartliving.domain.usecase.devices.dehumidifier.UpdateDehumidifierFanSpeedUseCase
import com.aposiamp.smartliving.domain.usecase.devices.dehumidifier.UpdateDehumidifierHumidityLevelUseCase
import com.aposiamp.smartliving.presentation.model.DeviceModeUiItem
import com.aposiamp.smartliving.presentation.model.DeviceStateUiItem
import com.aposiamp.smartliving.presentation.ui.theme.DryBlue
import com.aposiamp.smartliving.presentation.ui.theme.LightOrange
import com.aposiamp.smartliving.presentation.ui.theme.PrussianBlue
import com.aposiamp.smartliving.presentation.ui.theme.Purple40
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DehumidifierViewModel(
    private val getDehumidifierStatusUseCase: GetDehumidifierStatusUseCase,
    private val updateDeviceStateUseCase: UpdateDeviceStateUseCase,
    private val updateDeviceModeUseCase: UpdateDeviceModeUseCase,
    private val updateDehumidifierHumidityLevelUseCase: UpdateDehumidifierHumidityLevelUseCase,
    private val updateDehumidifierFanSpeedUseCase: UpdateDehumidifierFanSpeedUseCase,
    private val setDeviceHistoryUseCase: SetDeviceHistoryUseCase
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

    private val _deviceStatus = MutableStateFlow<DehumidifierStatusData?>(null)
    val deviceStatus: StateFlow<DehumidifierStatusData?> = _deviceStatus

    fun fetchDeviceStatus(deviceId: String) {
        viewModelScope.launch {
            _deviceStatus.value = getDehumidifierStatusUseCase.execute(deviceId)
        }
    }

    fun updateDeviceState(deviceId: String, deviceState: DeviceState) {
        viewModelScope.launch {
            val deviceHistoryData = updateDeviceStateUseCase.execute(deviceId, deviceState)
            if (deviceHistoryData != null) {
                setDeviceHistoryUseCase.execute(deviceId, deviceHistoryData)
            }
        }
    }

    fun updateDeviceMode(deviceId: String, deviceMode: DeviceMode) {
        viewModelScope.launch {
            updateDeviceModeUseCase.execute(deviceId, deviceMode)
        }
    }

    fun updateDehumidifierHumidityLevel(deviceId: String, humidityLevel: Int) {
        viewModelScope.launch {
            updateDehumidifierHumidityLevelUseCase.execute(deviceId, humidityLevel)
        }
    }

    fun updateDehumidifierFanSpeed(deviceId: String, fanSpeed: Int) {
        viewModelScope.launch {
            updateDehumidifierFanSpeedUseCase.execute(deviceId, fanSpeed)
        }
    }
}