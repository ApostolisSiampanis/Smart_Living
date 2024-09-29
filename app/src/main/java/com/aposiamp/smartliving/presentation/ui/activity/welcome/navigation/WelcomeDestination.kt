package com.aposiamp.smartliving.presentation.ui.activity.welcome.navigation

sealed class WelcomeDestination(val route: String) {
    data object Welcome : WelcomeDestination("welcome")
    data object Login : WelcomeDestination("login")
    data object SignUp : WelcomeDestination("signUp")
    data object TermsAndConditions : WelcomeDestination("termsAndConditions")
    data object PrivacyPolicy : WelcomeDestination("privacyPolicy")
    data object ForgotPassword : WelcomeDestination("forgotPassword")
    data object Permissions : WelcomeDestination("permissions")
    data object CreateANewSpace : WelcomeDestination("createANewSpace")
}