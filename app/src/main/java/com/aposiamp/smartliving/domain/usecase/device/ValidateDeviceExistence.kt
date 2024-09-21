package com.aposiamp.smartliving.domain.usecase.device

import android.content.Context
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateResult

class ValidateDeviceExistence(private val context: Context) {
    fun execute(deviceExists: Boolean): ValidateResult {
        return if (!deviceExists) {
            ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.this_device_cannot_be_found)
            )
        } else {
            ValidateResult(successful = true)
        }
    }
}