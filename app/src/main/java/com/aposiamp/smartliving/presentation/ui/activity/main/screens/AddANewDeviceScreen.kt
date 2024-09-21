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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.domain.utils.Result
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.ErrorSupportingTextComponent
import com.aposiamp.smartliving.presentation.ui.component.FormTextFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.GeneralButtonComponent
import com.aposiamp.smartliving.presentation.ui.component.GeneralNormalBlackText
import com.aposiamp.smartliving.presentation.ui.component.ProgressIndicatorComponent
import com.aposiamp.smartliving.presentation.ui.event.main.AddDeviceFormEvent
import com.aposiamp.smartliving.presentation.ui.state.main.AddDeviceFormState
import com.aposiamp.smartliving.presentation.viewmodel.main.AddANewDeviceViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddANewDeviceScreen(
    navController: NavController,
    viewModel: AddANewDeviceViewModel,
    mainSharedViewModel: MainSharedViewModel,
    state: AddDeviceFormState = viewModel.formState
) {
    val place by mainSharedViewModel.space.collectAsState()
    val placeId = place?.placeId ?: ""

    val uiDeviceTypes = viewModel.uiDeviceTypes
    var deviceTypesExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(placeId) {
        viewModel.fetchRoomList(placeId)
    }
    val roomList by viewModel.roomListFlow.collectAsState()
    var roomsExpanded by remember { mutableStateOf(false) }

    val addDeviceFlowState by viewModel.addDeviceFlow.collectAsState()
    var loadingState by remember { mutableStateOf(false) }

    LaunchedEffect(addDeviceFlowState) {
        when(addDeviceFlowState) {
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
                    title = stringResource(id = R.string.add_a_new_device),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            },
            content = { padding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    color = Color.White
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
                                FormTextFieldComponent(
                                    value = state.deviceId,
                                    onValueChange = {
                                        viewModel.viewModelScope.launch {
                                            viewModel.onEvent(AddDeviceFormEvent.DeviceIdChanged(it))
                                        }
                                    },
                                    labelValue = stringResource(id = R.string.device_id),
                                    painterResource = painterResource(id = R.drawable.password),
                                    contentDescription = stringResource(id = R.string.device_id_hint),
                                    keyboardType = KeyboardType.Text,
                                    supportedTextValue = state.deviceIdError ?: "",
                                    errorStatus = state.deviceIdError != null
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                FormTextFieldComponent(
                                    value = state.deviceName,
                                    onValueChange = {
                                        viewModel.viewModelScope.launch {
                                            viewModel.onEvent(AddDeviceFormEvent.DeviceNameChanged(it))
                                        }
                                    },
                                    labelValue = stringResource(id = R.string.device_name),
                                    painterResource = painterResource(id = R.drawable.device),
                                    contentDescription = stringResource(id = R.string.device_name_hint),
                                    keyboardType = KeyboardType.Text,
                                    supportedTextValue = state.deviceNameError ?: "",
                                    errorStatus = state.deviceNameError != null
                                )
                            }

                            ExposedDropdownMenuBox(
                                expanded = deviceTypesExpanded,
                                onExpandedChange = {
                                    deviceTypesExpanded = !deviceTypesExpanded
                                }
                            ) {
                                OutlinedTextField(
                                    value = state.deviceType.let { deviceType ->
                                        stringResource(id = uiDeviceTypes.first { it.type == deviceType }.text)
                                    },
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text(stringResource(id = R.string.device_type)) },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = deviceTypesExpanded) },
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
                                )

                                ExposedDropdownMenu(
                                    expanded = deviceTypesExpanded,
                                    onDismissRequest = { deviceTypesExpanded = false }
                                ) {
                                    uiDeviceTypes.forEach { deviceType ->
                                        DropdownMenuItem(
                                            text = { GeneralNormalBlackText(value = stringResource(id = deviceType.text)) },
                                            onClick = {
                                                viewModel.viewModelScope.launch {
                                                    viewModel.onEvent(AddDeviceFormEvent.DeviceTypeChanged(deviceType.type))
                                                    deviceTypesExpanded = false
                                                }
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            ExposedDropdownMenuBox(
                                expanded = roomsExpanded,
                                onExpandedChange = {
                                    roomsExpanded = !roomsExpanded
                                }
                            ) {
                                OutlinedTextField(
                                    value = state.roomId.let { roomId ->
                                        roomList.firstOrNull { it.roomId == roomId }?.roomName ?: ""
                                    },
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { GeneralNormalBlackText(value = stringResource(id = R.string.select_room)) },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = roomsExpanded) },
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
                                )

                                ExposedDropdownMenu(
                                    expanded = roomsExpanded,
                                    onDismissRequest = { roomsExpanded = false }
                                ) {
                                    roomList.forEach { room ->
                                        DropdownMenuItem(
                                            text = { room.roomName?.let { Text(text = it) } },
                                            onClick = {
                                                viewModel.viewModelScope.launch {
                                                    room.roomId?.let {
                                                        AddDeviceFormEvent.RoomIdChanged(
                                                            it
                                                        )
                                                    }?.let { viewModel.onEvent(it) }
                                                    roomsExpanded = false
                                                }
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            if (state.roomIdError != null) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    ErrorSupportingTextComponent(value = state.roomIdError)
                                }
                            }
                            if (state.deviceExistenceError != null && (state.deviceNameError == null && state.deviceIdError == null)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    ErrorSupportingTextComponent(value = state.deviceExistenceError)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.add_device),
                                    contentDescription = stringResource(id = R.string.add_a_new_device_image),
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
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
                                value = stringResource(id = R.string.add),
                                onButtonClicked = {
                                    viewModel.viewModelScope.launch {
                                        viewModel.onEvent(AddDeviceFormEvent.Submit(placeId = placeId))
                                    }
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}