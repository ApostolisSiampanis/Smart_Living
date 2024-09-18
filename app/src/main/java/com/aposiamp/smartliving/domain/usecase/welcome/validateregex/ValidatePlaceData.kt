package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.usecase.ValidateAddressProximityUseCase

class ValidatePlaceData(
    private val context: Context,
    private val validateAddressProximityUseCase: ValidateAddressProximityUseCase
) {
    suspend fun execute(placeId: String?): ValidateResult {
        if (placeId == null) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_space_address_is_not_nearby)
            )
        }

        val placeData = validateAddressProximityUseCase.execute(placeId)
        return if(placeData == null) {
            ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_space_address_is_not_nearby)
            )
        } else {
            ValidateResult(
                successful = true
            )
        }
    }
}