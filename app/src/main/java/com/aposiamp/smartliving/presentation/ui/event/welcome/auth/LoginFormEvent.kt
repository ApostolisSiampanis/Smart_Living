package com.aposiamp.smartliving.presentation.ui.event.welcome.auth

sealed class LoginFormEvent {
    data class EmailChanged(val email: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()
    data object Submit : LoginFormEvent()
}