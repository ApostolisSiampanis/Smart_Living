package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class GetCurrentUserUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(): FirebaseUser? {
        return authRepository.currentUser
    }
}