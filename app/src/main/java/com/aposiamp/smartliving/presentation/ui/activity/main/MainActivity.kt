package com.aposiamp.smartliving.presentation.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aposiamp.smartliving.SmartLiving
import com.aposiamp.smartliving.presentation.ui.activity.main.navigation.MainNavigation
import com.aposiamp.smartliving.presentation.ui.theme.SmartLivingTheme
import com.aposiamp.smartliving.presentation.viewmodel.main.DevicesViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.NavigationViewModel
import com.aposiamp.smartliving.presentation.utils.viewModelFactory
import com.aposiamp.smartliving.presentation.viewmodel.main.AddANewDeviceViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.CreateANewRoomViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainNavigationViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.SettingsViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.UserNotInSpaceViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.AccountProfileViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.AccountViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartLivingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = this

                    val devicesViewModel = viewModel<DevicesViewModel>(
                        factory = viewModelFactory {
                            DevicesViewModel(
                                checkIfAnyRoomExistsUseCase = SmartLiving.appModule.checkIfAnyRoomExistsUseCase
                            )
                        }
                    )

                    val mainSharedViewModel = viewModel<MainSharedViewModel>(
                        factory = viewModelFactory {
                            MainSharedViewModel(
                                getSpaceUseCase = SmartLiving.appModule.getSpaceUseCase,
                                getEnvironmentalDataUseCase = SmartLiving.appModule.getEnvironmentalDataUseCase,
                                setEnvironmentalDataUseCase = SmartLiving.appModule.setEnvironmentalDataUseCase
                            )
                        }
                    )

                    val navigationViewModel = viewModel<NavigationViewModel>(
                        factory = viewModelFactory {
                            NavigationViewModel(
                                getBottomNavigationItemsUseCase = SmartLiving.appModule.getBottomMenuItemsUseCase,
                                getDropdownMenuItemsUseCase = SmartLiving.appModule.getDropdownMenuItemsUseCase,
                                getNavigationDrawerItemsUseCase = SmartLiving.appModule.getNavigationDrawerItemsUseCase
                            )
                        }
                    )

                    val mainNavigationViewModel = viewModel<MainNavigationViewModel>(
                        factory = viewModelFactory {
                            MainNavigationViewModel(
                                getSpaceUseCase = SmartLiving.appModule.getSpaceUseCase,
                                checkIfUserIsInSpaceUseCase = SmartLiving.appModule.checkIfUserIsInSpaceUseCase
                            )
                        }
                    )

                    val userNotInSpaceViewModel = viewModel<UserNotInSpaceViewModel>(
                        factory = viewModelFactory {
                            UserNotInSpaceViewModel(
                                logoutUseCase = SmartLiving.appModule.logoutUseCase
                            )
                        }
                    )

                    val createANewRoomViewModel = viewModel<CreateANewRoomViewModel>(
                        factory = viewModelFactory {
                            CreateANewRoomViewModel(
                                validateRoomName = SmartLiving.appModule.validateRoomName,
                                setRoomDataUseCase = SmartLiving.appModule.setRoomDataUseCase
                            )
                        }
                    )

                    val addANewDeviceViewModel = viewModel<AddANewDeviceViewModel>(
                        factory = viewModelFactory {
                            AddANewDeviceViewModel(
                                validateDeviceName = SmartLiving.appModule.validateDeviceName,
                                validateDeviceId = SmartLiving.appModule.validateDeviceId,
                                validateRoomId = SmartLiving.appModule.validateRoomId,
                                validateDeviceExistence = SmartLiving.appModule.validateDeviceExistence,
                                checkIfDeviceExistsUseCase = SmartLiving.appModule.checkIfDeviceExistsUseCase,
                                getRoomListUseCase = SmartLiving.appModule.getRoomListUseCase,
                                setDeviceDataUseCase = SmartLiving.appModule.setDeviceDataUseCase
                            )
                        }
                    )

                    val settingsViewModel = viewModel<SettingsViewModel>(
                        factory = viewModelFactory {
                            SettingsViewModel(
                                getSettingsScreenItemsUseCase = SmartLiving.appModule.getSettingsScreenItemsUseCase
                            )
                        }
                    )

                    val accountProfileViewModel = viewModel<AccountProfileViewModel>(
                        factory = viewModelFactory {
                            AccountProfileViewModel(
                                getAccountProfileDetailsUseCase = SmartLiving.appModule.getAccountProfileDetailsUseCase
                            )
                        }
                    )

                    val accountViewModel = viewModel<AccountViewModel>(
                        factory = viewModelFactory {
                            AccountViewModel(
                                validatePassword = SmartLiving.appModule.validatePassword,
                                updatePasswordUseCase = SmartLiving.appModule.updatePasswordUseCase
                            )
                        }
                    )

                    val profileViewModel = viewModel<ProfileViewModel>(
                        factory = viewModelFactory {
                            ProfileViewModel(
                                validateFirstName = SmartLiving.appModule.validateFirstName,
                                validateLastName = SmartLiving.appModule.validateLastName,
                                validateEmail = SmartLiving.appModule.validateEmail,
                                updateFirstNameUseCase = SmartLiving.appModule.updateFirstNameUseCase,
                                updateLastNameUseCase = SmartLiving.appModule.updateLastNameUseCase,
                                updateEmailUseCase = SmartLiving.appModule.updateEmailUseCase
                            )
                        }
                    )

                    MainNavigation(
                        context = context,
                        devicesViewModel = devicesViewModel,
                        settingsViewModel = settingsViewModel,
                        accountViewModel = accountViewModel,
                        profileViewModel = profileViewModel,
                        mainSharedViewModel = mainSharedViewModel,
                        navigationViewModel = navigationViewModel,
                        mainNavigationViewModel = mainNavigationViewModel,
                        userNotInSpaceViewModel = userNotInSpaceViewModel,
                        createANewRoomViewModel = createANewRoomViewModel,
                        addANewDeviceViewModel = addANewDeviceViewModel,
                        accountProfileViewModel =accountProfileViewModel
                    )
                }
            }
        }
    }
}