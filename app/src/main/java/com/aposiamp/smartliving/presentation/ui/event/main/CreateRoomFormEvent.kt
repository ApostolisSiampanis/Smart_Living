package com.aposiamp.smartliving.presentation.ui.event.main

sealed class CreateRoomFormEvent {
    data class RoomNameChanged(val roomName: String) : CreateRoomFormEvent()
    data class Submit(val placeId: String) : CreateRoomFormEvent()
}