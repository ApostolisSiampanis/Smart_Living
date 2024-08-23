package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

class ValidateFirstName {
    fun execute(firstName: String): ValidateResult {
        if (firstName.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = "The firstname can't be blank"
            )
        }
        val allLetters = firstName.all { it.isLetter() }
        if (!allLetters) {
            return ValidateResult(
                successful = false,
                errorMessage = "The firstname can only contain letters"
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}