package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

class ValidatePassword {
    fun execute(password: String, isLogin: Boolean): ValidateResult {
        if (password.isBlank() && isLogin) {
            return ValidateResult(
                successful = false,
                errorMessage = "The password can't be blank"
            )
        }
        if (password.length < 8 && !isLogin) {
            return ValidateResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }
        val containsLetterAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLetterAndDigits && !isLogin) {
            return ValidateResult(
                successful = false,
                errorMessage = "The password needs to contain at least one letter and one digit"
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}