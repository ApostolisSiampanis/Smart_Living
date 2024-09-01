package com.aposiamp.smartliving.presentation.ui.activity.main.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevicesScreen(
    navController: NavController,
    viewModel: DevicesViewModel
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    //to be changed
    val scope = rememberCoroutineScope()

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
                //to be changed
                MenuMediumTopAppBar(
                    title = "My Home",
                    drawerState = drawerState,
                    scope = scope,
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                BottomBar(
                    navController = navController,
                    getBottomNavigationItemsUseCase = SmartLiving.appModule.getBottomMenuItemsUseCase
                )
            }
        ) { values ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
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
    }
}