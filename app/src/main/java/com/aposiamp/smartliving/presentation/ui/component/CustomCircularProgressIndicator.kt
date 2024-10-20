package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.DeviceMode
import com.aposiamp.smartliving.domain.model.DeviceState
import com.aposiamp.smartliving.presentation.model.DeviceModeUiItem
import com.aposiamp.smartliving.presentation.model.DeviceStateUiItem
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun DeviceCircularIndicator(
    modifier: Modifier = Modifier,
    initialValue: Int = 20,
    minValue: Int,
    maxValue: Int,
    circleRadius: Float,
    selectedState: DeviceStateUiItem,
    selectedMode: DeviceModeUiItem,
    onPositionChange: (Int) -> Unit
) {
    var circleCenter by remember { mutableStateOf(Offset.Zero) }
    var positionValue by remember { mutableIntStateOf(initialValue) }
    var dragStartedAngle by remember { mutableFloatStateOf(0f) }
    var oldPositionValue by remember { mutableIntStateOf(initialValue) }

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val arcColor = when {
            positionValue < 24 -> selectedMode.primaryColor
            else -> selectedMode.secondaryColor
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(selectedState){
                    if (selectedState.state == DeviceState.ON) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                dragStartedAngle = -atan2(
                                    x = circleCenter.y - offset.y,
                                    y = circleCenter.x - offset.x
                                ) * (180f / PI).toFloat()
                                dragStartedAngle = (dragStartedAngle + 180f).mod(360f)
                            },
                            onDrag = { change, _ ->
                                var touchAngle = -atan2(
                                    x = circleCenter.y - change.position.y,
                                    y = circleCenter.x - change.position.x
                                ) * (180f / PI).toFloat()

                                // Adjust touchAngle relative to the start angle (125 degrees)
                                touchAngle = (touchAngle + 360f + 140f).mod(360f)

                                // Calculate the new position value based on the touch angle
                                val newValue =
                                    (minValue + ((touchAngle / 290f) * (maxValue - minValue)).roundToInt()).coerceIn(
                                        minValue,
                                        maxValue
                                    )

                                if ((positionValue == maxValue && newValue < positionValue) ||
                                    (positionValue == minValue && newValue > positionValue) ||
                                    (positionValue in (minValue + 1)..maxValue)
                                ) {
                                    positionValue = newValue
                                }
                            },
                            onDragEnd = {
                                oldPositionValue = positionValue
                                onPositionChange(positionValue)
                            }
                        )
                    }
                }
        ) {
            val width = size.width
            val height = size.height
            val circleThickness = width / 25f
            circleCenter = Offset(x = width/2f, y = height/2f)

            // Inner Circle as Arc
            drawArc(
                style = Stroke(width = circleThickness),
                color = Color.Transparent,
                startAngle = 125f,
                sweepAngle = 290f,
                useCenter = false,
                size = Size(
                    width = circleRadius * 2f,
                    height = circleRadius * 2f
                ),
                topLeft = Offset(
                    (width - circleRadius * 2f) / 2f,
                    (height - circleRadius * 2f) / 2f
                )
            )

            // Value Arc
            drawArc(
                color = arcColor,
                startAngle = 125f,
                sweepAngle = (290f / (maxValue - minValue)) * (positionValue - minValue).toFloat(),
                style = Stroke(
                    width = circleThickness,
                    cap = StrokeCap.Round
                ),
                useCenter = false,
                size = Size(
                    width = circleRadius * 2f,
                    height = circleRadius * 2f
                ),
                topLeft = Offset(
                    (width - circleRadius * 2f)/2f,
                    (height - circleRadius * 2f)/2f
                )
            )

            val outerRadius = circleRadius + circleThickness/2f
            val gap = 15f
            for (i in 0..(maxValue - minValue)) {
                val isCurrentValue = i == positionValue - minValue
                val strokeWidth = 1.dp.toPx()  // Keep all lines with the same thickness

                val angleInDegrees = i * 290f / (maxValue - minValue).toFloat() + 35f
                val angleInRad = angleInDegrees * PI / 180f + PI / 2f

                val yGapAdjustment = cos(angleInDegrees * PI / 180f) * gap
                val xGapAdjustment = -sin(angleInDegrees * PI / 180f) * gap

                val start = Offset(
                    x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRad) + circleCenter.y + yGapAdjustment).toFloat()
                )

                val end = Offset(
                    x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRad) + circleThickness + circleCenter.y + yGapAdjustment).toFloat()
                )

                // Draw a line for non-current values
                if (!isCurrentValue) {
                    rotate(
                        angleInDegrees,
                        pivot = start
                    ) {
                        drawLine(
                            color = if (selectedState.state == DeviceState.ON && i <= positionValue - minValue) arcColor else Color.LightGray,
                            start = start,
                            end = end,
                            strokeWidth = strokeWidth  // Use the regular stroke width
                        )
                    }
                } else {
                    // Draw a dot at the current value position
                    val dotRadius = 9.dp.toPx()  // Size of the dot

                    drawCircle(
                        color = arcColor,
                        radius = dotRadius,
                        center = Offset(
                            x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment*2f).toFloat(),
                            y = (outerRadius * sin(angleInRad) + circleCenter.y + yGapAdjustment*2f).toFloat()
                        )
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            when (selectedState.state) {
                DeviceState.OFF -> {
                    IndicatorBoldTextComponent(
                        text = stringResource(id = R.string.off),
                        color = arcColor,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                DeviceState.ON -> {
                    when (selectedMode.mode) {
                        DeviceMode.AUTO -> {
                            IndicatorRegularTextComponent(
                                text = stringResource(id = R.string.auto_set_to),
                                color = arcColor,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            IndicatorBoldTextComponent(
                                text = "$positionValue ${stringResource(id = R.string.degree_celsius)}",
                                color = arcColor
                            )
                        }
                        DeviceMode.COOL -> {
                            IndicatorRegularTextComponent(
                                text = stringResource(id = R.string.cool_set_to),
                                color = arcColor,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            IndicatorBoldTextComponent(
                                text = "$positionValue ${stringResource(id = R.string.degree_celsius)}",
                                color = arcColor
                            )
                        }
                        DeviceMode.HEAT -> {
                            IndicatorRegularTextComponent(
                                text = stringResource(id = R.string.heat_set_to),
                                color = arcColor,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            IndicatorBoldTextComponent(
                                text = "$positionValue ${stringResource(id = R.string.degree_celsius)}",
                                color = arcColor
                            )
                        }
                        DeviceMode.DRY -> {
                            IndicatorRegularTextComponent(
                                text = stringResource(id = R.string.dry_set_to),
                                color = arcColor,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            IndicatorBoldTextComponent(
                                text = "$positionValue ${stringResource(id = R.string.degree_celsius)}",
                                color = arcColor
                            )
                        }
                        else -> null
                    }
                }
            }
        }
    }
}