package com.aposiamp.smartliving.presentation.model

data class NavigationDrawerUiItem(
    val title: String,
    val unselectedIcon: Int,
    val selectedIcon: Int? = null,
    val route: String? = null
)
