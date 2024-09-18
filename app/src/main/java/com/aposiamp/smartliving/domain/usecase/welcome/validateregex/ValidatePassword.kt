package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidatePassword(private val context: Context) {
    fun execute(password: String, isLogin: Boolean): ValidateResult {
        if (password.isBlank() && isLogin) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_password_can_not_be_blank)
            )
        }
        if (password.length < 8 && !isLogin) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_password_must_be_at_least_8_characters)
            )
        }
        val containsLetterAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLetterAndDigits && !isLogin) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_password_must_contain_at_least_one_letter_and_one_digit)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}