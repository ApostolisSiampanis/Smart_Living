package com.aposiamp.smartliving.domain.permissions

import androidx.compose.runtime.Composable

interface PermissionTextProvider {
    @Composable
    fun getDescription(isPermanentlyDeclined: Boolean): String
}
