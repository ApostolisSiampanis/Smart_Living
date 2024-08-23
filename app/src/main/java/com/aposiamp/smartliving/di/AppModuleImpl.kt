package com.aposiamp.smartliving.di

import android.content.Context
import com.aposiamp.smartliving.data.repository.AuthRepositoryImpl
import com.aposiamp.smartliving.data.source.remote.FirebaseDataSource
import com.aposiamp.smartliving.domain.repository.AuthRepository
import com.aposiamp.smartliving.domain.usecase.user.LoginUseCase
import com.aposiamp.smartliving.domain.usecase.user.LogoutUseCase
import com.aposiamp.smartliving.domain.usecase.user.SignUpUseCase
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateEmail
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateFirstName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateLastName
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidatePassword
import com.aposiamp.smartliving.domain.usecase.welcome.validateregex.ValidateTerms
import com.google.firebase.auth.FirebaseAuth

class AppModuleImpl(private val appContext: Context): AppModule {
    // Repositories
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(firebaseDataSource)
    }

    // Firebase
    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    private val firebaseDataSource = FirebaseDataSource(getFirebaseAuth())

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