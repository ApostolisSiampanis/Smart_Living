package com.aposiamp.smartliving.di

import android.content.Context
import com.aposiamp.smartliving.data.repository.AuthRepositoryImpl
import com.aposiamp.smartliving.data.repository.DeviceAndSpaceRepositoryImpl
import com.aposiamp.smartliving.data.repository.EnvironmentalSensorRepositoryImpl
import com.aposiamp.smartliving.data.source.local.EnvironmentalSensorDataSource
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.data.source.remote.FirestoreDataSource
import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.aposiamp.smartliving.domain.repository.DeviceAndSpaceRepository
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository
import com.aposiamp.smartliving.domain.usecase.main.GetBottomNavigationItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetDropdownMenuItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetNavigationDrawerItemsUseCase
import com.aposiamp.smartliving.domain.usecase.sensor.GetEnvironmentalDataUseCase
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class AppModuleImpl(private val appContext: Context): AppModule {
    // DataSources
    private val firebaseDataSource = FirebaseDataSource(getFirebaseAuth(), getFirebaseDatabase())
    private val firestoreDataSource = FirestoreDataSource(getFirestoreDatabase())
    private val environmentalSensorDataSource = EnvironmentalSensorDataSource(appContext)

    // Repositories
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(firebaseDataSource, firestoreDataSource)
    }

    override val environmentalSensorRepository: EnvironmentalSensorRepository by lazy {
        EnvironmentalSensorRepositoryImpl(environmentalSensorDataSource, firestoreDataSource)
    }

    override val deviceAndSpaceRepository: DeviceAndSpaceRepository by lazy {
        DeviceAndSpaceRepositoryImpl(firebaseDataSource)
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
        GetEnvironmentalDataUseCase(environmentalSensorRepository, getCurrentUserUseCase)
    }

    // Space UseCases
    override val setSpaceDataUseCase: SetSpaceDataUseCase by lazy {
        SetSpaceDataUseCase(deviceAndSpaceRepository, getCurrentUserUseCase)
    }

    override val checkIfSpaceDataExistsUseCase: CheckIfSpaceDataExistsUseCase by lazy {
        CheckIfSpaceDataExistsUseCase(deviceAndSpaceRepository, getCurrentUserUseCase)
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
}