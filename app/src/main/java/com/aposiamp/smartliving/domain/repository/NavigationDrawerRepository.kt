package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.presentation.model.NavigationUiItem

interface NavigationDrawerRepository {
    fun getNavigationDrawerItems(): List<NavigationUiItem>
}