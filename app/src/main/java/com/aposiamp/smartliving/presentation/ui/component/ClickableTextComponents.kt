package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aposiamp.smartliving.R
import com.aposiamp.smartliving.presentation.ui.theme.SkyBlue

@Composable
fun PolicyAndTermsClickableTextComponent(
    onTextSelected: (String) -> Unit
) {
    val acceptTermsPrefixText = stringResource(id = R.string.terms_and_conditions)
    val privacyPolicyText = stringResource(id = R.string.privacy_policy)
    val andText = stringResource(id = R.string.and)
    val termsOfUseText = stringResource(id = R.string.terms_of_use)

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black
            )
        ) {
            append(acceptTermsPrefixText)
        }
        pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
        withStyle(
            style = SpanStyle(
                color = SkyBlue
            )
        ) {
            append(privacyPolicyText)
        }
        pop()
        withStyle(
            style = SpanStyle(
                color = Color.Black
            )
        ) {
            append(andText)
        }
        pushStringAnnotation(tag = termsOfUseText, annotation = termsOfUseText)
        withStyle(
            style = SpanStyle(
                color = SkyBlue
            )
        ) {
            append(termsOfUseText)
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.carlito_regular))
        ),
        onClick = {offset ->
            annotatedString.getStringAnnotations(
                start = offset,
                end = offset
            ).firstOrNull()?.also {span ->
                if (span.item == privacyPolicyText || span.item == termsOfUseText) {
                    onTextSelected(span.item)
                }
            }
        }
    )
}

@Composable
fun HaveAnAccountOrNotClickableTextComponent(
    alreadyHaveAnAccount: Boolean = true,
    onTextSelected: (String) -> Unit
) {
    val nonClickableText =
        if (alreadyHaveAnAccount) stringResource(id = R.string.already_have_an_account)
        else stringResource(id = R.string.dont_have_an_account_yet)
    val clickableText =
        if (alreadyHaveAnAccount) stringResource(id = R.string.login)
        else stringResource(id = R.string.register)

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.carlito_regular))
            )
        ) {
            append(nonClickableText)
        }
        pushStringAnnotation(tag = clickableText, annotation = clickableText)
        withStyle(
            style = SpanStyle(
                color = SkyBlue,
                fontFamily = FontFamily(Font(R.font.carlito_regular))
            )
        ) {
            append(clickableText)
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.carlito_regular))
        ),
        onClick = {offset ->
            annotatedString.getStringAnnotations(
                start = offset,
                end = offset
            ).firstOrNull()?.also {span ->
                if (span.item == clickableText) {
                    onTextSelected(span.item)
                }
            }
        }
    )
}

@Composable
fun UseDifferentAccountLogoutAndLoginAgain(
    onTextSelected: (String) -> Unit
) {
    val startingText = stringResource(id = R.string.if_you_want_to_login_with_another_account)
    val logoutText = stringResource(id = R.string.logout_lower_case)
    val closingText = stringResource(id = R.string.first_and_then_login_again)

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black
            )
        ) {
            append(startingText)
        }
        pushStringAnnotation(tag = logoutText, annotation = logoutText)
        withStyle(
            style = SpanStyle(
                color = SkyBlue
            )
        ) {
            append(logoutText)
        }
        pop()
        withStyle(
            style = SpanStyle(
                color = Color.Black
            )
        ) {
            append(closingText)
        }
    }

    ClickableText(
        text = annotatedString,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.carlito_regular))
        ),
        onClick = {offset ->
            annotatedString.getStringAnnotations(
                start = offset,
                end = offset
            ).firstOrNull()?.also {span ->
                if (span.item == logoutText) {
                    onTextSelected(span.item)
                }
            }
        }
    )
}

@Composable
fun UnderlinedClickableTextComponent(
    value: String,
    onTextSelected: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append(value)
    }

    ClickableText(
        text = annotatedString,
        style = TextStyle(
            fontSize = 18.sp,
            color = SkyBlue,
            fontFamily = FontFamily(Font(R.font.carlito_regular)),
            textDecoration = TextDecoration.Underline
        ),
        onClick = { onTextSelected() }
    )
}