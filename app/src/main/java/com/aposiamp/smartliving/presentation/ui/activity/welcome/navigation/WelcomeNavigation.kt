package com.aposiamp.smartliving.presentation.ui.activity.welcome.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.PrivacyPolicyScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.TermsAndConditionsScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.WelcomeScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.LoginScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.SignUpScreen
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.LoginViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.SignUpViewModel

@Composable
internal fun WelcomeNavigation(
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel
) {
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
                navController = navController,
                viewModel = loginViewModel
            )
        }
        composable("signUp") {
            SignUpScreen(
                navController = navController,
                viewModel = signUpViewModel
            )
        }
        composable("termsAndConditions") {
            TermsAndConditionsScreen(
                navController = navController
            )
        }
        composable("privacyPolicy") {
            PrivacyPolicyScreen(
                navController = navController
            )
        }
    }
}