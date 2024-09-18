package com.aposiamp.smartliving.data.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtils {
    fun getCurrentDateUTC(): String {
        val now = Instant.now()
        val formatter = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("UTC"))
        return formatter.format(now)
    }
}