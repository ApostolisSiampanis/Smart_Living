package com.aposiamp.smartliving.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aposiamp.smartliving.presentation.ui.theme.PrussianBlue

@Composable
fun AuthButtonComponent(
    value: String,
    onButtonClicked: () -> Unit
) {
    Button(
        onClick = onButtonClicked,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = PrussianBlue,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            AuthButtonTextComponent(value = value)
        }
    }
}