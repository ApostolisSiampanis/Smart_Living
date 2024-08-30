package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.presentation.model.NavigationUiItem

interface BottomMenuRepository {
    fun getBottomMenuItems(): List<NavigationUiItem>
}