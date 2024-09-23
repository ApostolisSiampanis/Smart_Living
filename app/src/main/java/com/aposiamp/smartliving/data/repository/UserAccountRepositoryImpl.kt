package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.UserFirestore
import com.aposiamp.smartliving.data.source.remote.FirestoreDataSource
import com.aposiamp.smartliving.domain.repository.UserAccountRepository

class UserAccountRepositoryImpl(
    private val firestoreDataSource: FirestoreDataSource
) : UserAccountRepository {
    override suspend fun getUserProfile(userId: String): UserFirestore {
        return firestoreDataSource.getUserProfile(userId)
    }
}