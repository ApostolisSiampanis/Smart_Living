package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.theme.componentShapes

@Composable
fun AuthTextFieldComponent(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    painterResource: Painter,
    contentDescription: String,
    keyboardType: KeyboardType,
    supportedTextValue: String,
    errorStatus: Boolean = false
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { TextFieldComponentText(value = labelValue) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = keyboardType
        ),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                painter = painterResource,
                contentDescription = contentDescription
            )
        },
        supportingText = {
            if (errorStatus) {
                ErrorSupportingTextComponent(
                    value = supportedTextValue
                )
            } else {
                Text(
                    text = ""
                )
            }
        },
        isError = errorStatus
    )
}

@Composable
fun PasswordTextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    supportedTextValue: String,
    errorStatus: Boolean = false
) {
    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { TextFieldComponentText(value = labelValue) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.password),
                contentDescription = stringResource(id = R.string.password_hint)
            )
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                painterResource(id = R.drawable.visibility)
            } else {
                painterResource(id = R.drawable.visibility_off)
            }

            val contentDescription = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(
                    painter = iconImage,
                    contentDescription = contentDescription
                )
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        supportingText = {
            if (errorStatus) {
                ErrorSupportingTextComponent(
                    value = supportedTextValue
                )
            } else {
                Text(
                    text = ""
                )
            }
        },
        isError = errorStatus
    )
}