package com.aposiamp.smartliving.presentation.ui.state.main

data class AddDeviceFormState(
    val deviceId: String = "",
    val deviceIdError: String? = null,
    val deviceName: String = "",
    val deviceNameError: String? = null,
    val roomName: String = "",
    val roomNameError: String? = null
)
