package com.aposiamp.smartliving.presentation.viewmodel.welcome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.model.AutoCompletePrediction
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.domain.model.SpaceData
import com.aposiamp.smartliving.domain.usecase.places.GetAutoCompleteSuggestionsUseCase
import com.aposiamp.smartliving.domain.usecase.places.GetLocationFromPlaceIdUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.SetSpaceDataUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePlaceData
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateSpaceAddress
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateSpaceName
import com.aposiamp.smartliving.presentation.model.CreateSpaceResult
import com.aposiamp.smartliving.presentation.ui.event.CreateSpaceFormEvent
import com.aposiamp.smartliving.presentation.ui.state.CreateSpaceFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateANewSpaceViewModel(
    private val validateSpaceName: ValidateSpaceName,
    private val validateSpaceAddress: ValidateSpaceAddress,
    private val validatePlaceData: ValidatePlaceData,
    private val setSpaceDataUseCase: SetSpaceDataUseCase,
    private val getAutoCompleteSuggestionsUseCase: GetAutoCompleteSuggestionsUseCase,
    private val getLocationFromPlaceIdUseCase: GetLocationFromPlaceIdUseCase
): ViewModel() {
    private var _formState by mutableStateOf(CreateSpaceFormState())
    val formState: CreateSpaceFormState get() = _formState

    private val _createSpaceFlow = MutableStateFlow<Result<CreateSpaceResult>?>(null)
    val createSpaceFlow: StateFlow<Result<CreateSpaceResult>?> = _createSpaceFlow

    private var _addressPredictions by mutableStateOf(emptyList<AutoCompletePrediction>())
    val addressPredictions: List<AutoCompletePrediction> get() = _addressPredictions

    suspend fun onEvent(event: CreateSpaceFormEvent) {
        when (event) {
            is CreateSpaceFormEvent.SpaceNameChanged -> {
                _formState = _formState.copy(spaceName = event.spaceName)
            }

            is CreateSpaceFormEvent.SpaceAddressChanged -> {
                _formState = _formState.copy(spaceAddress = event.spaceAddress)
            }

            is CreateSpaceFormEvent.PlaceIdSelected -> {
                _formState = _formState.copy(spaceId = event.placeId)
            }

            is CreateSpaceFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private suspend fun submitData() {
        val spaceNameResult = validateSpaceName.execute(_formState.spaceName)
        val spaceAddressResult = validateSpaceAddress.execute(_formState.spaceAddress)
        val spaceIdResult = validatePlaceData.execute(_formState.spaceId)

        val hasError = listOf(
            spaceNameResult,
            spaceAddressResult,
            spaceIdResult
        ).any { it.errorMessage != null }

        if (hasError) {
            _formState = _formState.copy(
                spaceNameError = spaceNameResult.errorMessage,
                spaceAddressError = spaceAddressResult.errorMessage,
                spaceIdError = spaceIdResult.errorMessage
            )
            return
        }
        createSpace(_formState.spaceId)
    }

    private fun createSpace(spaceId: String) = viewModelScope.launch {
        try {
            val placeData = getLocationFromPlaceIdUseCase.execute(spaceId)
            val spaceData = SpaceData(
                placeId = placeData.placeId,
                placeName = placeData.name,
                spaceName = _formState.spaceName,
                fullAddress = placeData.fullAddress,
                location = placeData.location
            )
            setSpaceDataUseCase.execute(spaceData)
            _createSpaceFlow.value = Result.Success(CreateSpaceResult(success = true))
        } catch (e: Exception) {
            _formState = _formState.copy(spaceNameError = e.message)
            _createSpaceFlow.value = Result.Error(e)
        }
    }

    fun fetchAddressSuggestions(query: String) = viewModelScope.launch {
        val predictions = getAutoCompleteSuggestionsUseCase.execute(query)
        _addressPredictions = predictions
    }
}