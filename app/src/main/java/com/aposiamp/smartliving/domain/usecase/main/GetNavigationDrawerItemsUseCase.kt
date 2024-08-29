package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.repository.NavigationDrawerRepository
import com.aposiamp.smartliving.presentation.model.NavigationDrawerUiItem

class GetNavigationDrawerItemsUseCase(
    private val repository: NavigationDrawerRepository
) {
    fun execute(): List<NavigationDrawerUiItem> {
        return repository.getNavigationDrawerItems()
    }
}