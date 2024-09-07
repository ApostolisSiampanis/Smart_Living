package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidateTerms(private val context: Context) {
    fun execute(acceptedTerms: Boolean): ValidateResult {
        if (!acceptedTerms) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.you_need_to_accept_the_terms)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}