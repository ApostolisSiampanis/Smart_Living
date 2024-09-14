package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.AutoCompletePredictionData
import com.aposiamp.smartliving.data.utils.await
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient

class PlacesDataSource(
    private val placesClient: PlacesClient
) {
    suspend fun getAutocompletePredictions(query: String): List<AutoCompletePredictionData> {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()
        val response = placesClient.findAutocompletePredictions(request).await()

        return response.autocompletePredictions.map {
            AutoCompletePredictionData(
                placeId = it.placeId,
                fullAddress = it.getFullText(null).toString()
            )
        }
    }
}