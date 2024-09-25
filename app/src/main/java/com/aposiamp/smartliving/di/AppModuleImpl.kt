package com.aposiamp.smartliving.di

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import com.aposiamp.smartliving.BuildConfig
import com.aposiamp.smartliving.data.repository.AuthRepositoryImpl
import com.aposiamp.smartliving.data.repository.DeviceRepositoryImpl
import com.aposiamp.smartliving.data.repository.SpaceRepositoryImpl
import com.aposiamp.smartliving.data.repository.EnvironmentalSensorRepositoryImpl
import com.aposiamp.smartliving.data.repository.LocationRepositoryImpl
import com.aposiamp.smartliving.data.repository.PlacesRepositoryImpl
import com.aposiamp.smartliving.data.repository.RoomRepositoryImpl
import com.aposiamp.smartliving.data.repository.AccountProfileRepositoryImpl
import com.aposiamp.smartliving.data.repository.CleanupRepositoryImpl
import com.aposiamp.smartliving.data.source.local.EnvironmentalSensorDataSource
import com.aposiamp.smartliving.data.source.local.LocationDataSource
import com.aposiamp.smartliving.data.source.remote.CleanUpApiService
import com.aposiamp.smartliving.data.source.remote.CleanUpDataSource
import com.aposiamp.smartliving.data.source.remote.DeviceApiService
import com.aposiamp.smartliving.data.source.remote.DeviceDataSource
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.data.source.remote.FirestoreDataSource
import com.aposiamp.smartliving.data.source.remote.PlacesDataSource
import com.aposiamp.smartliving.data.utils.RetrofitClient
import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.aposiamp.smartliving.domain.repository.CleanupRepository
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
import com.aposiamp.smartliving.domain.usecase.devices.CheckIfDeviceExistsUseCase
import com.aposiamp.smartliving.domain.usecase.devices.GetDeviceListUseCase
import com.aposiamp.smartliving.domain.usecase.devices.SetDeviceDataUseCase
import com.aposiamp.smartliving.domain.usecase.devices.ValidateDeviceExistence
import com.aposiamp.smartliving.domain.usecase.main.CheckIfAnyRoomExistsUseCase
import com.aposiamp.smartliving.domain.usecase.main.CheckIfUserIsInSpaceUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetRoomListUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetSettingsScreenItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.SetRoomDataUseCase
import com.aposiamp.smartliving.domain.usecase.user.CleanupUserDataUseCase
import com.aposiamp.smartliving.domain.usecase.user.DeleteUserUseCase
import com.aposiamp.smartliving.domain.usecase.user.ForgotPasswordUseCase
import com.aposiamp.smartliving.domain.usecase.user.GetAccountProfileDetailsUseCase
import com.aposiamp.smartliving.domain.usecase.user.UpdateEmailUseCase
import com.aposiamp.smartliving.domain.usecase.user.UpdateFirstNameUseCase
import com.aposiamp.smartliving.domain.usecase.user.UpdateLastNameUseCase
import com.aposiamp.smartliving.domain.usecase.user.UpdatePasswordUseCase
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
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class AppModuleImpl(private val appContext: Context): AppModule {
    // DataSources
    private val firebaseDataSource = FirebaseDataSource(getFirebaseAuth(), getFirebaseDatabase())
    private val firestoreDataSource = FirestoreDataSource(getFirestoreDatabase())
    private val environmentalSensorDataSource = EnvironmentalSensorDataSource(getSensorManager(), getTemperatureSensor(), getHumiditySensor())
    private val locationDataSource = LocationDataSource(appContext, getFusedLocationProviderClient())
    private val deviceDataSource = DeviceDataSource(getDeviceRetrofitApi())
    private val placesDataSource = PlacesDataSource(getPlacesClient())
    private val cleanUpDataSource = CleanUpDataSource(getCleanUpRetrofitApi())

    // Repositories
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(firebaseDataSource, firestoreDataSource)
    }

    override val environmentalSensorRepository: EnvironmentalSensorRepository by lazy {
        EnvironmentalSensorRepositoryImpl(environmentalSensorDataSource, firestoreDataSource)
    }

    override val locationRepository: LocationRepository by lazy {
        LocationRepositoryImpl(locationDataSource)
    }

    override val spaceRepository: SpaceRepository by lazy {
        SpaceRepositoryImpl(firebaseDataSource)
    }

    override val roomRepository: RoomRepository by lazy {
        RoomRepositoryImpl(firebaseDataSource)
    }

    override val deviceRepository: DeviceRepository by lazy {
        DeviceRepositoryImpl(deviceDataSource, firebaseDataSource)
    }

    override val placesRepository: PlacesRepository by lazy {
        PlacesRepositoryImpl(placesDataSource)
    }

    override val userAccountRepository: UserAccountRepository by lazy {
        AccountProfileRepositoryImpl(firestoreDataSource)
    }

    override val cleanupRepository: CleanupRepository by lazy {
        CleanupRepositoryImpl(cleanUpDataSource)
    }

    // Firebase
    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override fun getFirebaseDatabase() : FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override fun getFirestoreDatabase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    // Places API
    override fun getPlacesClient(): PlacesClient {
        Places.initialize(appContext, BuildConfig.MAPS_API_KEY)
        return Places.createClient(appContext)
    }

    // Retrofit API
    override fun getDeviceRetrofitApi(): DeviceApiService {
        return RetrofitClient.createDeviceService()
    }

    override fun getCleanUpRetrofitApi(): CleanUpApiService {
        return RetrofitClient.createCleanUpService()
    }

    // SensorManager and Sensors
    override fun getSensorManager(): SensorManager {
        return appContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun getTemperatureSensor(): Sensor? {
        return getSensorManager().getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    }

    override fun getHumiditySensor(): Sensor? {
        return getSensorManager().getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
    }

    // FusedLocationProviderClient
    override fun getFusedLocationProviderClient(): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(appContext)
    }

    // NavigationDrawer UseCase
    override val getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase by lazy {
        GetNavigationDrawerItemsUseCase()
    }

    // BottomMenu UseCase
    override val getBottomMenuItemsUseCase: GetBottomNavigationItemsUseCase by lazy {
        GetBottomNavigationItemsUseCase()
    }

    // DropDownMenu UseCase
    override val getDropdownMenuItemsUseCase: GetDropdownMenuItemsUseCase by lazy {
        GetDropdownMenuItemsUseCase()
    }

    // Settings UseCases
    override val getSettingsScreenItemsUseCase: GetSettingsScreenItemsUseCase by lazy {
        GetSettingsScreenItemsUseCase()
    }

    // Sensor UseCases
    override val getEnvironmentalDataUseCase: GetEnvironmentalDataUseCase by lazy {
        GetEnvironmentalDataUseCase(environmentalSensorRepository)
    }

    override val setEnvironmentalDataUseCase: SetEnvironmentalDataUseCase by lazy {
        SetEnvironmentalDataUseCase(environmentalSensorRepository, getCurrentUserUseCase)
    }

    // Location UseCases
    override val getLocationDataUseCase: GetLocationDataUseCase by lazy {
        GetLocationDataUseCase(locationRepository)
    }

    override val getLocationFromPlaceIdUseCase: GetLocationFromPlaceIdUseCase by lazy {
        GetLocationFromPlaceIdUseCase(placesRepository)
    }

    // Places UseCases
    override val getAutoCompleteSuggestionsUseCase: GetAutoCompleteSuggestionsUseCase by lazy {
        GetAutoCompleteSuggestionsUseCase(placesRepository)
    }

    override val validateAddressProximityUseCase: ValidateAddressProximityUseCase by lazy {
        ValidateAddressProximityUseCase(getLocationDataUseCase, getLocationFromPlaceIdUseCase)
    }

    // Space UseCases
    override val setSpaceDataUseCase: SetSpaceDataUseCase by lazy {
        SetSpaceDataUseCase(spaceRepository, getCurrentUserUseCase)
    }

    override val getSpaceUseCase: GetSpaceUseCase by lazy {
        GetSpaceUseCase(spaceRepository, getCurrentUserUseCase)
    }

    override val checkIfSpaceDataExistsUseCase: CheckIfSpaceDataExistsUseCase by lazy {
        CheckIfSpaceDataExistsUseCase(spaceRepository, getCurrentUserUseCase)
    }

    override val checkIfUserIsInSpaceUseCase: CheckIfUserIsInSpaceUseCase by lazy {
        CheckIfUserIsInSpaceUseCase(validateAddressProximityUseCase)
    }

    // Room UseCases
    override val setRoomDataUseCase: SetRoomDataUseCase by lazy {
        SetRoomDataUseCase(roomRepository, getCurrentUserUseCase)
    }

    override val getRoomListUseCase: GetRoomListUseCase by lazy {
        GetRoomListUseCase(roomRepository, getCurrentUserUseCase)
    }

    override val checkIfAnyRoomExistsUseCase: CheckIfAnyRoomExistsUseCase by lazy {
        CheckIfAnyRoomExistsUseCase(roomRepository, getCurrentUserUseCase)
    }

    // Device UseCases
    override val checkIfDeviceExistsUseCase: CheckIfDeviceExistsUseCase by lazy {
        CheckIfDeviceExistsUseCase(deviceRepository)
    }

    override val setDeviceDataUseCase: SetDeviceDataUseCase by lazy {
        SetDeviceDataUseCase(deviceRepository, getCurrentUserUseCase)
    }

    override val getDeviceListUseCase: GetDeviceListUseCase by lazy {
        GetDeviceListUseCase(deviceRepository, getCurrentUserUseCase)
    }

    // Profile UseCases
    override val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(authRepository)
    }

    override val signUpUseCase: SignUpUseCase by lazy {
        SignUpUseCase(authRepository)
    }

    override val logoutUseCase: LogoutUseCase by lazy {
        LogoutUseCase(authRepository)
    }

    override val forgotPasswordUseCase: ForgotPasswordUseCase by lazy {
        ForgotPasswordUseCase(authRepository)
    }

    override val getCurrentUserUseCase: GetCurrentUserUseCase by lazy {
        GetCurrentUserUseCase(authRepository)
    }

    override val getAccountProfileDetailsUseCase: GetAccountProfileDetailsUseCase by lazy {
        GetAccountProfileDetailsUseCase(userAccountRepository, getCurrentUserUseCase)
    }

    override val updateFirstNameUseCase: UpdateFirstNameUseCase by lazy {
        UpdateFirstNameUseCase(authRepository, getCurrentUserUseCase)
    }

    override val updateLastNameUseCase: UpdateLastNameUseCase by lazy {
        UpdateLastNameUseCase(authRepository, getCurrentUserUseCase)
    }

    override val updateEmailUseCase: UpdateEmailUseCase by lazy {
        UpdateEmailUseCase(authRepository)
    }

    override val updatePasswordUseCase: UpdatePasswordUseCase by lazy {
        UpdatePasswordUseCase(authRepository)
    }

    override val deleteUserUseCase: DeleteUserUseCase by lazy {
        DeleteUserUseCase(authRepository)
    }

    override val cleanupUserDataUseCase: CleanupUserDataUseCase by lazy {
        CleanupUserDataUseCase(getCurrentUserUseCase, cleanupRepository)
    }

    // For SignIn and SignUp screens
    override val validateFirstName: ValidateFirstName by lazy {
        ValidateFirstName(appContext)
    }

    override val validateLastName: ValidateLastName by lazy {
        ValidateLastName(appContext)
    }

    override val validateEmail: ValidateEmail by lazy {
        ValidateEmail(appContext)
    }

    override val validatePassword: ValidatePassword by lazy {
        ValidatePassword(appContext)
    }

    override val validateTerms: ValidateTerms by lazy {
        ValidateTerms(appContext)
    }
    // For CreateANewSpace screen
    override val validateSpaceName: ValidateSpaceName by lazy {
        ValidateSpaceName(appContext)
    }

    override val validateSpaceAddress: ValidateSpaceAddress by lazy {
        ValidateSpaceAddress(appContext)
    }

    override val validatePlaceData: ValidatePlaceData by lazy {
        ValidatePlaceData(appContext, validateAddressProximityUseCase)
    }
    // For CreateANewRoom screen
    override val validateRoomName: ValidateRoomName by lazy {
        ValidateRoomName(appContext)
    }

    // For AddANewDevice screen
    override val validateDeviceName: ValidateDeviceName by lazy {
        ValidateDeviceName(appContext)
    }

    override val validateDeviceId: ValidateDeviceId by lazy {
        ValidateDeviceId(appContext)
    }

    override val validateRoomId: ValidateRoomId by lazy {
        ValidateRoomId(appContext)
    }

    override val validateDeviceExistence: ValidateDeviceExistence by lazy {
        ValidateDeviceExistence(appContext)
    }
}