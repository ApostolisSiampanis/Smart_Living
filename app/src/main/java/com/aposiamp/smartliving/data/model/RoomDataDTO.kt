package com.aposiamp.smartliving.data.model

import com.google.firebase.database.PropertyName

data class RoomDataDTO(
    @get:PropertyName("roomId") var roomId: String? = null,
    @get:PropertyName("room_name") @set:PropertyName("room_name") var roomName: String? = null
)
