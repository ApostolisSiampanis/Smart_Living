package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.usecase.sensor.GetEnvironmentalDataUseCase
import kotlinx.coroutines.launch

class MainSharedViewModel(
    private val getEnvironmentalDataUseCase: GetEnvironmentalDataUseCase
) : ViewModel() {
    private val _indoorTemperature = MutableLiveData<Float>()
    val indoorTemperature: LiveData<Float> = _indoorTemperature

    private val _indoorHumidity = MutableLiveData<Float>()
    val indoorHumidity: LiveData<Float> = _indoorHumidity

    fun setIndoorTemperature(temperature: Float) {
        _indoorTemperature.value = temperature
    }

    fun setIndoorHumidity(humidity: Float) {
        _indoorHumidity.value = humidity
    }

    init {
        viewModelScope.launch {
            val environmentalData = getEnvironmentalDataUseCase.execute()
            setIndoorTemperature(environmentalData.temperature!!)
            setIndoorHumidity(environmentalData.humidity!!)
        }
    }
}