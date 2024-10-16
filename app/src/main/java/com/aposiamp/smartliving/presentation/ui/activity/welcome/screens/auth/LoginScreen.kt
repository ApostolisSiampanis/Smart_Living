package com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.presentation.ui.activity.main.MainActivity
import com.aposiamp.smartliving.presentation.ui.activity.welcome.navigation.WelcomeDestination
import com.aposiamp.smartliving.presentation.ui.component.GeneralButtonComponent
import com.aposiamp.smartliving.presentation.ui.component.HeadingTextComponent
import com.aposiamp.smartliving.presentation.ui.component.DividerTextComponent
import com.aposiamp.smartliving.presentation.ui.component.HaveAnAccountOrNotClickableTextComponent
import com.aposiamp.smartliving.presentation.ui.component.PasswordTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.FormTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.ErrorTextComponent
import com.aposiamp.smartliving.presentation.ui.component.ProgressIndicatorComponent
import com.aposiamp.smartliving.presentation.ui.component.UnderlinedClickableTextComponent
import com.aposiamp.smartliving.presentation.ui.event.welcome.auth.LoginFormEvent
import com.aposiamp.smartliving.presentation.ui.state.welcome.auth.LoginFormState
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
    state: LoginFormState = viewModel.state
) {
    val context = LocalContext.current
    val loginFlowState by viewModel.loginFlow.collectAsStateWithLifecycle()
    var loadingState by remember { mutableStateOf(false) }

    LaunchedEffect(loginFlowState) {
        when(loginFlowState) {
            is Result.Error -> {
                loadingState = false
            }

            Result.Loading -> {
                loadingState = true
            }

            is Result.Success -> {
                loadingState = false
                Toast.makeText(context, context.getString(R.string.successfully_logged_in), Toast.LENGTH_SHORT).show()
                val destination = viewModel.determineDestination()
                if (destination == context.getString(R.string.permissions_lower_case)) {
                    navController.navigate(WelcomeDestination.Permissions.route)
                } else {
                    val intent = Intent(context, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    context.startActivity(intent)
                }
            }

            null -> {}
        }
    }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 60.dp, bottom = 28.dp)
    ) {
        if (loadingState) {
            ProgressIndicatorComponent()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                HeadingTextComponent(value = stringResource(id = R.string.welcome_to_smart_living_app))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FormTextFieldComponent(
                        value = state.email,
                        onValueChange = { viewModel.onEvent(LoginFormEvent.EmailChanged(it)) },
                        labelValue = stringResource(id = R.string.email),
                        painterResource = painterResource(id = R.drawable.email),
                        contentDescription = stringResource(id = R.string.email_hint),
                        keyboardType = KeyboardType.Email,
                        supportedTextValue = state.emailError ?: "",
                        errorStatus = state.emailError != null
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    PasswordTextFieldComponent(
                        value = state.password,
                        onValueChange = { viewModel.onEvent(LoginFormEvent.PasswordChanged(it)) },
                        labelValue = stringResource(id = R.string.password),
                        supportedTextValue = state.passwordError ?: "",
                        errorStatus = state.passwordError != null
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    UnderlinedClickableTextComponent(
                        value = stringResource(id = R.string.forgot_your_password),
                        onTextSelected = {
                            navController.navigate(WelcomeDestination.ForgotPassword.route)
                        }
                    )
                }
                if (state.errorMessage != null) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ErrorTextComponent(errorMessage = state.errorMessage)
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                GeneralButtonComponent(
                    value = stringResource(R.string.login),
                    onButtonClicked = {
                        viewModel.onEvent(LoginFormEvent.Submit)
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            DividerTextComponent()
            HaveAnAccountOrNotClickableTextComponent(
                alreadyHaveAnAccount = false,
                onTextSelected = {
                    if (it == context.getString(R.string.register)) {
                        navController.navigate(WelcomeDestination.SignUp.route)
                    }
                }
            )
        }
    }
}