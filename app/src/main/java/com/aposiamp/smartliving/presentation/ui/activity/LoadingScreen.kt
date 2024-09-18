package com.aposiamp.smartliving.presentation.ui.activity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.aposiamp.smartliving.presentation.ui.component.ProgressIndicatorComponent

@Composable
fun LoadingScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ProgressIndicatorComponent()
    }
}