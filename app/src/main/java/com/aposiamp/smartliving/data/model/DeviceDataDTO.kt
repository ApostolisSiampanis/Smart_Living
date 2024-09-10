package com.aposiamp.smartliving.data.model

import com.google.firebase.database.PropertyName

data class DeviceDataDTO(
    @get:PropertyName("device_name") @set:PropertyName("device_name") var deviceName: String? = null,
    @get:PropertyName("device_type") @set:PropertyName("device_type") var deviceType: String? = null,
    @get:PropertyName("device_state") @set:PropertyName("device_state") var deviceState: Boolean? = null,
    @get:PropertyName("device_mode") @set:PropertyName("device_mode") var deviceMode: String? = null,
    // TODO: Add values for each device type
)
