package com.aposiamp.smartliving.di

import android.hardware.Sensor
import android.hardware.SensorManager
import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.aposiamp.smartliving.domain.repository.DeviceAndSpaceRepository
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository
import com.aposiamp.smartliving.domain.repository.LocationRepository
import com.aposiamp.smartliving.domain.repository.PlacesRepository
import com.aposiamp.smartliving.domain.usecase.location.GetLocationDataUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetBottomNavigationItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetDevicesSpaceNameUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetDropdownMenuItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetNavigationDrawerItemsUseCase
import com.aposiamp.smartliving.domain.usecase.places.GetAutoCompleteSuggestionsUseCase
import com.aposiamp.smartliving.domain.usecase.sensor.GetEnvironmentalDataUseCase
import com.aposiamp.smartliving.domain.usecase.sensor.SetEnvironmentalDataUseCase
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase
import com.aposiamp.smartliving.domain.usecase.user.LoginUseCase
import com.aposiamp.smartliving.domain.usecase.user.LogoutUseCase
import com.aposiamp.smartliving.domain.usecase.user.SignUpUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.CheckIfSpaceDataExistsUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.SetSpaceDataUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateEmail
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateFirstName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateLastName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePassword
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateSpaceName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateTerms
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

interface AppModule {
    // Repositories
    val authRepository: AuthRepository
    val environmentalSensorRepository: EnvironmentalSensorRepository
    val locationRepository: LocationRepository
    val deviceAndSpaceRepository: DeviceAndSpaceRepository
    val placesRepository: PlacesRepository

    // Firebase
    fun getFirebaseAuth(): FirebaseAuth
    fun getFirebaseDatabase(): FirebaseDatabase
    fun getFirestoreDatabase(): FirebaseFirestore

    // Places API
    fun getPlacesClient(): PlacesClient

    // SensorManager and Sensors
    fun getSensorManager(): SensorManager
    fun getTemperatureSensor(): Sensor?
    fun getHumiditySensor(): Sensor?

    // FusedLocationProviderClient
    fun getFusedLocationProviderClient(): FusedLocationProviderClient

    // NavigationDrawer UseCase
    val getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase

    // BottomMenu UseCase
    val getBottomMenuItemsUseCase: GetBottomNavigationItemsUseCase

    // DropDownMenu UseCase
    val getDropdownMenuItemsUseCase: GetDropdownMenuItemsUseCase

    // Sensor UseCases
    val getEnvironmentalDataUseCase: GetEnvironmentalDataUseCase
    val setEnvironmentalDataUseCase: SetEnvironmentalDataUseCase

    // Location UseCases
    val getLocationDataUseCase: GetLocationDataUseCase

    // Places UseCases
    val getAutoCompleteSuggestionsUseCase: GetAutoCompleteSuggestionsUseCase

    // Space UseCases
    val setSpaceDataUseCase: SetSpaceDataUseCase
    val getDevicesSpaceNameUseCase: GetDevicesSpaceNameUseCase
    val checkIfSpaceDataExistsUseCase: CheckIfSpaceDataExistsUseCase

    // Profile UseCases
    val loginUseCase: LoginUseCase
    val signUpUseCase: SignUpUseCase
    val logoutUseCase: LogoutUseCase
    val getCurrentUserUseCase: GetCurrentUserUseCase

    // For SignIn and SignUp screens
    val validateFirstName: ValidateFirstName
    val validateLastName: ValidateLastName
    val validateEmail: ValidateEmail
    val validatePassword: ValidatePassword
    val validateTerms: ValidateTerms
    // For CreateANewSpace screen
    val validateSpaceName: ValidateSpaceName
}