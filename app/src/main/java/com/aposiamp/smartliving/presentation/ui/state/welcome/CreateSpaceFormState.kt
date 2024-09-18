package com.aposiamp.smartliving.presentation.ui.state.welcome

data class CreateSpaceFormState(
    val spaceName: String = "",
    val spaceNameError: String? = null,
    val spaceAddress: String = "",
    val spaceAddressError: String? = null,
    val spaceId: String = "",
    val spaceIdError: String? = null
)
