package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.HumidityLevelDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path

interface DehumidifierApiService {
    @PATCH("/dehumidifiers/{id}/humidity-level")
    suspend fun updateHumidityLevel(
        @Path("id") dehumidifierId: String,
        @Body humidityLevel: HumidityLevelDTO
    ): Response<Unit>
}