package com.aposiamp.smartliving.data.repository

import androidx.compose.ui.res.stringResource
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.repository.NavigationDrawerRepository
import com.aposiamp.smartliving.presentation.model.NavigationDrawerUiItem

class NavigationDrawerRepositoryImpl : NavigationDrawerRepository {
    override fun getNavigationDrawerItems() = listOf(
        NavigationDrawerUiItem(
            titleResId = R.string.settings,
            unselectedIcon = R.drawable.settings_outlined,
            selectedIcon = R.drawable.settings_outlined,
            route = "settings"
        )
    )
}