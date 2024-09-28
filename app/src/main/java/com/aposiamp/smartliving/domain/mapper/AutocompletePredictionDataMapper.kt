package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.AutoCompletePredictionDTO
import com.aposiamp.smartliving.domain.model.AutoCompletePrediction

object AutocompletePredictionDataMapper {
    private fun toDomain(data: AutoCompletePredictionDTO): AutoCompletePrediction {
        return AutoCompletePrediction(
            placeId = data.placeId,
            fullAddress = data.fullAddress
        )
    }

    fun toDomainList(dataList: List<AutoCompletePredictionDTO>): List<AutoCompletePrediction> {
        return dataList.map { toDomain(it) }
    }
}