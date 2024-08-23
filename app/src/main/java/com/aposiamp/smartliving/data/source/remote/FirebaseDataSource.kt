package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseDataSource(
    private val firebaseAuth: FirebaseAuth
) {
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    suspend fun login(email: String, password: String): FirebaseUser {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Login failed")
    }

    suspend fun signup(email: String, password: String): FirebaseUser {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Signup failed")
    }

    fun logout() {
        firebaseAuth.signOut()
    }
}