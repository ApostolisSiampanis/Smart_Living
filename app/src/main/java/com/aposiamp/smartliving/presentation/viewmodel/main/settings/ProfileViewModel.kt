package com.aposiamp.smartliving.presentation.viewmodel.main.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.usecase.user.UpdateEmailUseCase
import com.aposiamp.smartliving.domain.usecase.user.UpdateFirstNameUseCase
import com.aposiamp.smartliving.domain.usecase.user.UpdateLastNameUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateEmail
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateFirstName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateLastName
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val validateFirstName: ValidateFirstName,
    private val validateLastName: ValidateLastName,
    private val validateEmail: ValidateEmail,
    private val updateFirstNameUseCase: UpdateFirstNameUseCase,
    private val updateLastNameUseCase: UpdateLastNameUseCase,
    private val updateEmailUseCase: UpdateEmailUseCase
) : ViewModel() {
    private val _firstNameError = MutableStateFlow<String?>(null)
    val firstNameError: StateFlow<String?> get() = _firstNameError

    private val _lastNameError = MutableStateFlow<String?>(null)
    val lastNameError: StateFlow<String?> get() = _lastNameError

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> get() = _emailError

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> get() = _showDialog

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String> get() = _toastMessage

    fun validateAndUpdateFirstName(context: Context, firstName: String) {
        val firstNameResult = validateFirstName.execute(firstName)
        if (firstNameResult.successful) {
            _firstNameError.value = null
            viewModelScope.launch {
                try {
                    updateFirstNameUseCase.execute(firstName)
                    _toastMessage.emit(context.getString(R.string.first_name_updated_successfully))
                } catch (e: Exception) {
                    _firstNameError.value = e.message
                }
            }
        } else {
            _firstNameError.value = firstNameResult.errorMessage
        }
    }

    fun validateAndUpdateLastName(context: Context, lastName: String) {
        val lastNameResult = validateLastName.execute(lastName)
        if (lastNameResult.successful) {
            _lastNameError.value = null
            viewModelScope.launch {
                try {
                    updateLastNameUseCase.execute(lastName)
                    _toastMessage.emit(context.getString(R.string.last_name_updated_successfully))
                } catch (e: Exception) {
                    _lastNameError.value = e.message
                }
            }
        } else {
            _lastNameError.value = lastNameResult.errorMessage
        }
    }

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