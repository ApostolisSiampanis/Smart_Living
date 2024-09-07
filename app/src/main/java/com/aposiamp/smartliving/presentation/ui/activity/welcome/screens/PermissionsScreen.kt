package com.aposiamp.smartliving.presentation.ui.activity.welcome.screens

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.permission.LocationPermissionTextProvider
import com.aposiamp.smartliving.presentation.ui.component.GeneralButtonComponent
import com.aposiamp.smartliving.presentation.ui.component.PermissionCard
import com.aposiamp.smartliving.presentation.ui.component.PermissionDialog
import com.aposiamp.smartliving.presentation.viewmodel.welcome.PermissionsViewModel
import com.aposiamp.smartliving.utils.PermissionsUtils.areAllPermissionsGranted
import com.aposiamp.smartliving.utils.PermissionsUtils.openAppSettings

@Composable
fun PermissionsScreen(
    navController: NavController,
    viewModel: PermissionsViewModel
) {
    val context = LocalContext.current
    val dialogPermission = viewModel.visiblePermissionDialog.value

    val isExpandedLocation = remember { mutableStateOf(false) }
    val switchStateLocation = remember { mutableStateOf(false) }

    val locationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onPermissionResult(
                permission = Manifest.permission.ACCESS_FINE_LOCATION,
                isGranted = isGranted
            )
            switchStateLocation.value = isGranted
        }
    )

    LaunchedEffect(true) {
        switchStateLocation.value = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.permissions),
            contentDescription = stringResource(id = R.string.permission_screen_image),
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
                    PermissionCard(
                        permissionName = stringResource(id = R.string.location),
                        permissionRequestText = stringResource(id = R.string.allow_smart_living_to_access_your_location),
                        painter = painterResource(id = R.drawable.location_pin),
                        contentDescription = stringResource(id = R.string.location_pin),
                        isExpanded = isExpandedLocation,
                        switchState = switchStateLocation,
                        onToggleClick = {
                            if (switchStateLocation.value) {
                                locationPermissionResultLauncher.launch(
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                )
                            }
                        }
                    )
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
                        value = stringResource(id = R.string.next),
                        onButtonClicked = {
                            if (areAllPermissionsGranted(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))) {
                                navController.navigate("createANewSpace")
                            } else {
                                Toast.makeText(context, context.getString(R.string.please_grant_all_permissions), Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }

            dialogPermission?.let { permission ->
                PermissionDialog(
                    permissionTextProvider = when (permission) {
                        Manifest.permission.ACCESS_FINE_LOCATION -> {
                            LocationPermissionTextProvider()
                        }
                        else -> return@let
                    },
                    isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                        context as Activity,
                        permission
                    ),
                    onDismiss = {
                        viewModel.dismissDialog()
                    },
                    onOkClick = {
                        viewModel.dismissDialog()
                        locationPermissionResultLauncher.launch(permission)
                    },
                    onGoToAppSettingsClick = {
                        openAppSettings(context)
                    }
                )
            }
        }
    }
}