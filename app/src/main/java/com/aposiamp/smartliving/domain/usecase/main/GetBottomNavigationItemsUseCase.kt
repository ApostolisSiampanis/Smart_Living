package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.model.NavigationItemData

class GetBottomNavigationItemsUseCase {
    fun execute(devicesTitle: String, energyTitle: String): List<NavigationItemData> {
        return listOf(
            NavigationItemData(title = devicesTitle),
            NavigationItemData(title = energyTitle)
        )
    }
}