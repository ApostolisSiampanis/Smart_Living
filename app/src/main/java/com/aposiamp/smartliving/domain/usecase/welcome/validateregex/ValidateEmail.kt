package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.util.Patterns

class ValidateEmail {
    fun execute(email: String): ValidateResult {
        if (email.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidateResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}