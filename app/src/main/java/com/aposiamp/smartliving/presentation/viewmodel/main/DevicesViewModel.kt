package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.usecase.main.GetDevicesSpaceNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DevicesViewModel(
    private val getDevicesSpaceNameUseCase: GetDevicesSpaceNameUseCase
) : ViewModel() {
    private val _spaceName = MutableStateFlow("")
    val spaceName: StateFlow<String> = _spaceName

    init {
        fetchSpaceName()
    }

    private fun fetchSpaceName() {
        viewModelScope.launch {
            val spaceData = getDevicesSpaceNameUseCase.execute()
            _spaceName.value = spaceData.spaceName.toString()
        }
    }
}