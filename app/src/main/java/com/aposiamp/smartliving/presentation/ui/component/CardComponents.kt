package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.DeviceType
import com.aposiamp.smartliving.presentation.model.SettingsItemUiModel
import com.aposiamp.smartliving.presentation.ui.theme.componentShapes

@Composable
fun PermissionCard(
    permissionName: String,
    permissionRequestText: String,
    painter: Painter,
    contentDescription: String,
    isExpanded: MutableState<Boolean>,
    switchState: MutableState<Boolean>,
    onToggleClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(
                shape = componentShapes.medium,
                color = Color.White
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
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
                            painter = painter,
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .size(30.dp),
                            contentDescription = contentDescription
                        )
                        GeneralBoldText(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            value = permissionName,
                            fontSize = 22,
                            color = Color.Black
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(end = 8.dp),
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
                        },
                        enabled = !switchState.value
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
                        painter = if (isExpanded.value) {
                            painterResource(id = R.drawable.arrow_up)
                        } else {
                            painterResource(id = R.drawable.arrow_down)
                        },
                        contentDescription = if (isExpanded.value) {
                            stringResource(id = R.string.expand)
                        } else {
                            stringResource(id = R.string.collapse)
                        }
                    )
                }
            }
            if (isExpanded.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    GeneralNormalText(
                        value = permissionRequestText,
                        modifier = Modifier
                            .padding(start = 8.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun DeviceIndicatorCard(
    modifier : Modifier = Modifier,
    content : @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .background(
                shape = componentShapes.large,
                color = Color.White
            ),
        elevation = cardElevation(defaultElevation = 4.dp)
    ) {
        content()
    }
}

@Composable
fun IndoorEnvironmentalDataCard(
    indoorTemperature: Float?,
    indoorHumidity: Float?
){
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        elevation = cardElevation(defaultElevation = 4.dp),
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
            GeneralNormalText(value = "$indoorTemperature " + stringResource(id = R.string.degree_celsius))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GeneralNormalText(value = stringResource(id = R.string.indoor_humidity))
            GeneralNormalText(value = "$indoorHumidity " + stringResource(id = R.string.percentage))
        }
    }
}

@Composable
fun SettingCard(
    item: SettingsItemUiModel,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(8.dp)
            .background(
                shape = componentShapes.large,
                color = Color.White
            )
            .clickable {
                item.route?.let { navController.navigate(it) }
            },
        elevation = cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item.icon?.let { icon ->
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(id = item.titleResId),
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                ItemTextComponent(
                    text = stringResource(id = item.titleResId),
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun GeneralClickableCard(
    value: String,
    textColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
            .background(
                shape = componentShapes.medium,
                color = Color.White
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            GeneralBoldText(
                modifier = Modifier.padding(16.dp),
                value = value,
                fontSize = 20,
                color = textColor
            )
        }
    }
}

@Composable
fun DeviceCard(
    deviceName: String,
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable (onClick = onClick),
        elevation = cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(64.dp)
            )
            GeneralNormalBlackText(value = deviceName)
        }
    }
}