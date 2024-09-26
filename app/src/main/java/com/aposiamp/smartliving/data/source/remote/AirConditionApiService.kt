package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.AirDirectionDTO
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
}