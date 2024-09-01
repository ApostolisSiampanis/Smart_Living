package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.R
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AirDirectionControl(
    initialDirection: Int = 1,
    color: Color = Color.Black,
    onDirectionChange: (Int) -> Unit
) {
    var selectedDirection by remember { mutableIntStateOf(initialDirection) }
    var showDrawable by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .heightIn(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (showDrawable) {
            Image(
                painter = painterResource(id = R.drawable.swing),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 60.dp, height = 48.dp)
                    .background(shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp), color = Color.LightGray)
                    .padding(8.dp)
            )
        } else {
            Canvas(
                modifier = Modifier
                    .size(width = 60.dp, height = 48.dp)
                    .background(shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp), color = Color.LightGray)
                    .padding(8.dp)
            ) {
                drawAirDirectionLine(selectedDirection - 1, Color.Black)
            }
        }

        Button(
            modifier = Modifier
                .heightIn(48.dp),
            onClick = {
                if (selectedDirection == 4) {
                    selectedDirection = 0
                    showDrawable = true
                } else {
                    selectedDirection++
                    onDirectionChange(selectedDirection)
                    showDrawable = false
                }
            },
            shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = color)
        ) {
            Image(
                painter = painterResource(id = R.drawable.change_direction),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
            )
        }
    }
}

fun DrawScope.drawAirDirectionLine(direction: Int, color: Color) {
    val angles = listOf(90f, 60f, 35f, 10f)
    val angle = angles[direction]

    val radians = Math.toRadians(angle.toDouble())
    val startX = size.width / 1.2f
    val startY = 0f
    val endX = startX - (size.height * cos(radians)).toFloat()
    val endY = startY + (size.height * sin(radians)).toFloat()

    drawLine(
        color = color,
        start = Offset(startX, startY),
        end = Offset(endX, endY),
        strokeWidth = 4.dp.toPx()
    )
}