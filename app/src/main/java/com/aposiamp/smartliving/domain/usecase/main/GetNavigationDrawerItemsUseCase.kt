package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.model.NavigationItemData

class GetNavigationDrawerItemsUseCase {
    fun execute(settingsTitle: String, aboutTitle: String, logoutTitle: String): List<NavigationItemData> {
        return listOf(
            NavigationItemData(title = settingsTitle),
            NavigationItemData(title = aboutTitle),
            NavigationItemData(title = logoutTitle)
        )
    }
}