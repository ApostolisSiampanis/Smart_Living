package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.aposiamp.smartliving.R

@Composable
fun PermissionCard(
    iconResId: Int,
    permissionName: String,
    isExpanded: MutableState<Boolean>,
    switchState: MutableState<Boolean>,
    onToggleClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = iconResId),
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .size(40.dp),
                            contentDescription = permissionName
                        )
                        Text(
                            text = permissionName,
                            modifier = Modifier
                                .padding(start = 8.dp),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.carlito_regular))
                            ),
                            color = Color.Blue
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Switch(
                        checked = switchState.value,
                        onCheckedChange = {
                            if (it) {
                                switchState.value = it
                                onToggleClick()
                            } else {
                                switchState.value = false
                            }
                        }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { isExpanded.value = !isExpanded.value }) {
                    Image(
                        painter =
                        if (isExpanded.value) painterResource(
                            id = R.drawable.arrow_up
                        )
                        else painterResource(
                            id = R.drawable.arrow_down
                        ),
                        modifier = Modifier
                            .padding(horizontal = 5.dp),
                        contentDescription = null
                    )
                }
            }
            if (isExpanded.value) {
                //TODO Locale Settings
                val permissionRequestText = "Test"
                Row {
                   Text(
                       text = permissionRequestText,
                       modifier = Modifier
                           .padding(start = 8.dp),
                       style = TextStyle(
                           fontSize = 14.sp,
                           fontFamily = FontFamily(Font(R.font.carlito_regular))
                       )
                   )
                }
            }
        }
    }
}

@Composable
fun IndoorEnvironmentalDataCard(
    indoorTemperature: LiveData<Float>,
    indoorHumidity: LiveData<Float>
){
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        elevation = cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GeneralNormalText(value = stringResource(id = R.string.indoor_temperature))
            GeneralNormalText(value = "${indoorTemperature.value} " + stringResource(id = R.string.degree_celcius))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GeneralNormalText(value = stringResource(id = R.string.indoor_humidity))
            GeneralNormalText(value = "${indoorHumidity.value} " + stringResource(id = R.string.percentage))
        }
    }
}