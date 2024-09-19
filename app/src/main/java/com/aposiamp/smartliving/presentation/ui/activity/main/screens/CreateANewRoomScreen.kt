package com.aposiamp.smartliving.presentation.ui.activity.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.presentation.ui.component.AuthHeadingTextComponent
import com.aposiamp.smartliving.presentation.ui.component.AuthTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.GeneralButtonComponent
import com.aposiamp.smartliving.presentation.ui.component.ProgressIndicatorComponent
import com.aposiamp.smartliving.presentation.ui.event.main.CreateRoomFormEvent
import com.aposiamp.smartliving.presentation.ui.state.main.CreateRoomFormState
import com.aposiamp.smartliving.presentation.viewmodel.main.CreateANewRoomViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel

@Composable
fun CreateANewRoomScreen(
    navController: NavController,
    viewModel: CreateANewRoomViewModel,
    mainSharedViewModel: MainSharedViewModel,
    state: CreateRoomFormState = viewModel.formState
) {
    val place by mainSharedViewModel.space.collectAsState()
    val placeId = place?.placeId ?: ""
    val createRoomFlowState by viewModel.createRoomFlow.collectAsState()
    var loadingState by remember { mutableStateOf(false) }

    LaunchedEffect(createRoomFlowState) {
        when(createRoomFlowState) {
            is Result.Error -> {
                loadingState = false
            }

            is Result.Loading -> {
                loadingState = true
            }

            is Result.Success -> {
                loadingState = false
                navController.navigateUp()
            }

            null -> {}
        }
    }

    if (loadingState) {
        ProgressIndicatorComponent()
    } else {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                BackAppTopBar(
                    title = stringResource(id = R.string.create_a_new_room),
                    color = MaterialTheme.colorScheme.primaryContainer,
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                AuthTextFieldComponent(
                                    value = state.roomName,
                                    onValueChange = {
                                        viewModel.onEvent(CreateRoomFormEvent.RoomNameChanged(it))
                                    },
                                    labelValue = stringResource(id = R.string.room_name),
                                    painterResource = painterResource(id = R.drawable.create_room),
                                    contentDescription = stringResource(id = R.string.room_name_hint),
                                    keyboardType = KeyboardType.Text,
                                    supportedTextValue = state.roomNameError ?: "",
                                    errorStatus = state.roomNameError != null
                                )
                            }
                            Spacer(modifier = Modifier.height(32.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.create_a_new_room),
                                    contentDescription = stringResource(id = R.string.create_new_room_image),
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .aspectRatio(1f)
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
                                value = stringResource(id = R.string.create),
                                onButtonClicked = {
                                    viewModel.onEvent(CreateRoomFormEvent.Submit(placeId = placeId))
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}