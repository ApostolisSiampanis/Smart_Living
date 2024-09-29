package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.model.Period
import com.aposiamp.smartliving.domain.model.PeriodDropdownItemData

class GetPeriodItemsUseCase {
    fun execute(): List<PeriodDropdownItemData> {
        return listOf(
            PeriodDropdownItemData(Period.WEEK),
            PeriodDropdownItemData(Period.MONTH),
            PeriodDropdownItemData(Period.YEAR)
        )
    }
}