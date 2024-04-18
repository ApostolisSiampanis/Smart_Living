package com.aposiamp.smartliving

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aposiamp.smartliving.app.SmartLivingApp
import com.aposiamp.smartliving.ui.theme.SmartLivingTheme

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