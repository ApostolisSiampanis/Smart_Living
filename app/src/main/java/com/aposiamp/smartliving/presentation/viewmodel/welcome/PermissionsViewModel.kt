package com.aposiamp.smartliving.presentation.viewmodel.welcome

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PermissionsViewModel: ViewModel() {

    val visiblePermissionDialog = mutableStateOf<String?>(null)

    fun dismissDialog() {
        visiblePermissionDialog.value = null
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted) {
            visiblePermissionDialog.value = permission
        }
    }
}