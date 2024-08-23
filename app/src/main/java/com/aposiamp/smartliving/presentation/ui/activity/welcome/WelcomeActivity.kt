package com.aposiamp.smartliving.presentation.ui.activity.welcome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aposiamp.smartliving.SmartLiving
import com.aposiamp.smartliving.presentation.ui.activity.welcome.navigation.WelcomeNavigation
import com.aposiamp.smartliving.presentation.ui.theme.SmartLivingTheme
import com.aposiamp.smartliving.presentation.viewmodel.viewModelFactory
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.LoginViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.SignUpViewModel

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartLivingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val loginViewModel = viewModel<LoginViewModel>(
                        factory = viewModelFactory {
                            LoginViewModel(
                                loginUseCase = SmartLiving.appModule.loginUseCase,
                                validateEmail = SmartLiving.appModule.validateEmail,
                                validatePassword = SmartLiving.appModule.validatePassword
                            )
                        }
                    )

                    val signUpViewModel = viewModel<SignUpViewModel>(
                        factory = viewModelFactory {
                            SignUpViewModel(
                                signUpUseCase = SmartLiving.appModule.signUpUseCase,
                                validateEmail = SmartLiving.appModule.validateEmail,
                                validatePassword = SmartLiving.appModule.validatePassword,
                                validateFirstName = SmartLiving.appModule.validateFirstName,
                                validateLastName = SmartLiving.appModule.validateLastName,
                                validateTerms = SmartLiving.appModule.validateTerms
                            )
                        }
                    )

                    WelcomeNavigation(
                        loginViewModel = loginViewModel,
                        signUpViewModel = signUpViewModel
                    )
                }
            }
        }
    }
}