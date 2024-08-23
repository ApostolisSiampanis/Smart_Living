package com.aposiamp.smartliving

import android.app.Application
import com.aposiamp.smartliving.di.AppModule
import com.aposiamp.smartliving.di.AppModuleImpl

class SmartLiving : Application() {

        companion object {
            lateinit var appModule: AppModule
        }

        override fun onCreate() {
            super.onCreate()
            appModule = AppModuleImpl(this)
        }
}