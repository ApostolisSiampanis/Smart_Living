package com.aposiamp.smartliving.presentation.viewmodel.main.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.usecase.user.UpdateEmailUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val validateEmail: ValidateEmail,
    private val updateEmailUseCase: UpdateEmailUseCase
) : ViewModel() {
    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> get() = _emailError

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> get() = _showDialog

    fun validateAndUpdateEmail(email:String) {
        val emailResult = validateEmail.execute(email)
        if (emailResult.successful) {
            _emailError.value = null
            viewModelScope.launch {
                try {
                    updateEmailUseCase.execute(email)
                    _showDialog.value = true
                } catch (e: Exception) {
                    _emailError.value = e.message
                }
            }
        } else {
            _emailError.value = emailResult.errorMessage
        }
    }

    fun dismissDialog() {
        _showDialog.value = false
    }
}