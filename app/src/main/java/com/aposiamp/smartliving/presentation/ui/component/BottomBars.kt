package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.screen.screensInBottom

@Composable
fun BottomBar() {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        screensInBottom.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    //TODO: navigate to the route (item.route)
                },
                label = {
                    Text(
                        text = stringResource(id = item.titleResId),
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
                        contentDescription = stringResource(id = item.titleResId)
                    )
                }
            )
        }
    }
}