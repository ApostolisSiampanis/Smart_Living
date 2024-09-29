package com.aposiamp.smartliving.presentation.mapper

import androidx.navigation.NavController
import com.aposiamp.smartliving.domain.model.DropdownMenuItemData
import com.aposiamp.smartliving.presentation.model.DropdownMenuItemUiModel
import com.aposiamp.smartliving.presentation.ui.activity.main.navigation.MainDestination

object DropdownMenuItemUiMapper {
    private fun toUiModel(domain: DropdownMenuItemData, navController: NavController): DropdownMenuItemUiModel {
        val onClick: () -> Unit = when (domain.text) {
            "Add a New Room" -> { { navController.navigate(MainDestination.CreateANewRoom.route) } }
            "Add a New Device" -> { { navController.navigate(MainDestination.AddANewDevice.route) }}
            else -> { { } }
        }
        return DropdownMenuItemUiModel(
            text = domain.text,
            onClick = onClick
        )
    }

    fun List<DropdownMenuItemData>.toUiModelList(navController: NavController): List<DropdownMenuItemUiModel> {
        return this.map { toUiModel(it, navController) }
    }
}