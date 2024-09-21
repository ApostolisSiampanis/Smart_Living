package com.aposiamp.smartliving.presentation.ui.event.main

import com.aposiamp.smartliving.domain.model.DeviceType

sealed class AddDeviceFormEvent {
    data class DeviceIdChanged(val deviceId: String) : AddDeviceFormEvent()
    data class DeviceTypeChanged(val deviceType: DeviceType) : AddDeviceFormEvent()
    data class DeviceNameChanged(val deviceName: String) : AddDeviceFormEvent()
    data class RoomIdChanged(val roomId: String) : AddDeviceFormEvent()
    data class Submit(val placeId: String) : AddDeviceFormEvent()
}