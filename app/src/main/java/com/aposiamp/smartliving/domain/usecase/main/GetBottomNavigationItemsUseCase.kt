package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.repository.BottomMenuRepository
import com.aposiamp.smartliving.presentation.model.NavigationUiItem

class GetBottomNavigationItemsUseCase(
    private val repository: BottomMenuRepository
) {
    fun execute(): List<NavigationUiItem> {
        return repository.getBottomMenuItems()
    }
}