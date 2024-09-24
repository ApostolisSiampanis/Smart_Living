package com.aposiamp.smartliving.presentation.ui.activity.main.screens.settings

import android.content.Intent
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.activity.welcome.WelcomeActivity
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.GeneralClickableCard
import com.aposiamp.smartliving.presentation.ui.component.EditablePasswordField
import com.aposiamp.smartliving.presentation.ui.component.GeneralBoldText
import com.aposiamp.smartliving.presentation.ui.component.GeneralNormalText
import com.aposiamp.smartliving.presentation.ui.component.NameFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.ProgressIndicatorComponent
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.AccountProfileViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.AccountViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AccountScreen(
    navController: NavController,
    viewModel: AccountViewModel,
    accountProfileViewModel: AccountProfileViewModel
) {
    val context = LocalContext.current
    val accountDetails by accountProfileViewModel.detailsState.collectAsState()
    val isLoading by accountProfileViewModel.isLoading.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    var showPasswordField by remember { mutableStateOf(false) }
    val newPassword by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        accountProfileViewModel.fetchAccountDetails()
    }

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = stringResource(id = R.string.account),
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
                                value = stringResource(id = R.string.email_with_dots),
                                color = Color.Black
                            )
                            GeneralBoldText(
                                value = accountDetails?.email ?: "",
                                fontSize = 22,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            GeneralNormalText(
                                value = stringResource(id = R.string.full_name_with_dots),
                                color = Color.Black
                            )
                            NameFieldComponent(
                                firstName = accountDetails?.firstName,
                                lastName = accountDetails?.lastName
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            GeneralClickableCard(
                                value = stringResource(id = R.string.change_password),
                                onClick = { showPasswordField = !showPasswordField }
                            )

                            if (showPasswordField) {
                                EditablePasswordField(
                                    value = newPassword,
                                    error = passwordError,
                                    labelValue = stringResource(id = R.string.new_password),
                                    onUpdate = { newPassword ->
                                        viewModel.updatePassword(context, newPassword) {
                                            showPasswordField = false
                                        }
                                    },
                                    onClose = { showPasswordField = false }
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 28.dp, start = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        GeneralClickableCard(
                            value = stringResource(id = R.string.delete_account),
                            textColor = Color.Red,
                            onClick = {
                                viewModel.deleteUserAndLogout(context) {
                                    val intent = Intent(context, WelcomeActivity::class.java).apply {
                                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    }
                                    context.startActivity(intent)
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}