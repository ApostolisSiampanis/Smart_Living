package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.usecase.main.CheckIfUserIsInSpaceUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetSpaceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainNavigationViewModel(
    private val getSpaceUseCase: GetSpaceUseCase,
    private val checkIfUserIsInSpaceUseCase: CheckIfUserIsInSpaceUseCase
) : ViewModel() {
    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination

    init {
        determineStartDestination()
    }

    private fun determineStartDestination() {
        viewModelScope.launch {
            val spaceData = getSpaceUseCase.execute()
            val isUserInSpace = spaceData.placeId?.let { checkIfUserIsInSpaceUseCase.execute(it) }
            if (isUserInSpace == true) {
                _startDestination.value = "devices"
            } else {
                _startDestination.value = "notInSpace"
            }
        }
    }
}