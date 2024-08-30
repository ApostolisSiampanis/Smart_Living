package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.utils.await
import com.aposiamp.smartliving.data.model.EnvironmentalDataDTO
import com.aposiamp.smartliving.data.model.UserFirestore
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDataSource(private val db: FirebaseFirestore) {

    suspend fun setUserProfile(uid: String, user: UserFirestore) {
        db.collection("users").document(uid).set(user.toMap()).await()
    }

    suspend fun setEnvironmentalData(uid: String, environmentalDataDTO: EnvironmentalDataDTO) {
        db.collection("environmental_data")
            .document(uid)
            .collection(environmentalDataDTO.timestamp.toString())
            .document("data")
            .set(mapOf(
                "temperature" to environmentalDataDTO.temperature,
                "humidity" to environmentalDataDTO.humidity
        )).await()
    }
}