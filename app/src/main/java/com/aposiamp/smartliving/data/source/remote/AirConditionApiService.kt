package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.AirDirectionDTO
import com.aposiamp.smartliving.data.model.FanSpeedDTO
import com.aposiamp.smartliving.data.model.TemperatureDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AirConditionApiService {
    @PATCH("/air-conditions/{id}/air-direction")
    suspend fun updateAirDirection(
        @Path("id") airConditionId: String,
        @Body airDirection: AirDirectionDTO
    ): Response<Unit>

    @PATCH("/air-conditions/{id}/fan-speed")
    suspend fun updateFanSpeed(
        @Path("id") airConditionId: String,
        @Body fanSpeed: FanSpeedDTO
    ): Response<Unit>

    @PATCH("/air-conditions/{id}/temperature")
    suspend fun updateTemperature(
        @Path("id") airConditionId: String,
        @Body temperature: TemperatureDTO
    ): Response<Unit>
}