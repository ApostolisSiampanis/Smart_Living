package com.aposiamp.smartliving.data.repository

import com.aposiamp.smartliving.data.model.UserFirestore
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.data.source.remote.FirestoreDataSource
import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.aposiamp.smartliving.data.utils.DateUtils
import com.google.firebase.auth.FirebaseUser

class AuthRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource,
    private val firestoreDataSource: FirestoreDataSource
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
        val user = firebaseDataSource.signup(email, password)
        val uid = user.uid
        val userProfile = UserFirestore(
            firstName = firstName,
            lastName = lastName,
            dateRegistered = DateUtils.getCurrentDateUTC()
        )
        firestoreDataSource.setUserProfile(uid, userProfile)
        return user
    }

    override fun logout() {
        firebaseDataSource.logout()
    }

    override suspend fun forgotPassword(email: String) {
        firebaseDataSource.forgotPassword(email)
    }
}