package com.aposiamp.smartliving.presentation.ui.activity.main.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.SmartLiving
import androidx.compose.foundation.lazy.items
import com.aposiamp.smartliving.domain.model.DeviceType
import com.aposiamp.smartliving.presentation.ui.component.BottomBar
import com.aposiamp.smartliving.presentation.ui.component.DeviceCard
import com.aposiamp.smartliving.presentation.ui.component.DividerComponent
import com.aposiamp.smartliving.presentation.ui.component.MenuMediumTopAppBar
import com.aposiamp.smartliving.presentation.ui.component.NavigationDrawer
import com.aposiamp.smartliving.presentation.viewmodel.main.DevicesViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.MainSharedViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.NavigationViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevicesScreen(
    navController: NavController,
    viewModel: DevicesViewModel,
    mainSharedViewModel: MainSharedViewModel,
    navigationViewModel: NavigationViewModel,
    context: Context
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val space by mainSharedViewModel.space.collectAsState()

    val isAnyRoomExists by viewModel.isAnyRoomExists.collectAsState()
    val roomList by viewModel.roomListFlow.collectAsState()
    val devicesLists by viewModel.deviceListsFlow.collectAsState()

    // Retrieve the Navigation Drawer Items
    val navigationDrawerItems = navigationViewModel.getNavigationDrawerItems(context = context)
    // Retrieve the Bottom Navigation Items
    val bottomNavigationItems = navigationViewModel.getBottomNavigationItems(context = context)
    // Retrieve the dropdown menu items
    val dropdownMenuItems = navigationViewModel.getDropdownMenuItems(context = context, navController = navController)

    LaunchedEffect(space?.placeId) {
        space?.placeId?.let { viewModel.checkIfAnyRoomExists(it) }
    }

    LaunchedEffect(isAnyRoomExists) {
        if (isAnyRoomExists == true) {
            space?.placeId?.let { viewModel.fetchRoomList(it) }
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                drawerState = drawerState,
                navigationDrawerItems = navigationDrawerItems,
                logoutUseCase = SmartLiving.appModule.logoutUseCase
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MenuMediumTopAppBar(
                    title = space?.spaceName ?: "",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    onMenuClick = {
                        scope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            } else {
                                drawerState.open()
                            }
                        }
                    },
                    drawerState = drawerState,
                    scrollBehavior = scrollBehavior,
                    dropdownMenuItems = dropdownMenuItems
                )
            },
            bottomBar = {
                BottomBar(
                    navController = navController,
                    bottomMenuItems = bottomNavigationItems
                )
            },
            content = { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    item {
                        if (isAnyRoomExists == false) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(0.8f)
                                        .aspectRatio(1f)
                                        .clickable{
                                            navController.navigate("createANewRoom")
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.add_room),
                                        contentDescription = stringResource(id = R.string.create_your_first_room_image)
                                    )
                                    Surface(
                                        color = Color.White.copy(alpha = 0.4f),
                                        modifier = Modifier.fillMaxSize()
                                    ) {}
                                }
                            }
                        } else {
                            roomList.forEach { room ->
                                var isExpanded by remember { mutableStateOf(true) }

                                LaunchedEffect(room.roomId) {
                                    space?.placeId?.let { spaceId ->
                                        room.roomId?.let { roomId ->
                                            viewModel.fetchDeviceList(spaceId, roomId)
                                        }
                                    }
                                }

                                Column {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { isExpanded = !isExpanded }
                                            .padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = room.roomName ?: "",
                                            modifier = Modifier.weight(1f)
                                        )
                                        Icon(
                                            painter = painterResource(
                                                id = if (isExpanded) R.drawable.arrow_up else R.drawable.arrow_down
                                            ),
                                            contentDescription = stringResource(id = if (isExpanded) R.string.expand else R.string.collapse)
                                        )
                                    }
                                    DividerComponent()
                                    if (isExpanded) {
                                        val devices = devicesLists[room.roomId] ?: emptyList()
                                        LazyRow(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(top = 8.dp, start = 8.dp)
                                        ) {
                                            items(devices) { device ->
                                                val painter = when (device.deviceType) {
                                                    DeviceType.AIR_CONDITIONER -> painterResource(R.drawable.air_condition)
                                                    DeviceType.THERMOSTAT -> painterResource(R.drawable.thermostat)
                                                    DeviceType.DEHUMIDIFIER -> painterResource(R.drawable.dehumidifier)
                                                    else -> painterResource(R.drawable.air_condition)
                                                }
                                                val contentDescription = when (device.deviceType) {
                                                    DeviceType.AIR_CONDITIONER -> stringResource(R.string.air_conditioner)
                                                    DeviceType.THERMOSTAT -> stringResource(R.string.thermostat)
                                                    DeviceType.DEHUMIDIFIER -> stringResource(R.string.dehumidifier)
                                                    else -> stringResource(R.string.air_conditioner)
                                                }
                                                DeviceCard(
                                                    deviceName = device.deviceName ?: "",
                                                    painter = painter,
                                                    contentDescription = contentDescription,
                                                    onClick = {
                                                        mainSharedViewModel.setSelectedDevice(device)
                                                        when (device.deviceType) {
                                                            DeviceType.AIR_CONDITIONER -> navController.navigate("airCondition")
                                                            DeviceType.THERMOSTAT -> navController.navigate("thermostat")
                                                            DeviceType.DEHUMIDIFIER -> navController.navigate("dehumidifier")
                                                            else -> navController.navigate("airCondition")
                                                        }
                                                    }
                                                )
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}