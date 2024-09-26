package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.ThermostatStatusDTO
import com.aposiamp.smartliving.domain.model.ThermostatStatus

object ThermostatStatusMapper {
    fun toDomain(dto: ThermostatStatusDTO): ThermostatStatus {
        return ThermostatStatus(
            state = dto.state,
            mode = dto.mode,
            temperature = dto.temperature
        )
    }
}