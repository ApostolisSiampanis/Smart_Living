package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.RoomDataDTO
import com.aposiamp.smartliving.domain.model.RoomData

object RoomDataMapper {
    fun toDto(domain: RoomData): RoomDataDTO {
        return RoomDataDTO(
            roomId = domain.roomId,
            roomName = domain.roomName
        )
    }
}