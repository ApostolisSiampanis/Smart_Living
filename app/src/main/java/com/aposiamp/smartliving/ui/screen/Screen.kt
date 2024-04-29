package com.aposiamp.smartliving.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ViewComfy
import androidx.compose.material.icons.outlined.EnergySavingsLeaf
import androidx.compose.material.icons.outlined.PlayCircleOutline
import androidx.compose.material.icons.outlined.ViewComfy
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.aposiamp.smartliving.R

sealed class Screen(val titleResId: Int, val route: String) {

    sealed class DrawerScreen(titleResId: Int, route: String, val icon: ImageVector)
        : Screen(titleResId, route) {
        data object Settings : DrawerScreen(
            titleResId = R.string.settings,
            route = "settings",
            icon = Icons.Filled.Settings
        )
    }

    sealed class BottomScreen(titleResId: Int, route: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector)
        : Screen(titleResId, route) {
        data object Devices : BottomScreen(
            titleResId = R.string.devices,
            route = "devices",
            selectedIcon = Icons.Filled.ViewComfy,
            unselectedIcon = Icons.Outlined.ViewComfy
        )
        data object Routines : BottomScreen(
            titleResId = R.string.routines,
            route = "routines",
            selectedIcon = Icons.Filled.PlayCircleFilled,
            unselectedIcon = Icons.Outlined.PlayCircleOutline
        )
        data object Energy : BottomScreen(
            titleResId = R.string.energy,
            route = "energy",
            selectedIcon = Icons.Filled.EnergySavingsLeaf,
            unselectedIcon = Icons.Outlined.EnergySavingsLeaf
        )
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.Settings
)

val screensInBottom = listOf(
    Screen.BottomScreen.Devices,
    Screen.BottomScreen.Routines,
    Screen.BottomScreen.Energy
)
