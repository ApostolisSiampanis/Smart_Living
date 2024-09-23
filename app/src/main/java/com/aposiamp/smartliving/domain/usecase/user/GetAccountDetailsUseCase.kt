package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.mapper.UserAccountMapper
import com.aposiamp.smartliving.domain.model.UserAccount
import com.aposiamp.smartliving.domain.repository.UserAccountRepository

class GetAccountDetailsUseCase(
    private val userAccountRepository: UserAccountRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(): UserAccount {
        val currentUser = getCurrentUserUseCase.execute()
        val userId = currentUser?.uid ?: throw Exception("User not logged in")

        val userFirestore = userAccountRepository.getUserProfile(userId)
        val userAccount = UserAccountMapper.fromFirestore(userFirestore, currentUser)
        return userAccount
    }
}