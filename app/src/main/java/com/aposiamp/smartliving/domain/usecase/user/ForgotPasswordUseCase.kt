package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.repository.AuthRepository

class ForgotPasswordUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String) {
        authRepository.forgotPassword(email)
    }
}