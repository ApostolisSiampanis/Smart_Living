package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.domain.model.RoomData
import com.aposiamp.smartliving.presentation.model.RoomDataUiModel

object RoomDataUiMapper {
    private fun fromDomain(domain: RoomData): RoomDataUiModel {
        return RoomDataUiModel(
            roomId = domain.roomId,
            roomName = domain.roomName
        )
    }

    fun fromDomainList(domainList: List<RoomData>): List<RoomDataUiModel> {
        return domainList.map { fromDomain(it) }
    }
}