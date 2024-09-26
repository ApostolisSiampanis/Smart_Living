package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.TemperatureDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ThermostatApiService {
    @PATCH("/thermostats/{id}/temperature")
    suspend fun updateTemperature(
        @Path("id") thermostatId: String,
        @Body temperature: TemperatureDTO
    ): Response<Unit>
}