package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidateRoomName(private val context: Context) {
    fun execute(roomName: String): ValidateResult {
        if (roomName.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_room_name_can_not_be_blank)
            )
        }
        if (roomName.length < 3) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.the_room_name_must_be_at_least_3_characters)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}