package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.presentation.model.NavigationDrawerUiItem

interface NavigationDrawerRepository {
    fun getNavigationDrawerItems(): List<NavigationDrawerUiItem>
}