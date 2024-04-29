package com.aposiamp.smartliving.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.ViewComfy
import androidx.compose.material.icons.outlined.EnergySavingsLeaf
import androidx.compose.material.icons.outlined.PlayCircleOutline
import androidx.compose.material.icons.outlined.ViewComfy
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.aposiamp.smartliving.R

@Composable
fun BottomBar() {
    val items = listOf(
        BottomNavigationItem(
            title = stringResource(id = R.string.devices),
            selectedIcon = Icons.Filled.ViewComfy,
            unselectedIcon = Icons.Outlined.ViewComfy,
            route = "devices"
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.routines),
            selectedIcon = Icons.Filled.PlayCircleFilled,
            unselectedIcon = Icons.Outlined.PlayCircleOutline,
            route = "routines"
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.energy),
            selectedIcon = Icons.Filled.EnergySavingsLeaf,
            unselectedIcon = Icons.Outlined.EnergySavingsLeaf,
            route = "energy"
        )
    )
    var selectedItemIndex by rememberSaveable{ mutableStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    //TODO: navigate to the route (item.route)
                },
                label = {
                    Text(
                        text = item.title,
                        fontFamily = FontFamily(Font(R.font.carlito_regular))
                    )
                },
                icon = {
                    Icon(
                        imageVector = if(index == selectedItemIndex) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}