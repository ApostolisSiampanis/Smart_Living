package com.aposiamp.smartliving.data.model

import com.google.firebase.firestore.PropertyName

data class UserFirestore(
    @get:PropertyName("first_name") @set:PropertyName("first_name") var firstName: String? = null,
    @get:PropertyName("last_name") @set:PropertyName("last_name") var lastName: String? = null,
    @get:PropertyName("date_registered") @set:PropertyName("date_registered") var dateRegistered: String? = null
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "first_name" to firstName,
            "last_name" to lastName,
            "date_registered" to dateRegistered
        )
    }
}
