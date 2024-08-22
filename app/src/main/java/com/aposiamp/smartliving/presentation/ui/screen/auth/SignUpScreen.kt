package com.aposiamp.smartliving.presentation.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.component.AuthButtonComponent
import com.aposiamp.smartliving.presentation.ui.component.AuthHeadingTextComponent
import com.aposiamp.smartliving.presentation.ui.component.CheckboxComponent
import com.aposiamp.smartliving.presentation.ui.component.DividerTextComponent
import com.aposiamp.smartliving.presentation.ui.component.HaveAnAccountOrNotClickableTextComponent
import com.aposiamp.smartliving.presentation.ui.component.PasswordTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.TextFieldComponent

@Composable
fun SignUpScreen(navController: NavController? = null) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 28.dp, end = 28.dp, top = 80.dp, bottom = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AuthHeadingTextComponent(value = stringResource(id = R.string.create_an_account))
            TextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource = painterResource(id = R.drawable.account_box),
                contentDescription = stringResource(id = R.string.first_name_hint),
                keyboardType = KeyboardType.Text
            )
            TextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.account_box),
                contentDescription = stringResource(id = R.string.last_name_hint),
                keyboardType = KeyboardType.Text
            )
            TextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                contentDescription = stringResource(id = R.string.email_hint),
                keyboardType = KeyboardType.Email
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password)
            )
            CheckboxComponent(
                onTextSelected = {
                    if (it == "Privacy Policy") {
                        //TODO: Navigate to Privacy Policy Screen
                    } else if (it == "Terms of Use.") {
                        //TODO: Navigate to Terms of Use Screen
                    }
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                AuthButtonComponent(
                    value = stringResource(R.string.register),
                    onButtonClicked = {
                        //TODO: Check credentials and then Navigate to Next Screen
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()
                HaveAnAccountOrNotClickableTextComponent(
                    alreadyHaveAnAccount = true,
                    onTextSelected = {
                        if (it == "Login") {
                            navController?.navigate("login")
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}