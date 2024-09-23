package com.aposiamp.smartliving.di

import android.hardware.Sensor
import android.hardware.SensorManager
import com.aposiamp.smartliving.data.source.remote.DeviceApiService
import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.aposiamp.smartliving.domain.repository.DeviceRepository
import com.aposiamp.smartliving.domain.repository.SpaceRepository
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository
import com.aposiamp.smartliving.domain.repository.LocationRepository
import com.aposiamp.smartliving.domain.repository.PlacesRepository
import com.aposiamp.smartliving.domain.repository.RoomRepository
import com.aposiamp.smartliving.domain.repository.UserAccountRepository
import com.aposiamp.smartliving.domain.usecase.location.GetLocationDataUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetBottomNavigationItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetSpaceUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetDropdownMenuItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetNavigationDrawerItemsUseCase
import com.aposiamp.smartliving.domain.usecase.places.GetAutoCompleteSuggestionsUseCase
import com.aposiamp.smartliving.domain.usecase.places.GetLocationFromPlaceIdUseCase
import com.aposiamp.smartliving.domain.usecase.sensor.GetEnvironmentalDataUseCase
import com.aposiamp.smartliving.domain.usecase.sensor.SetEnvironmentalDataUseCase
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase
import com.aposiamp.smartliving.domain.usecase.user.LoginUseCase
import com.aposiamp.smartliving.domain.usecase.user.LogoutUseCase
import com.aposiamp.smartliving.domain.usecase.user.SignUpUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.CheckIfSpaceDataExistsUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.SetSpaceDataUseCase
import com.aposiamp.smartliving.domain.usecase.ValidateAddressProximityUseCase
import com.aposiamp.smartliving.domain.usecase.device.CheckIfDeviceExistsUseCase
import com.aposiamp.smartliving.domain.usecase.device.SetDeviceDataUseCase
import com.aposiamp.smartliving.domain.usecase.device.ValidateDeviceExistence
import com.aposiamp.smartliving.domain.usecase.main.CheckIfAnyRoomExistsUseCase
import com.aposiamp.smartliving.domain.usecase.main.CheckIfUserIsInSpaceUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetRoomListUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetSettingsScreenItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.SetRoomDataUseCase
import com.aposiamp.smartliving.domain.usecase.user.ForgotPasswordUseCase
import com.aposiamp.smartliving.domain.usecase.user.GetAccountDetailsUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateDeviceId
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateDeviceName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateEmail
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateFirstName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateLastName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePassword
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePlaceData
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateRoomId
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateRoomName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateSpaceAddress
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
    val spaceRepository: SpaceRepository
    val roomRepository: RoomRepository
    val deviceRepository: DeviceRepository
    val placesRepository: PlacesRepository
    val userAccountRepository: UserAccountRepository

    // Firebase
    fun getFirebaseAuth(): FirebaseAuth
    fun getFirebaseDatabase(): FirebaseDatabase
    fun getFirestoreDatabase(): FirebaseFirestore

    // Places API
    fun getPlacesClient(): PlacesClient

    // Retrofit API
    fun getRetrofitApi(): DeviceApiService

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

    // Settings UseCases
    val getSettingsScreenItemsUseCase: GetSettingsScreenItemsUseCase

    // Sensor UseCases
    val getEnvironmentalDataUseCase: GetEnvironmentalDataUseCase
    val setEnvironmentalDataUseCase: SetEnvironmentalDataUseCase

    // Location UseCases
    val getLocationDataUseCase: GetLocationDataUseCase
    val getLocationFromPlaceIdUseCase: GetLocationFromPlaceIdUseCase

    // Places UseCases
    val getAutoCompleteSuggestionsUseCase: GetAutoCompleteSuggestionsUseCase
    val validateAddressProximityUseCase: ValidateAddressProximityUseCase

    // Space UseCases
    val setSpaceDataUseCase: SetSpaceDataUseCase
    val getSpaceUseCase: GetSpaceUseCase
    val checkIfSpaceDataExistsUseCase: CheckIfSpaceDataExistsUseCase
    val checkIfUserIsInSpaceUseCase: CheckIfUserIsInSpaceUseCase

    // Room UseCases
    val setRoomDataUseCase: SetRoomDataUseCase
    val getRoomListUseCase: GetRoomListUseCase
    val checkIfAnyRoomExistsUseCase: CheckIfAnyRoomExistsUseCase

    // Device UseCases
    val checkIfDeviceExistsUseCase: CheckIfDeviceExistsUseCase
    val setDeviceDataUseCase: SetDeviceDataUseCase

    // Profile UseCases
    val loginUseCase: LoginUseCase
    val signUpUseCase: SignUpUseCase
    val logoutUseCase: LogoutUseCase
    val forgotPasswordUseCase: ForgotPasswordUseCase
    val getCurrentUserUseCase: GetCurrentUserUseCase
    val getAccountDetailsUseCase: GetAccountDetailsUseCase

    // For SignIn and SignUp screens
    val validateFirstName: ValidateFirstName
    val validateLastName: ValidateLastName
    val validateEmail: ValidateEmail
    val validatePassword: ValidatePassword
    val validateTerms: ValidateTerms
    // For CreateANewSpace screen
    val validateSpaceName: ValidateSpaceName
    val validateSpaceAddress: ValidateSpaceAddress
    val validatePlaceData: ValidatePlaceData
    // For CreateANewRoom screen
    val validateRoomName: ValidateRoomName
    // For AddANewDevice screen
    val validateDeviceName: ValidateDeviceName
    val validateDeviceId: ValidateDeviceId
    val validateRoomId: ValidateRoomId
    val validateDeviceExistence: ValidateDeviceExistence
}