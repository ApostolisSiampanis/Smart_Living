package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidateSpaceName(private val context: Context) {
    fun execute(spaceName: String): ValidateResult {
        if (spaceName.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_space_name_can_not_be_blank)
            )
        }
        if (spaceName.length < 3) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_space_name_must_be_at_least_3_characters)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}