package com.aposiamp.smartliving.presentation.ui.activity.main.screens

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.aposiamp.smartliving.SmartLiving
import com.aposiamp.smartliving.presentation.ui.component.BottomBar
import com.aposiamp.smartliving.presentation.ui.component.MenuMediumTopAppBar
import com.aposiamp.smartliving.presentation.ui.component.NavigationDrawer
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.NavigationViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnergyScreen(
    navController: NavController,
    mainSharedViewModel: MainSharedViewModel,
    navigationViewModel: NavigationViewModel,
    context: Context
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val space by mainSharedViewModel.space.collectAsState()

    // Retrieve the Navigation Drawer Items
    val navigationDrawerItems = navigationViewModel.getNavigationDrawerItems(context = context)
    // Retrieve the Bottom Navigation Items
    val bottomNavigationItems = navigationViewModel.getBottomNavigationItems(context = context)
    // Retrieve the dropdown menu items
    val dropdownMenuItems = navigationViewModel.getDropdownMenuItems(context = context, navController = navController)

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                drawerState = drawerState,
                navigationDrawerItems = navigationDrawerItems,
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
                MenuMediumTopAppBar(
                    title = space?.spaceName ?: "",
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
                    dropdownMenuItems = dropdownMenuItems,
                    isDevicesScreen = false
                )
            },
            bottomBar = {
                BottomBar(
                    navController = navController,
                    bottomMenuItems = bottomNavigationItems
                )
            },
            content = { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    item {

                    }
                }
            }
        )
    }
}