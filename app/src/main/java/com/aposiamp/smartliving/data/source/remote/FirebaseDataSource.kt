package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.SpaceDataDTO
import com.aposiamp.smartliving.data.utils.await
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

    suspend fun setSpaceData(userId: String, spaceDataDTO: SpaceDataDTO) {
        firebase.getReference("spaces").child(userId).setValue(spaceDataDTO).await()
    }

    suspend fun getSpaceData(userId: String): SpaceDataDTO {
        val snapshot = firebase.getReference("spaces").child(userId).get().await()
        val spaceDataDTO = snapshot.getValue(SpaceDataDTO::class.java)
        return spaceDataDTO ?: throw Exception("Space data not found")
    }

    suspend fun checkIfSpaceDataExists(userId: String): Boolean {
        val snapshot = firebase.getReference("spaces").child(userId).get().await()
        return snapshot.exists()
    }
}