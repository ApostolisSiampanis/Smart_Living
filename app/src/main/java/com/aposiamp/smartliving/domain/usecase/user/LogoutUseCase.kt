package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {
    fun execute() {
        return authRepository.logout()
    }
}