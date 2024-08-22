package com.aposiamp.smartliving.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.component.GeneralButtonComponent

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 28.dp, start = 32.dp, end = 32.dp, bottom = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                //TODO: Add App Logo
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.welcome_text),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = stringResource(id = R.string.creator),
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        GeneralButtonComponent(
                            value = stringResource(id = R.string.login),
                            onButtonClicked = {
                                navController.navigate("login")
                            }
                        )
                    }
                }
            }
        }
    }
}