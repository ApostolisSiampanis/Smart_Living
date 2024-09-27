package com.aposiamp.smartliving.presentation.model

import androidx.annotation.StringRes
import com.aposiamp.smartliving.domain.model.Period

data class PeriodItemUiModel(
    @StringRes val textResId: Int,
    val period: Period
)
