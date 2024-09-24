package com.aposiamp.smartliving.presentation.ui.activity.main.screens.settings

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.GeneralBoldText
import com.aposiamp.smartliving.presentation.ui.component.GeneralNormalText
import com.aposiamp.smartliving.presentation.ui.component.NameFieldComponent
import com.aposiamp.smartliving.presentation.ui.component.ProgressIndicatorComponent
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.AccountProfileViewModel
import com.aposiamp.smartliving.presentation.viewmodel.main.settings.AccountViewModel

@Composable
fun AccountScreen(
    navController: NavController,
    viewModel: AccountViewModel,
    accountProfileViewModel: AccountProfileViewModel
) {
    val accountDetails by accountProfileViewModel.detailsState.collectAsState()
    val isLoading by accountProfileViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        accountProfileViewModel.fetchAccountDetails()
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
                            NameFieldComponent(
                                firstName = accountDetails?.firstName,
                                lastName = accountDetails?.lastName
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            GeneralNormalText(
                                value = stringResource(id = R.string.full_name_with_dots),
                                color = Color.Black
                            )
                            GeneralBoldText(
                                value = accountDetails?.email ?: "",
                                fontSize = 22,
                                color = Color.Black
                            )

                            // TODO: Password
                            // TODO: Delete account
                        }
                    }
                }
            }
        }
    )
}