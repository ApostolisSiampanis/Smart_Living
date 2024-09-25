package com.aposiamp.smartliving.domain.repository

interface CleanupRepository {
    suspend fun cleanUpUserData(uid: String): Boolean
}