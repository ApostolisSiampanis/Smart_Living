package com.aposiamp.smartliving.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.aposiamp.smartliving.ui.theme.BgColor
import com.aposiamp.smartliving.ui.theme.PrussianBlue
import com.aposiamp.smartliving.ui.theme.componentShapes

@Composable
fun TextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    contentDescription: String,
    keyboardType: KeyboardType
) {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { TextFieldComponentText(value = labelValue) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BgColor,
            focusedContainerColor = PrussianBlue,
            focusedLabelColor = PrussianBlue,
            cursorColor = PrussianBlue
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = keyboardType),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        leadingIcon = {
            Icon(
                painter = painterResource,
                contentDescription = contentDescription
            )
        }
    )
}