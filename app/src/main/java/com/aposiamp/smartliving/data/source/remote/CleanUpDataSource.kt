package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.domain.model.CleanupRequest

class CleanUpDataSource(
    private val apiService: CleanUpApiService
) {
    suspend fun cleanUpUserData(uid: String): Boolean {
        return try {
            val response = apiService.cleanUpUserData(CleanupRequest(uid))
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}