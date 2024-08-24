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
fun TermsAndConditionsScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = stringResource(id = R.string.terms_and_conditions_header),
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
                        GeneralNormalText(value = stringResource(id = R.string.terms_of_use_intro))
                        GeneralBoldText(value = stringResource(id = R.string.terms_of_use_license))
                        GeneralNormalText(value = stringResource(id = R.string.terms_of_use_license_text))
                        GeneralBoldText(value = stringResource(id = R.string.terms_of_use_conduct))
                        GeneralNormalText(value = stringResource(id = R.string.terms_of_use_conduct_text))
                        GeneralBoldText(value = stringResource(id = R.string.terms_of_use_ip))
                        GeneralNormalText(value = stringResource(id = R.string.terms_of_use_ip_text))
                        GeneralBoldText(value = stringResource(id = R.string.terms_of_use_liability))
                        GeneralNormalText(value = stringResource(id = R.string.terms_of_use_liability_text))
                        GeneralBoldText(value = stringResource(id = R.string.terms_of_use_modifications))
                        GeneralNormalText(value = stringResource(id = R.string.terms_of_use_modifications_text))
                        GeneralBoldText(value = stringResource(id = R.string.terms_of_use_governing_law))
                        GeneralNormalText(value = stringResource(id = R.string.terms_of_use_governing_law_text))
                        GeneralBoldText(value = stringResource(id = R.string.terms_of_use_contact_us))
                        GeneralNormalText(value = stringResource(id = R.string.terms_of_use_contact_us_text))
                        GeneralNormalText(value = stringResource(id = R.string.terms_of_use_acknowledgement))
                    }
                }
            }
        }
    )
}