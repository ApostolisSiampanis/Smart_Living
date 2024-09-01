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
import androidx.compose.runtime.mutableIntStateOf
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
    initialSpeed: Int = 1,
    maxSpeed: Int = 5,
    color: Color,
    onSpeedChange: (Int) -> Unit
) {
    var selectedSpeed by remember { mutableIntStateOf(initialSpeed) }
    var isAuto by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.65f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FanSpeedButton(
            text = stringResource(id = R.string.minus),
            color = color,
            shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
            enabled = selectedSpeed > 1,
            onClick = {
                if (selectedSpeed > 1) {
                    selectedSpeed--
                    onSpeedChange(selectedSpeed)
                    isAuto = false
                }
            }
        )

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
                            .background(if (i <= selectedSpeed) Color.Black else Color.Gray)
                    )
                }
            }
        }

        FanSpeedButton(
            text = stringResource(id = R.string.plus),
            color = color,
            shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
            enabled = selectedSpeed < maxSpeed,
            onClick = {
                if (selectedSpeed < maxSpeed) {
                    selectedSpeed++
                    onSpeedChange(selectedSpeed)
                    isAuto = false
                }
            }
        )

        Spacer(modifier = Modifier.width(8.dp))

        AutoButton(
            text = stringResource(id = R.string.auto),
            color = color,
            onClick = {
                selectedSpeed = 0
                isAuto = true
                onSpeedChange(selectedSpeed)
            }
        )
    }
}