package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.aposiamp.smartliving.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuMediumTopAppBar(
    title: String,
    drawerState: DrawerState,
    scope: CoroutineScope,
    scrollBehavior: TopAppBarScrollBehavior
) {
    MediumTopAppBar(
        title = {
            Text(
                text = title,
                fontFamily = FontFamily(Font(R.font.carlito_regular))
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add a Device or a Scenario"
                )
            }
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