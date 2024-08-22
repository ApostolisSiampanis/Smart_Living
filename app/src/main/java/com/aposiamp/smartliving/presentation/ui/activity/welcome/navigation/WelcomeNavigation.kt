package com.aposiamp.smartliving.presentation.ui.activity.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.WelcomeScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.LoginScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.SignUpScreen

@Composable
internal fun WelcomeNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome" //TODO: Change to method to determine start destination
    ) {
        composable("welcome") {
            WelcomeScreen(
                navController = navController
            )
        }
        composable("login") {
            LoginScreen(
                navController = navController
            )
        }
        composable("signUp") {
            SignUpScreen(
                navController = navController
            )
        }
    }
}