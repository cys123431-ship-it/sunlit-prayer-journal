package com.sunlit.prayerjournal.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = OrangeSun,
    secondary = WarmPeach,
    tertiary = BerryPink,
    background = YellowPeach,
    surface = SoftBlush,
    onPrimary = NightText,
    onSecondary = NightText,
    onTertiary = NightText,
    onBackground = NightText,
    onSurface = NightText
)

private val DarkColors = darkColorScheme(
    primary = WarmPeach,
    secondary = OrangeSun,
    tertiary = BerryPink,
    background = NightText,
    surface = Color(0xFF2D2735),
    onPrimary = Color(0xFF2D2735),
    onSecondary = Color(0xFF2D2735),
    onTertiary = Color(0xFF2D2735),
    onBackground = SoftBlush,
    onSurface = SoftBlush
)

@Composable
fun PrayerJournalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}

