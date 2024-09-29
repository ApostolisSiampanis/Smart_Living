package com.aposiamp.smartliving.presentation.ui.activity.main.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aposiamp.smartliving.presentation.ui.activity.LoadingScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.AboutScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.AddANewDeviceScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.CreateANewRoomScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.DevicesScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.EnergyScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.settings.SettingsScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.UserNotInSpaceScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.devices.AirConditionScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.devices.DehumidifierScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.devices.ThermostatScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.settings.AccountScreen
import com.aposiamp.smartliving.presentation.ui.activity.main.screens.settings.ProfileScreen
import com.aposiamp.smartliving.presentation.viewmodel.main.AddANewDeviceViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.CreateANewRoomViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.DevicesViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.EnergyViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainNavigationViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.NavigationViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.SettingsViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.UserNotInSpaceViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.devices.AirConditionViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.devices.DehumidifierViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.devices.ThermostatViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.AccountProfileViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.AccountViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.ProfileViewModel

@Composable
internal fun MainNavigation(
    context: Context,
    devicesViewModel: DevicesViewModel,
    settingsViewModel: SettingsViewModel,
    accountViewModel: AccountViewModel,
    profileViewModel: ProfileViewModel,
    mainSharedViewModel: MainSharedViewModel,
    navigationViewModel: NavigationViewModel,
    mainNavigationViewModel: MainNavigationViewModel,
    userNotInSpaceViewModel: UserNotInSpaceViewModel,
    createANewRoomViewModel: CreateANewRoomViewModel,
    addANewDeviceViewModel: AddANewDeviceViewModel,
    accountProfileViewModel: AccountProfileViewModel,
    thermostatViewModel: ThermostatViewModel,
    airConditionViewModel: AirConditionViewModel,
    dehumidifierViewModel: DehumidifierViewModel,
    energyViewModel: EnergyViewModel
) {
    val navController = rememberNavController()
    val startDestination by mainNavigationViewModel.startDestination.collectAsStateWithLifecycle()

    if (startDestination == null) {
        LoadingScreen()
    } else {
        NavHost(
            navController = navController,
            startDestination = startDestination!!
        ) {
            composable("notInSpace"){
                UserNotInSpaceScreen(
                    userNotInSpaceViewModel = userNotInSpaceViewModel
                )
            }

            composable("devices"){
                DevicesScreen(
                    navController = navController,
                    viewModel = devicesViewModel,
                    mainSharedViewModel = mainSharedViewModel,
                    navigationViewModel = navigationViewModel,
                    context = context
                )
            }
            composable("energy"){
                EnergyScreen(
                    navController = navController,
                    viewModel = energyViewModel,
                    mainSharedViewModel = mainSharedViewModel,
                    navigationViewModel = navigationViewModel,
                    context = context
                )
            }

            composable("createANewRoom"){
                CreateANewRoomScreen(
                    navController = navController,
                    viewModel = createANewRoomViewModel,
                    mainSharedViewModel = mainSharedViewModel
                )
            }
            composable("addANewDevice"){
                AddANewDeviceScreen(
                    navController = navController,
                    viewModel = addANewDeviceViewModel,
                    mainSharedViewModel = mainSharedViewModel
                )
            }

            composable("settings"){
                SettingsScreen(
                    navController = navController,
                    settingsViewModel = settingsViewModel,
                    navigationViewModel = navigationViewModel,
                    context = context
                )
            }
            composable("profile"){
                ProfileScreen(
                    navController = navController,
                    viewModel = profileViewModel,
                    accountProfileViewModel = accountProfileViewModel
                )
            }
            composable("account"){
                AccountScreen(
                    navController = navController,
                    viewModel = accountViewModel,
                    accountProfileViewModel = accountProfileViewModel
                )
            }
            composable("about"){
                AboutScreen(
                    navController = navController,
                    navigationViewModel = navigationViewModel
                )
            }

            composable("thermostat"){
                ThermostatScreen(
                    navController = navController,
                    viewModel = thermostatViewModel,
                    sharedViewModel = mainSharedViewModel
                )
            }
            composable("airCondition"){
                AirConditionScreen(
                    navController = navController,
                    viewModel = airConditionViewModel,
                    sharedViewModel = mainSharedViewModel
                )
            }
            composable("dehumidifier"){
                DehumidifierScreen(
                    navController = navController,
                    viewModel = dehumidifierViewModel,
                    sharedViewModel = mainSharedViewModel
                )
            }
        }
    }
}