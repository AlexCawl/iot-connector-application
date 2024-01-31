package org.alexcawl.iot_connector.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import org.alexcawl.iot_connector.ui.theme.extended.ExtendedPalettes
import org.alexcawl.iot_connector.ui.theme.extended.sizes.CommonSizes
import org.alexcawl.iot_connector.ui.theme.extended.sizes.IconSizes
import org.alexcawl.iot_connector.ui.theme.extended.sizes.PaddingSizes

internal val LocalExtendedSizes = staticCompositionLocalOf { CommonSizes() }
internal val LocalExtendedIconSizes = staticCompositionLocalOf { IconSizes() }
internal val LocalExtendedPaddings = staticCompositionLocalOf { PaddingSizes() }
internal val LocalExtendedPalettes = staticCompositionLocalOf { ExtendedPalettes() }

@Composable
fun IoTConnectorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val extendedSizes = CommonSizes()
    val extendedIconSizes = IconSizes()
    val extendedPaddings = PaddingSizes()
    val extendedPalettes = ExtendedPalettes()
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColors
        else -> lightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightNavigationBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalExtendedSizes provides extendedSizes,
        LocalExtendedIconSizes provides extendedIconSizes,
        LocalExtendedPaddings provides extendedPaddings,
        LocalExtendedPalettes provides extendedPalettes
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

object ExtendedTheme {
    val palettes: ExtendedPalettes
        @Composable get() = LocalExtendedPalettes.current

    val padding: PaddingSizes
        @Composable get() = LocalExtendedPaddings.current

    val iconSize: IconSizes
        @Composable get() = LocalExtendedIconSizes.current

    val size: CommonSizes
        @Composable get() = LocalExtendedSizes.current
}