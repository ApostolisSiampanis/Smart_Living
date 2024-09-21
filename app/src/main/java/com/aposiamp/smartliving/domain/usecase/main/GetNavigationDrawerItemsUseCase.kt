package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.model.NavigationItemData

class GetNavigationDrawerItemsUseCase {
    fun execute(homeTitle: String, settingsTitle: String, aboutTitle: String, logoutTitle: String): List<NavigationItemData> {
        return listOf(
            NavigationItemData(title = homeTitle),
            NavigationItemData(title = settingsTitle),
            NavigationItemData(title = aboutTitle),
            NavigationItemData(title = logoutTitle)
        )
    }
}