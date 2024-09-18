package com.aposiamp.smartliving.domain.usecase.main

import com.aposiamp.smartliving.domain.usecase.ValidateAddressProximityUseCase

class CheckIfUserIsInSpaceUseCase(
    private val validateAddressProximityUseCase: ValidateAddressProximityUseCase
) {
    suspend fun execute(placeId: String): Boolean {
        val isUserInSpace = validateAddressProximityUseCase.execute(placeId)
        return isUserInSpace != null
    }
}