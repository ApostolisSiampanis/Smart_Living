package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.model.SettingsItemData

class GetSettingsScreenItemsUseCase {
    fun execute(profileText: String, accountText: String): List<SettingsItemData> {
        return listOf(
            SettingsItemData(title = profileText),
            SettingsItemData(title = accountText)
        )
    }
}