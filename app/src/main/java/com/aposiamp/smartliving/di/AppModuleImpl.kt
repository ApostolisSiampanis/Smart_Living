package com.aposiamp.smartliving.di

import android.content.Context
import com.aposiamp.smartliving.data.repository.AuthRepositoryImpl
import com.aposiamp.smartliving.data.repository.BottomMenuRepositoryImpl
import com.aposiamp.smartliving.data.repository.EnvironmentalSensorRepositoryImpl
import com.aposiamp.smartliving.data.repository.NavigationDrawerRepositoryImpl
import com.aposiamp.smartliving.data.source.local.EnvironmentalSensorDataSource
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.data.source.remote.FirestoreDataSource
import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository
import com.aposiamp.smartliving.domain.usecase.main.GetBottomNavigationItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetDropdownMenuItemsUseCase
import com.aposiamp.smartliving.domain.usecase.main.GetNavigationDrawerItemsUseCase
import com.aposiamp.smartliving.domain.usecase.sensor.GetEnvironmentalDataUseCase
import com.aposiamp.smartliving.domain.usecase.user.GetCurrentUserUseCase
import com.aposiamp.smartliving.domain.usecase.user.LoginUseCase
import com.aposiamp.smartliving.domain.usecase.user.LogoutUseCase
import com.aposiamp.smartliving.domain.usecase.user.SignUpUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateEmail
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateFirstName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateLastName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePassword
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateTerms
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AppModuleImpl(private val appContext: Context): AppModule {
    // DataSources
    private val firebaseDataSource = FirebaseDataSource(getFirebaseAuth())
    private val firestoreDataSource = FirestoreDataSource(getFirestoreDatabase())
    private val environmentalSensorDataSource = EnvironmentalSensorDataSource(appContext)

    // Repositories
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(firebaseDataSource, firestoreDataSource)
    }

    override val environmentalSensorRepository: EnvironmentalSensorRepository by lazy {
        EnvironmentalSensorRepositoryImpl(environmentalSensorDataSource, firestoreDataSource)
    }

    override val navigationDrawerRepository: NavigationDrawerRepositoryImpl by lazy {
        NavigationDrawerRepositoryImpl()
    }

    override val bottomMenuRepository: BottomMenuRepositoryImpl by lazy {
        BottomMenuRepositoryImpl()
    }

    // Firebase
    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override fun getFirestoreDatabase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    // NavigationDrawer UseCase
    override val getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase by lazy {
        GetNavigationDrawerItemsUseCase(navigationDrawerRepository)
    }

    // BottomMenu UseCase
    override val getBottomMenuItemsUseCase: GetBottomNavigationItemsUseCase by lazy {
        GetBottomNavigationItemsUseCase(bottomMenuRepository)
    }

    // DropDownMenu UseCase
    override val getDropdownMenuItemsUseCase: GetDropdownMenuItemsUseCase by lazy {
        GetDropdownMenuItemsUseCase()
    }

    // Sensor UseCases
    override val getEnvironmentalDataUseCase: GetEnvironmentalDataUseCase by lazy {
        GetEnvironmentalDataUseCase(environmentalSensorRepository, getCurrentUserUseCase)
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
        ValidateFirstName()
    }

    override val validateLastName: ValidateLastName by lazy {
        ValidateLastName()
    }

    override val validateEmail: ValidateEmail by lazy {
        ValidateEmail()
    }

    override val validatePassword: ValidatePassword by lazy {
        ValidatePassword()
    }

    override val validateTerms: ValidateTerms by lazy {
        ValidateTerms()
    }
}