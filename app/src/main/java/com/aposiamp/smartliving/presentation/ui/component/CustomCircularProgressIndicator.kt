package com.aposiamp.smartliving.presentation.ui.component

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aposiamp.smartliving.presentation.ui.theme.Blue
import com.aposiamp.smartliving.presentation.ui.theme.BrightBlue
import com.aposiamp.smartliving.presentation.ui.theme.Orange
import com.aposiamp.smartliving.presentation.ui.theme.RedOrange
import com.aposiamp.smartliving.presentation.ui.theme.WhiteColor
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun ThermostatCircularIndicator(
    modifier: Modifier = Modifier,
    initialValue: Int,
    minValue: Int = 0,
    maxValue: Int = 100,
    circleRadius: Float,
    onPositionChange: (Int) -> Unit
){
    var circleCenter by remember { mutableStateOf(Offset.Zero) }
    var positionValue by remember { mutableStateOf(initialValue) }
    var changeAngle by remember { mutableStateOf(0f) }
    var dragStartedAngle by remember { mutableStateOf(0f) }
    var oldPositionValue by remember { mutableStateOf(initialValue) }

    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true){
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
                            touchAngle = (touchAngle + 180f).mod(360f)

                            val currentAngle = oldPositionValue * 360f / (maxValue - minValue)
                            changeAngle = touchAngle - currentAngle

                            val lowerThreshold = currentAngle - (360f / (maxValue - minValue) * 5)
                            val higherThreshold = currentAngle + (360f / (maxValue - minValue) * 5)

                            if (dragStartedAngle in lowerThreshold..higherThreshold) {
                                val newValue = (oldPositionValue + (changeAngle / (360f / (maxValue - minValue))).roundToInt()).coerceIn(minValue, maxValue)

                                if ((positionValue == maxValue && newValue < positionValue) ||
                                    (positionValue == minValue && newValue > positionValue) ||
                                    (positionValue > minValue && positionValue < maxValue)) {
                                    positionValue = newValue
                                }
                            }
                        },
                        onDragEnd = {
                            oldPositionValue = positionValue
                            onPositionChange(positionValue)
                        }
                    )
                }
        ) {
            val width = size.width
            val height = size.height
            val circleThickness = width / 25f
            circleCenter = Offset(x = width/2f, y = height/2f)

            // Inner Circle as Arc
            drawArc(
               brush = Brush.radialGradient(
                   listOf(
                       Orange.copy(0.45f),
                       RedOrange.copy(0.15f)
                   )
               ),
                startAngle = 125f,
                sweepAngle = 290f,
                useCenter = true,
                size = Size(
                    width = circleRadius * 2f,
                    height = circleRadius * 2f
                ),
                topLeft = Offset(
                    (width - circleRadius * 2f) / 2f,
                    (height - circleRadius * 2f) / 2f
                )
            )

            // Outer Circle as Arc
            drawArc(
                style = Stroke(width = circleThickness),
                color = RedOrange,
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

            val arcColor = when {
                positionValue < 25 -> BrightBlue
                positionValue < 50 -> Blue
                positionValue < 75 -> Orange
                else -> RedOrange
            }

            // Value Arc
            drawArc(
                color = arcColor,
                startAngle = 125f,
                sweepAngle = (290f/maxValue) * positionValue.toFloat(),
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
                            color = arcColor,
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

            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    drawText(
                        "$positionValue %",
                        circleCenter.x,
                        circleCenter.y + 45.dp.toPx()/3f,
                        Paint().apply {
                            textSize = 38.sp.toPx()
                            textAlign = Paint.Align.CENTER
                            color = WhiteColor.toArgb()
                            isFakeBoldText = true
                        }
                    )
                }
            }

        }
    }
}