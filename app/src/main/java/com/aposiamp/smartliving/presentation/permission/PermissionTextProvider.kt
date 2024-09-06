package com.aposiamp.smartliving.presentation.permission

import androidx.compose.runtime.Composable

interface PermissionTextProvider {
    @Composable
    fun getDescription(isPermanentlyDeclined: Boolean): String
}
