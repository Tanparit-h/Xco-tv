package com.zicure.xcotv.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import timber.log.Timber


@Composable
fun CustomProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color = Color.White,
    backgroundColor: Color = Color.White,
    clipShape: Shape = RoundedCornerShape(16.dp),
    block: () -> Unit = { }
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor.copy(alpha = 0.6f))
            .height(10.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(clipShape)
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
        Timber.d("Progress: $progress")
    }
    if (progress >= 1f) { block() }
}