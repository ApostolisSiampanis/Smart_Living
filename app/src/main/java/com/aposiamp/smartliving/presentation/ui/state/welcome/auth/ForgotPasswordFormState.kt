package com.aposiamp.smartliving.presentation.ui.state.welcome.auth

data class ForgotPasswordFormState(
    var email: String = "",
    var emailError: String? = null,
    var errorMessage: String? = null
)
