package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.aposiamp.smartliving.presentation.model.NavigationItemUiModel

@Composable
fun BottomBar(
    navController: NavController,
    bottomMenuItems: List<NavigationItemUiModel>
) {
    fun getCurrentRouteIndex(): Int {
        val currentRoute = navController.currentDestination?.route
        return bottomMenuItems.indexOfFirst { it.route == currentRoute }
    }

    val selectedItemIndex = remember { mutableIntStateOf(getCurrentRouteIndex()) }

    NavigationBar {
        bottomMenuItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex.intValue == index,
                onClick = {
                    selectedItemIndex.intValue = index
                    item.route?.let { navController.navigate(it) }
                },
                label = {
                    NormalNavigationTextComponent(
                        text = stringResource(id = item.titleResId)
                    )
                },
                icon = {
                    val iconId = if(index == selectedItemIndex.intValue && item.selectedIcon != null) {
                        item.selectedIcon
                    } else {
                        item.unselectedIcon
                    }

                    iconId?.let { painterResource(id = it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = stringResource(id = item.titleResId)
                        )
                    }
                }
            )
        }
    }
}