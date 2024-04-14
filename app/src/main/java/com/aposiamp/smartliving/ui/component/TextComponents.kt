package com.aposiamp.smartliving.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aposiamp.smartliving.R

@Composable
fun AuthHeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
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
            fontFamily = FontFamily(Font(R.font.poppins_light)),
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText)
    )
}