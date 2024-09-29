package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.domain.model.Period

object PeriodMapper {
    private val periodMap = mapOf(
        Period.WEEK to "last_week",
        Period.MONTH to "last_month",
        Period.YEAR to "last_year"
    )

    fun toCollectionName(period: Period): String {
        return periodMap[period] ?: throw IllegalArgumentException("Invalid period")
    }
}