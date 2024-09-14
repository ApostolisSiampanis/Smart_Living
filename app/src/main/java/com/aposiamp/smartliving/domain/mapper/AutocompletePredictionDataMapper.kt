package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.AutoCompletePredictionData
import com.aposiamp.smartliving.domain.model.AutoCompletePrediction

object AutocompletePredictionDataMapper {
    private fun toDomain(data: AutoCompletePredictionData): AutoCompletePrediction {
        return AutoCompletePrediction(
            placeId = data.placeId,
            fullAddress = data.fullAddress
        )
    }

    fun toDomainList(dataList: List<AutoCompletePredictionData>): List<AutoCompletePrediction> {
        return dataList.map { toDomain(it) }
    }
}