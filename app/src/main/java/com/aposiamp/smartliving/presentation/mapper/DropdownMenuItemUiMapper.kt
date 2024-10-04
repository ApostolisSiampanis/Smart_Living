package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.domain.model.DropdownMenuItemData
import com.aposiamp.smartliving.presentation.model.DropdownMenuItemUiModel
import com.aposiamp.smartliving.presentation.ui.activity.main.navigation.MainDestination

object DropdownMenuItemUiMapper {
    private fun toUiModel(domain: DropdownMenuItemData): DropdownMenuItemUiModel {
        val route = when (domain.text) {
            "Add a New Room" -> MainDestination.CreateANewRoom.route
            "Add a New Device" -> MainDestination.AddANewDevice.route
            else -> null
        }
        return DropdownMenuItemUiModel(
            text = domain.text,
            route = route
        )
    }

    fun List<DropdownMenuItemData>.toUiModelList(): List<DropdownMenuItemUiModel> {
        return this.map { toUiModel(it) }
    }
}