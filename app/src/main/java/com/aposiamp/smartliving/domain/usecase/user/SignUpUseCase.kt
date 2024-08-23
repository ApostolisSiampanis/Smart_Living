package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class SignUpUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser {
        return authRepository.signup(firstName, lastName, email, password)
    }
}