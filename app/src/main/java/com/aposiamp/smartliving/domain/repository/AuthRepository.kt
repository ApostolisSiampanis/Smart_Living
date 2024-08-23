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
}