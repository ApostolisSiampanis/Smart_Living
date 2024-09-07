package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import android.util.Patterns
import com.aposiamp.smartliving.R

class ValidateEmail(private val context: Context) {
    fun execute(email: String): ValidateResult {
        if (email.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_email_can_not_be_blank)
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.that_is_not_a_valid_email)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}