package com.aposiamp.smartliving.presentation.viewmodel.welcome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aposiamp.smartliving.domain.Result
import com.aposiamp.smartliving.domain.model.SpaceData
import com.aposiamp.smartliving.domain.usecase.welcome.SetSpaceDataUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateSpaceName
import com.aposiamp.smartliving.presentation.model.CreateSpaceResult
import com.aposiamp.smartliving.presentation.ui.event.CreateSpaceFormEvent
import com.aposiamp.smartliving.presentation.ui.state.CreateSpaceFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateANewSpaceViewModel(
    private val validateSpaceName: ValidateSpaceName,
    private val setSpaceDataUseCase: SetSpaceDataUseCase
): ViewModel() {
    private var _formState by mutableStateOf(CreateSpaceFormState())
    val formState: CreateSpaceFormState get() = _formState

    private val _createSpaceFlow = MutableStateFlow<Result<CreateSpaceResult>?>(null)
    val createSpaceFlow: StateFlow<Result<CreateSpaceResult>?> = _createSpaceFlow

    fun onEvent(event: CreateSpaceFormEvent) {
        when (event) {
            is CreateSpaceFormEvent.SpaceNameChanged -> {
                _formState = _formState.copy(spaceName = event.spaceName)
            }

            is CreateSpaceFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val spaceNameResult = validateSpaceName.execute(_formState.spaceName)

        val hasError = listOf(
            spaceNameResult
        ).any { it.errorMessage != null }

        if (hasError) {
            _formState = _formState.copy(
                spaceNameError = spaceNameResult.errorMessage
            )
            return
        }
        createSpace(_formState.spaceName)
    }

    private fun createSpace(spaceName: String) = viewModelScope.launch {
        try {
            val spaceData = SpaceData(spaceName, listOf())
            setSpaceDataUseCase.execute(spaceData)
            _createSpaceFlow.value = Result.Success(CreateSpaceResult(success = true))
        } catch (e: Exception) {
            _formState = _formState.copy(spaceNameError = e.message)
            _createSpaceFlow.value = Result.Error(e)
        }
    }
}