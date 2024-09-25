package com.aposiamp.smartliving.presentation.viewmodel.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.usecase.main.CheckIfAnyRoomExistsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetRoomListUseCase
import com.aposiamp.smartliving.presentation.mapper.RoomDataUiMapper
import com.aposiamp.smartliving.presentation.model.RoomDataUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DevicesViewModel(
    private val checkIfAnyRoomExistsUseCase: CheckIfAnyRoomExistsUseCase,
    private val getRoomListUseCase: GetRoomListUseCase
) : ViewModel() {
    private val _isAnyRoomExists = MutableStateFlow<Boolean?>(null)
    val isAnyRoomExists: StateFlow<Boolean?> get() = _isAnyRoomExists

    private val _roomListFlow = MutableStateFlow<List<RoomDataUiModel>>(emptyList())
    val roomListFlow: StateFlow<List<RoomDataUiModel>> = _roomListFlow

    suspend fun checkIfAnyRoomExists(spaceId: String) {
        _isAnyRoomExists.value = checkIfAnyRoomExistsUseCase.execute(spaceId)
    }

    fun fetchRoomList(spaceId: String) {
        viewModelScope.launch {
            try {
                val roomListDomain = getRoomListUseCase.execute(spaceId)
                val roomList = RoomDataUiMapper.fromDomainList(roomListDomain)
                _roomListFlow.value = roomList
            } catch (e: Exception) {
                Log.e("DevicesViewModel", "Error fetching room list" , e)
            }
        }
    }
}