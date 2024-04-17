package com.aposiamp.smartliving.ui.component

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.ui.theme.SkyBlue

@Composable
fun SignUpClickableTextComponent(

) {
    val acceptTermsPrefixText = stringResource(id = R.string.terms_and_conditions)
    val privacyPolicyText = stringResource(id = R.string.privacy_policy)
    val andText = stringResource(id = R.string.and)
    val termsOfUseText = stringResource(id = R.string.terms_of_use)

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.carlito_regular))
            )
        ) {
            append(acceptTermsPrefixText)
        }
        pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
        withStyle(
            style = SpanStyle(
                color = SkyBlue,
                fontFamily = FontFamily(Font(R.font.carlito_regular))
            )
        ) {
            append(privacyPolicyText)
        }
        pop()
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.carlito_regular))
            )
        ) {
            append(andText)
        }
        pushStringAnnotation(tag = termsOfUseText, annotation = termsOfUseText)
        withStyle(
            style = SpanStyle(
                color = SkyBlue,
                fontFamily = FontFamily(Font(R.font.carlito_regular))
            )
        ) {
            append(termsOfUseText)
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = {offset ->
            annotatedString.getStringAnnotations(
                start = offset,
                end = offset
            ).firstOrNull()?.also {span ->
                Log.d("SignUpClickableTextComponent", "{${span}}")
            }
        }
    )
}