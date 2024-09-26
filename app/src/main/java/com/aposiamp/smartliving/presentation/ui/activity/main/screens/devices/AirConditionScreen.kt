package com.aposiamp.smartliving.presentation.ui.activity.main.screens.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.presentation.ui.activity.LoadingScreen
import com.aposiamp.smartliving.presentation.ui.component.AirDirectionControl
import com.aposiamp.smartliving.presentation.ui.component.DeviceCircularIndicator
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.DeviceIndicatorCard
import com.aposiamp.smartliving.presentation.ui.component.DeviceModeButtonsRowComponent
import com.aposiamp.smartliving.presentation.ui.component.DeviceOnOffButton
import com.aposiamp.smartliving.presentation.ui.component.FanSpeedControl
import com.aposiamp.smartliving.presentation.ui.component.IndoorEnvironmentalDataCard
import com.aposiamp.smartliving.presentation.viewmodel.main.devices.AirConditionViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel

@Composable
fun AirConditionScreen(
    navController: NavController,
    viewModel: AirConditionViewModel,
    sharedViewModel: MainSharedViewModel
) {
    val uiDeviceStates = viewModel.uiDeviceStates
    val uiDeviceModes = viewModel.uiDeviceModes

    val selectedDevice by sharedViewModel.selectedDevice.collectAsState()
    val deviceStatus by viewModel.deviceStatus.collectAsState()

    LaunchedEffect(selectedDevice) {
        selectedDevice?.let {
            viewModel.fetchDeviceStatus(it.deviceId!!)
        }
    }

    if (deviceStatus == null) {
        LoadingScreen()
    } else {
        val selectedState = uiDeviceStates.first { it.state == deviceStatus!!.state }
        val selectedMode = uiDeviceModes.first { it.mode == deviceStatus!!.mode }

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                BackAppTopBar(
                    title = selectedDevice?.deviceName ?: "",
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                DeviceIndicatorCard(
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .aspectRatio(1f)
                                ) {
                                    DeviceCircularIndicator(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.White),
                                        minValue = 16,
                                        maxValue = 30,
                                        circleRadius = 230f,
                                        selectedState = selectedState,
                                        selectedMode = selectedMode,
                                        initialValue = deviceStatus!!.temperature,
                                        onPositionChange = { position ->
                                            //TODO: Update device status temperature
                                        }
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            IndoorEnvironmentalDataCard(
                                indoorTemperature = sharedViewModel.environmentalData.value?.temperature,
                                indoorHumidity = sharedViewModel.environmentalData.value?.humidity
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                DeviceOnOffButton(
                                    initialState = selectedState,
                                    color = selectedMode.secondaryColor,
                                    onButtonClicked = { state ->
                                        //selectedState = uiDeviceStates.first { it.state == state }
                                        //TODO: Update device status state
                                    }
                                )
                                AirDirectionControl(
                                    color = selectedMode.secondaryColor,
                                    initialDirection = deviceStatus!!.airDirection,
                                    onDirectionChange = { direction ->
                                        //TODO: Update device status air direction
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            FanSpeedControl(
                                initialSpeed = deviceStatus!!.fanSpeed,
                                maxSpeed = 5,
                                color = selectedMode.secondaryColor,
                                selectedState = selectedState,
                                selectedMode = selectedMode,
                                onSpeedChange = { speed ->
                                    //TODO: Update device status fan speed
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            DeviceModeButtonsRowComponent(
                                modes = uiDeviceModes,
                                selectedMode = selectedMode,
                                onButtonClicked = { mode ->
                                    //selectedMode = mode
                                    // TODO: Update device status mode
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}
