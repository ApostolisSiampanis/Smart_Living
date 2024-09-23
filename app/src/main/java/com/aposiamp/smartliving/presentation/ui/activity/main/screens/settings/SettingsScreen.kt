package com.aposiamp.smartliving.presentation.ui.activity.main.screens.settings

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.SmartLiving
import com.aposiamp.smartliving.presentation.ui.component.MenuAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.NavigationDrawer
import com.aposiamp.smartliving.presentation.ui.component.SettingCard
import com.aposiamp.smartliving.presentation.viewmodel.main.NavigationViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel,
    navigationViewModel: NavigationViewModel,
    context: Context
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Retrieve the Navigation Drawer Items
    val navigationDrawerItems = navigationViewModel.getNavigationDrawerItems(context = context)
    // Retrieve the Settings Items
    val settingsItems = settingsViewModel.getSettingsScreenItems(context = context)

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
                .fillMaxSize(),
            topBar = {
                MenuAppTopBar(
                    title = stringResource(id = R.string.settings),
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
                    drawerState = drawerState
                )
            },
            content = { padding ->
                Surface(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(settingsItems) { item ->
                            SettingCard(
                                item = item,
                                navController = navController
                            )
                        }
                    }
                }
            }
        )
    }
}