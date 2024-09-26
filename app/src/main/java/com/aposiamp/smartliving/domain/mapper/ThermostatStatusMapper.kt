package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.ThermostatStatusDTO
import com.aposiamp.smartliving.domain.model.ThermostatStatusData

object ThermostatStatusMapper {
    fun toDomain(dto: ThermostatStatusDTO): ThermostatStatusData {
        return ThermostatStatusData(
            state = dto.state,
            mode = dto.mode,
            temperature = dto.temperature
        )
    }
}