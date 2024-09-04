package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.domain.model.EnvironmentalData
import com.aposiamp.smartliving.presentation.model.EnvironmentalDataUiModel

object EnvironmentalDataUiMapper {
    fun fromDomain(domain: EnvironmentalData): EnvironmentalDataUiModel {
        return EnvironmentalDataUiModel(
            temperature = domain.temperature,
            humidity = domain.humidity
        )
    }
}