package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.model.RoomData
import com.aposiamp.smartliving.domain.usecase.main.SetRoomDataUseCase
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateRoomName
import com.aposiamp.smartliving.presentation.model.FormResult
import com.aposiamp.smartliving.presentation.ui.event.main.CreateRoomFormEvent
import com.aposiamp.smartliving.presentation.ui.state.main.CreateRoomFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateANewRoomViewModel(
    private val validateRoomName: ValidateRoomName,
    private val setRoomDataUseCase: SetRoomDataUseCase
) : ViewModel() {
    private var _formState by mutableStateOf(CreateRoomFormState())
    val formState: CreateRoomFormState get() = _formState

    private val _createRoomFlow = MutableStateFlow<Result<FormResult>?>(null)
    val createRoomFlow: StateFlow<Result<FormResult>?> = _createRoomFlow

    fun onEvent(event: CreateRoomFormEvent) {
        when (event) {
            is CreateRoomFormEvent.RoomNameChanged -> {
                _formState = _formState.copy(roomName = event.roomName)
            }
            is CreateRoomFormEvent.Submit -> {
                submitData(event.placeId)
            }
        }
    }

    private fun submitData(placeId: String) {
        val roomNameResult = validateRoomName.execute(_formState.roomName)

        val hasError = roomNameResult.errorMessage != null

        if (hasError) {
            _formState = _formState.copy(
                roomNameError = roomNameResult.errorMessage
            )
            return
        }
        createRoom(placeId)
    }

    private fun createRoom(placeId: String) = viewModelScope.launch {
        try {
            val roomData = RoomData(
                roomId = null,
                roomName = _formState.roomName
            )
            setRoomDataUseCase.execute(placeId, roomData)
            _createRoomFlow.value = Result.Success(FormResult(success = true))
        } catch (e: Exception) {
            _formState = _formState.copy(roomNameError = e.message)
            _createRoomFlow.value = Result.Error(e)
        }
    }
}