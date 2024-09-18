package com.aposiamp.smartliving.presentation.model

data class SpaceDataUiModel(
    val placeId: String?,
    val placeName: String?,
    val spaceName: String?,
    val fullAddress: String?,
    val location: LocationDataUiModel?
)
