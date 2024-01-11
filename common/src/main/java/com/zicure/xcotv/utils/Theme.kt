package com.zicure.xcotv.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200,
    background = Color.White,
)

private val LightColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
@Composable
fun BaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    loadLocale(LocalContext.current)
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.surface.copy(.5f),
        )
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content,
    )
}

@Composable
fun ProgressIndicatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.surface.copy(.5f),
        )
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content,
    )
}
