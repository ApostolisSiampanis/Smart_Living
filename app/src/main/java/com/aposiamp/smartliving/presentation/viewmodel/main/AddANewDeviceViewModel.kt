package com.aposiamp.smartliving.presentation.viewmodel.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.DeviceData
import com.aposiamp.smartliving.domain.model.DeviceIdAndTypeData
import com.aposiamp.smartliving.domain.model.DeviceType
import com.aposiamp.smartliving.domain.model.DeviceTypeItem
import com.aposiamp.smartliving.domain.usecase.devices.CheckIfDeviceExistsUseCase
import com.aposiamp.smartliving.domain.usecase.devices.SetDeviceDataUseCase
import com.aposiamp.smartliving.domain.usecase.devices.ValidateDeviceExistence
import com.aposiamp.smartliving.domain.usecase.main.GetRoomListUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateDeviceId
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateDeviceName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateRoomId
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.presentation.mapper.RoomDataUiMapper
import com.aposiamp.smartliving.presentation.model.FormResult
import com.aposiamp.smartliving.presentation.model.DeviceTypeUiItem
import com.aposiamp.smartliving.presentation.model.RoomDataUiModel
import com.aposiamp.smartliving.presentation.ui.event.main.AddDeviceFormEvent
import com.aposiamp.smartliving.presentation.ui.state.main.AddDeviceFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddANewDeviceViewModel(
    private val validateDeviceName: ValidateDeviceName,
    private val validateDeviceId: ValidateDeviceId,
    private val validateRoomId: ValidateRoomId,
    private val validateDeviceExistence: ValidateDeviceExistence,
    private val checkIfDeviceExistsUseCase: CheckIfDeviceExistsUseCase,
    private val getRoomListUseCase: GetRoomListUseCase,
    private val setDeviceDataUseCase: SetDeviceDataUseCase
) : ViewModel() {
    private var _formState by mutableStateOf(AddDeviceFormState())
    val formState: AddDeviceFormState get() = _formState

    private val _addDeviceFlow = MutableStateFlow<Result<FormResult>?>(null)
    val addDeviceFlow: StateFlow<Result<FormResult>?> = _addDeviceFlow

    private val _roomListFlow = MutableStateFlow<List<RoomDataUiModel>>(emptyList())
    val roomListFlow: StateFlow<List<RoomDataUiModel>> = _roomListFlow

    suspend fun onEvent(event: AddDeviceFormEvent) {
        when (event) {
            is AddDeviceFormEvent.DeviceNameChanged -> {
                _formState = _formState.copy(deviceName = event.deviceName)
            }

            is AddDeviceFormEvent.DeviceTypeChanged -> {
                _formState = _formState.copy(deviceType = event.deviceType)
            }

            is AddDeviceFormEvent.DeviceIdChanged -> {
                _formState = _formState.copy(deviceId = event.deviceId)
            }

            is AddDeviceFormEvent.RoomIdChanged -> {
                _formState = _formState.copy(roomId = event.roomId)
            }

            is AddDeviceFormEvent.Submit -> {
                submitData(event.placeId)
            }
        }
    }

    private suspend fun submitData(placeId: String) {
        val deviceNameResult = validateDeviceName.execute(_formState.deviceName)
        val deviceIdResult = validateDeviceId.execute(_formState.deviceId)
        val roomIdResult = validateRoomId.execute(_formState.roomId)
        val deviceExists = checkIfDeviceExistsUseCase.execute(DeviceIdAndTypeData(_formState.deviceId, _formState.deviceType))
        val deviceExistResult = validateDeviceExistence.execute(deviceExists)

        val hasError = listOf(
            deviceNameResult,
            deviceIdResult,
            roomIdResult,
            deviceExistResult
        ).any { it.errorMessage != null }

        if (hasError) {
            _formState = _formState.copy(
                deviceNameError = deviceNameResult.errorMessage,
                deviceIdError = deviceIdResult.errorMessage,
                roomIdError = roomIdResult.errorMessage,
                deviceExistenceError = deviceExistResult.errorMessage
            )
            return
        }
        addDevice(placeId)
    }

    private fun addDevice(placeId: String) = viewModelScope.launch {
        try {
            val deviceData = DeviceData(
                deviceId = _formState.deviceId,
                deviceType = _formState.deviceType,
                deviceName = _formState.deviceName
            )
            setDeviceDataUseCase.execute(placeId, _formState.roomId, deviceData)
            _addDeviceFlow.value = Result.Success(FormResult(success = true))
        } catch (e: Exception) {
            _formState = _formState.copy(deviceExistenceError = e.message)
            _addDeviceFlow.value = Result.Error(e)
        }
    }

    private val deviceTypes = listOf(
        DeviceTypeItem(DeviceType.AIR_CONDITIONER),
        DeviceTypeItem(DeviceType.THERMOSTAT),
        DeviceTypeItem(DeviceType.DEHUMIDIFIER),
    )

    val uiDeviceTypes: List<DeviceTypeUiItem> = deviceTypes.mapNotNull {
        when (it.type) {
            DeviceType.AIR_CONDITIONER -> DeviceTypeUiItem(
                type = it.type,
                text = R.string.air_conditioner
            )
            DeviceType.THERMOSTAT -> DeviceTypeUiItem(
                type = it.type,
                text = R.string.thermostat
            )
            DeviceType.DEHUMIDIFIER -> DeviceTypeUiItem(
                type = it.type,
                text = R.string.dehumidifier
            )
            else -> null
        }
    }

    fun fetchRoomList(spaceId: String) {
        viewModelScope.launch {
            try {
                val roomListDomain = getRoomListUseCase.execute(spaceId)
                val roomList = RoomDataUiMapper.fromDomainList(roomListDomain)
                _roomListFlow.value = roomList
                if (roomList.isNotEmpty()) {
                    _formState = _formState.copy(roomId = roomList.first().roomId ?: "")
                }
            } catch (e: Exception) {
                Log.e("AddANewDeviceViewModel", "Error fetching room list", e)
            }
        }
    }
}