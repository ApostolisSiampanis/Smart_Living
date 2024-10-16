package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.aposiamp.smartliving.presentation.ui.theme.BlueGreen
import com.aposiamp.smartliving.presentation.ui.theme.GrayColor
import com.aposiamp.smartliving.presentation.ui.theme.PrussianBlue

@Composable
fun HeadingTextComponent(value: String) {
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
fun ErrorTextComponent(errorMessage: String?) {
    Text(
        text = errorMessage ?: "",
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

@Composable
fun GeneralNormalText(
    modifier: Modifier = Modifier,
    value: String,
    color: Color = Color.Unspecified
) {
    Text(
        modifier = modifier,
        text = value,
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.carlito_regular)),
            color = color
        )
    )
}

@Composable
fun GeneralBoldText(
    modifier: Modifier = Modifier,
    value: String,
    fontSize: Int = 18,
    color: Color = Color.Unspecified
) {
    Text(
        modifier = modifier,
        text = value,
        style = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = FontFamily(Font(R.font.carlito_bold)),
            color = color
        )
    )
}

@Composable
fun GeneralNormalBlackText(value: String) {
    Text(
        text = value,
        color = Color.Black,
        fontFamily = FontFamily(Font(R.font.carlito_regular))
    )
}

@Composable
fun GeneralBoldBlackText(value: String) {
    Text(
        text = value,
        color = Color.Black,
        fontFamily = FontFamily(Font(R.font.carlito_bold))
    )
}

@Composable
fun HeaderText(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.carlito_regular))
        ),
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

@Composable
fun ItemTextComponent(
    text: String,
    color: Color
) {
    Text(
        text = text,
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.carlito_regular)),
        color = color
    )
}

@Composable
fun NormalNavigationTextComponent(
    text: String
) {
    Text(
        text = text,
        fontFamily = FontFamily(Font(R.font.carlito_regular))
    )
}

@Composable
fun IndicatorRegularTextComponent(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 22.sp,
        color = color,
        fontFamily = FontFamily(Font(R.font.carlito_regular)),
        modifier = modifier
    )
}

@Composable
fun IndicatorBoldTextComponent(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 38.sp,
        color = color,
        fontFamily = FontFamily(Font(R.font.carlito_bold)),
        modifier = modifier
    )
}

@Composable
fun NameFieldComponent(
    firstName: String?,
    lastName: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        GeneralBoldText(
            value = firstName ?: "",
            fontSize = 22,
            color = PrussianBlue
        )
        Spacer(modifier = Modifier.width(8.dp))
        GeneralBoldText(
            value = lastName ?: "",
            fontSize = 22,
            color = BlueGreen
        )
    }
}