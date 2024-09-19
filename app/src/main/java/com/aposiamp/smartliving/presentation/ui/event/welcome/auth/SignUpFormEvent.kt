package com.aposiamp.smartliving.presentation.ui.event.welcome.auth

sealed class SignUpFormEvent {
    data class FirstNameChanged(val firstName: String) : SignUpFormEvent()
    data class LastNameChanged(val lastName: String) : SignUpFormEvent()
    data class EmailChanged(val email: String) : SignUpFormEvent()
    data class PasswordChanged(val password: String) : SignUpFormEvent()
    data class AcceptTerms(val isAccepted: Boolean) : SignUpFormEvent()
    object Submit : SignUpFormEvent()
}