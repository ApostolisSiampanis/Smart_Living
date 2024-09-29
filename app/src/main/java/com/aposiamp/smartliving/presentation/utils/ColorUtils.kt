package com.aposiamp.smartliving.presentation.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

object ColorUtils {
    fun generateRandomColor(): Color {
        return Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat(),
            alpha = 1.0f
        )
    }
}