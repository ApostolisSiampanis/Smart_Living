package com.aposiamp.smartliving.presentation.ui.activity.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.LoginScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.SignUpScreen

@Composable
internal fun WelcomeNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login" //TODO: Change to "welcome" or "home" after implementing the logic for the start destination
    ) {
        composable("login") { LoginScreen(navController) }
        composable("signUp") { SignUpScreen(navController) }
    }
}