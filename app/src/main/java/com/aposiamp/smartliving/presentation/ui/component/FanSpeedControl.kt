package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.theme.componentShapes

@Composable
fun FanSpeedControl(
    initialSpeed: Int = 0,
    maxSpeed: Int = 5,
    color: Color,
    onSpeedChange: (Int) -> Unit
) {
    var selectedSpeed by remember { mutableStateOf(initialSpeed) }
    var isAuto by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.65f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                if (selectedSpeed > 0) {
                    selectedSpeed--
                    onSpeedChange(selectedSpeed)
                    isAuto = false
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = color
            ),
            shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
            elevation = ButtonDefaults.buttonElevation(2.dp),
            enabled = selectedSpeed > 0
        ) {
            Text(
                text = stringResource(id = R.string.minus),
                color = Color.Black
            )
        }

        Row(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray)
                .padding(8.dp)
                .fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = if(isAuto) Arrangement.Center else Arrangement.SpaceBetween
        ) {
            if (isAuto) {
                Image(
                    painter = painterResource(id = R.drawable.auto),
                    contentDescription = stringResource(id = R.string.auto),
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                )
            } else {
                for (i in 1..maxSpeed) {
                    Box(
                        modifier = Modifier
                            .height((24 * i / maxSpeed).dp) // Ascending height
                            .width(4.dp)
                            .background(if (i <= selectedSpeed) color else Color.Gray)
                    )
                }
            }
        }

        Button(
            onClick = {
                if (selectedSpeed < maxSpeed) {
                    selectedSpeed++
                    onSpeedChange(selectedSpeed)
                    isAuto = false
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = color
            ),
            shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
            elevation = ButtonDefaults.buttonElevation(2.dp),
            enabled = selectedSpeed < maxSpeed
        ) {
            Text(
                text = stringResource(id = R.string.plus),
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                selectedSpeed = -1
                isAuto = true
                onSpeedChange(selectedSpeed)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = color
            ),
            shape = componentShapes.large,
            elevation = ButtonDefaults.buttonElevation(2.dp)
        ) {
            Text(
                text = stringResource(id = R.string.auto),
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.carlito_regular))
            )
        }
    }
}