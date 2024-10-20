package com.aposiamp.smartliving.presentation.permissions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.permissions.PermissionTextProvider

class LocationPermissionTextProvider : PermissionTextProvider {
    @Composable
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            stringResource(id = R.string.location_permission_is_permanently_denied)
        } else {
            stringResource(id = R.string.location_permission_is_not_permanently_denied)
        }
    }
}