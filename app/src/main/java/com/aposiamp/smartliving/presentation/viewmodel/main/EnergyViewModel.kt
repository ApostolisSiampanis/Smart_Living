package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.model.Period
import com.aposiamp.smartliving.domain.usecase.main.GetPeriodDataUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetPeriodItemsUseCase
import com.aposiamp.smartliving.presentation.mapper.PeriodUiMapper.toUiModelList
import com.aposiamp.smartliving.presentation.model.PeriodItemUiModel
import com.aposiamp.smartliving.presentation.model.SimpleDeviceDataUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EnergyViewModel(
    private val getPeriodItemsUseCase: GetPeriodItemsUseCase,
    private val getPeriodDataUseCase: GetPeriodDataUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _periodData = MutableStateFlow<Map<String, Double>>(emptyMap())
    val periodData: StateFlow<Map<String, Double>> get() = _periodData

    fun getPeriodItems(): List<PeriodItemUiModel> {
        val domainItems = getPeriodItemsUseCase.execute()
        return domainItems.toUiModelList()
    }

    fun fetchPeriodData(deviceList: List<SimpleDeviceDataUiModel>, period: Period) {
        viewModelScope.launch {
            _isLoading.value = true
            val data = deviceList.associate { device ->
                device.deviceId!! to getPeriodDataUseCase.execute(device.deviceId, period)
            }
            _periodData.value = data
            _isLoading.value = false
        }
    }
}