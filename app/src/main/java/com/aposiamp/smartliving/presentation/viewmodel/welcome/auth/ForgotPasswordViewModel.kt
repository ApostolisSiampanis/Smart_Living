package com.aposiamp.smartliving.presentation.viewmodel.welcome.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.usecase.user.ForgotPasswordUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateEmail
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.presentation.model.FormResult
import com.aposiamp.smartliving.presentation.ui.event.welcome.auth.ForgotPasswordFormEvent
import com.aposiamp.smartliving.presentation.ui.state.welcome.auth.ForgotPasswordFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val validateEmail: ValidateEmail,
    private val forgotPasswordUseCase: ForgotPasswordUseCase
): ViewModel() {
    private var _state by mutableStateOf(ForgotPasswordFormState())
    val state: ForgotPasswordFormState get() = _state

    private val _forgotPasswordFlow= MutableStateFlow<Result<FormResult>?>(null)
    val forgotPasswordFlow: StateFlow<Result<FormResult>?> = _forgotPasswordFlow

    fun onEvent(event: ForgotPasswordFormEvent) {
        when (event) {
            is ForgotPasswordFormEvent.EmailChanged -> {
                _state = _state.copy(email = event.email)
            }

            is ForgotPasswordFormEvent.ResetPasswordButtonClicked -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(_state.email)

        val hasError = listOf(
            emailResult
        ).any { it.errorMessage != null }

        if (hasError) {
            _state = _state.copy(
                emailError = emailResult.errorMessage
            )
            return
        }
        resetPassword(_state.email)
    }

    private fun resetPassword(email: String) = viewModelScope.launch {
        try {
            forgotPasswordUseCase.execute(email)
            _forgotPasswordFlow.value = Result.Success(FormResult(success = true))
        } catch (e: Exception) {
            _state = _state.copy(errorMessage = e.message)
            _forgotPasswordFlow.value = Result.Error(e)
        }
    }
}