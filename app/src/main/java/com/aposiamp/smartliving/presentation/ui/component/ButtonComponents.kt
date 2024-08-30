package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.presentation.model.ThermostatModeUiItem
import com.aposiamp.smartliving.presentation.ui.theme.PrussianBlue
import com.aposiamp.smartliving.presentation.ui.theme.componentShapes

@Composable
fun GeneralButtonComponent(
    value: String,
    onButtonClicked: () -> Unit
) {
    Button(
        onClick = onButtonClicked,
        modifier = Modifier
            .width(185.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = PrussianBlue,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            GeneralButtonTextComponent(value = value)
        }
    }
}

@Composable
fun ThermostatButtonsRowComponent(
    modes: List<ThermostatModeUiItem>,
    selectedMode: ThermostatModeUiItem,
    onButtonClicked: (ThermostatModeUiItem) -> Unit
) {
    var value by remember { mutableStateOf(selectedMode) }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.Center
    ) {
        modes.forEachIndexed { index, item ->
            Button(
                onClick = {
                    value = item
                    onButtonClicked(item)
                },
                modifier = Modifier
                    .weight(1f)
                    .heightIn(48.dp),
                contentPadding = PaddingValues(),
                shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                    modes.size - 1 -> RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                    else -> componentShapes.extraSmall
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (value == item) item.secondaryColor else Color.LightGray
                )
            ) {
                GeneralBoldText(
                    value = stringResource(id = item.text)
                )
            }
        }
    }
}