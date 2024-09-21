package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidateDeviceName(private val context: Context) {
    fun execute(deviceName: String): ValidateResult {
        if (deviceName.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_device_name_can_not_be_blank)
            )
        }
        if (deviceName.length < 3) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_device_name_must_be_at_least_3_characters)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}