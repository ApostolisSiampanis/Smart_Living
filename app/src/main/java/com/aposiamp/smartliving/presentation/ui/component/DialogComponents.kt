package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.permissions.PermissionTextProvider

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier : Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            GeneralBoldBlackText(value = stringResource(id = R.string.permission_required))
        },
        text = {
            GeneralNormalBlackText(
                value = permissionTextProvider.getDescription(
                    isPermanentlyDeclined = isPermanentlyDeclined
                )
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if (isPermanentlyDeclined) {
                        onGoToAppSettingsClick()
                    } else {
                        onOkClick()
                    }
                }
            ) {
                if (isPermanentlyDeclined) {
                    GeneralBoldBlackText(
                        value = stringResource(id = R.string.grant_permission),
                    )
                } else {
                    GeneralBoldBlackText(
                        value = stringResource(id = R.string.ok),
                    )
                }
            }
        },
        modifier = modifier
    )
}