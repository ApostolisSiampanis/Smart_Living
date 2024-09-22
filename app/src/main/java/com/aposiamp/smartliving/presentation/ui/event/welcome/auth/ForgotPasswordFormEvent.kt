package com.aposiamp.smartliving.presentation.ui.event.welcome.auth

sealed class ForgotPasswordFormEvent {
    data class EmailChanged(val email: String) : ForgotPasswordFormEvent()
    data object ResetPasswordButtonClicked : ForgotPasswordFormEvent()
}