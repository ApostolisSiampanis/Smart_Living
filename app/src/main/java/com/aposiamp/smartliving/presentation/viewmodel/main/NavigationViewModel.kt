package com.aposiamp.smartliving.presentation.viewmodel.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.usecase.main.GetBottomNavigationItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetDropdownMenuItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetNavigationDrawerItemsUseCase
import com.aposiamp.smartliving.presentation.mapper.DropdownMenuItemUiMapper.toUiModelList
import com.aposiamp.smartliving.presentation.mapper.NavigationItemUiMapper.toUiModelList
import com.aposiamp.smartliving.presentation.model.DropdownMenuItemUiModel
import com.aposiamp.smartliving.presentation.model.NavigationItemUiModel

class NavigationViewModel(
    private val getDropdownMenuItemsUseCase: GetDropdownMenuItemsUseCase,
    private val getBottomNavigationItemsUseCase: GetBottomNavigationItemsUseCase,
    private val getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase
) : ViewModel() {
    fun getBottomNavigationItems(context: Context): List<NavigationItemUiModel> {
        val domainItems = getBottomNavigationItemsUseCase.execute(
            devicesTitle = context.getString(R.string.devices),
            energyTitle = context.getString(R.string.energy)
        )

        return domainItems.toUiModelList()
    }

    fun getNavigationDrawerItems(context: Context): List<NavigationItemUiModel> {
        val domainItems = getNavigationDrawerItemsUseCase.execute(
            homeTitle = context.getString(R.string.home),
            settingsTitle = context.getString(R.string.settings),
            aboutTitle = context.getString(R.string.about),
            logoutTitle = context.getString(R.string.logout)
        )

        return domainItems.toUiModelList()
    }

    fun getDropdownMenuItems(context: Context, navController: NavController): List<DropdownMenuItemUiModel> {
        val domainItems = getDropdownMenuItemsUseCase.execute(
            addANewRoomText = context.getString(R.string.add_a_new_room),
            addANewDeviceText = context.getString(R.string.add_a_new_device)
        )

        return domainItems.toUiModelList(navController)
    }
}