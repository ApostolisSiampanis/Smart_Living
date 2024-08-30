package com.aposiamp.smartliving.presentation.ui.activity.welcome.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase
import com.aposiamp.smartliving.presentation.ui.activity.main.MainActivity
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.PrivacyPolicyScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.TermsAndConditionsScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.WelcomeScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.LoginScreen
import com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth.SignUpScreen
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.LoginViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.SignUpViewModel

@Composable
internal fun WelcomeNavigation(
    context: Context,
    getCurrentUserUseCase: GetCurrentUserUseCase,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = determineStartDestination(
            context = context,
            getCurrentUserUseCase = getCurrentUserUseCase
        )
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

fun determineStartDestination(
    context: Context,
    getCurrentUserUseCase: GetCurrentUserUseCase
): String {
    getCurrentUserUseCase.execute() ?: return "welcome"
    try {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        return "welcome"
    }
    return "welcome"
}