package com.aposiamp.smartliving.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object AppSettingsUtils {
    fun openAppSettings(context: Context) {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", context.packageName, null)
            data = uri
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}