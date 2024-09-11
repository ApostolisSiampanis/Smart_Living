package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.usecase.sensor.GetEnvironmentalDataUseCase
import com.aposiamp.smartliving.domain.usecase.sensor.SetEnvironmentalDataUseCase
import com.aposiamp.smartliving.presentation.mapper.EnvironmentalDataUiMapper
import com.aposiamp.smartliving.presentation.model.EnvironmentalDataUiModel
import kotlinx.coroutines.launch

class MainSharedViewModel(
    private val getEnvironmentalDataUseCase: GetEnvironmentalDataUseCase,
    private val setEnvironmentalDataUseCase: SetEnvironmentalDataUseCase
) : ViewModel() {
    private val _environmentalData = MutableLiveData<EnvironmentalDataUiModel>()
    val environmentalData: LiveData<EnvironmentalDataUiModel> = _environmentalData

    init {
        viewModelScope.launch {
            val environmentalData = getEnvironmentalDataUseCase.execute()
            setEnvironmentalDataUseCase.execute(environmentalData)
            _environmentalData.value = EnvironmentalDataUiMapper.fromDomain(environmentalData)
        }
    }
}