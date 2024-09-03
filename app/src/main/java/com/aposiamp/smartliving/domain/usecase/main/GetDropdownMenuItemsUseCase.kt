package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.model.DropdownMenuItemData

class GetDropdownMenuItemsUseCase {
    fun execute(addANewRoomText: String, addANewDeviceText: String): List<DropdownMenuItemData>  {
        return listOf(
            DropdownMenuItemData(text = addANewRoomText),
            DropdownMenuItemData(text = addANewDeviceText)
        )
    }
}