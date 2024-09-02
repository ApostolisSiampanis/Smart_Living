package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.aposiamp.smartliving.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuMediumTopAppBar(
    title: String,
    color: Color,
    onMenuClick: () -> Unit,
    drawerState: DrawerState,
    scrollBehavior: TopAppBarScrollBehavior
) {
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
            IconButton(
                onClick = { /*TODO*/},
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = stringResource(id = R.string.add)
                    )
                }
            )
        },
        scrollBehavior = scrollBehavior
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
                    Image(
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