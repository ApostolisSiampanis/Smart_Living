package com.aposiamp.smartliving.presentation.ui.activity.main.screens

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.aposiamp.smartliving.SmartLiving
import com.aposiamp.smartliving.presentation.ui.component.BottomBar
import com.aposiamp.smartliving.presentation.ui.component.MenuMediumTopAppBar
import com.aposiamp.smartliving.presentation.ui.component.NavigationDrawer
import com.aposiamp.smartliving.presentation.viewmodel.main.DevicesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevicesScreen(
    navController: NavController,
    viewModel: DevicesViewModel,
    context: Context
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Retrieve the dropdown menu items
    val dropdownMenuItems = viewModel.getDropdownMenuItems(context = context, navController = navController)

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                drawerState = drawerState,
                getNavigationDrawerItemsUseCase = SmartLiving.appModule.getNavigationDrawerItemsUseCase,
                logoutUseCase = SmartLiving.appModule.logoutUseCase
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                //TODO: to be changed, use the name of the "house"
                MenuMediumTopAppBar(
                    title = "My Home",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    onMenuClick = {
                        scope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            } else {
                                drawerState.open()
                            }
                        }
                    },
                    drawerState = drawerState,
                    scrollBehavior = scrollBehavior,
                    dropdownMenuItems = dropdownMenuItems
                )
            },
            bottomBar = {
                BottomBar(
                    navController = navController,
                    getBottomNavigationItemsUseCase = SmartLiving.appModule.getBottomMenuItemsUseCase
                )
            },
            content = { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    item {
                        // TODO: Add the devices here, remove the buttons
                        Button(
                            onClick = {
                                navController.navigate("thermostat")
                            }
                        ) {
                            Text(text = "Thermostat")
                        }
                        Button(
                            onClick = {
                                navController.navigate("airCondition")
                            }
                        ) {
                            Text(text = "Air Condition")
                        }
                        Button(
                            onClick = {
                                navController.navigate("dehumidifier")
                            }
                        ) {
                            Text(text = "Dehumidifier")
                        }
                    }
                }
            }
        )
    }
}