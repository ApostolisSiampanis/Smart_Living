package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.theme.componentShapes

@Composable
fun FormTextFieldComponent(
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
    val passwordVisible = remember { mutableStateOf(false) }

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

@Composable
fun EditableField(
    value: String,
    error: String?,
    keyboardType: KeyboardType,
    onUpdate: (String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(value) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),
                enabled = isEditing,
                isError = error != null,
                supportingText = {
                    if (error != null) {
                        ErrorSupportingTextComponent(
                            value = error
                        )
                    } else {
                        Text(
                            text = ""
                        )
                    }
                }
            )
        }
        if (isEditing) {
            Row(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                IconButton(
                    onClick = {
                        isEditing = false
                        text = value
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = stringResource(id = R.string.close_edit)
                    )
                }
                IconButton(
                    onClick = {
                        isEditing = false
                        onUpdate(text)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = stringResource(id = R.string.complete_edit)
                    )
                }
            }
        } else {
            IconButton(
                onClick = {
                    isEditing = true
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = stringResource(id = R.string.edit)
                )
            }
        }
    }
}

@Composable
fun EditablePasswordField(
    value: String,
    error: String?,
    labelValue: String,
    onUpdate: (String) -> Unit,
    onClose: () -> Unit
) {
    val passwordVisible = remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(value) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                label = { TextFieldComponentText(value = labelValue) },
                isError = error != null,
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
                    if (error != null) {
                        ErrorSupportingTextComponent(
                            value = error
                        )
                    } else {
                        Text(
                            text = ""
                        )
                    }
                }
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.End)
        ) {
            IconButton(
                onClick = {
                    password = value
                    onClose()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = stringResource(id = R.string.close_edit)
                )
            }
            IconButton(
                onClick = {
                    onUpdate(password)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = stringResource(id = R.string.complete_edit)
                )
            }
        }
    }
}