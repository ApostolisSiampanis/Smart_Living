package com.aposiamp.smartliving.presentation.ui.state.main

import com.aposiamp.smartliving.domain.model.DeviceType

data class AddDeviceFormState(
    val deviceId: String = "",
    val deviceIdError: String? = null,
    val deviceType: DeviceType = DeviceType.AIR_CONDITIONER,
    val deviceName: String = "",
    val deviceNameError: String? = null,
    val roomId: String = "",
    val roomIdError: String? = null,
    val deviceExistenceError: String? = null
)
