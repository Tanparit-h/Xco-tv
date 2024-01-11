package com.zicure.xcotv.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun MultiColorText(modifier: Modifier = Modifier, vararg textWithColors: Triple<String, Color, FontWeight>) {
    Text(modifier = modifier, text = buildAnnotatedString {
        textWithColors.forEach { (text, color, fontWeight) ->
            withStyle(
                style = SpanStyle(
                    color = color,
                    fontFamily = fontFCIconic,
                    fontWeight = fontWeight,
                    fontSize = 24.sp
                )
            ) {
                append(text)
            }
        }
    })
}