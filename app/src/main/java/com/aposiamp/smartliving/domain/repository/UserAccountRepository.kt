package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.UserFirestore

interface UserAccountRepository {
    suspend fun getUserProfile(userId: String): UserFirestore
}