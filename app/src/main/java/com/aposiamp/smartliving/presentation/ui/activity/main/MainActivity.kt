package com.aposiamp.smartliving.presentation.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aposiamp.smartliving.app.SmartLivingApp
import com.aposiamp.smartliving.presentation.ui.theme.SmartLivingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartLivingTheme {
                SmartLivingApp()
            }
        }
    }
}