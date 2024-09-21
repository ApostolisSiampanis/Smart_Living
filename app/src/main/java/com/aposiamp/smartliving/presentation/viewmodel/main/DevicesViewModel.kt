package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import com.aposiamp.smartliving.domain.usecase.main.CheckIfAnyRoomExistsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DevicesViewModel(
    private val checkIfAnyRoomExistsUseCase: CheckIfAnyRoomExistsUseCase
) : ViewModel() {
    private val _isAnyRoomExists = MutableStateFlow<Boolean?>(null)
    val isAnyRoomExists: StateFlow<Boolean?> get() = _isAnyRoomExists

    suspend fun checkIfAnyRoomExists(spaceId: String) {
        _isAnyRoomExists.value = checkIfAnyRoomExistsUseCase.execute(spaceId)
    }
}