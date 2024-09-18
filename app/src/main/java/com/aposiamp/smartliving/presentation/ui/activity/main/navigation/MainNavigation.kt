package com.aposiamp.smartliving.presentation.ui.activity.main.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.AboutScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.DevicesScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.devices.AirConditionScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.devices.DehumidifierScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.devices.ThermostatScreen
import com.aposiamp.smartliving.presentation.viewmodel.main.DevicesViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.NavigationViewModel

@Composable
internal fun MainNavigation(
    context: Context,
    devicesViewModel: DevicesViewModel,
    mainSharedViewModel: MainSharedViewModel,
    navigationViewModel: NavigationViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "devices"
    ) {
        composable("devices"){
            DevicesScreen(
                navController = navController,
                viewModel = devicesViewModel,
                mainSharedViewModel = mainSharedViewModel,
                navigationViewModel = navigationViewModel,
                context = context
            )
        }

        composable("about"){
            AboutScreen(
                navController = navController,
                navigationViewModel = navigationViewModel
            )
        }

        composable("thermostat"){
            ThermostatScreen(
                navController = navController,
                sharedViewModel = mainSharedViewModel
            )
        }
        composable("airCondition"){
            AirConditionScreen(
                navController = navController,
                sharedViewModel = mainSharedViewModel
            )
        }
        composable("dehumidifier"){
            DehumidifierScreen(
                navController = navController,
                sharedViewModel = mainSharedViewModel
            )
        }
    }
}