package com.aposiamp.smartliving.presentation.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.usecase.main.GetSpaceUseCase
import com.aposiamp.smartliving.domain.usecase.sensor.GetEnvironmentalDataUseCase
import com.aposiamp.smartliving.domain.usecase.sensor.SetEnvironmentalDataUseCase
import com.aposiamp.smartliving.presentation.mapper.EnvironmentalDataUiMapper
import com.aposiamp.smartliving.presentation.mapper.SpaceDataUiMapper
import com.aposiamp.smartliving.presentation.model.EnvironmentalDataUiModel
import com.aposiamp.smartliving.presentation.model.SpaceDataUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainSharedViewModel(
    private val getSpaceUseCase: GetSpaceUseCase,
    private val getEnvironmentalDataUseCase: GetEnvironmentalDataUseCase,
    private val setEnvironmentalDataUseCase: SetEnvironmentalDataUseCase
) : ViewModel() {
    private val _environmentalData = MutableLiveData<EnvironmentalDataUiModel>()
    val environmentalData: LiveData<EnvironmentalDataUiModel> = _environmentalData

    private val _space = MutableStateFlow<SpaceDataUiModel?>(null)
    val space: StateFlow<SpaceDataUiModel?> = _space

    init {
        viewModelScope.launch {
            val spaceData = getSpaceUseCase.execute()
            _space.value = SpaceDataUiMapper.fromDomain(spaceData)
            val environmentalData = getEnvironmentalDataUseCase.execute()
            _space.value!!.placeId?.let { setEnvironmentalDataUseCase.execute(it, environmentalData) }
            _environmentalData.value = EnvironmentalDataUiMapper.fromDomain(environmentalData)
        }
    }
}