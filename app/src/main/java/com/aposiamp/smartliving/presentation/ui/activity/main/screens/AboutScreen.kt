package com.aposiamp.smartliving.presentation.ui.activity.main.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.SmartLiving
import com.aposiamp.smartliving.presentation.ui.component.ButtonWithImageComponent
import com.aposiamp.smartliving.presentation.ui.component.GeneralBoldText
import com.aposiamp.smartliving.presentation.ui.component.GeneralNormalText
import com.aposiamp.smartliving.presentation.ui.component.MenuAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.NavigationDrawer
import kotlinx.coroutines.launch

@Composable
fun AboutScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
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
                .fillMaxSize(),
            topBar = {
                MenuAppTopBar(
                    title = stringResource(id = R.string.about),
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
                            .fillMaxSize()
                            .padding(start = 28.dp, end = 28.dp, top = 18.dp)
                    ) {
                        item {
                            GeneralNormalText(value = stringResource(id = R.string.about_intro))
                            GeneralBoldText(value = stringResource(id = R.string.about_our_features))
                            GeneralBoldText(value = stringResource(id = R.string.about_unified_control))
                            GeneralNormalText(value = stringResource(id = R.string.about_unified_control_text))
                            GeneralBoldText(value = stringResource(id = R.string.about_energy_monitoring))
                            GeneralNormalText(value = stringResource(id = R.string.about_energy_monitoring_text))
                            GeneralBoldText(value = stringResource(id = R.string.about_user_friendly))
                            GeneralNormalText(value = stringResource(id = R.string.about_user_friendly_text))
                            GeneralBoldText(value = stringResource(id = R.string.about_our_vision))
                            GeneralNormalText(value = stringResource(id = R.string.about_our_vision_text))

                            val githubLink = Uri.parse("https://github.com/ApostolisSiampanis")
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    ButtonWithImageComponent(
                                        onClick = {
                                            context.startActivity(
                                                Intent(
                                                    Intent.ACTION_VIEW,
                                                    githubLink
                                                )
                                            )
                                        },
                                        color = MaterialTheme.colorScheme.primaryContainer,
                                        painter = painterResource(id = R.drawable.github),
                                        contentDescription = stringResource(id = R.string.github_profile),
                                        text = stringResource(id = R.string.apostolis_siampanis)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}