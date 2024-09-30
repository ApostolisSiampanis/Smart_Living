package com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.presentation.ui.component.HeadingTextComponent
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.EmailSentDialog
import com.aposiamp.smartliving.presentation.ui.component.FormTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.GeneralButtonComponent
import com.aposiamp.smartliving.presentation.ui.event.welcome.auth.ForgotPasswordFormEvent
import com.aposiamp.smartliving.presentation.ui.state.welcome.auth.ForgotPasswordFormState
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel,
    state: ForgotPasswordFormState = viewModel.state
) {
    val forgotPasswordFlowState by viewModel.forgotPasswordFlow.collectAsStateWithLifecycle()
    var loadingState by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(forgotPasswordFlowState) {
        when(forgotPasswordFlowState) {
            is Result.Error -> {
                loadingState = false
            }

            Result.Loading -> {
                loadingState = true
            }

            is Result.Success -> {
                loadingState = false
                showDialog = true
            }

            null -> {}
        }
    }

    if (showDialog) {
        EmailSentDialog(
            title = stringResource(id = R.string.password_reset_email_sent),
            message = stringResource(id = R.string.check_your_email_for_instructions),
            onDismiss = {
                showDialog = false
            },
            onConfirm = {
                showDialog = false
                navController.navigateUp()
            }
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = "",
                color = Color.White,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        },
        content = { padding ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 28.dp, end = 28.dp, top = 18.dp)
                ) {
                    item {
                        HeadingTextComponent(value = stringResource(id = R.string.forgot_password_header))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            FormTextFieldComponent(
                                value = state.email,
                                onValueChange = { viewModel.onEvent(ForgotPasswordFormEvent.EmailChanged(it)) },
                                labelValue = stringResource(id = R.string.email),
                                painterResource = painterResource(id = R.drawable.email),
                                contentDescription = stringResource(id = R.string.email_hint),
                                keyboardType = KeyboardType.Email,
                                supportedTextValue = state.emailError ?: "",
                                errorStatus = state.emailError != null
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 28.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        GeneralButtonComponent(
                            value = stringResource(R.string.send_email),
                            onButtonClicked = {
                                viewModel.onEvent(ForgotPasswordFormEvent.ResetPasswordButtonClicked)
                            }
                        )
                    }
                }
            }
        }
    )
}