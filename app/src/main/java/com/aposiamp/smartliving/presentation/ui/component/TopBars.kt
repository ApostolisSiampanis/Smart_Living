package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.model.DropdownMenuItemUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuMediumTopAppBar(
    title: String,
    color: Color,
    onMenuClick: () -> Unit,
    drawerState: DrawerState,
    scrollBehavior: TopAppBarScrollBehavior,
    dropdownMenuItems: List<DropdownMenuItemUiModel>,
    isDevicesScreen: Boolean = false,
    navController: NavController
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    MediumTopAppBar(
        title = {
            NormalNavigationTextComponent(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onMenuClick()
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = stringResource(id = if (drawerState.isOpen) R.string.close_menu else R.string.open_menu)
                    )
                }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        ),
        actions = {
            if (isDevicesScreen) {
                Box {
                    IconButton(
                        onClick = {
                            isContextMenuVisible = true
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.add),
                                contentDescription = stringResource(id = R.string.add)
                            )
                        }
                    )

                    DropdownMenuComponent(
                        isContextMenuVisible = isContextMenuVisible,
                        onDismissRequest = { isContextMenuVisible = false },
                        items = dropdownMenuItems,
                        navController = navController
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuAppTopBar(
    title: String,
    color: Color,
    onMenuClick: () -> Unit,
    drawerState: DrawerState
) {
    TopAppBar(
        title = {
            NormalNavigationTextComponent(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onMenuClick()
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = stringResource(id = if (drawerState.isOpen) R.string.close_menu else R.string.open_menu)
                    )
                }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackAppTopBar(
    title: String,
    color: Color,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            HeaderText(value = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackClick()
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = stringResource(id = R.string.go_back)
                    )
                }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        )
    )
}