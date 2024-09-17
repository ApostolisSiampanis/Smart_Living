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
                                getSpaceNameUseCase = SmartLiving.appModule.getSpaceNameUseCase
                            )
                        }
                    )

                    val mainSharedViewModel = viewModel<MainSharedViewModel>(
                        factory = viewModelFactory {
                            MainSharedViewModel(
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

                    MainNavigation(
                        context = context,
                        devicesViewModel = devicesViewModel,
                        mainSharedViewModel = mainSharedViewModel,
                        navigationViewModel = navigationViewModel
                    )
                }
            }
        }
    }
}