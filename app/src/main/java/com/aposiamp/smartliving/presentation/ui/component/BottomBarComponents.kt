package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.aposiamp.smartliving.domain.usecase.main.GetBottomNavigationItemsUseCase
import com.aposiamp.smartliving.presentation.ui.screen.screensInBottom

@Composable
fun BottomBar(
    navController: NavController,
    getBottomNavigationItemsUseCase: GetBottomNavigationItemsUseCase
) {
    val screensInBottomMenu = getBottomNavigationItemsUseCase.execute()

    fun getCurrentRouteIndex(): Int {
        val currentRoute = navController.currentDestination?.route
        return screensInBottomMenu.indexOfFirst { it.route == currentRoute }
    }

    val selectedItemIndex = remember { mutableIntStateOf(getCurrentRouteIndex()) }

    NavigationBar {
        screensInBottom.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex.intValue == index,
                onClick = {
                    selectedItemIndex.intValue = index
                    item.route?.let { navController.navigate(it) }
                },
                label = {
                    BottomMenuItemTextComponent(
                        text = stringResource(id = item.titleResId)
                    )
                },
                icon = {
                    Icon(
                        imageVector = if(index == selectedItemIndex.intValue) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = stringResource(id = item.titleResId)
                    )
                }
            )
        }
    }
}