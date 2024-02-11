package com.zicure.xcotv.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun XcotvTagButton(
    modifier: Modifier = Modifier,
    text: String,
    colorButton: Color = Color.White,
    colorText: Color = grayFF1B1B1B,
    fontWeight: FontWeight = FontWeight.Normal,
    size: Int = 24,
    action: () -> Unit = {}
) {
    Button(
        modifier = Modifier
            .wrapContentSize(unbounded = true)
            .then(modifier),
        shape = RoundedCornerShape(16),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorButton,
            contentColor = colorButton
        ),
        onClick = {
            action()
        }
    ) {

        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            modifier = Modifier,
            text = text,
            color = colorText,
            fontFamily = fontFCIconic,
            fontWeight = fontWeight,
            fontSize = size.sp
        )
        Spacer(modifier = Modifier.weight(0.5f))
    }
}