package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.DeviceMode
import com.aposiamp.smartliving.domain.model.DeviceState
import com.aposiamp.smartliving.presentation.model.DeviceModeUiItem
import com.aposiamp.smartliving.presentation.model.DeviceStateUiItem

@Composable
fun DehumidifierIndicator(
    modifier: Modifier = Modifier,
    initialValue: Int = 60,
    minValue: Int,
    maxValue: Int,
    selectedState: DeviceStateUiItem,
    selectedMode: DeviceModeUiItem,
    onSetValue: (Int) -> Unit
) {
    var humidityValue by remember { mutableIntStateOf(initialValue) }

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val color = selectedMode.secondaryColor

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            when (selectedState.state) {
                DeviceState.OFF -> {
                    IndicatorBoldTextComponent(
                        text = stringResource(id = R.string.off),
                        color = color,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )
                }
                DeviceState.ON -> {
                    when(selectedMode.mode) {
                        DeviceMode.AUTO -> {
                            IndicatorRegularTextComponent(
                                text = stringResource(id = R.string.set_to),
                                color = color
                            )
                            IndicatorBoldTextComponent(
                                text = stringResource(id = R.string.auto),
                                color = color
                            )
                        }
                        DeviceMode.HUMIDITY -> {
                            IndicatorRegularTextComponent(
                                text = stringResource(id = R.string.set_to),
                                color = color,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            IndicatorBoldTextComponent(
                                text = "$humidityValue " + stringResource(id = R.string.percentage),
                                color = color
                            )
                            Slider(
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                                value = humidityValue.toFloat(),
                                onValueChange = { newValue ->
                                    humidityValue = newValue.toInt()
                                    onSetValue(humidityValue)
                                },
                                valueRange = minValue.toFloat()..maxValue.toFloat()
                            )
                        }
                        DeviceMode.DRY -> {
                            IndicatorRegularTextComponent(
                                text = stringResource(id = R.string.set_to),
                                color = color,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            IndicatorBoldTextComponent(
                                text = stringResource(id = R.string.dry),
                                color = color,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        DeviceMode.SILENT -> {
                            IndicatorRegularTextComponent(
                                text = stringResource(id = R.string.set_to),
                                color = color,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            IndicatorBoldTextComponent(
                                text = stringResource(id = R.string.silent),
                                color = color,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        else -> null
                    }
                }
            }
        }
    }
}