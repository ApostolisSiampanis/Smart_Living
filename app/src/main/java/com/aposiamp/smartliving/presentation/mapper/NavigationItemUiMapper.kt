package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.NavigationItemData
import com.aposiamp.smartliving.presentation.model.NavigationItemUiModel

object NavigationItemUiMapper {
    private fun toUiModel(domain: NavigationItemData): NavigationItemUiModel {
        val titleResId = when (domain.title) {
            // Bottom Menu
            "Devices" -> R.string.devices
            "Energy" -> R.string.energy
            // Navigation Drawer
            "Home" -> R.string.home
            "Settings" -> R.string.settings
            "About" -> R.string.about
            "Logout" -> R.string.logout
            else -> 0
        }

        val selectedIcon = when (domain.title) {
            // Bottom Menu
            "Devices" -> R.drawable.devices_filled
            "Energy" -> R.drawable.energy_filled
            // Navigation Drawer
            "Home" -> R.drawable.home_filled
            "Settings" -> R.drawable.settings_filled
            "About" -> R.drawable.about_filled
            "Logout" -> R.drawable.logout
            else -> null
        }

        val unSelectedIcon = when (domain.title) {
            // Bottom Menu
            "Devices" -> R.drawable.devices_outlined
            "Energy" -> R.drawable.energy_outlined
            // Navigation Drawer
            "Home" -> R.drawable.home_outlined
            "Settings" -> R.drawable.settings_outlined
            "About" -> R.drawable.about_outlined
            "Logout" -> R.drawable.logout
            else -> null
        }

        val route = when (domain.title) {
            // Bottom Menu
            "Devices" -> "devices"
            "Energy" -> "energy"
            // Navigation Drawer
            "Home" -> "devices"
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
        return this.map { toUiModel(it) }
    }
}