package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.model.Period
import com.aposiamp.smartliving.domain.usecase.main.GetPeriodDataUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetPeriodItemsUseCase
import com.aposiamp.smartliving.presentation.mapper.PeriodUiMapper.toUiModelList
import com.aposiamp.smartliving.presentation.model.PeriodItemUiModel
import com.aposiamp.smartliving.presentation.model.SimpleDeviceDataUiModel
import com.aposiamp.smartliving.presentation.utils.ColorUtils
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

    private val deviceColors = mutableMapOf<String, Color>()

    fun getPeriodItems(): List<PeriodItemUiModel> {
        val domainItems = getPeriodItemsUseCase.execute()
        return domainItems.toUiModelList()
    }

    fun fetchPeriodData(deviceList: List<SimpleDeviceDataUiModel>, period: Period) {
        viewModelScope.launch {
            _isLoading.value = true
            val data = deviceList.associate { device ->
                val deviceName = device.deviceName ?: "Unknown Device"
                val powerConsumption = getPeriodDataUseCase.execute(device.deviceId!!, period)
                deviceName to powerConsumption
            }
            _periodData.value = data
            _isLoading.value = false
        }
    }

    fun getDeviceColors(deviceList: List<SimpleDeviceDataUiModel>): List<Color> {
        return deviceList.map { device ->
            deviceColors.getOrPut(device.deviceName ?: "Unknown Device") {
                ColorUtils.generateRandomColor()
            }
        }
    }
}