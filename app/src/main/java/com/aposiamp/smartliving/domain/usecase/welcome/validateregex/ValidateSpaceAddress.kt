package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidateSpaceAddress(private val context: Context) {
    fun execute(spaceAddress: String): ValidateResult {
        if (spaceAddress.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_space_address_can_not_be_blank)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}