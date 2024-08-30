package com.aposiamp.smartliving.data.source.local

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

class EnvironmentalSensorDataSource(private val context: Context) {
    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val temperatureSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    private val humiditySensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getTemperatureData(): Float? {
        return suspendCancellableCoroutine { continuation ->
            val temperatureListener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    event?.values?.firstOrNull()?.let { temperature ->
                        try {
                            if (continuation.isActive) {
                                continuation.resume(temperature) {
                                    sensorManager.unregisterListener(this)
                                }
                            }
                        } finally {
                            sensorManager.unregisterListener(this)
                        }
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                    // Do nothing
                }
            }

            temperatureSensor?.let {
                sensorManager.registerListener(temperatureListener, it, SensorManager.SENSOR_DELAY_NORMAL)
            } ?: continuation.resume(null) {
                sensorManager.unregisterListener(temperatureListener)
            }

            continuation.invokeOnCancellation {
                sensorManager.unregisterListener(temperatureListener)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getHumidityData(): Float? {
        return suspendCancellableCoroutine { continuation ->
            val humidityListener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    event?.values?.firstOrNull()?.let { humidity ->
                        try {
                            if (continuation.isActive) {
                                continuation.resume(humidity) {
                                    sensorManager.unregisterListener(this)
                                }
                            }
                        } finally {
                            sensorManager.unregisterListener(this)
                        }
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                    // Do nothing
                }
            }

            humiditySensor?.let {
                sensorManager.registerListener(humidityListener, it, SensorManager.SENSOR_DELAY_NORMAL)
            } ?: continuation.resume(null) {
                sensorManager.unregisterListener(humidityListener)
            }

            continuation.invokeOnCancellation {
                sensorManager.unregisterListener(humidityListener)
            }
        }
    }
}