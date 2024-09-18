package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidateFirstName(private val context: Context) {
    fun execute(firstName: String): ValidateResult {
        if (firstName.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_first_name_can_not_be_blank)
            )
        }
        val allLetters = firstName.all { it.isLetter() }
        if (!allLetters) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_first_name_can_only_contain_letters)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}