package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.domain.model.DeviceState
import com.aposiamp.smartliving.presentation.model.DeviceModeUiItem
import com.aposiamp.smartliving.presentation.model.DeviceStateUiItem
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
fun DeviceOnOffButton(
    initialState: DeviceStateUiItem,
    color: Color,
    onButtonClicked: (DeviceState) -> Unit
) {
    val value by remember { mutableStateOf(initialState) }

    Button(
        onClick = {
            if (initialState.state == DeviceState.OFF) {
                onButtonClicked(DeviceState.ON)
            } else {
                onButtonClicked(DeviceState.OFF)
            }
        },
        shape = componentShapes.large,
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .heightIn(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(initialState.state == DeviceState.ON) color else Color.LightGray
        ),
        elevation = ButtonDefaults.buttonElevation(2.dp)
    ) {
        Image(
            painter = painterResource(id = value.icon),
            contentDescription = stringResource(id = value.text)
        )
    }
}

@Composable
fun DeviceModeButtonsRowComponent(
    modes: List<DeviceModeUiItem>,
    selectedMode: DeviceModeUiItem,
    onButtonClicked: (DeviceModeUiItem) -> Unit
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
                    .height(48.dp),
                contentPadding = PaddingValues(),
                shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                    modes.size - 1 -> RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                    else -> componentShapes.extraSmall
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (value == item) item.secondaryColor else Color.LightGray
                ),
                elevation = ButtonDefaults.buttonElevation(2.dp)
            ) {
                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = stringResource(id = item.text)
                )
            }
        }
    }
}

@Composable
fun FanSpeedButton(
    text: String,
    color: Color,
    shape: RoundedCornerShape,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = shape,
        elevation = ButtonDefaults.buttonElevation(2.dp),
        enabled = enabled
    ) {
        GeneralNormalBlackText(value = text)
    }
}

@Composable
fun AutoFanButton(
    text: String,
    color: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = componentShapes.large,
        enabled = enabled,
        elevation = ButtonDefaults.buttonElevation(2.dp)
    ) {
        GeneralNormalBlackText(value = text)
    }
}

@Composable
fun AirDirectionControlButton(
    painter: Painter,
    contentDescription: String,
    color: Color,
    shape: RoundedCornerShape,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .heightIn(48.dp),
        onClick = onClick,
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        elevation = ButtonDefaults.buttonElevation(2.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(28.dp)
        )
    }
}