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
import com.aposiamp.smartliving.presentation.utils.viewModelFactory
import com.aposiamp.smartliving.presentation.viewmodel.welcome.CreateANewSpaceViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.PermissionsViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.WelcomeNavigationViewModel
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.ForgotPasswordViewModel
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
                                checkIfSpaceDataExistsUseCase = SmartLiving.appModule.checkIfSpaceDataExistsUseCase,
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

                    val forgotPasswordViewModel = viewModel<ForgotPasswordViewModel>(
                        factory = viewModelFactory {
                            ForgotPasswordViewModel(
                                forgotPasswordUseCase = SmartLiving.appModule.forgotPasswordUseCase,
                                validateEmail = SmartLiving.appModule.validateEmail
                            )
                        }
                    )

                    val permissionsViewModel = viewModel<PermissionsViewModel>()

                    val createANewSpaceViewModel = viewModel<CreateANewSpaceViewModel>(
                        factory = viewModelFactory {
                            CreateANewSpaceViewModel(
                                validateSpaceName = SmartLiving.appModule.validateSpaceName,
                                validateSpaceAddress = SmartLiving.appModule.validateSpaceAddress,
                                validatePlaceData = SmartLiving.appModule.validatePlaceData,
                                setSpaceDataUseCase = SmartLiving.appModule.setSpaceDataUseCase,
                                getAutoCompleteSuggestionsUseCase = SmartLiving.appModule.getAutoCompleteSuggestionsUseCase,
                                getLocationFromPlaceIdUseCase = SmartLiving.appModule.getLocationFromPlaceIdUseCase
                            )
                        }
                    )

                    val welcomeNavigationViewModel = viewModel<WelcomeNavigationViewModel>(
                        factory = viewModelFactory {
                            WelcomeNavigationViewModel(
                                context = this,
                                getCurrentUserUseCase = SmartLiving.appModule.getCurrentUserUseCase,
                                checkIfSpaceDataExistsUseCase = SmartLiving.appModule.checkIfSpaceDataExistsUseCase
                            )
                        }
                    )

                    WelcomeNavigation(
                        welcomeNavigationViewModel = welcomeNavigationViewModel,
                        loginViewModel = loginViewModel,
                        signUpViewModel = signUpViewModel,
                        forgotPasswordViewModel = forgotPasswordViewModel,
                        permissionsViewModel = permissionsViewModel,
                        createANewSpaceViewModel = createANewSpaceViewModel
                    )
                }
            }
        }
    }
}