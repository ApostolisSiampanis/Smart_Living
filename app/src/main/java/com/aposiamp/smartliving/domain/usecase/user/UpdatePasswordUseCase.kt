package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.repository.AuthRepository

class UpdatePasswordUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(newPassword: String) {
        authRepository.updatePassword(newPassword)
    }
}