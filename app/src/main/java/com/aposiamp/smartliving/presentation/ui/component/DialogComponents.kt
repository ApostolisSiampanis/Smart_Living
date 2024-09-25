package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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

@Composable
fun EmailSentDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            GeneralBoldBlackText(value = title)
        },
        text = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.email_outlined),
                        contentDescription = stringResource(id = R.string.email_image)
                    )
                }
                GeneralNormalBlackText(value = message)
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm
            ) {
                GeneralBoldBlackText(value = stringResource(id = R.string.ok))
            }
        }
    )
}