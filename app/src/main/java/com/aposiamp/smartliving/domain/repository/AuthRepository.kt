package com.aposiamp.smartliving.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun login(
        email: String,
        password: String
    ): FirebaseUser

    suspend fun signup(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser

    fun logout()

    suspend fun forgotPassword(email: String)

    suspend fun updateEmail(email: String)

    suspend fun updateFirstName(uid: String, firstName: String)

    suspend fun updateLastName(uid: String, lastName: String)

    suspend fun updatePassword(password: String)

    suspend fun deleteUser()
}