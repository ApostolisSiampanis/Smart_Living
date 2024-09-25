package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.UserFirestore
import com.aposiamp.smartliving.data.source.remote.FirestoreDataSource
import com.aposiamp.smartliving.domain.repository.UserAccountRepository

class AccountProfileRepositoryImpl(
    private val firestoreDataSource: FirestoreDataSource
) : UserAccountRepository {
    override suspend fun getUserAccountProfile(userId: String): UserFirestore {
        return firestoreDataSource.getUserAccountProfile(userId)
    }
}