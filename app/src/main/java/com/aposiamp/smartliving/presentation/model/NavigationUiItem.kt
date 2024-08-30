package com.aposiamp.smartliving.presentation.model

import androidx.annotation.StringRes

data class NavigationUiItem(
    @StringRes val titleResId: Int,
    val unselectedIcon: Int,
    val selectedIcon: Int? = null,
    val route: String? = null
)
