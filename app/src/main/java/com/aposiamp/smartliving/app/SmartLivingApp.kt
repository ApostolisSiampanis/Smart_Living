package com.aposiamp.smartliving.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aposiamp.smartliving.ui.screen.auth.SignUpScreen

@Composable
fun SmartLivingApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SignUpScreen()
    }
}