package com.aposiamp.smartliving.presentation.viewmodel.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.usecase.main.GetDropdownMenuItemsUseCase
import com.aposiamp.smartliving.presentation.mapper.toUiModelList
import com.aposiamp.smartliving.presentation.model.DropdownMenuItemUiModel

class DevicesViewModel(
    private val getDropdownMenuItemsUseCase: GetDropdownMenuItemsUseCase
) : ViewModel() {
    fun getDropdownMenuItems(context: Context, navController: NavController): List<DropdownMenuItemUiModel> {
        val domainItems = getDropdownMenuItemsUseCase.execute(
            addANewRoomText = context.getString(R.string.add_a_new_room),
            addANewDeviceText = context.getString(R.string.add_a_new_device)
        )

        return domainItems.toUiModelList(navController)
    }
}