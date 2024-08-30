package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.EnvironmentalDataDTO

interface EnvironmentalSensorRepository {
    suspend fun getEnvironmentalData(): EnvironmentalDataDTO
    suspend fun setEnvironmentalData(uid: String, environmentalDataDTO: EnvironmentalDataDTO)
}