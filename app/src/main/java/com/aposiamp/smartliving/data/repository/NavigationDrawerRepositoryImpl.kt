package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.repository.NavigationDrawerRepository
import com.aposiamp.smartliving.presentation.model.NavigationUiItem

class NavigationDrawerRepositoryImpl : NavigationDrawerRepository {
    override fun getNavigationDrawerItems() = listOf(
        NavigationUiItem(
            titleResId = R.string.settings,
            unselectedIcon = R.drawable.settings_outlined,
            selectedIcon = R.drawable.settings_filled,
            route = "settings"
        ),
        NavigationUiItem(
            titleResId = R.string.about,
            unselectedIcon = R.drawable.about_outlined,
            selectedIcon = R.drawable.about_filled,
            route = "about"
        ),
        NavigationUiItem(
            titleResId = R.string.logout,
            unselectedIcon = R.drawable.logout,
            selectedIcon = R.drawable.logout,
            route = null
        )
    )
}