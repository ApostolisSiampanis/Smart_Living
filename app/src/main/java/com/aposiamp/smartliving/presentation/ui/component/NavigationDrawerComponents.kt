package com.aposiamp.smartliving.presentation.ui.component

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
import com.aposiamp.smartliving.domain.usecase.main.GetNavigationDrawerItemsUseCase
import com.aposiamp.smartliving.domain.usecase.user.LogoutUseCase
import com.aposiamp.smartliving.presentation.model.NavigationUiItem
import com.aposiamp.smartliving.presentation.ui.activity.welcome.WelcomeActivity
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    navController: NavController,
    drawerState: DrawerState,
    getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase,
    logoutUseCase: LogoutUseCase,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val screensInDrawer = getNavigationDrawerItemsUseCase.execute()

    fun getCurrentRouteIndex(): Int {
        val currentRoute = navController.currentDestination?.route
        return screensInDrawer.indexOfFirst { it.route == currentRoute }
    }

    val selectedItemIndex = remember { mutableIntStateOf(getCurrentRouteIndex()) }

    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            screensInDrawer.forEachIndexed { index, item ->
                DrawerItem(
                    item = item,
                    isSelected = selectedItemIndex.intValue == index,
                    isEnabled = selectedItemIndex.intValue != index,
                    onItemClick = {
                        scope.launch { drawerState.close() }
                        selectedItemIndex.intValue = index
                        item.route?.let { navController.navigate(it) }
                    }
                )
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DrawerItem(
                item = NavigationUiItem(
                    titleResId = R.string.logout,
                    unselectedIcon = R.drawable.logout
                ),
                isSelected = selectedItemIndex.intValue == screensInDrawer.size,
                textColor = Color.Red,
                onItemClick = {
                    scope.launch {
                        drawerState.close()

                        selectedItemIndex.intValue = screensInDrawer.size

                        logoutUseCase.execute()

                        val intent = Intent(context, WelcomeActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        context.startActivity(intent)
                    }
                }
            )
        }
    }
}

@Composable
fun DrawerItem(
    item: NavigationUiItem,
    isSelected: Boolean,
    isEnabled: Boolean = true,
    textColor: Color = if (item.titleResId == R.string.logout) Color.Red else Color.Black,
    onItemClick: () -> Unit
) {
    val iconId = if (isSelected && item.selectedIcon != null) {
        item.selectedIcon
    } else {
        item.unselectedIcon
    }

    NavigationDrawerItem(
        icon = {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = stringResource(id = item.titleResId)
            )
        },
        label = {
            DrawerItemTextComponent(
                text = stringResource(id = item.titleResId),
                color = textColor.copy(alpha = if (isSelected) 1f else 0.6f)
            )
        },
        selected = isSelected,
        onClick = {
            if (isEnabled) {
                onItemClick()
            }
        }
    )
}