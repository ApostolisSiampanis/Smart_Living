package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): FirebaseUser {
        return authRepository.login(email, password)
    }
}