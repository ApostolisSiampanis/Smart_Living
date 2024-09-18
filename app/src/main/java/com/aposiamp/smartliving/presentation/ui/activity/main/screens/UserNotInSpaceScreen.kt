package com.aposiamp.smartliving.presentation.ui.activity.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.component.AuthHeadingTextComponent
import com.aposiamp.smartliving.presentation.ui.component.TextFieldComponentText
import com.aposiamp.smartliving.presentation.ui.component.UseDifferentAccountLogoutAndLoginAgain
import com.aposiamp.smartliving.presentation.viewmodel.main.UserNotInSpaceViewModel

@Composable
fun UserNotInSpaceScreen(
    userNotInSpaceViewModel: UserNotInSpaceViewModel
) {
    val context = LocalContext.current

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 60.dp, bottom = 28.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AuthHeadingTextComponent(value = stringResource(id = R.string.you_are_not_in_your_space))
                Image(
                    painter = painterResource(id = R.drawable.user_not_in_space),
                    contentDescription = stringResource(id = R.string.user_not_in_space)
                )
                Spacer(modifier = Modifier.height(32.dp))
                TextFieldComponentText(value = stringResource(id = R.string.you_need_to_be_in_your_space_to_use_the_app))
                Spacer(modifier = Modifier.height(32.dp))
                UseDifferentAccountLogoutAndLoginAgain(
                    onTextSelected = {
                        userNotInSpaceViewModel.logout(context)
                    }
                )
            }
        }
    }
}