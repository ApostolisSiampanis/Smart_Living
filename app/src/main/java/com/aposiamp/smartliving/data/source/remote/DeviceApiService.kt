package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.AirConditionStatusDTO
import com.aposiamp.smartliving.data.model.DehumidifierStatusDTO
import com.aposiamp.smartliving.data.model.DeviceHistoryDTO
import com.aposiamp.smartliving.data.model.DeviceModeDTO
import com.aposiamp.smartliving.data.model.DeviceStateDTO
import com.aposiamp.smartliving.data.model.ThermostatStatusDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface DeviceApiService {
    @GET("/devices/id-and-type/{id}/{type}")
    suspend fun checkIfDeviceExists(
        @Path("id") deviceId: String,
        @Path("type") deviceType: String
    ): Response<Unit>

    @GET("/devices/{id}")
    suspend fun getThermostatStatus(
        @Path("id") deviceId: String
    ): Response<ThermostatStatusDTO>

    @GET("/devices/{id}")
    suspend fun getAirConditionStatus(
        @Path("id") deviceId: String
    ): Response<AirConditionStatusDTO>

    @GET("/devices/{id}")
    suspend fun getDehumidifierStatus(
        @Path("id") deviceId: String
    ): Response<DehumidifierStatusDTO>

    @PATCH("/devices/{id}/state")
    suspend fun updateDeviceState(
        @Path("id") deviceId: String,
        @Body deviceState: DeviceStateDTO
    ): Response<DeviceHistoryDTO?>

    @PATCH("/devices/{id}/mode")
    suspend fun updateDeviceMode(
        @Path("id") deviceId: String,
        @Body deviceMode: DeviceModeDTO
    ): Response<Unit>
}