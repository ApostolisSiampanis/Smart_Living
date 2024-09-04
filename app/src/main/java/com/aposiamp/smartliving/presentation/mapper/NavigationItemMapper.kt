package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.NavigationItemData
import com.aposiamp.smartliving.presentation.model.NavigationItemUiModel

fun NavigationItemData.toUiModel(): NavigationItemUiModel {
    val titleResId = when (this.title) {
        // Bottom Menu
        "Devices" -> R.string.devices
        "Routines" -> R.string.routines
        "Energy" -> R.string.energy
        // Navigation Drawer
        "Settings" -> R.string.settings
        "About" -> R.string.about
        "Logout" -> R.string.logout
        else -> 0
    }

    val selectedIcon = when (this.title) {
        // Bottom Menu
        "Devices" -> R.drawable.devices_filled
        "Routines" -> R.drawable.routines_filled
        "Energy" -> R.drawable.energy_filled
        // Navigation Drawer
        "Settings" -> R.drawable.settings_filled
        "About" -> R.drawable.about_filled
        "Logout" -> R.drawable.logout
        else -> null
    }

    val unSelectedIcon = when (this.title) {
        // Bottom Menu
        "Devices" -> R.drawable.devices_outlined
        "Routines" -> R.drawable.routines_outlined
        "Energy" -> R.drawable.energy_outlined
        // Navigation Drawer
        "Settings" -> R.drawable.settings_outlined
        "About" -> R.drawable.about_outlined
        "Logout" -> R.drawable.logout
        else -> null
    }

    val route = when (this.title) {
        // Bottom Menu
        "Devices" -> "devices"
        "Routines" -> "routines"
        "Energy" -> "energy"
        // Navigation Drawer
        "Settings" -> "settings"
        "About" -> "about"
        "Logout" -> null
        else -> null
    }

    return NavigationItemUiModel(
        titleResId = titleResId,
        unselectedIcon = unSelectedIcon,
        selectedIcon = selectedIcon,
        route = route
    )
}

fun List<NavigationItemData>.toUiModelList(): List<NavigationItemUiModel> {
    return this.map { it.toUiModel() }
}