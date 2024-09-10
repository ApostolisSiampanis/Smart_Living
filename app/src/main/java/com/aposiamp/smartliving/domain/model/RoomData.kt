package com.aposiamp.smartliving.domain.model

data class RoomData(
    val roomName: String,
    val devices: List<DeviceData>
)
