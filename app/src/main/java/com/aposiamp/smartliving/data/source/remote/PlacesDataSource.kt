package com.aposiamp.smartliving.data.source.remote

import com.aposiamp.smartliving.data.model.AutoCompletePredictionDTO
import com.aposiamp.smartliving.data.model.LocationDataDTO
import com.aposiamp.smartliving.data.model.PlaceDataDTO
import com.aposiamp.smartliving.data.utils.await
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient

class PlacesDataSource(
    private val placesClient: PlacesClient
) {
    suspend fun getAutocompletePredictions(query: String): List<AutoCompletePredictionDTO> {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()
        val response = placesClient.findAutocompletePredictions(request).await()

        return response.autocompletePredictions.map {
            AutoCompletePredictionDTO(
                placeId = it.placeId,
                fullAddress = it.getFullText(null).toString()
            )
        }
    }

    suspend fun getLocationFromPlaceId(placeId: String): PlaceDataDTO {
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.builder(placeId, placeFields).build()
        val response = placesClient.fetchPlace(request).await()

        val place = response.place
        return PlaceDataDTO(
            placeId = place.id,
            name = place.name,
            fullAddress = place.address,
            location = place.latLng?.let {
                LocationDataDTO(
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            }
        )
    }
}