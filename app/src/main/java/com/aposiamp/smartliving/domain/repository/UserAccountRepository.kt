package com.aposiamp.smartliving.domain.repository

import com.aposiamp.smartliving.data.model.UserFirestore

interface UserAccountRepository {
    suspend fun getUserAccountProfile(userId: String): UserFirestore
}