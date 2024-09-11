package com.aposiamp.smartliving.presentation.viewmodel.welcome

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.CheckIfSpaceDataExistsUseCase
import com.aposiamp.smartliving.presentation.ui.activity.main.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WelcomeNavigationViewModel(
    context: Context,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val checkIfSpaceDataExistsUseCase: CheckIfSpaceDataExistsUseCase
) : ViewModel() {
    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination

    init {
        determineStartDestination(context)
    }

    private fun determineStartDestination(context: Context) {
        viewModelScope.launch {
            val user = getCurrentUserUseCase.execute()
            if (user == null) {
                _startDestination.value = "welcome"
            } else {
                if (determineActivity(context, checkIfSpaceDataExistsUseCase)) {
                    _startDestination.value = "welcome"
                } else {
                    _startDestination.value = "permissions"
                }
            }
        }
    }

    private suspend fun determineActivity(
        context: Context,
        checkIfSpaceDataExistsUseCase: CheckIfSpaceDataExistsUseCase
    ): Boolean {
        return try {
            val isSpaceExists = checkIfSpaceDataExistsUseCase.execute()
            if (isSpaceExists) {
                val intent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                context.startActivity(intent)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}