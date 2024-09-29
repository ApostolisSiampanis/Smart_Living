package com.aposiamp.smartliving.presentation.ui.activity.main.navigation

sealed class MainDestination(val route: String) {
    data object NotInSpace : MainDestination("notInSpace")
    data object Devices : MainDestination("devices")
    data object Energy : MainDestination("energy")
    data object CreateANewRoom : MainDestination("createANewRoom")
    data object AddANewDevice : MainDestination("addANewDevice")
    data object Settings : MainDestination("settings")
    data object Profile : MainDestination("profile")
    data object Account : MainDestination("account")
    data object About : MainDestination("about")
    data object Thermostat : MainDestination("thermostat")
    data object AirCondition : MainDestination("airCondition")
    data object Dehumidifier : MainDestination("dehumidifier")
}