package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.AutoCompletePredictionDTO
import com.aposiamp.smartliving.domain.model.AutoCompletePredictionData

object AutocompletePredictionDataMapper {
    private fun toDomain(data: AutoCompletePredictionDTO): AutoCompletePredictionData {
        return AutoCompletePredictionData(
            placeId = data.placeId,
            fullAddress = data.fullAddress
        )
    }

    fun toDomainList(dataList: List<AutoCompletePredictionDTO>): List<AutoCompletePredictionData> {
        return dataList.map { toDomain(it) }
    }
}