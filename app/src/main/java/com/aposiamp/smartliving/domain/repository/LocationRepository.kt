package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.LocationDataDTO

interface LocationRepository {
    suspend fun getLocationData(): LocationDataDTO
}