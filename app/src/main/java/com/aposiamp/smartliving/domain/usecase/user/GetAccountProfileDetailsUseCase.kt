package com.aposiamp.smartliving.domain.usecase.user

import com.aposiamp.smartliving.domain.mapper.AccountProfileMapper
import com.aposiamp.smartliving.domain.model.UserAccount
import com.aposiamp.smartliving.domain.repository.UserAccountRepository

class GetAccountProfileDetailsUseCase(
    private val userAccountRepository: UserAccountRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend fun execute(): UserAccount {
        val currentUser = getCurrentUserUseCase.execute()
        val userId = currentUser?.uid ?: throw Exception("User not logged in")

        val userFirestore = userAccountRepository.getUserAccountProfile(userId)
        val userAccount = AccountProfileMapper.fromFirestore(userFirestore, currentUser)
        return userAccount
    }
}