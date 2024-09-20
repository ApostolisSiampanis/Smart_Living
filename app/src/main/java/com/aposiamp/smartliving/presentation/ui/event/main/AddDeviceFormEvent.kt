package com.aposiamp.smartliving.presentation.ui.event.main

sealed class AddDeviceFormEvent {
    data class DeviceIdChanged(val deviceId: String) : AddDeviceFormEvent()
    data class DeviceNameChanged(val deviceName: String) : AddDeviceFormEvent()
    data class RoomNameChanged(val roomName: String) : AddDeviceFormEvent()
    data class Submit(val placeId: String) : AddDeviceFormEvent()
}