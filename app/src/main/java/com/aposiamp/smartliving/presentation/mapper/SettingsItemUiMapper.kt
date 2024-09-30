package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.SettingsItemData
import com.aposiamp.smartliving.presentation.model.SettingsItemUiModel
import com.aposiamp.smartliving.presentation.ui.activity.main.navigation.MainDestination

object SettingsItemUiMapper {
    private fun toUiModel(domain: SettingsItemData): SettingsItemUiModel {
        val titleResId = when (domain.title) {
            "Profile" -> R.string.profile
            "Account" -> R.string.account
            else -> 0
        }

        val icon = when (domain.title) {
            "Profile" -> R.drawable.profile
            "Account" -> R.drawable.account_box
            else -> null
        }

        val route = when (domain.title) {
            "Profile" -> MainDestination.Profile.route
            "Account" -> MainDestination.Account.route
            else -> null
        }

        return SettingsItemUiModel(
            titleResId = titleResId,
            icon = icon,
            route = route
        )
    }

    fun List<SettingsItemData>.toUiModelList(): List<SettingsItemUiModel> {
        return this.map { toUiModel(it) }
    }
}