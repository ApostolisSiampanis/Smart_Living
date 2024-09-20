package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidateDeviceId(private val context: Context) {
    fun execute(deviceId: String): ValidateResult {
        if (deviceId.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_device_id_can_not_be_blank)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}