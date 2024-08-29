package com.aposiamp.smartliving.presentation.ui.activity.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.HomeScreen
import com.aposiamp.smartliving.presentation.viewmodel.main.HomeViewModel

@Composable
internal fun MainNavigation(
    homeViewModel: HomeViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home"){
            HomeScreen(
                navController = navController,
                viewModel = homeViewModel
            )
        }
    }
}