package com.aposiamp.smartliving.presentation.ui.state.welcome.auth

data class LoginFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    var errorMessage: String? = null
)