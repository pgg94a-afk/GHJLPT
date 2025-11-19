package com.gg.ghjnpt.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = YongdalBlue,                    // 어두운 블루
    onPrimary = Color.White,
    primaryContainer = YongdalBlueLight,
    onPrimaryContainer = YongdalBlueDark,

    secondary = YongdalBlueAccent,
    onSecondary = Color.White,
    secondaryContainer = YongdalBlueBackground,
    onSecondaryContainer = YongdalBlueDark,

    background = YongdalBlueSurface,
    onBackground = Color(0xFF1A1C1E),

    surface = Color.White,
    onSurface = Color(0xFF1A1C1E),
    surfaceVariant = YongdalBlueBackground,
    onSurfaceVariant = Color(0xFF43474E),
)

@Composable
fun GHJNPTTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}