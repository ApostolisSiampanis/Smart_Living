package com.aposiamp.smartliving.data.model

import com.google.firebase.database.PropertyName

data class SpaceDataDTO(
    @get:PropertyName("space_name") @set:PropertyName("space_name") var spaceName: String? = null
)
