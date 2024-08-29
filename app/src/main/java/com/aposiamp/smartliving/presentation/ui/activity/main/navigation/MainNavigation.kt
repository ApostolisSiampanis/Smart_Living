package com.aposiamp.smartliving.presentation.ui.activity.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.DevicesScreen
import com.aposiamp.smartliving.presentation.viewmodel.main.DevicesViewModel

@Composable
internal fun MainNavigation(
    devicesViewModel: DevicesViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "devices"
    ) {
        composable("devices"){
            DevicesScreen(
                navController = navController,
                viewModel = devicesViewModel
            )
        }
    }
}