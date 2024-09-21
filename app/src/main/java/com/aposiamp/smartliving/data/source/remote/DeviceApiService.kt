package com.aposiamp.smartliving.data.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DeviceApiService {
    @GET("/devices/id-and-type/{id}/{type}")
    suspend fun checkIfDeviceExists(
        @Path("id") deviceId: String,
        @Path("type") deviceType: String
    ): Response<Unit>
}