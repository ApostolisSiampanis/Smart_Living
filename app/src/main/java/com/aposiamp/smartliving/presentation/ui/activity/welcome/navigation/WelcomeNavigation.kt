package com.aposiamp.smartliving.presentation.ui.activity.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.CreateANewSpaceScreen
import com.aposiamp.smartliving.presentation.ui.activity.LoadingScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.PermissionsScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.PrivacyPolicyScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.TermsAndConditionsScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.WelcomeScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.ForgotPasswordScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.LoginScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.SignUpScreen
import com.aposiamp.smartliving.presentation.viewmodel.welcome.CreateANewSpaceViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.PermissionsViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.WelcomeNavigationViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.ForgotPasswordViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.LoginViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.SignUpViewModel

@Composable
internal fun WelcomeNavigation(
    welcomeNavigationViewModel: WelcomeNavigationViewModel,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    forgotPasswordViewModel: ForgotPasswordViewModel,
    permissionsViewModel: PermissionsViewModel,
    createANewSpaceViewModel: CreateANewSpaceViewModel
) {
    val navController = rememberNavController()
    val startDestination by welcomeNavigationViewModel.startDestination.collectAsStateWithLifecycle()

    if (startDestination == null) {
        LoadingScreen()
    } else {
        NavHost(
            navController = navController,
            startDestination = startDestination!!
        ) {
            composable(WelcomeDestination.Welcome.route) {
                WelcomeScreen(
                    navController = navController
                )
            }
            composable(WelcomeDestination.Login.route) {
                LoginScreen(
                    navController = navController,
                    viewModel = loginViewModel
                )
            }
            composable(WelcomeDestination.SignUp.route) {
                SignUpScreen(
                    navController = navController,
                    viewModel = signUpViewModel
                )
            }
            composable(WelcomeDestination.TermsAndConditions.route) {
                TermsAndConditionsScreen(
                    navController = navController
                )
            }
            composable(WelcomeDestination.PrivacyPolicy.route) {
                PrivacyPolicyScreen(
                    navController = navController
                )
            }
            composable(WelcomeDestination.ForgotPassword.route) {
                ForgotPasswordScreen(
                    navController = navController,
                    viewModel = forgotPasswordViewModel
                )
            }
            composable(WelcomeDestination.Permissions.route) {
                PermissionsScreen(
                    navController = navController,
                    viewModel = permissionsViewModel
                )
            }
            composable(WelcomeDestination.CreateANewSpace.route) {
                CreateANewSpaceScreen(
                    viewModel = createANewSpaceViewModel
                )
            }
        }
    }
}
