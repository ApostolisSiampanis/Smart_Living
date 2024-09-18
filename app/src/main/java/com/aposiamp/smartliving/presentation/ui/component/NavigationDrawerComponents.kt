package com.aposiamp.smartliving.presentation.ui.component

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.aposiamp.smartliving.domain.usecase.user.LogoutUseCase
import com.aposiamp.smartliving.presentation.model.NavigationItemUiModel
import com.aposiamp.smartliving.presentation.ui.activity.welcome.WelcomeActivity
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    navController: NavController,
    drawerState: DrawerState,
    navigationDrawerItems: List<NavigationItemUiModel>,
    logoutUseCase: LogoutUseCase,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    fun getCurrentRouteIndex(): Int {
        val currentRoute = navController.currentDestination?.route
        return navigationDrawerItems.indexOfFirst { it.route == currentRoute }
    }

    val selectedItemIndex = remember { mutableIntStateOf(getCurrentRouteIndex()) }

    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    modifier = Modifier.aspectRatio(1f),
                    painter = painterResource(id = R.drawable.drawer_image),
                    contentDescription = stringResource(id = R.string.drawer_image)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            navigationDrawerItems.forEachIndexed { index, item ->
                if (item.titleResId != R.string.logout) {
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
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val logoutItem = navigationDrawerItems.find { it.titleResId == R.string.logout }
            logoutItem?.let {
                DrawerItem(
                    item = it,
                    isSelected = selectedItemIndex.intValue == navigationDrawerItems.size,
                    textColor = Color.Red,
                    onItemClick = {
                        scope.launch {
                            drawerState.close()

                            selectedItemIndex.intValue = navigationDrawerItems.size

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
}

@Composable
fun DrawerItem(
    item: NavigationItemUiModel,
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
            iconId?.let { painterResource(id = it) }?.let {
                Image(
                    painter = it,
                    contentDescription = stringResource(id = item.titleResId)
                )
            }
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