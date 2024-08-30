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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.IndoorEnvironmentalDataCard
import com.aposiamp.smartliving.presentation.ui.component.ThermostatButtonsRowComponent
import com.aposiamp.smartliving.presentation.ui.component.ThermostatCircularIndicator
import com.aposiamp.smartliving.presentation.ui.theme.componentShapes
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.ThermostatViewModel

@Composable
fun ThermostatScreen(
    navController: NavController,
    sharedViewModel: MainSharedViewModel
){
    val viewModel = viewModel<ThermostatViewModel>()
    val uiModes = viewModel.uiModes
    var selectedMode by remember { mutableStateOf(uiModes[0]) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = "Thermostat",
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
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .aspectRatio(1f)
                                    .background(
                                        shape = componentShapes.large,
                                        color = Color.White
                                    ),
                                elevation = CardDefaults.cardElevation(8.dp)
                            ) {
                                ThermostatCircularIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.White),
                                    circleRadius = 230f,
                                    selectedMode = selectedMode,
                                    onPositionChange = { position ->

                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        IndoorEnvironmentalDataCard(
                            indoorTemperature = sharedViewModel.indoorTemperature,
                            indoorHumidity = sharedViewModel.indoorHumidity
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ThermostatButtonsRowComponent(
                            modes = uiModes,
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