package com.aposiamp.smartliving.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.component.BackAppTopBar
import com.aposiamp.smartliving.presentation.ui.component.GeneralBoldText
import com.aposiamp.smartliving.presentation.ui.component.GeneralNormalText

@Composable
fun PrivacyPolicyScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = stringResource(id = R.string.privacy_policy_header),
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
                        GeneralNormalText(value = stringResource(id = R.string.privacy_policy_intro))
                        GeneralBoldText(value = stringResource(id = R.string.privacy_policy_info_collect))
                        GeneralNormalText(value = stringResource(id = R.string.privacy_policy_info_collect_text))
                        GeneralBoldText(value = stringResource(id = R.string.privacy_policy_use_info))
                        GeneralNormalText(value = stringResource(id = R.string.privacy_policy_use_info_text))
                        GeneralBoldText(value = stringResource(id = R.string.privacy_policy_share_info))
                        GeneralNormalText(value = stringResource(id = R.string.privacy_policy_share_info_text))
                        GeneralBoldText(value = stringResource(id = R.string.privacy_policy_data_security))
                        GeneralNormalText(value = stringResource(id = R.string.privacy_policy_data_security_text))
                        GeneralBoldText(value = stringResource(id = R.string.privacy_policy_children))
                        GeneralNormalText(value = stringResource(id = R.string.privacy_policy_children_text))
                        GeneralBoldText(value = stringResource(id = R.string.privacy_policy_changes))
                        GeneralNormalText(value = stringResource(id = R.string.privacy_policy_changes_text))
                        GeneralBoldText(value = stringResource(id = R.string.privacy_policy_contact_us))
                        GeneralNormalText(value = stringResource(id = R.string.privacy_policy_contact_us_text))
                        GeneralNormalText(value = stringResource(id = R.string.privacy_policy_acknowledgement))
                    }
                }
            }
        }
    )
}
