package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.await
import com.aposiamp.smartliving.data.model.UserFirestore
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDataSource(val db: FirebaseFirestore) {

    suspend fun setUserProfile(uid: String, user: UserFirestore) {
        db.collection("users").document(uid).set(user.toMap()).await()
    }
}