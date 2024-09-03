package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import com.aposiamp.smartliving.presentation.model.DropdownMenuItemUiModel

@Composable
fun DropdownMenuComponent(
    isContextMenuVisible: Boolean,
    onDismissRequest: () -> Unit,
    items: List<DropdownMenuItemUiModel>
) {
    DropdownMenu(
        expanded = isContextMenuVisible,
        onDismissRequest = onDismissRequest
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = { GeneralNormalBlackText(value = item.text) },
                onClick = {
                    item.onClick()
                    onDismissRequest()
                }
            )
        }
    }
}