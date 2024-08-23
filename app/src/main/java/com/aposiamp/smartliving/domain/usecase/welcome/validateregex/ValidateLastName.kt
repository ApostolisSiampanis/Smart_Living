package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

class ValidateLastName {
    fun execute(lastName: String): ValidateResult {
        if (lastName.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = "The firstname can't be blank"
            )
        }
        val allLetters = lastName.all { it.isLetter() }
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