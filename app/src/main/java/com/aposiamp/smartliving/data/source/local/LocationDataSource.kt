package com.aposiamp.smartliving.data.source.local

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationDataSource(
    private val context: Context,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) {
    suspend fun getCurrentLocation(): Location? {
        return suspendCoroutine { continuation ->  
            if (hasNotLocationPermission()) {
                continuation.resume(null)
                return@suspendCoroutine
            }
            try {
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            continuation.resume(location)
                        } else {
                            requestNewLocation(continuation)
                        }
                    }
                    .addOnFailureListener {
                        requestNewLocation(continuation)
                    }
            } catch (e: SecurityException) {
                continuation.resume(null)
            }
        }
    }

    private fun requestNewLocation(continuation: Continuation<Location?>) {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
            .setMaxUpdates(1)
            .build()

        try {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        val location = p0.lastLocation
                        continuation.resume(location)
                        fusedLocationProviderClient.removeLocationUpdates(this)
                    }
                },
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            continuation.resume(null)
        }
    }

    private fun hasNotLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
    }
}