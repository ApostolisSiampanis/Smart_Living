package com.aposiamp.smartliving.presentation.ui.activity.welcome.screens.auth

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
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.presentation.ui.component.GeneralButtonComponent
import com.aposiamp.smartliving.presentation.ui.component.HeadingTextComponent
import com.aposiamp.smartliving.presentation.ui.component.TermsCheckboxComponent
import com.aposiamp.smartliving.presentation.ui.component.DividerTextComponent
import com.aposiamp.smartliving.presentation.ui.component.HaveAnAccountOrNotClickableTextComponent
import com.aposiamp.smartliving.presentation.ui.component.PasswordTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.FormTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.ErrorTextComponent
import com.aposiamp.smartliving.presentation.ui.component.ProgressIndicatorComponent
import com.aposiamp.smartliving.presentation.ui.event.welcome.auth.SignUpFormEvent
import com.aposiamp.smartliving.presentation.ui.state.welcome.auth.SignUpFormState
import com.aposiamp.smartliving.presentation.viewmodel.welcome.auth.SignUpViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel,
    state: SignUpFormState = viewModel.state
) {
    val context = LocalContext.current
    val signupFlowState by viewModel.signupFlow.collectAsState()
    var loadingState by remember { mutableStateOf(false) }

    LaunchedEffect(signupFlowState) {
        when(signupFlowState) {
            is Result.Error -> {
                loadingState = false
            }

            Result.Loading -> {
                loadingState = true
            }

            is Result.Success -> {
                loadingState = false
                Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                navController.navigate("permissions")
            }

            null -> {}
        }
    }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 80.dp, bottom = 28.dp)
    ) {
        if (loadingState) {
            ProgressIndicatorComponent()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                HeadingTextComponent(value = stringResource(id = R.string.create_an_account))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FormTextFieldComponent(
                        value = state.firstName,
                        onValueChange = { viewModel.onEvent(SignUpFormEvent.FirstNameChanged(it)) },
                        labelValue = stringResource(id = R.string.first_name),
                        painterResource = painterResource(id = R.drawable.account_box),
                        contentDescription = stringResource(id = R.string.first_name_hint),
                        keyboardType = KeyboardType.Text,
                        supportedTextValue = state.firstNameError ?: "",
                        errorStatus = state.firstNameError != null
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FormTextFieldComponent(
                        value = state.lastName,
                        onValueChange = { viewModel.onEvent(SignUpFormEvent.LastNameChanged(it)) },
                        labelValue = stringResource(id = R.string.last_name),
                        painterResource = painterResource(id = R.drawable.account_box),
                        contentDescription = stringResource(id = R.string.last_name_hint),
                        keyboardType = KeyboardType.Text,
                        supportedTextValue = state.lastNameError ?: "",
                        errorStatus = state.lastNameError != null
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FormTextFieldComponent(
                        value = state.email,
                        onValueChange = { viewModel.onEvent(SignUpFormEvent.EmailChanged(it)) },
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
                        onValueChange = { viewModel.onEvent(SignUpFormEvent.PasswordChanged(it)) },
                        labelValue = stringResource(id = R.string.password),
                        supportedTextValue = state.passwordError ?: "",
                        errorStatus = state.passwordError != null
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TermsCheckboxComponent(
                        checkedValue = state.acceptedTerms,
                        onCheckedChange = { viewModel.onEvent(SignUpFormEvent.AcceptTerms(it)) },
                        onTextSelected = {
                            if (it == "Privacy Policy") {
                                navController.navigate("privacyPolicy")
                            } else if (it == "Terms of Use.") {
                                navController.navigate("termsAndConditions")
                            }
                        },
                        errorMessageValue = state.termsError ?: "",
                        errorStatus = state.termsError != null
                    )
                }
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
                    value = stringResource(R.string.register),
                    onButtonClicked = {
                        viewModel.onEvent(SignUpFormEvent.Submit)
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            DividerTextComponent()
            HaveAnAccountOrNotClickableTextComponent(
                alreadyHaveAnAccount = true,
                onTextSelected = {
                    if (it == "Login") {
                        navController.navigate("login")
                    }
                }
            )
        }
    }
}