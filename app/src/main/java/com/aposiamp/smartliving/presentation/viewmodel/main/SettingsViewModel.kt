package com.aposiamp.smartliving.presentation.viewmodel.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.usecase.main.GetSettingsScreenItemsUseCase
import com.aposiamp.smartliving.presentation.mapper.SettingsItemUiMapper.toUiModelList
import com.aposiamp.smartliving.presentation.model.SettingsItemUiModel

class SettingsViewModel(
    private val getSettingsScreenItemsUseCase: GetSettingsScreenItemsUseCase
) : ViewModel() {
    fun getSettingsScreenItems(context: Context): List<SettingsItemUiModel> {
        val domainItems = getSettingsScreenItemsUseCase.execute(
            profileText = context.getString(R.string.profile),
            accountText = context.getString(R.string.account)
        )

        return domainItems.toUiModelList()
    }
}