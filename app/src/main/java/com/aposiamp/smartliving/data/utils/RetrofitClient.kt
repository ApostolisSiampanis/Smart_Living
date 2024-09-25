package com.aposiamp.smartliving.data.utils

import com.aposiamp.smartliving.data.source.remote.CleanUpApiService
import com.aposiamp.smartliving.data.source.remote.DeviceApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun createDeviceService(): DeviceApiService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeviceApiService::class.java)
    }

    fun createCleanUpService(): CleanUpApiService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://cleanup-user-data-uz342cmona-uc.a.run.app")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CleanUpApiService::class.java)
    }
}