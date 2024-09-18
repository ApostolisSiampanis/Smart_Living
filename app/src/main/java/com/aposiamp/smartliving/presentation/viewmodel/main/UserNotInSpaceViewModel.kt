package com.aposiamp.smartliving.presentation.viewmodel.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.aposiamp.smartliving.domain.usecase.user.LogoutUseCase
import com.aposiamp.smartliving.presentation.ui.activity.welcome.WelcomeActivity

class UserNotInSpaceViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    fun logout(context: Context) {
        logoutUseCase.execute()

        val intent = Intent(context, WelcomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }
}