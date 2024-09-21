package com.aposiamp.smartliving.domain.usecase.welcome.validateregex

import android.content.Context
import com.aposiamp.smartliving.R

class ValidateRoomId(private val context: Context) {
    fun execute(roomId: String): ValidateResult {
        if (roomId.isBlank()) {
            return ValidateResult(
                successful = false,
                errorMessage = context.getString(R.string.you_must_select_a_room)
            )
        }
        return ValidateResult(
            successful = true
        )
    }
}