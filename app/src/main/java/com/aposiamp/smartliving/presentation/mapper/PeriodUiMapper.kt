package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.model.Period
import com.aposiamp.smartliving.domain.model.PeriodDropdownItemData
import com.aposiamp.smartliving.presentation.model.PeriodItemUiModel

object PeriodUiMapper {
    private fun toUiModel(domain: PeriodDropdownItemData): PeriodItemUiModel {
        val resId = when (domain.period) {
            Period.WEEK -> R.string.last_week
            Period.MONTH -> R.string.last_month
            Period.YEAR -> R.string.last_year
        }
        return PeriodItemUiModel(
            textResId = resId,
            period = domain.period
        )
    }

    fun List<PeriodDropdownItemData>.toUiModelList(): List<PeriodItemUiModel> {
        return this.map { toUiModel(it) }
    }
}