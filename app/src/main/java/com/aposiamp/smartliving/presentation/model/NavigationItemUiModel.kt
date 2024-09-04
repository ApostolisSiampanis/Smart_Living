package com.aposiamp.smartliving.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavigationItemUiModel(
    @StringRes val titleResId: Int,
    @DrawableRes val unselectedIcon: Int? = null,
    @DrawableRes val selectedIcon: Int? = null,
    val route: String? = null
)
