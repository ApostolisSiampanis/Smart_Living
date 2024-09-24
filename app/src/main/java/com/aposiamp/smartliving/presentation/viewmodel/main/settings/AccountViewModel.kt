package com.aposiamp.smartliving.presentation.viewmodel.main.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.usecase.user.DeleteUserUseCase
import com.aposiamp.smartliving.domain.usecase.user.UpdatePasswordUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePassword
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccountViewModel(
    private val validatePassword: ValidatePassword,
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
): ViewModel() {
    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> get() = _passwordError

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String> get() = _toastMessage

    fun updatePassword(context: Context, password: String, onSuccess: () -> Unit) {
        val passwordResult = validatePassword.execute(password, true)
        if (passwordResult.successful) {
            _passwordError.value = null
            viewModelScope.launch {
                try {
                    updatePasswordUseCase.execute(password)
                    _toastMessage.emit(context.getString(R.string.password_updated_successfully))
                    onSuccess()
                } catch (e: Exception) {
                    _passwordError.value = e.message
                }
            }
        } else {
            _passwordError.value = passwordResult.errorMessage
        }
    }

    fun deleteUserAndLogout(context: Context, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                deleteUserUseCase.execute()
                onSuccess()
            } catch (e: Exception) {
                _toastMessage.emit(e.message ?: context.getString(R.string.error_deleting_account))
            }
        }
    }
}