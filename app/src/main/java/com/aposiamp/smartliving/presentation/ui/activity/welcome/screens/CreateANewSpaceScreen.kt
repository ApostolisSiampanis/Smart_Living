package com.aposiamp.smartliving.presentation.ui.activity.welcome.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.aposiamp.smartliving.domain.utils.Result
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.activity.main.MainActivity
import com.aposiamp.smartliving.presentation.ui.component.AuthHeadingTextComponent
import com.aposiamp.smartliving.presentation.ui.component.AuthTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.GeneralButtonComponent
import com.aposiamp.smartliving.presentation.ui.component.ProgressIndicatorComponent
import com.aposiamp.smartliving.presentation.ui.event.CreateSpaceFormEvent
import com.aposiamp.smartliving.presentation.ui.state.CreateSpaceFormState
import com.aposiamp.smartliving.presentation.viewmodel.welcome.CreateANewSpaceViewModel

@Composable
fun CreateANewSpaceScreen(
    viewModel: CreateANewSpaceViewModel,
    state: CreateSpaceFormState = viewModel.formState
) {
    val context = LocalContext.current
    val createSpaceFlowState by viewModel.createSpaceFlow.collectAsState()
    var loadingState by remember { mutableStateOf(false) }

    LaunchedEffect(createSpaceFlowState) {
        when(createSpaceFlowState) {
            is Result.Error -> {
                loadingState = false
            }

            is Result.Loading -> {
                loadingState = true
            }

            is Result.Success -> {
                loadingState = false
                val intent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                context.startActivity(intent)
            }

            null -> {}
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loadingState) {
            ProgressIndicatorComponent()
        }

        Image(
            painter = painterResource(id = R.drawable.space),
            contentDescription = stringResource(id = R.string.create_new_space_screen_image),
            modifier = Modifier.fillMaxSize(0.8f)
        )

        Surface(
            color = Color.White.copy(alpha = 0.4f),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 28.dp, end = 28.dp, top = 40.dp, bottom = 28.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    AuthHeadingTextComponent(value = stringResource(id = R.string.welcome_to_smart_living_app))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AuthTextFieldComponent(
                            value = state.spaceName,
                            onValueChange = { viewModel.onEvent(CreateSpaceFormEvent.SpaceNameChanged(it)) },
                            labelValue = stringResource(id = R.string.space_name),
                            painterResource = painterResource(id = R.drawable.add_space),
                            contentDescription = stringResource(id = R.string.space_name_hint),
                            keyboardType = KeyboardType.Text,
                            supportedTextValue = state.spaceNameError ?: "",
                            errorStatus = state.spaceNameError != null
                        )
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
                        value = stringResource(id = R.string.create),
                        onButtonClicked = {
                            viewModel.onEvent(CreateSpaceFormEvent.Submit)
                        }
                    )
                }
            }
        }
    }
}