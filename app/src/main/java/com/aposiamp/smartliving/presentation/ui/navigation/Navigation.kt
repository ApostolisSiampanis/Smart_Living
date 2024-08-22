package com.aposiamp.smartliving.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.screen.auth.LoginScreen
import com.aposiamp.smartliving.presentation.ui.screen.auth.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login" //TODO: Change to "welcome" or "home" after implementing the logic for the start destination
    ) {
        composable("login") { LoginScreen(navController) }
        composable("signUp") { SignUpScreen(navController) }
    }
}