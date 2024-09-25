package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.domain.model.CleanupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CleanUpApiService {
    @POST("/")
    suspend fun cleanUpUserData(@Body request: CleanupRequest): Response<Unit>
}