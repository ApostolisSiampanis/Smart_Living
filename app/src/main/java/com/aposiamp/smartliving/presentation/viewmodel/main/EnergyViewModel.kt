package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import com.aposiamp.smartliving.domain.usecase.main.GetPeriodItemsUseCase
import com.aposiamp.smartliving.presentation.mapper.PeriodUiMapper.toUiModelList
import com.aposiamp.smartliving.presentation.model.PeriodItemUiModel

class EnergyViewModel(
    private val getPeriodItemsUseCase: GetPeriodItemsUseCase
) : ViewModel() {
    fun getPeriodItems(): List<PeriodItemUiModel> {
        val domainItems = getPeriodItemsUseCase.execute()
        return domainItems.toUiModelList()
    }
}