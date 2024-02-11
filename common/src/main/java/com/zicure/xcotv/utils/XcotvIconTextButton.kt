package com.zicure.xcotv.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun XcotvIconTextButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    colorIcon: Color = Color.White,
    colorButton: Color = Color.White,
    colorText: Color = grayFF1B1B1B,
    fontWeight: FontWeight = FontWeight.Bold,
    sizeIcon: Int = 56,
    sizeText: Int = 24,
    action: () -> Unit = {}
) {
    var borderColor by remember { mutableStateOf(Color.White) }
    fun applyColors(focusState: FocusState) {
        borderColor = if (focusState.isFocused) {
            grayFF616161
        } else {
            Color.Transparent
        }
    }
    Button(
        modifier = modifier
            .wrapContentSize(unbounded = true)
            .onFocusEvent(::applyColors)
            .focusable(),
        shape = RoundedCornerShape(16),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorButton,
            contentColor = colorButton
        ),
        border = BorderStroke(1.dp, borderColor),
        enabled = true,
        onClick = {
            action()
        }
    ) {
        Icon(
            modifier = Modifier
                .size(sizeIcon.dp),
            imageVector = icon,
            contentDescription = null,
            tint = colorIcon
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = text,
            color = colorText,
            fontFamily = fontFCIconic,
            fontWeight = fontWeight,
            fontSize = sizeText.sp
        )
    }
}