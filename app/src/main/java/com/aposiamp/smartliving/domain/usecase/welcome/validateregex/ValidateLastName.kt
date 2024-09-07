package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidateLastName(private val context: Context) {
    fun execute(lastName: String): ValidateResult {
        if (lastName.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_last_name_can_not_be_blank)
            )
        }
        val allLetters = lastName.all { it.isLetter() }
        if (!allLetters) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_last_name_can_only_contain_letters)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}