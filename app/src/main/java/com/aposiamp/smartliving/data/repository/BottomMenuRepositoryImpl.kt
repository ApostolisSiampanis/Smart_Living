package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.repository.BottomMenuRepository
import com.aposiamp.smartliving.presentation.model.NavigationUiItem

class BottomMenuRepositoryImpl : BottomMenuRepository {
    override fun getBottomMenuItems() = listOf(
        NavigationUiItem(
            titleResId = R.string.devices,
            unselectedIcon = R.drawable.devices_outlined,
            selectedIcon = R.drawable.devices_filled,
            route = "devices"
        ),
        NavigationUiItem(
            titleResId = R.string.routines,
            unselectedIcon = R.drawable.routines_outlined,
            selectedIcon = R.drawable.routines_filled,
            route = "routines"
        ),
        NavigationUiItem(
            titleResId = R.string.energy,
            unselectedIcon = R.drawable.energy_outlined,
            selectedIcon = R.drawable.energy_filled,
            route = "energy"
        )
    )
}