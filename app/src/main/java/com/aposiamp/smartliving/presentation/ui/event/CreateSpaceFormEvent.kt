package com.aposiamp.smartliving.presentation.ui.event

sealed class CreateSpaceFormEvent{
    data class SpaceNameChanged(val spaceName: String) : CreateSpaceFormEvent()
    data class SpaceAddressChanged(val spaceAddress: String) : CreateSpaceFormEvent()
    data class PlaceIdSelected(val placeId: String) : CreateSpaceFormEvent()
    data object Submit : CreateSpaceFormEvent()
}