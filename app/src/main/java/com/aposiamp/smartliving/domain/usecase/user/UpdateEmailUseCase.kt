package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.repository.AuthRepository

class UpdateEmailUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String) {
        authRepository.updateEmail(email)
    }
}