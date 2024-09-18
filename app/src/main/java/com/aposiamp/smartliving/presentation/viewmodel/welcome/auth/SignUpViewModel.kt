package com.aposiamp.smartliving.presentation.viewmodel.welcome.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.domain.usecase.user.SignUpUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateEmail
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateFirstName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateLastName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePassword
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateTerms
import com.aposiamp.smartliving.presentation.ui.event.welcome.auth.SignUpFormEvent
import com.aposiamp.smartliving.presentation.ui.state.welcome.auth.SignUpFormState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val validateFirstName: ValidateFirstName,
    private val validateLastName: ValidateLastName,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateTerms: ValidateTerms
): ViewModel() {
    private var _state by mutableStateOf(SignUpFormState())
    val state: SignUpFormState get() = _state

    private val _signupFlow = MutableStateFlow<Result<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Result<FirebaseUser>?> = _signupFlow

    fun onEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.FirstNameChanged -> {
                _state = _state.copy(firstName = event.firstName)
            }

            is SignUpFormEvent.LastNameChanged -> {
                _state = _state.copy(lastName = event.lastName)
            }

            is SignUpFormEvent.EmailChanged -> {
                _state = _state.copy(email = event.email)
            }

            is SignUpFormEvent.PasswordChanged -> {
                _state = _state.copy(password = event.password)
            }

            is SignUpFormEvent.AcceptTerms -> {
                _state = _state.copy(acceptedTerms = event.isAccepted)
            }

            is SignUpFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val firstNameResult = validateFirstName.execute(_state.firstName)
        val lastNameResult = validateLastName.execute(_state.lastName)
        val emailResult = validateEmail.execute(_state.email)
        val passwordResult = validatePassword.execute(_state.password, isLogin = false)
        val termsResult = validateTerms.execute(_state.acceptedTerms)

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            termsResult
        ).any { it.errorMessage != null }

        if (hasError) {
            _state = _state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        signUpUser(_state.firstName, _state.lastName, _state.email, _state.password)
    }

    private fun signUpUser(firstName: String, lastName: String, email: String, password: String) = viewModelScope.launch {
        try {
            val user = signUpUseCase.execute(firstName, lastName, email, password)
            _signupFlow.value =
                Result.Success(user)
        } catch (e: Exception) {
            _state = _state.copy(errorMessage = e.message)
            _signupFlow.value =
                Result.Error(e)
        }
    }
}