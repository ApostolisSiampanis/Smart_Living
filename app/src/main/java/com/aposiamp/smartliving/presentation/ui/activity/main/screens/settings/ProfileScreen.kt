package com.aposiamp.smartliving.presentation.ui.activity.main.screens.settings

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.EditableField
import com.aposiamp.smartliving.presentation.ui.component.EmailSentDialog
import com.aposiamp.smartliving.presentation.ui.component.GeneralNormalText
import com.aposiamp.smartliving.presentation.ui.component.ProgressIndicatorComponent
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.AccountProfileViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel,
    accountProfileViewModel: AccountProfileViewModel
) {
    val context = LocalContext.current
    val accountDetails by accountProfileViewModel.detailsState.collectAsStateWithLifecycle()
    val isLoading by accountProfileViewModel.isLoading.collectAsStateWithLifecycle()
    val firstNameError by viewModel.firstNameError.collectAsStateWithLifecycle()
    val lastNameError by viewModel.lastNameError.collectAsStateWithLifecycle()
    val emailError by viewModel.emailError.collectAsStateWithLifecycle()
    val showDialog by viewModel.showDialog.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        accountProfileViewModel.fetchAccountDetails()
    }

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    if (showDialog) {
        EmailSentDialog(
            title = stringResource(id = R.string.email_update_link_sent),
            message = stringResource(id = R.string.check_your_email_to_update),
            onDismiss = { viewModel.dismissDialog() },
            onConfirm = { viewModel.dismissDialog() }
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = stringResource(id = R.string.profile),
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
                if (isLoading) {
                    ProgressIndicatorComponent()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 18.dp, start = 16.dp, end = 16.dp),
                    ) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.account_image),
                                    contentDescription = stringResource(id = R.string.account_image),
                                    modifier = Modifier
                                        .fillMaxWidth(0.3f)
                                        .aspectRatio(1f)
                                )

                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            GeneralNormalText(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                                value = stringResource(id = R.string.first_name_with_dots),
                                color = Color.Black
                            )
                            EditableField(
                                value = accountDetails?.firstName ?: "",
                                error = firstNameError,
                                keyboardType = KeyboardType.Text,
                                onUpdate = { newFirstName ->
                                    viewModel.validateAndUpdateFirstName(context, newFirstName)
                                }
                            )

                            GeneralNormalText(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                                value = stringResource(id = R.string.last_name_with_dots),
                                color = Color.Black
                            )
                            EditableField(
                                value = accountDetails?.lastName ?: "",
                                error = lastNameError,
                                keyboardType = KeyboardType.Text,
                                onUpdate = { newLastName ->
                                    viewModel.validateAndUpdateLastName(context, newLastName)
                                }
                            )

                            GeneralNormalText(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                                value = stringResource(id = R.string.email_with_dots),
                                color = Color.Black
                            )
                            EditableField(
                                value = accountDetails?.email ?: "",
                                error = emailError,
                                keyboardType = KeyboardType.Email,
                                onUpdate = { newEmail ->
                                    viewModel.validateAndUpdateEmail(newEmail)
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}