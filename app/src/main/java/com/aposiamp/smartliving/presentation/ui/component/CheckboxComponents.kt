package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.presentation.ui.theme.PrussianBlue
import com.aposiamp.smartliving.presentation.ui.theme.SkyBlue

@Composable
fun TermsCheckboxComponent(
    checkedValue: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTextSelected: (String) -> Unit,
    errorMessageValue: String,
    errorStatus: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = checkedValue,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkmarkColor = PrussianBlue,
                    checkedColor = SkyBlue
                )
            )
            PolicyAndTermsClickableTextComponent(onTextSelected)
        }
        if (errorStatus) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ErrorTextComponent(value = errorMessageValue)
            }
        }
    }
}