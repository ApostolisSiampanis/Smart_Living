package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

data class ValidateResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
