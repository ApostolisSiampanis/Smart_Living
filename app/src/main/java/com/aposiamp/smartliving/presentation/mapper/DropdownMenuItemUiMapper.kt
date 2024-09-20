package com.aposiamp.smartliving.presentation.mapper

import androidx.navigation.NavController
import com.aposiamp.smartliving.domain.model.DropdownMenuItemData
import com.aposiamp.smartliving.presentation.model.DropdownMenuItemUiModel

object DropdownMenuItemUiMapper {
    private fun toUiModel(domain: DropdownMenuItemData, navController: NavController): DropdownMenuItemUiModel {
        val onClick: () -> Unit = when (domain.text) {
            "Add a New Room" -> { { navController.navigate("createANewRoom") } } //TODO: Add the correct route
            "Add a New Device" -> { { navController.navigate("addANewDevice") }} //TODO: Add the correct route
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