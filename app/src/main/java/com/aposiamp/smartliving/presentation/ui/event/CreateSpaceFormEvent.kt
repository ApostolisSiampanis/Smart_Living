package com.aposiamp.smartliving.presentation.ui.event

sealed class CreateSpaceFormEvent{
    data class SpaceNameChanged(val spaceName: String) : CreateSpaceFormEvent()
    object Submit : CreateSpaceFormEvent()
}