package com.aposiamp.smartliving.presentation.viewmodel.main.settings

import androidx.lifecycle.ViewModel
import com.aposiamp.smartliving.domain.usecase.user.GetAccountProfileDetailsUseCase
import com.aposiamp.smartliving.presentation.mapper.UserAccountUiMapper
import com.aposiamp.smartliving.presentation.model.AccountProfileUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AccountProfileViewModel(
    private val getAccountProfileDetailsUseCase: GetAccountProfileDetailsUseCase
) : ViewModel() {
    private val _detailsState = MutableStateFlow<AccountProfileUiModel?>(null)
    val detailsState: StateFlow<AccountProfileUiModel?> get() = _detailsState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    suspend fun fetchAccountDetails() {
        _isLoading.value = true
        val userAccountData = getAccountProfileDetailsUseCase.execute()
        _detailsState.value = UserAccountUiMapper.fromDomain(userAccountData)
        _isLoading.value = false
    }
}