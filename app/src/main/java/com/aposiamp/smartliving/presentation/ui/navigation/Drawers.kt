package com.aposiamp.smartliving.presentation.ui.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.screen.Screen
import com.aposiamp.smartliving.presentation.ui.screen.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(-1)
    }

    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))

        screensInDrawer.forEachIndexed { index, item ->
            DrawerItem(
                item = item,
                index = index,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = { selectedIndex ->
                    selectedItemIndex = selectedIndex
                    scope.launch {
                        //TODO: navigate to the route (item.route)
                        drawerState.close()
                    }
                }
            )
        }
    }
}

@Composable
fun DrawerItem(
    item: Screen.DrawerScreen,
    index: Int,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationDrawerItem(
        label = {
            Text(
                text = stringResource(id = item.titleResId),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.carlito_regular))
            )
        },
        selected = index == selectedItemIndex,
        onClick = { onItemSelected(index) },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = stringResource(id = item.titleResId)
            )
        },
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}