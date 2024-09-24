package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.repository.AuthRepository

class DeleteUserUseCase(
    private val authRepository: AuthRepository
) {
    suspend fun execute() {
        authRepository.deleteUser()
    }
}