package com.aposiamp.smartliving.domain.mapper

import com.aposiamp.smartliving.data.model.UserFirestore
import com.aposiamp.smartliving.domain.model.UserAccount
import com.google.firebase.auth.FirebaseUser

object UserAccountMapper {
    fun fromFirestore(userFirestore: UserFirestore, firebaseUser: FirebaseUser): UserAccount {
        return UserAccount(
            email = firebaseUser.email,
            firstName = userFirestore.firstName,
            lastName = userFirestore.lastName
        )
    }
}