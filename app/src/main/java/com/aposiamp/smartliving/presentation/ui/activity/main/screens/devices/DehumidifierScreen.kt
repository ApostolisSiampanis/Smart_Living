package com.aposiamp.smartliving.presentation.ui.activity.main.screens.devices

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.DehumidifierIndicator
import com.aposiamp.smartliving.presentation.ui.component.DeviceIndicatorCard
import com.aposiamp.smartliving.presentation.ui.component.DeviceModeButtonsRowComponent
import com.aposiamp.smartliving.presentation.ui.component.DeviceOnOffButton
import com.aposiamp.smartliving.presentation.ui.component.FanSpeedControl
import com.aposiamp.smartliving.presentation.ui.component.IndoorEnvironmentalDataCard
import com.aposiamp.smartliving.presentation.viewmodel.main.DehumidifierViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel

@Composable
fun DehumidifierScreen(
    navController: NavController,
    sharedViewModel: MainSharedViewModel
) {
    val viewModel = DehumidifierViewModel()
    val uiDeviceStates = viewModel.uiDeviceStates
    val uiDeviceModes = viewModel.uiDeviceModes
    var selectedState by remember { mutableStateOf(uiDeviceStates[0]) }
    var selectedMode by remember { mutableStateOf(uiDeviceModes[0]) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = "Dehumidifier",
                color = MaterialTheme.colorScheme.primaryContainer,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        },
        content = { padding ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        DeviceIndicatorCard(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        ) {
                            DehumidifierIndicator(
                                minValue = 40,
                                maxValue = 95,
                                selectedState = selectedState,
                                selectedMode = selectedMode,
                                onSetValue = { value ->

                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        IndoorEnvironmentalDataCard(
                            indoorTemperature = sharedViewModel.environmentalData.value?.temperature,
                            indoorHumidity = sharedViewModel.environmentalData.value?.humidity
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        DeviceOnOffButton(
                            initialState = selectedState,
                            color = selectedMode.secondaryColor,
                            onButtonClicked = { state ->
                                selectedState = uiDeviceStates.first { it.state == state }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FanSpeedControl(
                            initialSpeed = 1,
                            maxSpeed = 5,
                            color = selectedMode.secondaryColor,
                            isDehumidifier = true,
                            selectedState = selectedState,
                            selectedMode = selectedMode,
                            onSpeedChange = { speed ->

                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        DeviceModeButtonsRowComponent(
                            modes = uiDeviceModes,
                            selectedMode = selectedMode,
                            onButtonClicked = { mode ->
                                selectedMode = mode
                            }
                        )
                    }
                }
            }
        }
    )
}