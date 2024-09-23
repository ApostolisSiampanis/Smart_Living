package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.utils.await
import com.aposiamp.smartliving.data.model.EnvironmentalDataDTO
import com.aposiamp.smartliving.data.model.UserFirestore
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDataSource(private val db: FirebaseFirestore) {

    suspend fun setUserProfile(uid: String, user: UserFirestore) {
        db.collection("users").document(uid).set(user.toMap()).await()
    }

    suspend fun setEnvironmentalData(uid: String, placeId: String, environmentalDataDTO: EnvironmentalDataDTO) {
        db.collection("environmental_data")
            .document(uid)
            .collection(placeId)
            .document(environmentalDataDTO.timestamp.toString())
            .set(mapOf(
                "temperature" to environmentalDataDTO.temperature,
                "humidity" to environmentalDataDTO.humidity
        )).await()
    }

    suspend fun getUserProfile(uid: String): UserFirestore {
        val result = db.collection("users").document(uid).get().await()
        return result.toObject(UserFirestore::class.java)!!
    }
}