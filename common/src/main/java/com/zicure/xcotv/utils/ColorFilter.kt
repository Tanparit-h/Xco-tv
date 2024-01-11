package com.zicure.xcotv.utils

import androidx.compose.ui.graphics.ColorMatrix

/***
 *  @param contrast     0f..10f (1 should be default)
 *  @param brightness  -255f..255f (0 should be default)
 *
 *  @return [ColorMatrix] that contain float array
 */
fun CreateColorFilterArray(contrast: Float = 1f,brightness: Float = -30f): ColorMatrix {
    return ColorMatrix(
        floatArrayOf(
            contrast, 0f, 0f, 0f, brightness,
            0f, contrast, 0f, 0f, brightness,
            0f, 0f, contrast, 0f, brightness,
            0f, 0f, 0f, 1f, 0f
        )
    )
}