package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.source.remote.CleanUpDataSource
import com.aposiamp.smartliving.domain.repository.CleanupRepository

class CleanupRepositoryImpl(
    private val cleanUpDataSource: CleanUpDataSource
): CleanupRepository {
    override suspend fun cleanUpUserData(uid: String): Boolean {
        val response = cleanUpDataSource.cleanUpUserData(uid)
        return response
    }
}