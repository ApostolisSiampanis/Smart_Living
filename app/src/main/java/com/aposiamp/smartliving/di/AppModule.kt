package com.aposiamp.smartliving.di

import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.aposiamp.smartliving.domain.repository.BottomMenuRepository
import com.aposiamp.smartliving.domain.repository.EnvironmentalSensorRepository
import com.aposiamp.smartliving.domain.repository.NavigationDrawerRepository
import com.aposiamp.smartliving.domain.usecase.main.GetBottomNavigationItemsUseCase
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

interface AppModule {
    // Repositories
    val authRepository: AuthRepository
    val environmentalSensorRepository: EnvironmentalSensorRepository
    val navigationDrawerRepository: NavigationDrawerRepository
    val bottomMenuRepository: BottomMenuRepository

    // Firebase
    fun getFirebaseAuth(): FirebaseAuth
    fun getFirestoreDatabase(): FirebaseFirestore

    // NavigationDrawer UseCase
    val getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase

    // BottomMenu UseCase
    val getBottomMenuItemsUseCase: GetBottomNavigationItemsUseCase

    // Sensor UseCases
    val getEnvironmentalDataUseCase: GetEnvironmentalDataUseCase

    // Profile UseCases
    val loginUseCase: LoginUseCase
    val signUpUseCase: SignUpUseCase
    val logoutUseCase: LogoutUseCase
    val getCurrentUserUseCase: GetCurrentUserUseCase

    // For the SignIn and SignUp screens
    val validateFirstName: ValidateFirstName
    val validateLastName: ValidateLastName
    val validateEmail: ValidateEmail
    val validatePassword: ValidatePassword
    val validateTerms: ValidateTerms
}