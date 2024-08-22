package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.theme.GrayColor

@Composable
fun AuthHeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.carlito_bold)),
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextFieldComponentText(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.carlito_regular)),
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText)
    )
}

@Composable
fun ErrorSupportingTextComponent(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.carlito_regular))
        ),
        color = MaterialTheme.colorScheme.error
    )
}

@Composable
fun ErrorTextComponent(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.carlito_regular)),
            fontStyle = FontStyle.Normal
        ),
        color = MaterialTheme.colorScheme.error
    )
}

@Composable
fun GeneralTextComponent(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.carlito_regular)),
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun GeneralButtonTextComponent(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.carlito_bold))
        ),
        color = Color.White
    )
}

@Composable
fun AuthDividerTextComponent(value: String) {
    Text(
        modifier = Modifier
            .padding(8.dp),
        text = value,
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.carlito_regular))
        ),
        color = GrayColor
    )
}