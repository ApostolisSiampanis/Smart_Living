package com.aposiamp.smartliving.presentation.mapper

import com.aposiamp.smartliving.domain.model.UserAccount
import com.aposiamp.smartliving.presentation.model.AccountProfileUiModel

object UserAccountUiMapper {
    fun fromDomain(domain: UserAccount): AccountProfileUiModel {
        return AccountProfileUiModel(
            email = domain.email,
            firstName = domain.firstName,
            lastName = domain.lastName
        )
    }
}