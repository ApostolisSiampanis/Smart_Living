package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseDataSource.currentUser

    override suspend fun login(email: String, password: String): FirebaseUser {
        return firebaseDataSource.login(email, password)
    }

    override suspend fun signup(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser {
        return firebaseDataSource.signup(email, password)
    }

    override fun logout() {
        firebaseDataSource.logout()
    }
}