package com.aposiamp.smartliving.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SettingsItemUiModel(
    @StringRes val titleResId: Int,
    @DrawableRes val icon: Int? = null,
    val route: String? = null
)
