package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.SpaceDataDTO
import com.aposiamp.smartliving.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class FirebaseDataSource(
    private val firebaseAuth: FirebaseAuth,
    private val firebase: FirebaseDatabase
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

    suspend fun setDevicesSpaceData(userId: String, spaceDataDTO: SpaceDataDTO) {
        firebase.getReference("devices").child(userId).setValue(spaceDataDTO).await()
    }
}