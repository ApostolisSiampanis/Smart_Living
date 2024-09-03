package com.aposiamp.smartliving.presentation.mapper

import androidx.navigation.NavController
import com.aposiamp.smartliving.domain.model.DropdownMenuItemData
import com.aposiamp.smartliving.presentation.model.DropdownMenuItemUiModel

fun DropdownMenuItemData.toUiModel(navController: NavController): DropdownMenuItemUiModel {
    val onClick: () -> Unit = when (this.text) {
        "Add a New Room" -> { { navController.navigate("") } } //TODO: Add the correct route
        "Add a New Device" -> { { navController.navigate("") }} //TODO: Add the correct route
        else -> { { } }
    }
    return DropdownMenuItemUiModel(
        text = this.text,
        onClick = onClick
    )
}

fun List<DropdownMenuItemData>.toUiModelList(navController: NavController): List<DropdownMenuItemUiModel> {
    return this.map { it.toUiModel(navController) }
}