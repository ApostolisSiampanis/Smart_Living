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
import com.aposiamp.smartliving.presentation.viewmodel.main.CreateANewRoomViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainNavigationViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.UserNotInSpaceViewModel

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

                    val devicesViewModel = viewModel<DevicesViewModel>()

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

                    MainNavigation(
                        context = context,
                        devicesViewModel = devicesViewModel,
                        mainSharedViewModel = mainSharedViewModel,
                        navigationViewModel = navigationViewModel,
                        mainNavigationViewModel = mainNavigationViewModel,
                        userNotInSpaceViewModel = userNotInSpaceViewModel,
                        createANewRoomViewModel = createANewRoomViewModel
                    )
                }
            }
        }
    }
}