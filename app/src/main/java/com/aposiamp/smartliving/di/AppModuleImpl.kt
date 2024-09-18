package com.aposiamp.smartliving.di

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import com.aposiamp.smartliving.BuildConfig
import com.aposiamp.smartliving.data.repository.AuthRepositoryImpl
import com.aposiamp.smartliving.data.repository.SpaceRepositoryImpl
import com.aposiamp.smartliving.data.repository.EnvironmentalSensorRepositoryImpl
import com.aposiamp.smartliving.data.repository.LocationRepositoryImpl
import com.aposiamp.smartliving.data.repository.PlacesRepositoryImpl
import com.aposiamp.smartliving.data.source.local.EnvironmentalSensorDataSource
import com.aposiamp.smartliving.data.source.local.LocationDataSource
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.data.source.remote.FirestoreDataSource
import com.aposiamp.smartliving.data.source.remote.PlacesDataSource
import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.aposiamp.smartliving.domain.repository.SpaceRepository
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository
import com.aposiamp.smartliving.domain.repository.LocationRepository
import com.aposiamp.smartliving.domain.repository.PlacesRepository
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
import com.aposiamp.smartliving.domain.usecase.main.CheckIfUserIsInSpaceUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateEmail
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateFirstName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateLastName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePassword
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePlaceData
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
    private val placesDataSource = PlacesDataSource(getPlacesClient())

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

    override val placesRepository: PlacesRepository by lazy {
        PlacesRepositoryImpl(placesDataSource)
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

    override val getCurrentUserUseCase: GetCurrentUserUseCase by lazy {
        GetCurrentUserUseCase(authRepository)
    }

    // For the SignIn and SignUp screens
    override val validateFirstName: ValidateFirstName by lazy {
        ValidateFirstName(context = appContext)
    }

    override val validateLastName: ValidateLastName by lazy {
        ValidateLastName(context = appContext)
    }

    override val validateEmail: ValidateEmail by lazy {
        ValidateEmail(context = appContext)
    }

    override val validatePassword: ValidatePassword by lazy {
        ValidatePassword(context = appContext)
    }

    override val validateTerms: ValidateTerms by lazy {
        ValidateTerms(context = appContext)
    }

    override val validateSpaceName: ValidateSpaceName by lazy {
        ValidateSpaceName(context = appContext)
    }

    override val validateSpaceAddress: ValidateSpaceAddress by lazy {
        ValidateSpaceAddress(context = appContext)
    }

    override val validatePlaceData: ValidatePlaceData by lazy {
        ValidatePlaceData(context = appContext, validateAddressProximityUseCase = validateAddressProximityUseCase)
    }
}