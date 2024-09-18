package com.aposiamp.smartliving.presentation.ui.activity.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.CreateANewSpaceScreen
import com.aposiamp.smartliving.presentation.ui.activity.LoadingScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.PermissionsScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.PrivacyPolicyScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.TermsAndConditionsScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.WelcomeScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.LoginScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.SignUpScreen
import com.aposiamp.smartliving.presentation.viewmodel.welcome.CreateANewSpaceViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.PermissionsViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.WelcomeNavigationViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.LoginViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.SignUpViewModel

@Composable
internal fun WelcomeNavigation(
    welcomeNavigationViewModel: WelcomeNavigationViewModel,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    permissionsViewModel: PermissionsViewModel,
    createANewSpaceViewModel: CreateANewSpaceViewModel
) {
    val navController = rememberNavController()
    val startDestination by welcomeNavigationViewModel.startDestination.collectAsState()

    if (startDestination == null) {
        LoadingScreen()
    } else {
        NavHost(
            navController = navController,
            startDestination = startDestination!!
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
            composable("permissions") {
                PermissionsScreen(
                    navController = navController,
                    viewModel = permissionsViewModel
                )
            }
            composable("createANewSpace") {
                CreateANewSpaceScreen(
                    viewModel = createANewSpaceViewModel
                )
            }
        }
    }
}
