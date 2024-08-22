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
import com.aposiamp.smartliving.presentation.ui.component.DividerTextComponent
import com.aposiamp.smartliving.presentation.ui.component.HaveAnAccountOrNotClickableTextComponent
import com.aposiamp.smartliving.presentation.ui.component.PasswordTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.TextFieldComponent

@Composable
fun LoginScreen(navController: NavController? = null) {
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
            AuthHeadingTextComponent(value = stringResource(id = R.string.welcome_to_smart_living_app))
            TextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                contentDescription = stringResource(id = R.string.email_hint),
                keyboardType = KeyboardType.Email
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                AuthButtonComponent(
                    value = stringResource(R.string.login),
                    onButtonClicked = {
                        //TODO: Check credentials and then Navigate to Next Screen
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()
                HaveAnAccountOrNotClickableTextComponent(
                    alreadyHaveAnAccount = false,
                    onTextSelected = {
                        if (it == "Register") {
                            navController?.navigate("signUp")
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}