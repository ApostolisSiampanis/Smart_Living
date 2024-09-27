package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aposiamp.smartliving.presentation.model.DropdownMenuItemUiModel
import com.aposiamp.smartliving.presentation.model.PeriodItemUiModel

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodDropdownMenu(
    items: List<PeriodItemUiModel>,
    selectedItem: PeriodItemUiModel?,
    onItemSelected: (PeriodItemUiModel) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedPeriod by remember { mutableStateOf(selectedItem ?: items.firstOrNull()) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedPeriod?.textResId?.let { stringResource(id = it) } ?: "",
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { GeneralNormalBlackText(value = stringResource(id = item.textResId)) },
                    onClick = {
                        selectedPeriod = item
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}