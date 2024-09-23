package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.domain.model.UserAccount
import com.aposiamp.smartliving.presentation.model.UserAccountUiModel

object UserAccountUiMapper {
    fun fromDomain(domain: UserAccount): UserAccountUiModel {
        return UserAccountUiModel(
            email = domain.email,
            firstName = domain.firstName,
            lastName = domain.lastName
        )
    }
}